package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.ManagerGroup;
import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.model.SignupInfo;
import com.dami.stockcontrol.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Controller
public class UsersController {


    @Autowired
    private PersonService personService;


    @GetMapping("/users")
    public String home(Model model){

        model.addAttribute("signupInfo",new SignupInfo());
        model.addAttribute("users",getUsers());
        model.addAttribute("roles",getRoles());
        model.addAttribute("person",new Person());
        model.addAttribute("newRole",new ManagerGroup());
        return "users";
    }

    @RequestMapping(value = "/users/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute SignupInfo signupInfo){

        saveNewUser(signupInfo);
        return "redirect:/users";
    }
    @RequestMapping(value = "/users/delete/{id}", method = RequestMethod.GET)
    public String deleteUser(@PathVariable String id){

        System.out.println(id);
        personService.deletePerson(Integer.parseInt(id));
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/role/change", method = RequestMethod.POST)
    private String changeRole(@ModelAttribute Person person){

        personService.updateCompanyPersonRole(person.getRole() , person.getUsername());
        return "redirect:/users";
    }

    @RequestMapping(value = "/users/role/create", method = RequestMethod.POST)
    private String createRole(@ModelAttribute ManagerGroup role,  Model model){

        personService.createCompanyRole(role.getGroupName(), getPrincipalName());
        return "redirect:/users";
    }

    private List<ManagerGroup> getRoles() {

        List<ManagerGroup> managerGroups = new ArrayList<>();

        managerGroups.add(new ManagerGroup(Role.Group.COMPANYMANAGER));
        managerGroups.add(new ManagerGroup(Role.Group.SALESPERSON));

        managerGroups.addAll(personService.getManagerGroups(getPrincipalName()));

        return managerGroups;
    }

    private List<Person> getUsers() {
        return personService.getUsersUnderAdmin(getPrincipalName());
    }

    private void saveNewUser(SignupInfo signupInfo) {
        Person person = new Person(signupInfo.getUsername(),signupInfo.getPassword(),
                signupInfo.getLname(),signupInfo.getFname(),true,
                Role.Group.SALESPERSON,personService.getPersonByUsername(getPrincipalName()).getId(),
                String.valueOf(LocalDate.now()));

        personService.saveNewPerson(person);

        //personService.saveInGroup(person, Role.Group.SALESPERSON);
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}