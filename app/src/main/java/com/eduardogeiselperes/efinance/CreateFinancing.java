package com.eduardogeiselperes.efinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.Random;

public class CreateFinancing extends AppCompatActivity {

    private Button btnHome;
    private Spinner type;
    private EditText name, value, years, downPayment, contribution, interestRate;
    private Button btnSubmit;
    DatabaseReference reference;
    Integer key = new Random().nextInt();

    //getting user
    private FirebaseUser user;
    private String userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_financing);

        //getting ids
        btnHome = findViewById(R.id.btnHome);
        name = findViewById(R.id.editTextFinancingName);
        type = findViewById(R.id.spinnerFinancingType);
        value = findViewById(R.id.editTextFinancingValue);
        years = findViewById(R.id.editTextFinancingLength);
        downPayment = findViewById(R.id.editTextDownPayment);
        contribution = findViewById(R.id.editTextTaxes);
        interestRate = findViewById(R.id.editTextInterestRate);
        btnSubmit = findViewById(R.id.btnFinancingSubmit);


        //getting user email
        user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail().replace(".", "&");

        btnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CreateFinancing.this, Welcome.class));
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //inserting data into firebase realtime DB
                reference = FirebaseDatabase.getInstance().getReference().child("Financing").child("userEmail: " + userEmail).child("FinancingNumber"+key);

                reference.child("name").setValue(name.getText().toString());
                reference.child("type").setValue(type.getSelectedItem().toString());
                reference.child("value").setValue(value.getText().toString());
                reference.child("years").setValue(years.getText().toString());
                reference.child("downPayment").setValue(downPayment.getText().toString());
                reference.child("contribution").setValue(contribution.getText().toString());
                reference.child("interestRate").setValue(interestRate.getText().toString());

                reference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Toast.makeText(CreateFinancing.this, "Financing created", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });

            }
        });
    }
}