package com.eduardogeiselperes.efinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Register extends AppCompatActivity {

    private FirebaseAuth auth;
    private EditText registerEmail, registerPass, registerPassConfirm;
    private Button registerBtn;
    private static final String TAG = "FIREBASE";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //Initializing firebase instance
        auth = FirebaseAuth.getInstance();

        //Getting the Id's
        registerEmail = findViewById(R.id.editTextRegisterEmail);
        registerPass = findViewById(R.id.editTextRegisterPassword);
        registerPassConfirm = findViewById(R.id.editTextRegisterPasswordConfirm);
        registerBtn = findViewById(R.id.btnRegister);

        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                register();
            }
        });
    }

    private void register() {
        String email = registerEmail.getText().toString().trim();
        String password = registerPass.getText().toString().trim().toLowerCase();
        String passwordConfirm = registerPassConfirm.getText().toString().trim().toLowerCase();

        if(password.equals(passwordConfirm)){
            //create a user
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(Register.this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            Log.d(TAG, "user creation: Complete");

                            //progressBar.setVisibility(View.GONE);

                            if(!task.isSuccessful()) {
                                Log.d(TAG, "Creation failure." + task.getException());
                            } else {
                                startActivity(new Intent(Register.this, Login.class));
                                finish();
                            }
                        }
                    });
            Toast.makeText(getApplicationContext(), "Thank you for registering!", Toast.LENGTH_SHORT).show();
        }
        else{
            Toast.makeText(this, "Passwords don't match, please try again!", Toast.LENGTH_LONG).show();
            return;
        }


    }

}