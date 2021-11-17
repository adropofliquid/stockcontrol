package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyMembersService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    ProductService productService;

    @Autowired
    PersonService personService;

    public List<Company> getCompaniesBy(String principal) {

        Person owner = personService.getPersonByUsername(principal);
        List<Company> companies = companyRepo.findAllByOwnerUserId(owner.getId());

        companies.forEach((c) -> {
            c.setProductCount(productService.getProductsCountFrom(c.getId()));
        });

        return companies;
    }
}
