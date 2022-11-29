package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TicketAccounting extends AppCompatActivity implements View.OnClickListener {

    Button submitAccountingTicket;
    private EditText etsubjecttext, etmainmassage;
    private TextView tvsender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        etsubjecttext = (EditText) findViewById(R.id.subjectText);
        etmainmassage = (EditText) findViewById(R.id.mainMessage);

        submitAccountingTicket = findViewById(R.id.submitButton);
        submitAccountingTicket.setOnClickListener(this);

        tvsender = (TextView) findViewById(R.id.dept_text);

        tvsender.setText("Concerns relating to billing statements and the likes fall under ACCOUNTING DEPARTMENT. Make sure to be as detailed as possible so we can get you the most relevant response in the most timely manner.");

    }
    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                submit();
                break;
        }

    }
    private void submit() {
        String emailsoa = etsubjecttext.getText().toString().trim();
        String accountsoa = etmainmassage.getText().toString().trim();

        if (emailsoa.isEmpty()) {
            etsubjecttext.setError("Please input Subject");
            etsubjecttext.requestFocus();
            return;
        }
        if (accountsoa.isEmpty()) {
            etmainmassage.setError("Ticket content cannot be empty");
            etmainmassage.requestFocus();
            return;

        }
        Toast.makeText(TicketAccounting.this,"Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
    }

}