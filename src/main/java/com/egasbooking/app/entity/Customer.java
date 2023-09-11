package com.egasbooking.app.entity;
 

 
import javax.persistence.CascadeType;
import javax.persistence.Entity;

import javax.persistence.JoinColumn;

import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.validation.constraints.Size;

@Entity
@PrimaryKeyJoinColumn(name = "customer_id")
public class Customer extends AbstractUser {
 
    
    private String pan;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "cylinder_fk", referencedColumnName = "cylinderId")
    private Cylinder cylinder;
    @OneToOne(cascade = CascadeType.MERGE)
    @JoinColumn(name = "bank_fk", referencedColumnName = "bankId")
    private Bank bank;
    public Customer()
    {

    }


    public Customer(String pan, Cylinder cylinder, Bank bank) {
        super();
        this.pan = pan;
        this.cylinder = cylinder;
        this.bank = bank;
    }
 
    public String getPan() {
        return pan;
    }
 

    public void setPan(String pan) {
        this.pan = pan;
    }
 

    public Cylinder getCylinder() {
        return cylinder;
    }
    public void setCylinder(Cylinder cylinder) {
        this.cylinder = cylinder;
    }
    public Bank getBank() {
        return bank;
    }
    public void setBank(Bank bank) {
        this.bank = bank;
    }
 

}
