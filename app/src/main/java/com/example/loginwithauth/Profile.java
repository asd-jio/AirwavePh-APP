package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {

    private Button logout;
    private FirebaseUser user;
    private DatabaseReference reference;

    private String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        logout = (Button) findViewById(R.id.logoutbutton);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, MainActivity.class));
            }
        });

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        final TextView welcomeText = (TextView) findViewById(R.id.welcome);
        final TextView emailText = (TextView) findViewById(R.id.emailDisplay);
        final TextView firstNameText = (TextView) findViewById(R.id.firstNameDisplay);
        final TextView lastNameText = (TextView) findViewById(R.id.lastnameDisplay);

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                Users userProfile = snapshot.getValue(Users.class);

                if(userProfile != null){
                    String email = userProfile.email;
                    String firstName = userProfile.firstname;
                    String lastName = userProfile.lastname;

                    welcomeText.setText("Welcome, " + firstName + "!");
                    emailText.setText(email);
                    firstNameText.setText(firstName);
                    lastNameText.setText(lastName);


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(Profile.this, "Error", Toast.LENGTH_LONG).show();
            }
        });

    }
}