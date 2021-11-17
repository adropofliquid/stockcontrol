package com.dami.stockcontrol.service;

import com.dami.stockcontrol.model.Product;
import com.dami.stockcontrol.model.Transactions;
import com.dami.stockcontrol.repo.TransRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.thymeleaf.spring5.processor.SpringErrorClassTagProcessor;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class TransactionsService {

    @Autowired
    TransRepo transRepo;

    public List<Double> getProfitInSales() {
        List<Double> profit = new ArrayList<>();
        profit.add(calculateProfitInSales());
        profit.add(calculateProfitInSalesPercent());

        return profit;
    }

    private double calculateProfitInSales() {
        double sellPrice ,costPrice;

        List<Transactions> all = getAll();
        sellPrice = all.stream().mapToDouble(Transactions::getSoldPrice).sum();
        costPrice = all.stream().mapToDouble(Transactions::getCostPrice).sum();


        return sellPrice - costPrice;
    }

    private double calculateProfitInSalesPercent() {
        double costPrice;

        List<Transactions> all = getAll();
        costPrice = all.stream().mapToDouble(Transactions::getCostPrice).sum();

        double profit = calculateProfitInSales();


        return ((profit/costPrice)* 100);
    }

    public List<Transactions> getRecentTransactions() {
        List<Transactions> all = getAll();
        //Collections.reverse(all);
        return all;
    }

    private List<Transactions> getAll(){
        List<Transactions> all = new ArrayList<>();
        transRepo.findAll().forEach(all::add);
        return all;
    }
}
