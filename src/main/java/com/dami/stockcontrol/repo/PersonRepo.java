package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Person;
import org.springframework.data.repository.CrudRepository;

public interface PersonRepo extends CrudRepository<Person, Integer> {

    Person findByUsername(String username);

}
