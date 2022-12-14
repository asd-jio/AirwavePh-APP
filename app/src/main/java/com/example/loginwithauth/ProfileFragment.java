package com.example.loginwithauth;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.NonNull;
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

public class ProfileFragment extends Fragment implements View.OnClickListener {


    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;
    private FirebaseAuth mAuth;
    Activity context;


    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();

        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        return view;

    }

    public void onStart(){
        super.onStart();



        user = FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getUid();
        mAuth = FirebaseAuth.getInstance();
        reference = FirebaseDatabase.getInstance().getReference("users");


        final Button logout = (Button) context.findViewById(R.id.logout);
        logout.setOnClickListener(this);
        final TextView fullName = (TextView) context.findViewById(R.id.fullName);
        final TextView emailText = (TextView) context.findViewById(R.id.emailAddress);
        final TextView accNum = (TextView) context.findViewById(R.id.accountNumber);
        final TextView contactNum = (TextView) context.findViewById(R.id.contactNumber);
        final TextView fullAddress = (TextView) context.findViewById(R.id.fullAddress);
        final TextView userPlan = (TextView) context.findViewById(R.id.tvCurrentPlan);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProfile = snapshot.getValue(Users.class);
                if (userProfile != null){
                    String fName = userProfile.fname;
                    String  aNum = userProfile.Anum;
                    String email = userProfile.email;
                    String cNum = userProfile.Cnum;
                    String hNum = userProfile.Hnum;
                    String Street = userProfile.street;
                    String barangay = userProfile.barangay;
                    String town = userProfile.town;
                    String province = userProfile.province;
                    String plan = userProfile.plan;

                    emailText.setText(" " + email);
                    fullName.setText(fName);
                    accNum.setText(aNum);
                    contactNum.setText(cNum);
                    fullAddress.setText(hNum + " " + Street + " " + barangay + " " + town + " "+ province);
                    userPlan.setText(plan);
//                    firstNameText.setText(firstName);
//                    lastNameText.setText(lastName);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }



    public void onClick (View v){
        switch (v.getId()){


            case R.id.logout:{
                mAuth.signOut();
                Intent intent = new Intent(context, MainActivity.class);
                startActivity(intent);
                break;
                }
        }
    }





}