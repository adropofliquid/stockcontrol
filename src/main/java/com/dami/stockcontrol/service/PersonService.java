package com.dami.stockcontrol.service;


import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.repo.PersonRepo;
import com.dami.stockcontrol.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.List;

@Service
public class PersonService{

    @Autowired
    PersonRepo personRepo;

    @Autowired
    ProductRepo productRepo;

    public void saveNewPerson(Person person) {
        personRepo.save(person);
    }

    public Person getPersonByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    public int getUsersUnderAdmin(String principal) {
//        personRepo.
        return 0;
    }

    public int getTotalUsers() {

        return (int) personRepo.count();
    }

}
