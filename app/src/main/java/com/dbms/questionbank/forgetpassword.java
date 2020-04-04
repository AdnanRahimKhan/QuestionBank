package com.dbms.questionbank;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class forgetpassword extends AppCompatActivity {

    EditText em ;
    Button b;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forgetpassword);
        em = findViewById(R.id.editText9);
        b = findViewById(R.id.button10);
     mAuth=FirebaseAuth.getInstance();
     b.setOnClickListener(new View.OnClickListener() {
         @Override
         public void onClick(View v) {
             String email=em.getText().toString();
             if(TextUtils.isEmpty(email)){
                 Toast.makeText(forgetpassword.this,"Please enter email address...",Toast.LENGTH_SHORT).show();
             }
             if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
                 em.setError("Please enter a valid email address");
                 return;
             }

             else {

                 mAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                     @Override
                     public void onComplete(@NonNull Task<Void> task) {
                         if(task.isSuccessful()){
                             Toast.makeText(forgetpassword.this,"Please check your email if yu want to reset password ",Toast.LENGTH_SHORT).show();
                             startActivity(new Intent(forgetpassword.this,student.class));

                         }
                         else{
                             String message=task.getException().getMessage();
                             Toast.makeText(forgetpassword.this,"An Error Ocuured "+message,Toast.LENGTH_SHORT).show();

                         }
                     }
                 });
             }
         }
     });
    }
}
