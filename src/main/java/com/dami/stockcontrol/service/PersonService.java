package com.dami.stockcontrol.service;


import com.dami.stockcontrol.model.*;
import com.dami.stockcontrol.repo.ManagerGroupRepo;
import com.dami.stockcontrol.repo.PersonRepo;
import com.dami.stockcontrol.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PersonService{

    @Autowired
    PersonRepo personRepo;

    @Autowired
    ProductRepo productRepo;

    @Autowired
    ManagerGroupRepo managerGroupRepo;

    public void saveNewPerson(Person person) {
        personRepo.save(person);
    }

    public Person getPersonByUsername(String username) {
        return personRepo.findByUsername(username);
    }

    public List<Person> getUsersUnderAdmin(String principal) {

        return personRepo.findByCompanyId(getPersonByUsername(principal).getId());
    }

    public int getTotalUsers() {

        return (int) personRepo.count();
    }

    public Person getPersonById(int personId) {
        return personRepo.findById(personId).get();
    }

    public void updatePersonRole(int personId,String role) {

        if(personRepo.findById(personId).isPresent()){
            Person person = personRepo.findById(personId).get();
            person.setRole(role);

            personRepo.save(person);
        }

    }

    public void updateCompanyPersonRole(String role, String personId) {

        if(personRepo.findById(Integer.parseInt(personId)).isPresent()){
            Person person = personRepo.findById(Integer.parseInt(personId)).get();
            person.setRole(role);

            personRepo.save(person);
        }

    }

    public void deletePerson(int id) {
        if(personRepo.findById(id).isPresent()){
            Person person = personRepo.findById(id).get();
            personRepo.delete(person);
        }
    }

    public void saveInGroup(Person person, String group) {
        ManagerGroup managerGroup = new ManagerGroup();

//        companyGroup.setId(person.getId());
        managerGroup.setBossId(person.getCompanyId());
        managerGroup.setGroupName(group);
        managerGroup.setBuyProduct(true);
        managerGroup.setViewSalesReport(true);

        managerGroupRepo.save(managerGroup);
    }

    public List<ManagerGroup> getManagerGroups(String boss) {
        return managerGroupRepo.findAllByBossId(getPersonByUsername(boss).getId());
    }

    public void createCompanyRole(String role, String principal) {

        ManagerGroup managerGroup = new ManagerGroup();

        managerGroup.setBossId(getPersonByUsername(principal).getId());
        managerGroup.setGroupName(role);
        managerGroup.setBuyProduct(true);
        managerGroup.setViewSalesReport(true);

        managerGroupRepo.save(managerGroup);
    }
}