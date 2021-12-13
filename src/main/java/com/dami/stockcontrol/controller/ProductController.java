package com.dami.stockcontrol.controller;

import com.dami.stockcontrol.model.*;
import com.dami.stockcontrol.service.CompanyService;
import com.dami.stockcontrol.service.ProductService;
import com.dami.stockcontrol.service.TransactionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.security.Principal;
import java.util.List;


@Controller
public class ProductController {

    @Autowired
    ProductService productService;

    @Autowired
    CompanyService companyService;

    @GetMapping("/product")
    public String product(Model model){

        model.addAttribute("products", getAllProducts());
        model.addAttribute("categoryFilter", new CategoryFilter());
        model.addAttribute("categories", getCategories());
        model.addAttribute("productAdd", new ProductAddInfo());
        model.addAttribute("companies", getCompanies());

        return "product";
    }

    @RequestMapping(value = "/product/add", method = RequestMethod.POST)
    public String add(@ModelAttribute ProductAddInfo productAddInfo, Model model){
        addNewProduct(productAddInfo);
        return "redirect:/product";
    }

    @RequestMapping(value = "/product", method = RequestMethod.POST)
    public String filter(@ModelAttribute CategoryFilter categoryFilter, Model model){

        model.addAttribute("products", getProducts(categoryFilter.getFilter()));
        model.addAttribute("categoryFilter", new CategoryFilter());
        model.addAttribute("categories", getCategories());
        model.addAttribute("productAdd", new ProductAddInfo());
        model.addAttribute("companies", getCompanies());

        if(categoryFilter.getFilter() == 0)
            return "redirect:/product";

        return "product";
    }

    private void addNewProduct(ProductAddInfo productAddInfo) {
        System.out.println(productAddInfo.getSubCategoryName()+" "+productAddInfo.getCategoryName());
        productService.addNew(productAddInfo);
    }

    private List<Category> getCategories() {
        return productService.getCategories();
    }

    private List<Product> getProducts(int catId) {
        System.out.println(catId);
        return productService.getProductsByPersonAndCategory(getPrincipalName(), catId);
    }

    private List<Product> getAllProducts() {
        return productService.getAllProductsBy(getPrincipalName());
    }

    private List<Company> getCompanies() {
        return companyService.getCompaniesByPersonName(getPrincipalName());
    }

    private String getPrincipalName(){
        return SecurityContextHolder.getContext().getAuthentication().getName();
    }

}