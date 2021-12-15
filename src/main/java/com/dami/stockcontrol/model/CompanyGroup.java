package com.dami.stockcontrol.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyGroup {

    //TODO have default groups dat's generalized
    // den d rest
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String groupName;
    private int bossId;
    private boolean viewSalesReport;
    private boolean buyProduct;

    public int getId() {
        return id;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public boolean isViewSalesReport() {
        return viewSalesReport;
    }

    public void setViewSalesReport(boolean viewSalesReport) {
        this.viewSalesReport = viewSalesReport;
    }

    public boolean isBuyProduct() {
        return buyProduct;
    }

    public void setBuyProduct(boolean buyProduct) {
        this.buyProduct = buyProduct;
    }
}
