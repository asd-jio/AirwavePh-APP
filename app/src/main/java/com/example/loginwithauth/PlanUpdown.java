package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class PlanUpdown extends AppCompatActivity implements View.OnClickListener {

    private Spinner locationSpinner, servicetypespinner, currentplanSpinner, newplanSpinner;
    private List<String> locationList, servicetype, currentPlan, newPlan;

    Button submit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_updown);

        submit = findViewById(R.id.submitonplan);
        submit.setOnClickListener(this);

        servicetypespinner = (Spinner) findViewById(R.id.servicetypeSpinner);
        servicetype = new ArrayList<>();
        servicetype.add("Select Service type");
        servicetype.add("Wired");
        servicetype.add("Wireless");

        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        locationList = new ArrayList<>();
        locationList.add("Select your Location");
        locationList.add("Hagonoy");
        locationList.add("Paombong");
        locationList.add("Malolos");
        locationList.add("Calumpit");

        currentplanSpinner = (Spinner) findViewById(R.id.currentplan);
        currentPlan = new ArrayList<>();
        currentPlan.add("Select your Current Plan");
        currentPlan.add("8Mbps");
        currentPlan.add("10Mbps");
        currentPlan.add("15Mbps");
        currentPlan.add("20Mbps");
        currentPlan.add("25Mbps");

        newplanSpinner = (Spinner) findViewById(R.id.newplan);
        newPlan = new ArrayList<>();
        newPlan.add("Select New Plan");
        newPlan.add("8Mbps");
        newPlan.add("10Mbps");
        newPlan.add("15Mbps");
        newPlan.add("20Mbps");
        newPlan.add("25Mbps");




        servicetypespinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                servicetype));

        locationSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                locationList));

        currentplanSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                currentPlan));

        newplanSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                newPlan));
    }

    @Override
    public void onClick(View view) {
        Toast.makeText(PlanUpdown.this,"Your request has been received. Please wait while we process your NEW PLAN. Thank You!", Toast.LENGTH_SHORT).show();
    }
}