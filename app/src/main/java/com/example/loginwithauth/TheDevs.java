package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class TheDevs extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_the_devs);
    }
}