package com.dbms.questionbank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.NonNull;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import android.app.ProgressDialog;
import android.text.TextUtils;

import java.util.regex.Pattern;
public class adminsignup extends AppCompatActivity implements View.OnClickListener{
    private static final Pattern PASSWORD_PATTERN=Pattern.compile("^" +
            "(?=.*[0-9])" +
            "(?=.*[a-z])"+
            "(?=\\S+$)" +
            ".{6,}" +
            "$");

    private EditText editTextEmail;
    private EditText conpas,editTextPassword;
    private Button buttonSignup;

    private TextView textViewSignin;

    private ProgressDialog progressDialog;


    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        getSupportActionBar().setTitle("SignUp Form");


        firebaseAuth = FirebaseAuth.getInstance();


        if(firebaseAuth.getCurrentUser() != null){

            finish();


            startActivity(new Intent(getApplicationContext(), page2.class));
        }

        conpas=(EditText)findViewById(R.id.editText8);
        editTextEmail = (EditText) findViewById(R.id.editText3);
        editTextPassword = (EditText) findViewById(R.id.editText4);
        textViewSignin = (TextView) findViewById(R.id.textView7);

        buttonSignup = (Button) findViewById(R.id.button8);

        progressDialog = new ProgressDialog(this);

        buttonSignup.setOnClickListener(this);
        textViewSignin.setOnClickListener(this);
    }

    private void registerUser(){


        final String email = editTextEmail.getText().toString().trim();
        String password  = editTextPassword.getText().toString().trim();
        String conpass=conpas.getText().toString().trim();


        if(TextUtils.isEmpty(email)) {
            Toast.makeText(this, "Please enter email", Toast.LENGTH_LONG).show();
            return;
        }
        else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            editTextEmail.setError("Please enter a valid email address");
            return;

        }

        else if(TextUtils.isEmpty(password)){
            Toast.makeText(this,"Please enter password",Toast.LENGTH_LONG).show();
            return;
        }
        else if(!PASSWORD_PATTERN.matcher(password).matches()){
            editTextPassword.setError("Password too weak");
            return;
        }
        if(!password.equals(conpass)){

            conpas.setError("The Passwords are not Matching");
            Toast.makeText(adminsignup.this,"Please Enter Same Passwords ",Toast.LENGTH_SHORT);
            return;


        }



        progressDialog.setMessage("Registering Please Wait...");
        progressDialog.show();


        firebaseAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if(task.isSuccessful()){
                            finish();
                            startActivity(new Intent(getApplicationContext(), page2.class));
                        }else{
                            String message=task.getException().getMessage();
                            Toast.makeText(adminsignup.this,"Registration Error  "+message,Toast.LENGTH_LONG).show();
                        }
                        progressDialog.dismiss();
                    }
                });

    }

    @Override
    public void onClick(View view) {

        if(view == buttonSignup){
            registerUser();
        }

        if(view == textViewSignin){

            startActivity(new Intent(this, admin.class));
        }



    }

}
