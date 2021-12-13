package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Transactions;
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
public class ImportController {

    @GetMapping("/import")
    public String home(Model model){

        return "import";
    }

}