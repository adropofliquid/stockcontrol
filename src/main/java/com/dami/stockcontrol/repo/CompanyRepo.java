package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyRepo extends CrudRepository<Company, Integer> {

    List<Company> findAllByOwnerUserId(int ownerId);

    Company findByName(String name);

    boolean existsCompanyByNameAndOwnerUserId(String name, int ownerUserId);
}
