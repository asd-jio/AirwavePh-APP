package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class RequestSOA extends AppCompatActivity implements View.OnClickListener {


    private EditText etemailSoa, etAccountnumSoa;
    Button submitOnSoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_soa);

        etemailSoa = (EditText) findViewById(R.id.soaEmail);
        etAccountnumSoa = (EditText) findViewById(R.id.soaAccnum);
        submitOnSoa = findViewById(R.id.soaSubmit);
        submitOnSoa.setOnClickListener(this);
    }
    @Override
    public void onClick(View view) {

        switch (view.getId()) {
            case R.id.soaSubmit:
                submit();
                break;
        }
    }
    private void submit(){
        String emailsoa = etemailSoa.getText().toString().trim();
        String accountsoa = etAccountnumSoa.getText().toString().trim();

        if (emailsoa.isEmpty()){
            etemailSoa.setError("Please enter your Email Address");
            etemailSoa.requestFocus();
            return;
        }
        if (accountsoa.isEmpty()){
            etAccountnumSoa.setError("Please enter your Account number");
            etAccountnumSoa.requestFocus();
            return;
        }


            Toast.makeText(RequestSOA.this, "Your request has been received. Please wait while we process your SOA REQUEST. Thank You!", Toast.LENGTH_SHORT).show();

    }




}