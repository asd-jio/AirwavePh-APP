package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class TicketingIT extends AppCompatActivity {

    private TextView tvsender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

       tvsender = (TextView) findViewById(R.id.dept_text);

       tvsender.setText("Concerns relating to network contingencies and the likes fall under IT DEPARTMENT. Please fill the form below to open a new ticket.");
    }
}