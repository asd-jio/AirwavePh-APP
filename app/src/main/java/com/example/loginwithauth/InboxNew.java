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
    private DatabaseReference reference, userReference;
    private FirebaseUser user;
    private String userID, accountNumber;
    private TextView tvAccountNumber;
    private FirebaseAuth mAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_layout);

        user = FirebaseAuth.getInstance().getCurrentUser();

        userID = user.getUid();
        mAuth = FirebaseAuth.getInstance();
        userReference = FirebaseDatabase.getInstance().getReference("users");
        tvAccountNumber = (TextView) findViewById(R.id.number);

        userReference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                Users userProfile = snapshot.getValue(Users.class);

                if (userProfile != null){
                    String accountNumber = userProfile.Anum;
                    System.out.println(accountNumber);

                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });



        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        reference = FirebaseDatabase.getInstance().getReference("AW0004new");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));


        listMsgs = new ArrayList();
        myAdapter = new Adapter(this, listMsgs);
        recyclerView.setAdapter(myAdapter);
        reference.addValueEventListener(new ValueEventListener(){


            public void onDataChange(DataSnapshot snapshot) {


                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Messages msgs = dataSnapshot.getValue(Messages.class);
                    //if (msgs != null) {
                    listMsgs.add(msgs);
                    //}
                }
                myAdapter.notifyDataSetChanged();
            }


            public void onCancelled(DatabaseError error) {
            }
        });


    }
}