package com.example.loginwithauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class HomeFragment extends Fragment implements View.OnClickListener {

    private Button planSub;
    private Button newsletter;
    private Button request;
    private Button ticket;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;

    Intent intent;

    Activity context;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_home, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = this.user.getUid();
        final TextView fullName = (TextView) this.context.findViewById(R.id.welcome1);
        reference.child(this.userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Users userProfile = (Users) snapshot.getValue(Users.class);
                if (userProfile != null) {
                    fullName.setText("Welcome: " + userProfile.fname);
                }
            }


            public void onCancelled(DatabaseError error) {


            }
        });
        planSub = (Button) context.findViewById(R.id.planSubscription);
        planSub.setOnClickListener(this);

        newsletter = (Button) context.findViewById(R.id.newsletter);
        newsletter.setOnClickListener(this);

        request = (Button) context.findViewById(R.id.request);
        request.setOnClickListener(this);

        ticket = (Button) context.findViewById(R.id.ticket);
        ticket.setOnClickListener(this);


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.planSubscription:
                intent = new Intent(context, PlanUpdown.class);
                startActivity(intent);
                break;

            case R.id.newsletter:
                intent = new Intent(context, Newsletter.class);
                startActivity(intent);
                break;

            case R.id.request:
                intent = new Intent(context, RequestSOA.class);
                startActivity(intent);
                break;

            case R.id.ticket:
                intent = new Intent(context, Ticketing.class);
                startActivity(intent);
                break;

        }

    }
}