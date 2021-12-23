package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Search;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.service.PersonService;
import com.dami.stockcontrol.service.ProductService;
import com.dami.stockcontrol.service.TransactionsService;
import org.apache.catalina.LifecycleState;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;


@Controller
public class SearchController {


    @Autowired
    ProductService productService;

    @Autowired
    PersonService personService;

    @Autowired
    TransactionsService transactionsService;

    @GetMapping("/search")
    public String search(Model model){

        model.addAttribute("search", new Search());
        model.addAttribute("products", getProductsResult());
        model.addAttribute("users", getUsersResult());
        model.addAttribute("transactions", getTransactionsResult());
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getSearch(@ModelAttribute Search search){

        

        return "redirect:/search";
    }

    private List<Product> getProductsResult() {
        productService.getProductsLikeName()
    }

    private List<Person> getUsersResult() {
    }

    private List<Transactions> getTransactionsResult() {
    }


}