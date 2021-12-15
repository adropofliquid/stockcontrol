package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.*;
import com.dami.stockcontrol.repo.CategoryRepo;
import com.dami.stockcontrol.repo.ProductRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductRepo productRepo;

    @Autowired
    PersonService personService;

    @Autowired
    CompanyService companyService;

    @Autowired
    CompanyMembersService companyMembersService;

    @Autowired
    CategoryRepo categoryRepo;

    public List<Product> getLatestProducts() {

        return new ArrayList<>();
    }

    public int getProductsCountFrom(int companyId) {
        return  productRepo.findByCompanyId(companyId).size();
    }

    public List<Product> getAllProductsBy(String name) {
        List<Company> companies = companyMembersService.getCompaniesBy(name);
        List<Integer> companyIds = new ArrayList<>();

        companies.forEach((c)-> companyIds.add(c.getId()));

        return productRepo.findByCompanyIdIn(companyIds);
    }

    public void addNew(ProductAddInfo productAddInfo) {


        if(!productAddInfo.getCategoryName().equals("exists"))//<--boilerplate
            createCategoryIfNotexist(productAddInfo.getCategoryName(),productAddInfo.getSubCategoryName());

        Product product = new Product(productAddInfo.getName(),
                categoryRepo.findByName(productAddInfo.getSubCategoryName()).getId(),
                productAddInfo.getDescription(),productAddInfo.getCostPrice(),
                productAddInfo.getSellingPrice(),productAddInfo.getQuantity(),
                Integer.parseInt(productAddInfo.getCompany()),
                "imageLocation", String.valueOf(System.currentTimeMillis()));

        productRepo.save(product);
    }

    private void createCategoryIfNotexist(String categoryName, String subCategoryName) {
        if(categoryRepo.existsCategoryByName(categoryName)){  // if Super category already exists
            Category superCat = categoryRepo.findByName(categoryName);

            //if Sub Category doesnt exist, create one
            if(!categoryRepo.existsCategoryByNameAndSuperCategoryId(subCategoryName, superCat.getId())){
                Category subCat = new Category();
                subCat.setSuperCategoryId(superCat.getId());
                subCat.setName(subCategoryName);
                categoryRepo.save(subCat);
            }
        }else {

            Category superCat = new Category();
            superCat.setName(categoryName);
            superCat.setSuperCategoryId(0);
            categoryRepo.save(superCat);

            Category savedSuperCat = categoryRepo.findByName(categoryName);

            Category subCat = new Category();
            subCat.setName(subCategoryName);
            subCat.setSuperCategoryId(savedSuperCat.getId());
            categoryRepo.save(subCat);
        }
    }

    public List<Category> getCategories() {
        List<Category> categories = new ArrayList<>();
        categoryRepo.findAll().forEach(categories::add);
        return categories;
    }

    public List<Product> getProductsByPersonAndCategory(String personName, int catId) {
        List<Product> userProducts = getAllProductsBy(personName);
        List<Product> userProductsByCatrgoryId = new ArrayList<>();

        userProducts.forEach((p)->{
            if(p.getCategoryId() == catId)
                userProductsByCatrgoryId.add(p);
        });

        return userProductsByCatrgoryId;
    }

    public List<Product> getAllProductsByOthers(String name) {

        List<Company> companies = companyMembersService.getCompaniesBy(name);
        List<Integer> companyIds = new ArrayList<>();

        companies.forEach((c)-> companyIds.add(c.getId()));

        return productRepo.findByCompanyIdNotIn(companyIds);
    }

    public String getNameFromId(int categoryId) {
        return categoryRepo.findById(categoryId).get().getName();
    }
}
