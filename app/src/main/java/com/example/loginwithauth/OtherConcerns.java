package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class OtherConcerns extends AppCompatActivity implements View.OnClickListener {

    Button submitOthers;
    private EditText etsubjecttext, etmainmassage;
    private TextView tvsender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        etsubjecttext = (EditText) findViewById(R.id.subjectText);
        etmainmassage = (EditText) findViewById(R.id.mainMessage);

        submitOthers = findViewById(R.id.submitButton);
        submitOthers.setOnClickListener(this);

        tvsender = (TextView) findViewById(R.id.dept_text);

        tvsender.setText("Other concerns not covered by the other departments fall under OTHER CONCERNS. Make sure to be as detailed as possible so we can get you the most relevant response in the most timely manner.");
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
            etsubjecttext.setError("Please input subject");
            etsubjecttext.requestFocus();
            return;
        }
        if (accountsoa.isEmpty()) {
            etmainmassage.setError("Ticket content cannot be empty");
            etmainmassage.requestFocus();
            return;

        }
        Toast.makeText(OtherConcerns.this, "Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
    }
}