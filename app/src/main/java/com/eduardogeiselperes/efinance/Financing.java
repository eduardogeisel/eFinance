package com.eduardogeiselperes.efinance;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Financing {

    public String name;
    public String type;
    public String value;
    public String years;
    public String downPayment;
    public String contribution; //taxes...
    //public String taxes;
    public String interestRate;

    public Financing() {
        //Default constructor to call DataSnapshot.getValue(Financing.class)
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getYears() {
        return years;
    }

    public void setYears(String years) {
        this.years = years;
    }

    public String getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(String downPayment) {
        this.downPayment = downPayment;
    }

    public String getContribution() {
        return contribution;
    }

    public void setContribution(String contribution) {
        this.contribution = contribution;
    }

    public String getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(String interestRate) {
        this.interestRate = interestRate;
    }
}

