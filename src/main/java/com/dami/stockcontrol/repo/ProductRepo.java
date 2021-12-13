package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface ProductRepo extends CrudRepository<Product, Integer> {

    List<Product> findByCompanyId(int companyId);
    List<Product> findByCompanyIdIn(List<Integer> ids);
}
