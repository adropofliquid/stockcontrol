package com.dami.stockcontrol.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class CompanyUser {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private int personId;
    private int bossId;
    private int companyGroupId;

    public int getId() {
        return id;
    }

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public int getBossId() {
        return bossId;
    }

    public void setBossId(int bossId) {
        this.bossId = bossId;
    }

    public int getCompanyGroupId() {
        return companyGroupId;
    }

    public void setCompanyGroupId(int companyGroupId) {
        this.companyGroupId = companyGroupId;
    }


}
