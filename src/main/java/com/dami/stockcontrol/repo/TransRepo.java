package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Transactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransRepo extends CrudRepository<Transactions, Integer> {

}
