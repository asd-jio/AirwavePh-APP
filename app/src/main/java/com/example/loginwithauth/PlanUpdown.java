package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
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

public class PlanUpdown extends AppCompatActivity implements View.OnClickListener {

    private Spinner servicetypespinner, newplanSpinner;
    private List<String> servicetype, newPlan;
    private FirebaseUser user;
    private String userID;
    private TextView currentPlan, location, senderNumber, senderName, senderEmail;
    private DatabaseReference reference, reference1;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;



    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_updown);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        System.out.println(date);

        submit = findViewById(R.id.submitonplan);
        currentPlan = findViewById(R.id.currentPlan);
        location = findViewById(R.id.location);

        senderNumber = (TextView) findViewById(R.id.senderNumber);
        senderName = (TextView) findViewById(R.id.senderName);
        senderEmail = (TextView) findViewById(R.id.senderEmail);

        submit.setOnClickListener(this);


        servicetypespinner = (Spinner) findViewById(R.id.servicetypeSpinner);
        servicetype = new ArrayList<>();
        servicetype.add("---Select your New Service Type---");
        servicetype.add("Wired");
        servicetype.add("Wireless");


        newplanSpinner = (Spinner) findViewById(R.id.newplan);
        newPlan = new ArrayList<>();
        newPlan.add("---Select New Plan---");
        newPlan.add("8Mbps = 800 PHP");
        newPlan.add("10Mbps = 1,000 PHP");
        newPlan.add("15Mbps = 1,200 PHP");
        newPlan.add("20Mbps = 1,400 PHP");
        newPlan.add("25Mbps = 1,700 PHP");


        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user != null) {

                    String plan = user.plan;
                    String town = user.town;
                    String name = user.fname;
                    String acctNum = user.Anum;
                    String userEmail = user.email;

                    senderName.setText(name);
                    senderNumber.setText(acctNum);
                    senderEmail.setText(userEmail);
                    currentPlan.setText("Current Plan: "+plan);
                    location.setText("Location: "+town);

                }
            }

            public void onCancelled(DatabaseError error) {
            }
        });




        servicetypespinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                servicetype));


        newplanSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                newPlan));
    }

    @Override
    public void onClick(View view) {
        submit();
        Intent intent = new Intent(this, HomeFragment.class);
        startActivity(intent);
        finish();

    }

    private void submit() {
        String subText = "Plan Update";
        String senderNum = senderNumber.getText().toString().trim();
        String newService = servicetypespinner.getSelectedItem().toString();
        String newSpeed = newplanSpinner.getSelectedItem().toString();
        String newPlan = newService+" "+ newSpeed;
        String current = currentPlan.getText().toString();
        String sender = senderName.getText().toString().trim();
        String msgMain = "Customer "+ senderNum +" "+ sender + " is requesting for plan update from "+ current +" to "+ newPlan;
        String email = senderEmail.getText().toString().trim();
        String status = "queued";
        String key = reference.child("posts").push().getKey();
        String response = "";
        String category = "IT Department";
        String time = date;

        reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        reference1 = FirebaseDatabase.getInstance().getReference("New Tickets").child(category+key);
        Messages messages = new Messages(subText, msgMain, senderNum, sender, email, status, key, category, response, time);

        reference.setValue(messages);
        reference1.setValue(messages);
        Toast.makeText(PlanUpdown.this,"Your request has been received. Please wait while we process your NEW PLAN. Thank You!", Toast.LENGTH_SHORT).show();

    }
}