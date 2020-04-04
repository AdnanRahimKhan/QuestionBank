package com.dbms.questionbank;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addquestion extends AppCompatActivity implements AdapterView.OnItemSelectedListener {


    EditText q;
    Button b;
    Spinner s,s1;
    DatabaseReference databaseQuse;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addquestion);
        getSupportActionBar().setTitle("Add Question");

        databaseQuse= FirebaseDatabase.getInstance().getReference("questions");
        q=(EditText)findViewById(R.id.editText7);
        b=(Button)findViewById(R.id.button9);

         s=(Spinner)findViewById(R.id.editText5);
        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.subject,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);
        s.setOnItemSelectedListener(this);

        s1=(Spinner)findViewById(R.id.editText6);
        ArrayAdapter<CharSequence> adapter1=ArrayAdapter.createFromResource(this,R.array.level,android.R.layout.simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s1.setAdapter(adapter1);
        s1.setOnItemSelectedListener(this);
        b.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(addquestion.this ,"The Question is being added please wait  ",Toast.LENGTH_SHORT).show();
                addQuestion();
            }
        });

    }
    private void addQuestion(){
        String ques=q.getText().toString().trim();
        String sub = s.getSelectedItem().toString();
        String lev =s1.getSelectedItem().toString();
        if(!TextUtils.isEmpty(ques)){
           String id= databaseQuse.push().getKey();
           quest q=new quest(id,ques,lev,sub);
           databaseQuse.child(id).setValue(q);
           Toast.makeText(this,"question Added",Toast.LENGTH_SHORT).show();
        }
        else {
            Toast.makeText(this,"Please Enter a Question ",Toast.LENGTH_SHORT).show();
        }




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

