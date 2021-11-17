package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.service.CompanyService;
import com.dami.stockcontrol.service.PersonService;
import com.dami.stockcontrol.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    PersonService personService;

    @GetMapping("/company")
    public String home(Model model){
        model.addAttribute("companyList", getCompanies());
        model.addAttribute("role", getRole());
        model.addAttribute("roles", new Role());
        return "company";
    }


    private String getRole() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        return personService.getPersonByUsername(principal.getName()).getRole();
    }

    private List<Company> getCompanies() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();

        return companyService.getCompaniesBy(principal.getName());
    }

}