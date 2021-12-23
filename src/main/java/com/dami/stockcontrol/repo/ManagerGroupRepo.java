package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.ManagerGroup;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ManagerGroupRepo extends CrudRepository<ManagerGroup, Integer> {

    List<ManagerGroup> findAllByBossId(int id);
}
