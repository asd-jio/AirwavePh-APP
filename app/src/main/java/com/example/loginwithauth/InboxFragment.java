package com.example.loginwithauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InboxFragment extends Fragment implements View.OnClickListener{
    Activity context;
    Intent intent;

    private Button itDept, accountDept, techDept, others;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        return view;
    }
    public void onStart() {
        super.onStart();

        itDept = (Button) context.findViewById(R.id.itDept);
        itDept.setOnClickListener(this);

        accountDept = (Button) context.findViewById(R.id.acctDept);
        accountDept.setOnClickListener(this);

        techDept = (Button) context.findViewById(R.id.techDept);
        techDept.setOnClickListener(this);

        others = (Button) context.findViewById(R.id.others);
        others.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.itDept:
                intent = new Intent(context, InboxIT.class);
                startActivity(intent);
                break;

            case R.id.acctDept:
                intent = new Intent(context, InboxAccounting.class);
                startActivity(intent);
                break;

            case R.id.techDept:
                intent = new Intent(context, InboxTech.class);
                startActivity(intent);

                break;
            case R.id.others:
                intent = new Intent(context, InboxOthers.class);
                startActivity(intent);
                break;
        }

    }
}