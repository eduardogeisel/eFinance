package com.eduardogeiselperes.efinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

import java.text.DecimalFormat;

public class MyFinancing extends AppCompatActivity {

    TextView name, type, value;

    private FirebaseUser user; //Firebase obj
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_financing);

        getUser();

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        name = findViewById(R.id.tvMyFinancingFinancingName);
        type = findViewById(R.id.tvMyFinancingPaymentType);
        value = findViewById(R.id.tvMyFinancingPaymentValue);

        String getName = bundle.getString("getName");
        String getType = bundle.getString("getType");
        String getValue = bundle.getString("getValue");
        double valueDouble = Double.parseDouble(getValue);
        String getYears = bundle.getString("getYears");
        int yearsInt= Integer.parseInt(getYears);
        String getDownPayment = bundle.getString("getDownPayment");
        double downPaymentDouble = Double.parseDouble(getDownPayment);
        String getContribution = bundle.getString("getContribution");
        double contributionDouble = Double.parseDouble(getContribution);
        String getInterestRate = bundle.getString("getInterestRate");
        double interestRateDouble = Double.parseDouble(getInterestRate);

        double valueWithTaxes = valueDouble * ((contributionDouble/100)+1);
        double borrowedAmount = (valueWithTaxes - downPaymentDouble);

        //calculating debt with yearly interest rate
        double currentDebt = borrowedAmount;
        for(int i = 0; i < yearsInt; i++){
            currentDebt = (currentDebt*interestRateDouble/100) + currentDebt;
        }

        double payments;
        if(getType.equals("Weekly")){
            payments = currentDebt/(yearsInt * (365.25/7));
        }
        else if(getType.equals("Bi-weekly")){
            payments = currentDebt/(yearsInt * (365.25/14));
        }
        else{
            payments = currentDebt/(yearsInt * (12));
        }

        //formatting payments to 2 decimal spaces
        DecimalFormat df = new DecimalFormat("0.00");
        String pay = df.format(payments);

        name.setText(getName);
        type.setText(getType);
        value.setText(pay);
    }

    public void getUser(){
        user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail().replace(".", "&");
    }
}