package com.dbms.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class deletequestion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    Spinner s,s1;
    Button b;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_deletequestion);
        getSupportActionBar().setTitle("Delete Question ");
        s=(Spinner)findViewById(R.id.spinner);
        s1=(Spinner)findViewById(R.id.spinner2);
        b=(Button)findViewById(R.id.button11);
        final DatabaseReference dbref= FirebaseDatabase.getInstance().getReference();
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.subject,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);

        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.level,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(deletequestion.this,"Question is Getting deleted \n Please Wait.... ",Toast.LENGTH_SHORT).show();

            }
        });
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        String text=parent.getItemAtPosition(position).toString();
        Toast.makeText(parent.getContext(),text,Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
}
