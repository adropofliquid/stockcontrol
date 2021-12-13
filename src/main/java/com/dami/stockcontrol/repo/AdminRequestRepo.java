package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.AdminRequests;
import com.dami.stockcontrol.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.function.Supplier;

public interface AdminRequestRepo extends CrudRepository<AdminRequests, Integer> {

    void deleteByPersonId(int id);

    AdminRequests findByPersonId(int id);
}
