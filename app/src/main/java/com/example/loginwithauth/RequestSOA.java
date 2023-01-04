package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
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

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class RequestSOA extends AppCompatActivity implements View.OnClickListener {


    private TextView senderNumber, senderName, senderEmail, MainMessage;
    private DatabaseReference reference, reference1;
    private FirebaseUser user;
    private String userID;

    private TextView  mainMessage;
    private Spinner subjectSpinner;
    private List<String> subject;

    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;
    Button submitOnSoa;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request_soa);

        MainMessage = (TextView) findViewById(R.id.mainMessage);
        senderEmail = (TextView) findViewById(R.id.soaEmail);
        senderNumber = (TextView) findViewById(R.id.soaAccnum);
        senderName = (TextView) findViewById(R.id.senderName);

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        System.out.println(date);

        String receipt = "Request SOA";

        subjectSpinner = (Spinner) findViewById(R.id.currentPlan);
        subject = new ArrayList<>();
        subject.add(receipt);

        subjectSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                subject));


        submitOnSoa = findViewById(R.id.soaSubmit);
        submitOnSoa.setOnClickListener(this);
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
            case R.id.soaSubmit:
                submit();
                break;
        }
    }
    private void submit(){

        String subText = subjectSpinner.getSelectedItem().toString();
        String  msgMain = MainMessage.getText().toString().trim();

        String senderNum = senderNumber.getText().toString().trim();
        String sender = senderName.getText().toString().trim();
        String email = senderEmail.getText().toString().trim();
        String status = "queued";
        String key = reference.child("posts").push().getKey();
        String response = "";
        String category = "";
        String time = date;
        String plan = "";

        switch (subText){

            case "Request SOA":
                category = "Accounting Department";
        }
        if (category == "Accounting Department") {
            reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        }

        //reference = FirebaseDatabase.getInstance().getReference("Technical Department");

        reference1 = FirebaseDatabase.getInstance().getReference("New Tickets").child(category+key);
        Messages messages = new Messages(subText, msgMain, senderNum, sender, email, status, key, category, response, time);

        reference.setValue(messages);
        reference1.setValue(messages);


//        if (emailsoa.isEmpty()){
//            emailSoa.setError("Please enter your Email Address");
//            emailSoa.requestFocus();
//            return;
//        }
//        if (accountsoa.isEmpty()){
//            AccountnumSoa.setError("Please enter your Account number");
//            AccountnumSoa.requestFocus();
//            return;
//        }


            Toast.makeText(RequestSOA.this, "Your request has been received. Please wait while we process your SOA REQUEST. Thank You!", Toast.LENGTH_SHORT).show();

    }




}