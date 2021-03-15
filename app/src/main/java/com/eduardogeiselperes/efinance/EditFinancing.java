package com.eduardogeiselperes.efinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

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

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        String getType = bundle.getString("getType");
        String getName = bundle.getString("getName");
        String getValue = bundle.getString("getValue");
        String getYears = bundle.getString("getYears");
        String getDownPayment = bundle.getString("getDownPayment");
        String getContribution = bundle.getString("getContribution");
        String getInterestRate = bundle.getString("getInterestRate");
        String getKey = bundle.getString("key");

        editTextEditName.setText(getName);
        editTextEditDownPayment.setText(getDownPayment);
        editTextEditFinancingLength.setText(getYears);
        editTextEditInterestRate.setText(getInterestRate);
        editTextEditFinancingValue.setText(getValue);
        editTextEditTaxes.setText(getContribution);

        //setting type shown on spinner
        if(getType.equals("Weekly")){
            spinnerEditFinancingType.setSelection(0);
        }
        else if(getType.equals("Bi-weekly")){
            spinnerEditFinancingType.setSelection(1);
        }
        else{
            spinnerEditFinancingType.setSelection(2);
        }

        //saving edited items
        btnEditFinancingSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(editTextEditName.getText().toString().equals("") || editTextEditFinancingValue.getText().toString().equals("") || editTextEditFinancingLength.getText().toString().equals("")
                        || editTextEditDownPayment.getText().toString().equals("") || editTextEditTaxes.getText().toString().equals("") || editTextEditInterestRate.getText().toString().equals("")){
                    Toast.makeText(EditFinancing.this, "Please make sure every option has data.", Toast.LENGTH_SHORT).show();
                }

                databaseReference = FirebaseDatabase.getInstance().getReference().child("Financing").child("userEmail: " + userEmail).child("FinancingNumber"+getKey);

                databaseReference.child("name").setValue(editTextEditName.getText().toString());
                databaseReference.child("type").setValue(spinnerEditFinancingType.getSelectedItem().toString());
                databaseReference.child("value").setValue(editTextEditFinancingValue.getText().toString());
                databaseReference.child("years").setValue(editTextEditFinancingLength.getText().toString());
                databaseReference.child("downPayment").setValue(editTextEditDownPayment.getText().toString());
                databaseReference.child("contribution").setValue(editTextEditTaxes.getText().toString());
                databaseReference.child("interestRate").setValue(editTextEditInterestRate.getText().toString());

                databaseReference.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        Intent i = new Intent(EditFinancing.this, Welcome.class);
                        startActivity(i);
                        finish();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
    }

    public void getUser() {
        user = FirebaseAuth.getInstance().getCurrentUser();
        userEmail = user.getEmail().replace(".", "&");
    }
}