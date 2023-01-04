package com.example.loginwithauth;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.UUID;

public class SubmitTicket extends AppCompatActivity implements View.OnClickListener {

    private ImageView addImage, addImage2, addImage3;
    Button submitTicket;
    private EditText  etMainMessage;
    private TextView tvSender;
    private DatabaseReference reference, reference1, reference2;
    private FirebaseUser user;
    private String userID;
    private TextView senderNumber, senderName, senderEmail;
    private Spinner subjectSpinner;
    private List<String>  subject;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;
    private String date;



    FirebaseStorage storage;
    Uri imageUri;



    ActivityResultLauncher<String> mTakePhoto, mTakePhoto2, mTakePhoto3;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        storage = FirebaseStorage.getInstance();
        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if(result != null){
                            addImage.setImageURI(result);
                            imageUri = result;
                        }

                    }
                }
        );

        mTakePhoto2 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        addImage2.setImageURI(result);

                    }
                }
        );

        mTakePhoto3 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        addImage3.setImageURI(result);

                    }
                }
        );

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        System.out.println(date);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        progressBar.setVisibility(View.GONE);

        String connection = "Connection";
        String router = "Router";
        String others = "Others";
        String antenna = "Antenna";
        String wire = "Wire";
        String receipt = "Receipt";


        subjectSpinner = (Spinner) findViewById(R.id.currentPlan);
        subject = new ArrayList<>();
        subject.add("Select Issue");
        subject.add(connection);
        subject.add(router);
        subject.add(others);
        subject.add(antenna);
        subject.add(wire);
        subject.add(receipt);

        subjectSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                subject));

        etMainMessage = (EditText) findViewById(R.id.mainMessage);
        senderNumber = (TextView) findViewById(R.id.senderNumber);
        senderName = (TextView) findViewById(R.id.senderName);
        senderEmail = (TextView) findViewById(R.id.senderEmail);



        addImage = (ImageView) findViewById(R.id.addimage);
        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTakePhoto.launch("image/*");
            }
        });

        addImage2 = (ImageView) findViewById(R.id.addimage2);
        addImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto2.launch("image/*");
            }
        });

        addImage3 = (ImageView) findViewById(R.id.addimage3);
        addImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mTakePhoto3.launch("image/*");

            }
        });

        submitTicket = findViewById(R.id.submitButton);
        submitTicket.setOnClickListener(this);

        tvSender = (TextView) findViewById(R.id.dept_text);

        user = FirebaseAuth.getInstance().getCurrentUser();
        reference = FirebaseDatabase.getInstance().getReference("users");
        userID = user.getUid();

        reference.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            public void onDataChange(DataSnapshot snapshot) {
                Users user = snapshot.getValue(Users.class);
                if (user != null) {

                    String name = user.fname;
                    String acctNum = user.Anum;
                    String userEmail = user.email;

                    senderName.setText(name);
                    senderNumber.setText(acctNum);
                    senderEmail.setText(userEmail);
                }
            }

            public void onCancelled(DatabaseError error) {
            }
        });



    }


    private void uploadImage() {

//        ProgressDialog dialog = new ProgressDialog(this);
//        dialog.setMessage("Uploading...");
//        dialog.show();

        if (imageUri != null){
            StorageReference reference = storage.getReference().child("images/" + UUID.randomUUID().toString());


            reference.putFile(imageUri).addOnCompleteListener(new OnCompleteListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onComplete(@NonNull Task<UploadTask.TaskSnapshot> task) {
                    if (task.isSuccessful()){

                        //dialog.dismiss();


                        Toast.makeText(SubmitTicket.this, "Image uploaded Successfully!", Toast.LENGTH_SHORT).show();
                    }
                    else {
                        //dialog.dismiss();
                        Toast.makeText(SubmitTicket.this,"Failed to Upload Image!", Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.submitButton:
                uploadImage();
                submit();
                break;
        }

    }


    private void submit() {
        String subText = subjectSpinner.getSelectedItem().toString();
        String  msgMain = etMainMessage.getText().toString().trim();
        String senderNum = senderNumber.getText().toString().trim();
        String sender = senderName.getText().toString().trim();
        String email = senderEmail.getText().toString().trim();
        String status = "queued";
        String key = reference.child("posts").push().getKey();
        String response = "";
        String category = "";
        String time = date;
        String plan = "";


        switch (subText){
            case "Connection":
                category = "IT Department";
                break;
            case "Router":
            case "Antenna":
            case "Wire":
                category = "Technical Department";
                break;
            case "Receipt":
                category = "Accounting Department";
                break;
            case "Others":
                category = "Other Concerns";
                break;

            default:
                reference = FirebaseDatabase.getInstance().getReference("Invalid Tickets").child(category + key);
                etMainMessage.setError("Please select Category!");
                etMainMessage.requestFocus();
                return;
        }


        if (msgMain.isEmpty()) {
            etMainMessage.setError("Ticket content cannot be empty");
            etMainMessage.requestFocus();
            return;

        }
        progressBar.setVisibility(View.VISIBLE);
        if (category == "Technical Department") {
            reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        }
        if (category == "IT Department") {
            reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        }
        if (category == "Other Concerns") {
            reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        }
        if (category == "Accounting Department") {
            reference = FirebaseDatabase.getInstance().getReference(category).child(category + key);
        }


        reference1 = FirebaseDatabase.getInstance().getReference("New Tickets").child(category+key);
        Messages messages = new Messages(subText, msgMain, senderNum, sender, email, status, key, category, response, time);

        reference.setValue(messages);
        reference1.setValue(messages);



        Toast.makeText(SubmitTicket.this,"Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SubmitTicket.this, HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}