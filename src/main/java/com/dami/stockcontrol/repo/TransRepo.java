package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Transactions;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface TransRepo extends CrudRepository<Transactions, Integer> {

    List<Transactions> findAllByBuyer(int buyer);

    List<Transactions> findAllByCompanyId(int companyId);

    List<Transactions> findAllByBuyerAndType(int buyer, int type);

    List<Transactions> findByCompanyIdIn(List<Integer> ids);
}
