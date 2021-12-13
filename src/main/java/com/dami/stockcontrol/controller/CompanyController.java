package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.*;
import com.dami.stockcontrol.service.CompanyService;
import com.dami.stockcontrol.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class CompanyController {

    @Autowired
    CompanyService companyService;

    @Autowired
    PersonService personService;

    @GetMapping("/company")
    public String home(Model model){

        model.addAttribute("personId", personService.getPersonByUsername(getPrincipalName()).getId());
        model.addAttribute("companyList", getCompanies());
        model.addAttribute("companyObject", new Company());
        model.addAttribute("role", getRole());
        model.addAttribute("roles", new Role());
        return "company";
    }

    @RequestMapping(value = "/company", method = RequestMethod.POST)
    public String addCompany(@ModelAttribute Company company, Model model){

        addNewCompany(company);

        model.addAttribute("companyList", getCompanies());
        model.addAttribute("companyObject", new Company());
        model.addAttribute("role", getRole());
        model.addAttribute("roles", new Role());

        return "company";
    }

    private void addNewCompany(Company company) {

        Person owner = personService.getPersonByUsername(getPrincipalName());
        company.setOwnerUserId(owner.getId());
        companyService.addNew(company);
    }

    private String getRole() {
        return personService.getPersonByUsername(getPrincipalName()).getRole();
    }

    private List<Company> getCompanies() {
        return companyService.getCompaniesByPersonName(getPrincipalName());
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}