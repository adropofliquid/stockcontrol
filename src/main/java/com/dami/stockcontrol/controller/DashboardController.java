package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.AdminRequests;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.List;


@Controller
public class DashboardController {

    @Autowired
    TransactionsService transactionsService;

    @Autowired
    PersonService personService;

    @Autowired
    CompanyService companyService;

    @Autowired
    ProductService productService;

    @Autowired
    AdminRequestsService adminRequestsService;

    @GetMapping("/dashboard")
    public String home(Model model){

        model.addAttribute("adminRequests", getAdminRequests());
        model.addAttribute("role", getRole());
        model.addAttribute("roles", new Role());
        model.addAttribute("productsCount", getProductCount());
        model.addAttribute("companyCount", getCompanyCount());
        model.addAttribute("usersCount", getUsersCount());
        model.addAttribute("transactions", getRecentTransactions());
        model.addAttribute("profitInSales", getProfitInSales().get(0));
        model.addAttribute("profitInSalesPercent", getProfitInSales().get(1));

        return "dashboard";
    }

    private List<AdminRequests> getAdminRequests() {
        return adminRequestsService.getRequests();
    }

    private List<Double> getProfitInSales() {
        return transactionsService.getProfitInSales();
    }

    private List<Transactions> getRecentTransactions() {
        return transactionsService.getRecentTransactions();
    }

    private int getUsersCount() {
        //FIXME should be users under dis manager
        return personService.getTotalUsers();
    }

    private int getCompanyCount() {
        return companyService.getCompaniesByPersonName(getPrincipalName()).size();
    }

    private int getProductCount() {
        return productService.getAllProductsBy(getPrincipalName()).size();
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

    private String getRole() {
        return personService.getPersonByUsername(getPrincipalName()).getRole();
    }
}