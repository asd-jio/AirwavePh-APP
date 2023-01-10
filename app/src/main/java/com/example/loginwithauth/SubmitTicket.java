package com.example.loginwithauth;

import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
import com.google.android.gms.tasks.OnSuccessListener;
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

public class SubmitTicket extends AppCompatActivity implements View.OnClickListener{

    private ImageView addImage, addImage2, addImage3;
    Button submitTicket;
    private EditText  etMainMessage;
    private FirebaseUser user;
    private String userID, date, randomID1, randomID2, randomID3;
    private TextView senderNumber, senderName, senderEmail, tvSender, imageText1, imageText2, imageText3;
    private Spinner subjectSpinner;
    private List<String>  subject;
    private Calendar calendar;
    private SimpleDateFormat dateFormat;

    private DatabaseReference reference, reference1, reference2;
    private StorageReference storageReference;
    private FirebaseStorage storage;
    private Uri imageUri, imageUri2, imageUri3;



    ActivityResultLauncher<String> mTakePhoto, mTakePhoto2, mTakePhoto3;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.ticketing_layout);

        storageReference = FirebaseStorage.getInstance().getReference("Images");
        reference = FirebaseDatabase.getInstance().getReference("users");

        user = FirebaseAuth.getInstance().getCurrentUser();
        userID = user.getUid();

        submitTicket = findViewById(R.id.submitButton);
        submitTicket.setOnClickListener(this);

        tvSender = (TextView) findViewById(R.id.dept_text);
        etMainMessage = (EditText) findViewById(R.id.mainMessage);
        senderNumber = (TextView) findViewById(R.id.senderNumber);
        senderName = (TextView) findViewById(R.id.senderName);
        senderEmail = (TextView) findViewById(R.id.senderEmail);

        progressBar = (ProgressBar)findViewById(R.id.progressBar2);
        subjectSpinner = (Spinner) findViewById(R.id.currentPlan);

        addImage = (ImageView) findViewById(R.id.addImage1);
        addImage2 = (ImageView) findViewById(R.id.addImage2);
        addImage3 = (ImageView) findViewById(R.id.addImage3);

        imageText1 = (TextView) findViewById(R.id.imageText1);
        imageText2 = (TextView) findViewById(R.id.imageText2);
        imageText3 = (TextView) findViewById(R.id.imageText3);




        storage = FirebaseStorage.getInstance();
        mTakePhoto = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {

                        if(result != null){
                            addImage.setImageURI(result);
                            imageUri = result;
                            String uriString = imageUri.toString();
                            System.out.println(uriString);
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
                        imageUri2 = result;

                    }
                }
        );

        mTakePhoto3 = registerForActivityResult(
                new ActivityResultContracts.GetContent(),
                new ActivityResultCallback<Uri>() {
                    @Override
                    public void onActivityResult(Uri result) {
                        addImage3.setImageURI(result);
                        imageUri3 = result;

                    }
                }
        );

        calendar = Calendar.getInstance();
        dateFormat = new SimpleDateFormat("MM-dd-yyyy HH:mm");
        date = dateFormat.format(calendar.getTime());
        System.out.println(date);


        progressBar.setVisibility(View.GONE);

        String connection = "Latency Issue";
        String router = "Faulty Router";
        String antenna = "Faulty Antenna";
        String wire = "Disjointed Wire";
        String receipt = "Reissue Receipt Request";
        String others = "Other Concern";


        subject = new ArrayList<>();
        subject.add("---Select Issue---");
        subject.add(connection);
        subject.add(router);
        subject.add(antenna);
        subject.add(wire);
        subject.add(receipt);
        subject.add(others);

        subjectSpinner.setAdapter(new ArrayAdapter<>(this,
                androidx.appcompat.R.layout.support_simple_spinner_dropdown_item,
                subject));





        addImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                mTakePhoto.launch("image/*");
            }
        });


        addImage2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTakePhoto2.launch("image/*");
            }
        });


        addImage3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                mTakePhoto3.launch("image/*");

            }
        });


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
        String status = "QUEUED";
        String key = reference.child("posts").push().getKey();
        String response = " ";
        String category = "";
        String time = date;
        String plan = "";
        String image1 = key+"image1";
        String image2 = key+"image2";
        String image3 = key+"image3";

//image upload

        if (imageUri != null){
            randomID1 = UUID.randomUUID().toString();
            StorageReference reference = storage.getReference().child("images/" + image1);

            reference.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    String URL = taskSnapshot.getUploadSessionUri().toString();
                    System.out.println(URL+"Hotdpg");

                }
            });
        }if (imageUri2 != null) {
            randomID2 = UUID.randomUUID().toString();
            StorageReference reference = storage.getReference().child("images/" + image2);

            reference.putFile(imageUri2).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    taskSnapshot.getUploadSessionUri().toString();
                    System.out.println(randomID2);


                }
            });
        }if (imageUri3 != null) {
            randomID3 = UUID.randomUUID().toString();
            StorageReference reference = storage.getReference().child("images/" + image3);

            reference.putFile(imageUri3).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {

                    taskSnapshot.getUploadSessionUri().toString();
                    System.out.println(randomID3);
                }
            });
        }
        else {
            //dialog.dismiss();
        }

        switch (subText){
            case "Latency Issue":
                category = "IT Department";
                break;
            case "Faulty Router":
            case "Faulty Antenna":
            case "Disjointed Wire":
                category = "Technical Department";
                break;
            case "Reissue Receipt Request":
                category = "Accounting Department";
                break;
            case "Other Concern":
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


        reference2 = FirebaseDatabase.getInstance().getReference(senderNum+"new").child("new" + key);
        reference1 = FirebaseDatabase.getInstance().getReference("New Tickets").child(category+key);
        Messages messages = new Messages(subText, msgMain, senderNum, sender, email, status, key, category, response, time, image1, image2, image3);

        reference.setValue(messages);
        reference1.setValue(messages);
        reference2.setValue(messages);



        Toast.makeText(SubmitTicket.this,"Your Ticket has been received. Please wait while we process your ticket. Thank You!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(SubmitTicket.this, HomePage.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);

    }
}