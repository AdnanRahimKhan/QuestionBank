package com.dbms.questionbank;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class page2 extends AppCompatActivity {
    Button b1, b2, b3, b4;
    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page2);
        getSupportActionBar().setTitle("Admin Page");
        firebaseAuth = FirebaseAuth.getInstance();

        b1 = (Button) findViewById(R.id.button);
        b2 = (Button) findViewById(R.id.button2);
        b3 = (Button) findViewById(R.id.button4);
        b4 = (Button) findViewById(R.id.button6);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent3 = new Intent(page2.this, addquestion.class);
                startActivity(intent3);
            }
        });
        b2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(page2.this, deletequestion.class);
                startActivity(intent4);
            }
        });

        b3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(page2.this, viewadminquestion.class);
                startActivity(intent5);
            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(page2.this, page1.class);
                startActivity(intent5);
            }
        });
        if (firebaseAuth.getCurrentUser() == null) {

            finish();

            startActivity(new Intent(this, student.class));
        }


        FirebaseUser user = firebaseAuth.getCurrentUser();


    }

    public void onClick(View view) {
        //if logout is pressed
        if (view == b4) {
            //logging out the user
            firebaseAuth.signOut();
            //closing activity
            finish();
            //starting login activity
            startActivity(new Intent(this, student.class));
        }
    }
}