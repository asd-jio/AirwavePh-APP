package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TicketingTech extends AppCompatActivity {

    private TextView tvsender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        tvsender = (TextView) findViewById(R.id.dept_text);

        tvsender.setText("Concerns relating to unit repairs, replacement  and the likes fall under TECHNICAL DEPARTMENT. Please fill the form below to open a new ticket.");
    }
}