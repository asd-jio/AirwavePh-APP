    package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;


    public class ViewMessage extends AppCompatActivity {


        TextView tvTicket, tvSubject, tvMessage, tvSenderName, tvSenderNumber, tvSenderEmail, tvCategory, tvTime, tvStatus, tvResponse;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_message);

        Intent intent = getIntent();



        String intentKey = intent.getStringExtra("keyID");
        String intentSubject = intent.getStringExtra("subjectID");
        String intentMessages = intent.getStringExtra("messageID");
        String intentName = intent.getStringExtra("nameID");
        String intentNumber = intent.getStringExtra("numberID");
        String intentEmail = intent.getStringExtra("emailID");
        String intentCategory = intent.getStringExtra("categoryID");
        String intentResponse = intent.getStringExtra("responseID");
        String intentTime = intent.getStringExtra("timeID");





        tvMessage = (TextView) findViewById(R.id.tvmessage);
        tvSubject = (TextView) findViewById(R.id.tvsubject);
        tvTicket = (TextView) findViewById(R.id.ticketNumberID);
        tvSenderName = (TextView) findViewById(R.id.name);
        tvSenderNumber = (TextView) findViewById(R.id.number);
        tvSenderEmail = (TextView) findViewById(R.id.email);
        tvCategory = (TextView) findViewById(R.id.category);
        tvResponse = (TextView) findViewById(R.id.tvresponse);
        tvTime = (TextView) findViewById(R.id.time);
        tvStatus = (TextView) findViewById(R.id.status);

        System.out.println(intentResponse);
        System.out.println(intentCategory);


        tvMessage.setText("     "+intentMessages);
        tvSubject.setText(intentSubject);
        tvTicket.setText(intentKey);
        tvSenderName.setText(intentName);
        tvSenderNumber.setText(intentNumber);
        tvSenderEmail.setText(intentEmail);
        tvCategory.setText(intentCategory);
        tvResponse.setText("            "+intentResponse);
        tvTime.setText(intentTime);


    }
}