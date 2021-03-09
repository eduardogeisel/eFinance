package com.eduardogeiselperes.efinance;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Financing {

    public String name;
    public String type;
    public double value;
    public double years;
    public double downPayment;
    public double taxes;
    public double interestRate;

    public Financing(){
        //Default constructor to call DataSnapshot.getValue(Financing.class)
    }

    public Financing(String name, String type, double value, double years, double downPayment, double taxes, double interestRate) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.years = years;
        this.downPayment = downPayment;
        this.taxes = taxes;
        this.interestRate = interestRate;
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

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getYears() {
        return years;
    }

    public void setYears(double years) {
        this.years = years;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public void setDownPayment(double downPayment) {
        this.downPayment = downPayment;
    }

    public double getTaxes() {
        return taxes;
    }

    public void setTaxes(double taxes) {
        this.taxes = taxes;
    }

    public double getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
    }
}
