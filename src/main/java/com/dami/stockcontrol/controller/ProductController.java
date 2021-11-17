package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.service.PersonService;
import com.dami.stockcontrol.service.ProductService;
import com.dami.stockcontrol.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.security.Principal;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    TransactionsService transactionsService;

    @Autowired
    ProductService productService;

    @GetMapping("/product")
    public String home(Model model){

        model.addAttribute("products", getAllProducts());
        model.addAttribute("productAdd", new Product());

        return "product";
    }

    private List<Product> getAllProducts() {
        Principal principal = SecurityContextHolder.getContext().getAuthentication();
        return productService.getAllProductsBy(principal.getName());
    }

    private List<Double> getProfitInSales() {
        return transactionsService.getProfitInSales();
    }

    private List<Transactions> getRecentTransactions() {
        return transactionsService.getRecentTransactions();
    }



}