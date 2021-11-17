package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.model.SignupInfo;
import com.dami.stockcontrol.service.PersonService;
import com.dami.stockcontrol.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Controller
public class HomeController {

    @Autowired
    private ProductService productService;

    @Autowired
    private PersonService personService;

    @GetMapping("/")
    public String home(Model model){
        model.addAttribute("signupInfo",new SignupInfo());
        return "index";
    }

    @RequestMapping(value = "/", method = RequestMethod.POST)
    public String signUp(@ModelAttribute SignupInfo signupInfo,Model model){

        model.addAttribute("username", signupInfo.getUsername());

        saveNewUser(signupInfo);
        return "index";
    }

    private void saveNewUser(SignupInfo signupInfo) {
        Person person = new Person(signupInfo.getUsername(),signupInfo.getPassword(),
                signupInfo.getLname(),signupInfo.getFname(),true,
                Role.USER,0, String.valueOf(LocalDate.now()));

        personService.saveNewPerson(person);
    }

    private List<Product> latestProducts(){

        return productService.getLatestProducts();
    }


}