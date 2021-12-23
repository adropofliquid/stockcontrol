package com.dami.stockcontrol.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Transactions {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String productName;
    private int categoryId;
    private String description;
    private double costPrice;
    private double soldPrice;
    private int type;
    private int quantity;
    private int buyer;
    private int companyId;
    private String imageLocation;
    public String date;

    public Transactions(){}

    public Transactions(String productName, int categoryId, String description, double costPrice, double soldPrice, int type, int quantity, int buyer, int companyId, String imageLocation, String date) {

        this.productName = productName;
        this.categoryId = categoryId;
        this.description = description;
        this.costPrice = costPrice;
        this.soldPrice = soldPrice;
        this.type = type;
        this.quantity = quantity;
        this.companyId = companyId;
        this.buyer = buyer;
        this.imageLocation = imageLocation;
        this.date = date;
    }

    public int getId() {
        return id;
    }

    public String getProductName() {
        return productName;
    }
    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getCostPrice() {
        return costPrice;
    }

    public void setCostPrice(double costPrice) {
        this.costPrice = costPrice;
    }

    public double getSoldPrice() {
        return soldPrice;
    }

    public void setSoldPrice(double soldPrice) {
        this.soldPrice = soldPrice;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public int getBuyer() {
        return buyer;
    }

    public void setBuyer(int buyerId) {
        this.buyer = buyerId;
    }

    public String getImageLocation() {
        return imageLocation;
    }

    public void setImageLocation(String imageLocation) {
        this.imageLocation = imageLocation;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
