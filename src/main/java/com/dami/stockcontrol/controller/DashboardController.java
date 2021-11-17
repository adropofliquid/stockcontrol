package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Transactions;
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
public class DashboardController {

    @Autowired
    TransactionsService transactionsService;

    @Autowired
    PersonService personService;

    @GetMapping("/dashboard")
    public String home(Model model){

        model.addAttribute("productsCount", getProductCount());
        model.addAttribute("companyCount", getCompanyCount());
        model.addAttribute("usersCount", getUsersCount());
        model.addAttribute("transactions", getRecentTransactions());
        model.addAttribute("profitInSales", getProfitInSales().get(0));
        model.addAttribute("profitInSalesPercent", getProfitInSales().get(1));

        return "dashboard";
    }

    private List<Double> getProfitInSales() {
        return transactionsService.getProfitInSales();
    }

    private List<Transactions> getRecentTransactions() {
        return transactionsService.getRecentTransactions();
    }

    private int getUsersCount() {
//        Principal principal = SecurityContextHolder.getContext().getAuthentication();
//        return personService.getUsersUnderAdmin(principal.getName());
        return personService.getTotalUsers();
    }

    private int getCompanyCount() {
        return 0;
    }

    private int getProductCount() {
        return 0;
    }


}