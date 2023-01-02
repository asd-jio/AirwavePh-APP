package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class Ticketing extends AppCompatActivity implements View.OnClickListener {

    private Button itDept, accounting, technical, others;

    private ImageView logo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketing);



        itDept = (Button) findViewById(R.id.itDept);
        itDept.setOnClickListener(this);

        accounting = (Button) findViewById(R.id.accountingDept);
        accounting.setOnClickListener(this);

        technical = (Button) findViewById(R.id.techDept);
        technical.setOnClickListener(this);

        others = (Button) findViewById(R.id.others);
        others.setOnClickListener(this);

    }

    public void onClick(View v) {
        switch (v.getId()) {
            case (R.id.itDept):
                startActivity(new Intent(Ticketing.this, SubmitTicket.class));
                break;

//            case (R.id.accountingDept):
//                startActivity(new Intent(Ticketing.this, TicketAccounting.class));
//                break;
//
//            case (R.id.techDept):
//                startActivity(new Intent(Ticketing.this, TicketingTech.class));
//                break;
//
//            case (R.id.others):
//                startActivity(new Intent(Ticketing.this, OtherConcerns.class));
//                break;
        }
    }


}