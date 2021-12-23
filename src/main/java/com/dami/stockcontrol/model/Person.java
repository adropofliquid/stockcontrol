package com.dami.stockcontrol.model;



import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Person {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    private String username;
    private String password;
    private String lname;
    private String fname;
    private boolean enabled;
    private String role;
    private int companyId; // TODO Info, this is Id of employer??
                            // if Id = 0, e;s not a CompanyUser
    private String dateAdded;


    public Person(){}

    public Person(String username,String password,String lname,String fname,
                  boolean enabled, String role,int companyId,String dateAdded) {

        this.username = username;
        this.password = password;
        this.lname = lname;
        this.fname = fname;
        this.enabled = enabled;
        this. role = role;
        this.companyId = companyId;
        this.dateAdded = dateAdded;
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLname() {
        return lname;
    }

    public void setLname(String lname) {
        this.lname = lname;
    }

    public String getFname() {
        return fname;
    }

    public void setFname(String fname) {
        this.fname = fname;
    }

    public void setEnabled(boolean enabled){
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public int getCompanyId() {
        return companyId;
    }

    public void setCompanyId(int companyId) {
        this.companyId = companyId;
    }

    public String getDateAdded() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded = dateAdded;
    }
}
