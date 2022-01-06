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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
        return "search";
    }

    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public String getSearch(@ModelAttribute Search search,  RedirectAttributes attributes){


        attributes.addFlashAttribute("products", getProductsResult());
        attributes.addFlashAttribute("users", getUsersResult());
        attributes.addFlashAttribute("transactions", getTransactionsResult());

        return "redirect:/search";
    }

    private List<Product> getProductsResult() {
        //productService.getProductsLikeName()
        return null;
    }

    private List<Person> getUsersResult() {
        return null;
    }

    private List<Transactions> getTransactionsResult() {
        return null;
    }


}