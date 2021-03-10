package com.eduardogeiselperes.efinance;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Welcome extends AppCompatActivity {

    private FirebaseUser user;
    DatabaseReference reference;
    private TextView txtViewWelcome;
    private Button btnLogout, btnNewFinancing;

    //Recycler view
    RecyclerView recyclerView;
    ArrayList<Financing> list;
    WelcomeAdapter welcomeAdapter;

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
        String userEmail2 = user.getEmail().replace(".", "&");
        String userEmail = user.getEmail();

        //greeting logged in user
        txtViewWelcome.setText(userEmail);

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
                startActivity(new Intent(Welcome.this, CreateFinancing.class));
            }
        });

        //recycler view
        recyclerView = findViewById(R.id.recyclerViewWelcome);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        list = new ArrayList<Financing>();

        //getting data from firebase
        reference = FirebaseDatabase.getInstance().getReference().child("Financing").child("userEmail: " + userEmail2);
        reference.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                list.clear();

                for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                    Financing financing = dataSnapshot.getValue(Financing.class);
                    list.add(financing);
                }

                welcomeAdapter = new WelcomeAdapter(Welcome.this, list);
                recyclerView.refreshDrawableState();
                recyclerView.setAdapter(welcomeAdapter);
                welcomeAdapter.notifyDataSetChanged();

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getApplicationContext(), "No Data", Toast.LENGTH_SHORT).show();
            }
        });


    }
}