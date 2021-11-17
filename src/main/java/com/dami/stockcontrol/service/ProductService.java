package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PersonService personService;

    @Autowired
    CompanyMembersService companyMembersService;

    public List<Product> getLatestProducts() {

        return new ArrayList<>();
    }

    public int getProductsCountFrom(int companyId) {
        return  productRepo.findByCompanyId(companyId).size();
    }

    public List<Product> getAllProductsBy(String name) {
        List<Company> companies = companyMembersService.getCompaniesBy(name);
        List<Integer> companyIds = new ArrayList<>();

        companies.forEach((c)-> companyIds.add(c.getId()));

        return productRepo.findByCompanyIdIn(companyIds);
    }
}
