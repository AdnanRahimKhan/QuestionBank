package com.dbms.questionbank;


import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.provider.MediaStore;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class viewadminquestion extends AppCompatActivity {

    ListView qlist;
    DatabaseReference databaseQuse;
    List<quest> questList;


    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_viewadminquestion);
        databaseQuse= FirebaseDatabase.getInstance().getReference("questions");
        questList=new ArrayList<>();
        qlist=(ListView) findViewById(R.id.qlist);

        databaseQuse.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                questList.clear();
                for(DataSnapshot questsnapshot:dataSnapshot.getChildren()){
                    quest Quest=questsnapshot.getValue(quest.class);
                    questList.add(Quest);

                }
                questionlist adapter=new questionlist(viewadminquestion.this,questList);
                qlist.setAdapter(adapter);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }

}