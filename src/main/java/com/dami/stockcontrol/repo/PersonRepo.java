package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepo extends CrudRepository<Person, Integer> {

    Person findByUsername(String username);

    List<Person> findByCompanyId(int id);
}
