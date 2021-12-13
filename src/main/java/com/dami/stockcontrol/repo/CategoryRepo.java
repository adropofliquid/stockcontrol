package com.dami.stockcontrol.repo;

import com.dami.stockcontrol.model.Category;
import org.springframework.data.repository.CrudRepository;


public interface CategoryRepo extends CrudRepository<Category, Integer> {

    Category findByName(String categoryName);

    boolean existsCategoryByName(String categoryName);

    boolean existsCategoryByNameAndSuperCategoryId(String name, int id);

}
