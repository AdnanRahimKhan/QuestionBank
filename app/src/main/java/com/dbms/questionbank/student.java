package com.dbms.questionbank;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Pattern;

public class student extends AppCompatActivity implements View.OnClickListener{
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])"+
            "(?=\\S+$)" +
            ".{6,}" +
            "$");


    private Button buttonSignIn;
    private EditText editTextEmail;
    private EditText editTextPassword;
    private TextView textViewSignup;
    private  TextView forgot;


    private FirebaseAuth firebaseAuth;


    private ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student);

        getSupportActionBar().setTitle("Login Page");
        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){

            finish();

            startActivity(new Intent(getApplicationContext(), Viewquestion.class));
        }


        editTextEmail = (EditText) findViewById(R.id.editText);
        editTextPassword = (EditText)  findViewById(R.id.editText2);
        buttonSignIn = (Button) findViewById(R.id.button5);
        textViewSignup  = (TextView) findViewById(R.id.textView5);
        forgot=(TextView)findViewById(R.id.textView11);

        progressDialog = new ProgressDialog(this);


        buttonSignIn.setOnClickListener(this);

        textViewSignup.setOnClickListener(this);
    }


    private void userLogin(){
        String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();



        if(TextUtils.isEmpty(email)){
            Toast.makeText(this,"Please enter email",Toast.LENGTH_LONG).show();
            return;
        }



      if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
      if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
          editTextEmail.setError("Please enter a valid email address");
          return;
    }

      if(!PASSWORD_PATTERN.matcher(password).matches()){
          editTextPassword.setError("Password too weak" );
          return;
      }



        progressDialog.setMessage(" Please Wait...");
        progressDialog.show();


        firebaseAuth.signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        progressDialog.dismiss();


                        if(task.isSuccessful()){

                            finish();
                            startActivity(new Intent(getApplicationContext(), Viewquestion.class));
                        }
                        else{
                            String message=task.getException().getMessage();
                            Toast.makeText(student.this,"Login Failed or User Not Available"+message,Toast.LENGTH_LONG).show();

                        }

                    }

                });




    }


    @Override
    public void onClick(View view) {
        if(view == buttonSignIn){
            userLogin();
        }

        if(view == textViewSignup){
            finish();
            startActivity(new Intent(this, signup.class));
        }
    }

        public void forgot(View v) {
            Intent i=new Intent(student.this,forgetpassword.class);
            startActivity(i);
        }

    }