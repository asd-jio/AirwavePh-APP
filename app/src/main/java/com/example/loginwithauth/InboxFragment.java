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

import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;


public class InboxFragment extends Fragment {
    Activity context;
    Fragment fragment;
    Intent intent;
    ArrayList<Messages> listMsgs;
    Adapter myAdapter;
    RecyclerView recyclerView;
    private DatabaseReference reference;
    private FirebaseUser user;
    private String userID;


    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        context = getActivity();
        View view = inflater.inflate(R.layout.fragment_inbox, container, false);
        return view;
    }
    public void onStart() {
        super.onStart();
        recyclerView = (RecyclerView) context.findViewById(R.id.recyclerView);
        reference = FirebaseDatabase.getInstance().getReference("Messages/ ITDept");
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));


        listMsgs = new ArrayList();
        myAdapter = new Adapter(getActivity(), listMsgs);
        recyclerView.setAdapter(myAdapter);
        reference.addValueEventListener(new ValueEventListener() {

            public void onDataChange(DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {

                    Messages msgs = dataSnapshot.getValue(Messages.class);
                    listMsgs.add(msgs);
                }
                myAdapter.notifyDataSetChanged();
            }

            public void onCancelled(DatabaseError error) {
            }
        });
    }
}