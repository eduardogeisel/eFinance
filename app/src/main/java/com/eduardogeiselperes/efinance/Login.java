package com.eduardogeiselperes.efinance;


import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Login extends AppCompatActivity {

    private FirebaseAuth auth;
    private Button loginBtn, registerBtn;
    private EditText emailInput, passwordInput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //Initializing firebase instance
        auth = FirebaseAuth.getInstance();

        //finding EditText and login ids
        emailInput = findViewById(R.id.editTextEmail);
        passwordInput = findViewById(R.id.editTextTextPassword);
        loginBtn = findViewById(R.id.btnLogin);
        registerBtn = findViewById(R.id.btnGoToRegister);

        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailInput.getText().toString().trim();
                String password = passwordInput.getText().toString().trim();

                //Toast.makeText(MainActivity.this, password, Toast.LENGTH_LONG).show();

                if(TextUtils.isEmpty(email)){
                    Toast.makeText(Login.this, "Please enter an email", Toast.LENGTH_LONG).show();
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    Toast.makeText(Login.this, "Please enter a password", Toast.LENGTH_LONG).show();
                    return;
                }
                if(password.length() < 6){
                    Toast.makeText(Login.this, "Password must have " +
                            "6 or more characters", Toast.LENGTH_LONG).show();
                    return;
                }
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this, "Login Successful", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(Login.this, "Login Failed", Toast.LENGTH_LONG).show();
                        }
                    }
                });
                //loginForm();
            }
        });

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(Login.this, Register.class);
                startActivity(i);
            }
        });


    }


    private void loginForm() {
        String email = emailInput.getText().toString().trim();
        String password = passwordInput.toString().trim();
        //Toast.makeText(MainActivity.this, email, Toast.LENGTH_LONG).show();

        //in the future, create two functions called checkEmail() and checkPassword() and verify them here

        auth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Toast.makeText(Login.this, email, Toast.LENGTH_LONG).show();

                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            //Log.d(TAG, "signInWithEmail:success");
                            FirebaseUser user = auth.getCurrentUser();
                            Toast.makeText(Login.this, "Authentication successful.",
                                    Toast.LENGTH_LONG).show();
                            Log.d("TAG", "signInWithEmail:success");
                            Intent i = new Intent(Login.this, Welcome.class);
                            startActivity(i);
                            //updateUI(user);
                        } else {
                            // If sign in fails, display a message to the user.
                            //Log.w(TAG, "signInWithEmail:failure", task.getException());
                            Toast.makeText(Login.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();
                            //updateUI(null);
                        }

                        // ...
                    }
                });
    }

}