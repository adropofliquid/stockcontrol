package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.Category;
import com.dami.stockcontrol.model.CategoryFilter;
import com.dami.stockcontrol.model.MarketProduct;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.service.CompanyService;
import com.dami.stockcontrol.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;
import java.util.List;


@Controller
public class MarketController {


    @Autowired
    ProductService productService;

    @Autowired
    CompanyService companyService;
    
    
    @GetMapping("/market")
    public String market(Model model){

        model.addAttribute("marketProducts", getAllMarketProducts());
        model.addAttribute("categoryFilter", new CategoryFilter());
        model.addAttribute("categories", getCategories());

        return "market";
    }

    private List<MarketProduct> getAllMarketProducts() {

        List<Product> products = productService.getAllProductsByOthers(getPrincipalName());
        List<MarketProduct> marketProducts = new ArrayList<>();

        products.forEach(p -> {
            MarketProduct marketProduct = new MarketProduct(p.getName(), productService.getNameFromId(p.getCategoryId()),
                    p.getDescription(), p.getCostPrice(), p.getSellingPrice(),  p.getQuantity(),
                    companyService.getCompanyNameById(p.getCompanyId()), "imageLocation",
                    String.valueOf(System.currentTimeMillis()));

            marketProducts.add(marketProduct);
            });

        return marketProducts;
    }

    private List<Category> getCategories() {
        return productService.getCategories();
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}