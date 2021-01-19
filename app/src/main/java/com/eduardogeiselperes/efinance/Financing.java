package com.eduardogeiselperes.efinance;

import com.google.firebase.database.IgnoreExtraProperties;

@IgnoreExtraProperties
public class Financing {

    public String name;
    public String type;
    public double value;
    public String firstPayment;
    public double years;

    public Financing(){
        //Default constructor to call DataSnapshot.getValue(Financing.class)
    }

    public Financing(String name, String type, double value, String firstPayment, double years) {
        this.name = name;
        this.type = type;
        this.value = value;
        this.firstPayment = firstPayment;
        this.years = years;
    }
}
