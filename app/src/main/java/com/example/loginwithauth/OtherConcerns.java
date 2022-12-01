package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class OtherConcerns extends AppCompatActivity implements View.OnClickListener {

    Button submitTicket;
    private EditText etSubjectText, etMainMessage;
    private TextView tvSender;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;
    private TextView senderNumber, sender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        etSubjectText = (EditText) findViewById(R.id.subjectText);
        etMainMessage = (EditText) findViewById(R.id.mainMessage);
        senderNumber = (TextView) findViewById(R.id.senderNumber);
        sender = (TextView) findViewById(R.id.senderName);


        submitTicket = findViewById(R.id.submitButton);
        submitTicket.setOnClickListener(this);

        tvSender = (TextView) findViewById(R.id.dept_text);
        tvSender.setText("Other concerns not covered by the other departments fall under OTHER CONCERNS. Make sure to be as detailed as possible so we can get you the most relevant response in the most timely manner.");

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();



        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user != null) {

                    String name = user.fname;
                    String acctNum = user.Anum;

                    sender.setText(name);
                    senderNumber.setText(acctNum);
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
        String subText = etSubjectText.getText().toString().trim();
        String  msgMain = etMainMessage.getText().toString().trim();
        String senderNum = senderNumber.getText().toString().trim();
        String sender = senderNumber.getText().toString().trim();

        if (subText.isEmpty()) {
            etSubjectText.setError("Please input Subject");
            etSubjectText.requestFocus();
            return;
        }
        if (msgMain.isEmpty()) {
            etMainMessage.setError("Ticket content cannot be empty");
            etMainMessage.requestFocus();
            return;

        }
        reference = FirebaseDatabase.getInstance().getReference().child("Messages/ Others");

        Messages messages = new Messages(subText, msgMain, senderNum, sender);

        reference.push().setValue(messages);

        Toast.makeText(OtherConcerns.this,"Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
        startActivity(new Intent(this, Ticketing.class));

    }
//
}