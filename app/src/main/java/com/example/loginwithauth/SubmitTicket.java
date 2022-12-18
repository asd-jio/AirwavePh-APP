package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class SubmitTicket extends AppCompatActivity implements View.OnClickListener {

    Button submitTicket;
    private EditText  etMainMessage;
    private TextView tvSender;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    private TextView senderNumber, senderName, senderEmail;
    private Spinner  Subjectspinner;
    private List<String>  subject;



    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        String connection = "Connection";
        String router = "Router";
        String others = "Others";
        String antenna = "Antenna";
        String wire = "Wire";

        Subjectspinner = (Spinner) findViewById(R.id.servicetypeSpinner);
        subject = new ArrayList<>();
        subject.add("Select Issue");
        subject.add(connection);
        subject.add(router);
        subject.add(others);
        subject.add(antenna);
        subject.add(wire);

        Subjectspinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                subject));

        etMainMessage = (EditText) findViewById(R.id.mainMessage);
        senderNumber = (TextView) findViewById(R.id.senderNumber);
        senderName = (TextView) findViewById(R.id.senderName);
        senderEmail = (TextView) findViewById(R.id.senderEmail);


        submitTicket = findViewById(R.id.submitButton);
        submitTicket.setOnClickListener(this);

        tvSender = (TextView) findViewById(R.id.dept_text);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user != null) {

                    String name = user.fname;
                    String acctNum = user.Anum;
                    String userEmail = user.email;

                    senderName.setText(name);
                    senderNumber.setText(acctNum);
                    senderEmail.setText(userEmail);
                }
            }

            public void onCancelled(DatabaseError error) {
            }
        });

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
        String subText = Subjectspinner.getSelectedItem().toString();
        String  msgMain = etMainMessage.getText().toString().trim();
        String senderNum = senderNumber.getText().toString().trim();
        String sender = senderName.getText().toString().trim();
        String email = senderEmail.getText().toString().trim();
        String status = "queued";
        String key = reference.child("posts").push().getKey();
        String category = "";
        String response = "";


        if (msgMain.isEmpty()) {
            etMainMessage.setError("Ticket content cannot be empty");
            etMainMessage.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        reference = FirebaseDatabase.getInstance().getReference().child("Delivered Tickets");

        Messages messages = new Messages(subText, msgMain, senderNum, sender, email, status, key, response, category);

        reference.push().setValue(messages);

        Toast.makeText(SubmitTicket.this,"Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SubmitTicket.this, HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}