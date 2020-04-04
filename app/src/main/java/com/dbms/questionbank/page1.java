package com.dbms.questionbank;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;


public class page1 extends AppCompatActivity {
    Button b3, b4;
    ImageView img;
    String pi="6362";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_page1);
        getSupportActionBar().setTitle("Question Bank");




        b3 = (Button) findViewById(R.id.button1);
        b4 = (Button) findViewById(R.id.button3);
        img=(ImageView)findViewById(R.id.imageView2);
        b3.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                conad();

            }
        });
        b4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(page1.this, student.class);
                startActivity(intent2);


            }


        });
        img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  i3=new Intent(page1.this,dsce.class);
                startActivity(i3);

            }
        });


    }

    public void onBackPressed(){
        AlertDialog.Builder builder=new AlertDialog.Builder(page1.this);

        builder.setMessage("Are You Sure \nYou Want to exit ?")
                .setPositiveButton("Ok ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        page1.super.onBackPressed();
                    }
                })
                .setNegativeButton("Cancel",null);
        AlertDialog alert=builder.create();
        alert.show();
    }
    public  void conad(){

        final AlertDialog.Builder alert =new AlertDialog.Builder(page1.this);
        View mview=getLayoutInflater().inflate(R.layout.activity_confirmadmin,null);
        final EditText pin=(EditText)mview.findViewById(R.id.editText10);
        Button cancel=(Button)mview.findViewById(R.id.button12);
        Button ok=(Button)mview.findViewById(R.id.button13);
        alert.setView(mview);
        final String pin1 = pin.getText().toString().trim();
        final AlertDialog alertDialog=alert.create();
        alertDialog.setCanceledOnTouchOutside(false);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                alertDialog.dismiss();
            }
        });
        ok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

               if(TextUtils.isEmpty(pin.getText().toString())){
                    Toast.makeText(page1.this,"Please enter the pin ...",Toast.LENGTH_SHORT).show();
                    return;
                }
                if(pin.getText().toString().equals("6362")){
                    startActivity(new Intent(page1.this,admin.class));
                    return;
                }

                else if (!pin.getText().toString().equals("6362")){
                   pin.setError("This Pin is Invalid ");
                    return;
                }


            }
        });
        alertDialog.show();



    }


}