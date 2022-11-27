package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Newsletter extends AppCompatActivity implements View.OnClickListener {
    private Button AboutUs, ContactUs, TheDevs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newsletter);

        AboutUs = (Button) findViewById(R.id.help_btn1);
        AboutUs.setOnClickListener(this);

        ContactUs = (Button) findViewById(R.id.help_btn2);
        ContactUs.setOnClickListener(this);
        TheDevs = (Button) findViewById(R.id.help_btn3);
        TheDevs.setOnClickListener(this);
    }
    public void onClick(View v){
        switch (v.getId()) {
            case R.id.help_btn1:
                startActivity(new Intent(Newsletter.this, AboutUs.class));
                break;
        }
        switch (v.getId()) {
            case R.id.help_btn2:_btn:
                startActivity(new Intent(Newsletter.this, ContactUs.class));
                break;
        }
        switch (v.getId()) {
            case R.id.help_btn3:_btn:
                startActivity(new Intent(Newsletter.this, TheDevs.class));
                break;
        }

    }
}