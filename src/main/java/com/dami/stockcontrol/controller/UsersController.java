package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.model.SignupInfo;
import com.dami.stockcontrol.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.time.LocalDate;
import java.util.List;


@Controller
public class UsersController {


    @Autowired
    private PersonService personService;


    @GetMapping("/users")
    public String home(Model model){

        model.addAttribute("signupInfo",new SignupInfo());
        model.addAttribute("users",getUsers());
        return "users";
    }

    private List<Person> getUsers() {
        return null;
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute SignupInfo signupInfo, Model model){

        saveNewUser(signupInfo);
        return "redirect:/users";
    }

    private void saveNewUser(SignupInfo signupInfo) {
        Person person = new Person(signupInfo.getUsername(),signupInfo.getPassword(),
                signupInfo.getLname(),signupInfo.getFname(),true,
                Role.COMPANYUSER,0, String.valueOf(LocalDate.now()));

        personService.saveNewPerson(person);
    }

}