package com.dami.stockcontrol.service;


import com.dami.stockcontrol.model.AdminRequests;
import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Role;
import com.dami.stockcontrol.repo.AdminRequestRepo;
import com.dami.stockcontrol.repo.PersonRepo;
import com.dami.stockcontrol.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
public class AdminRequestsService {

    @Autowired
    PersonService personService;

    @Autowired
    AdminRequestRepo adminRequestRepo;

    public void makeNewRequest(int personId) {
        Person person = personService.getPersonById(personId);

        AdminRequests adminRequests = new AdminRequests();
        adminRequests.setPersonId(personId);
        adminRequests.setDateAdded(String.valueOf(LocalDate.now()));
        adminRequests.setUsername(person.getUsername());
        adminRequests.setRole(person.getRole());

        adminRequestRepo.save(adminRequests);
    }

    public void acceptRequest(int id) {
        System.out.println(id);
        makePersonAdmin(id);
        adminRequestRepo.deleteByPersonId(id);
    }

    private void makePersonAdmin(int id) {
        AdminRequests adminRequests = adminRequestRepo.findByPersonId(id);
        personService.updatePersonRole(adminRequests.getPersonId(), Role.ADMIN);
    }

    public void declineRequest(int id) {
        adminRequestRepo.deleteByPersonId(id);
    }

    public List<AdminRequests> getRequests() {
        List<AdminRequests> requests = new ArrayList<>();
        adminRequestRepo.findAll().forEach(requests::add);

        return requests;
    }
}
