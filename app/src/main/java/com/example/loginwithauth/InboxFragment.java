package com.example.loginwithauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;


public class InboxFragment extends Fragment implements View.OnClickListener{
    Activity context;
    Intent intent;
    ArrayList<Messages> listMsgs;
    Adapter myAdapter;
    RecyclerView recyclerView;
    private DatabaseReference reference;

    private Button itDept, accountDept, techDept, others;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        return view;
    }
    public void onStart() {
        super.onStart();

        itDept = (Button) context.findViewById(R.id.newTickets);
        itDept.setOnClickListener(this);

        accountDept = (Button) context.findViewById(R.id.pendingTickets);
        accountDept.setOnClickListener(this);

        techDept = (Button) context.findViewById(R.id.completedTickets);
        techDept.setOnClickListener(this);



    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

            case R.id.newTickets:
                intent = new Intent(context, InboxNew.class);
                startActivity(intent);
                break;

            case R.id.pendingTickets:
                intent = new Intent(context, InboxPending.class);
                startActivity(intent);
                break;

            case R.id.completedTickets:
                intent = new Intent(context, InboxCompleted.class);
                startActivity(intent);

                break;
        }

    }
}