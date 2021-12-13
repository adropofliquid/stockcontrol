package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Company;
import com.dami.stockcontrol.model.CompanyMembers;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface CompanyMembersRepo extends CrudRepository<CompanyMembers, Integer> {

}
