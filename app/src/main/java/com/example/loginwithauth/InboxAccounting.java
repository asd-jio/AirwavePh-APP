package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class InboxAccounting extends AppCompatActivity {

    ArrayList<Messages> listMsgs;
    Adapter myAdapter;
    RecyclerView recyclerView;
    private DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inbox_layout);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        reference = FirebaseDatabase.getInstance().getReference("Messages/ AccountingDept");
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