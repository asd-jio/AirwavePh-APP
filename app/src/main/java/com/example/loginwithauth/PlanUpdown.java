package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class PlanUpdown extends AppCompatActivity {

    private Spinner locationSpinner;
    private List<String> locationList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan_updown);

        locationSpinner = (Spinner) findViewById(R.id.locationSpinner);
        locationList = new ArrayList<>();
        locationList.add("Hagonoy");
        locationList.add("Paombong");
        locationList.add("Malolos");
        locationList.add("Calumpit");

        locationSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                locationList));

    }
}