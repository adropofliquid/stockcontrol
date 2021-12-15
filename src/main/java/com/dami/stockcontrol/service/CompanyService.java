package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.repo.CompanyRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {

    @Autowired
    CompanyRepo companyRepo;

    @Autowired
    ProductService productService;

    @Autowired
    PersonService personService;

    public List<Company> getCompaniesByPersonName(String principal) {

        Person owner = personService.getPersonByUsername(principal);

        List<Company> companies = companyRepo.findAllByOwnerUserId(owner.getId());
        companies.forEach((c) -> {
            c.setProductCount(productService.getProductsCountFrom(c.getId()));
        });

        return companies;
    }

    public void addNew(Company company) {
        companyRepo.save(company);
    }

    public int getCompanyIdByName(String company) {
        return companyRepo.findByName(company).getId();
    }

    public String getCompanyNameById(int companyId) {
        return companyRepo.findById(companyId).get().getName();
    }
}
