package com.eduardogeiselperes.efinance;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {

    private FirebaseAuth auth;
    private FirebaseUser user;
    private TextView txtViewWelcome;
    private Button btnLogout, btnNewFinancing;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);

        //Getting id's
        txtViewWelcome = findViewById(R.id.txtViewWelcome);
        btnLogout = findViewById(R.id.btnLogout);
        btnNewFinancing = findViewById(R.id.btnNewFinancing);

        //getting logged in user's information
        user = FirebaseAuth.getInstance().getCurrentUser();
        String userEmail = user.getEmail();

        //greeting logged in user
        txtViewWelcome.setText("Welcome, " + userEmail + " ! We're happy to see you!");

        //logging user out
        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Welcome.this, Login.class));
            }
        });

        //going to new financing activity
        btnNewFinancing.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this, Financing.class));
            }
        });





    }
}