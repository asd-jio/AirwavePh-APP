package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InboxNew extends AppCompatActivity {

    ArrayList<Messages> listMsgs;
    Adapter myAdapter;
    RecyclerView recyclerView;
    private FirebaseUser user;
    private DatabaseReference reference;
    private String userID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_layout);

        user = FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getEmail();
        ;
        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        listMsgs = new ArrayList();
        myAdapter = new Adapter(this, listMsgs);
        recyclerView.setAdapter(myAdapter);

        switch (userID) {
            case ("areniegojanjonelle19@gmail.com"):
                reference = FirebaseDatabase.getInstance().getReference("AW0001new");
                reference.addValueEventListener(new ValueEventListener() {


                    public void onDataChange(DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            // Users userProfile = snapshot.getValue(Users.class);
                            //if (msgs != null) {

                            listMsgs.add(msgs);
                            //   }

                        }
                        myAdapter.notifyDataSetChanged();
                    }


                    public void onCancelled(DatabaseError error) {
                    }
                });
                break;
            case ("gremember1@gmail.com"):
                reference = FirebaseDatabase.getInstance().getReference("AW0002new");
                reference.addValueEventListener(new ValueEventListener() {


                    public void onDataChange(DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            // Users userProfile = snapshot.getValue(Users.class);
                            //if (msgs != null) {

                            listMsgs.add(msgs);
                            //   }

                        }
                        myAdapter.notifyDataSetChanged();
                    }


                    public void onCancelled(DatabaseError error) {
                    }
                });
                break;
            case ("mikemanalo@gmail.com"):
                reference = FirebaseDatabase.getInstance().getReference("AW0003new");
                reference.addValueEventListener(new ValueEventListener() {


                    public void onDataChange(DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            // Users userProfile = snapshot.getValue(Users.class);
                            //if (msgs != null) {

                            listMsgs.add(msgs);
                            //   }

                        }
                        myAdapter.notifyDataSetChanged();
                    }


                    public void onCancelled(DatabaseError error) {
                    }
                });
                break;
            case("jamesmarkabsalon@gmail.com"):
                reference = FirebaseDatabase.getInstance().getReference("AW0004new");
                reference.addValueEventListener(new ValueEventListener() {


                    public void onDataChange(DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            // Users userProfile = snapshot.getValue(Users.class);
                            //if (msgs != null) {

                            listMsgs.add(msgs);
                            //   }

                        }
                        myAdapter.notifyDataSetChanged();
                    }


                    public void onCancelled(DatabaseError error) {
                    }
                });
                break;

            case("bsit4b@gmail.com"):
                reference = FirebaseDatabase.getInstance().getReference("AW0005new");
                reference.addValueEventListener(new ValueEventListener() {


                    public void onDataChange(DataSnapshot snapshot) {


                        for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                            Messages msgs = dataSnapshot.getValue(Messages.class);
                            // Users userProfile = snapshot.getValue(Users.class);
                            //if (msgs != null) {

                            listMsgs.add(msgs);
                            //   }

                        }
                        myAdapter.notifyDataSetChanged();
                    }


                    public void onCancelled(DatabaseError error) {
                    }
                });
                break;

        }

    }
}