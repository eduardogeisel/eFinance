package com.eduardogeiselperes.efinance;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;

public class EditFinancing extends AppCompatActivity {

    EditText editTextEditName, editTextEditFinancingValue, editTextEditFinancingLength,
            editTextEditDownPayment, editTextEditTaxes, editTextEditInterestRate;

    Button btnEditFinancingSubmit;

    Spinner spinnerEditFinancingType;

    DatabaseReference databaseReference;
    private FirebaseUser user;
    private String userEmail;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_financing);

        editTextEditDownPayment = findViewById(R.id.editTextEditDownPayment);
        editTextEditName = findViewById(R.id.editTextEditName);
        editTextEditFinancingValue = findViewById(R.id.editTextEditFinancingValue);
        editTextEditFinancingLength = findViewById(R.id.editTextEditFinancingLength);
        editTextEditTaxes = findViewById(R.id.editTextEditTaxes);
        editTextEditInterestRate = findViewById(R.id.editTextEditInterestRate);

        btnEditFinancingSubmit = findViewById(R.id.btnEditFinancingSubmit);
        spinnerEditFinancingType = findViewById(R.id.spinnerEditFinancingType);

        getUser();

        editTextEditName.setText(getIntent().getStringExtra("name"));
        editTextEditDownPayment.setText(getIntent().getStringExtra("downPayment"));
        editTextEditFinancingLength.setText(getIntent().getStringExtra("years"));
        editTextEditInterestRate.setText(getIntent().getStringExtra("interestRate"));
        editTextEditFinancingValue.setText(getIntent().getStringExtra("value"));
        editTextEditTaxes.setText(getIntent().getStringExtra("contribution"));


    }

    public void getUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail().replace(".", "&");
    }
}