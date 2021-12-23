package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Person;
import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.repo.TransRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    TransRepo transRepo;

    @Autowired
    PersonService personService;

    @Autowired
    ProductService productService;

    @Autowired
    CompanyService companyService;

    public List<Double> getProfitInSales(String person) {
        List<Double> profit = new ArrayList<>();
        profit.add(calculateProfitInSales(person));
        profit.add(calculateProfitInSalesPercent(person));

        return profit;
    }

    public double calculateProfitInSales(String person) {
        double sellPrice ,costPrice;

        List<Transactions> all = getSalesTransactionsBy(person);
        sellPrice = all.stream().mapToDouble(Transactions::getSoldPrice).sum();
        costPrice = all.stream().mapToDouble(Transactions::getCostPrice).sum();

        return sellPrice - costPrice;
    }

    public double calculateProfitInSalesPercent(String person) {
        double costPrice;

        List<Transactions> all = getSalesTransactionsBy(person);
        costPrice = all.stream().mapToDouble(Transactions::getCostPrice).sum();

        double profit = calculateProfitInSales(person);


        return ((profit/costPrice)* 100);
    }

    public List<Transactions> getSalesTransactionsBy(String p) {

        Person person = personService.getPersonByUsername(p);

        List<Integer> companies = new ArrayList<>();
        companyService.getCompaniesByPersonId(person.getId()).forEach((c) -> companies.add(c.getId()));


        return transRepo.findByCompanyIdIn(companies);
    }

    public List<Transactions> getRecentTransactionsBy(String p) {
        Person person = personService.getPersonByUsername(p);
        List<Transactions> transactions =  transRepo.findAllByBuyer(person.getId()); //get purchase

        List<Integer> companies = new ArrayList<>();
        companyService.getCompaniesByPersonId(person.getId()).forEach((c) -> companies.add(c.getId()));


        List<Transactions> sales =  transRepo.findByCompanyIdIn(companies); //get sales
        sales.forEach((sale) -> sale.setType(1));//turn type to sales
        transactions.addAll(sales);

        //format date
        if(!transactions.isEmpty())
            transactions.forEach((t) -> t.setDate(new SimpleDateFormat("MMM dd, yyyy").format(new Date(Long.parseLong(t.getDate())))));

        return transactions;
    }

    private List<Transactions> getAll(){
        List<Transactions> all = new ArrayList<>();
        transRepo.findAll().forEach(all::add);
        return all;
    }

    public void buyProduct(String principalName, String id) {
        Person buyer = personService.getPersonByUsername(principalName);
        Product product = productService.getProductsById(Integer.parseInt(id));

        product.setQuantity(product.getQuantity() - 1);

        Transactions transactions = new Transactions(
                product.getName(), product.getCategoryId(),
                product.getDescription(), product.getCostPrice(),
                product.getSellingPrice(), 0/***O for buy **/,1,
                buyer.getId(),product.getCompanyId(),
                product.getImageLocation(), String.valueOf(System.currentTimeMillis()));

        productService.updateProduct(product);
        transRepo.save(transactions);

        }
}
