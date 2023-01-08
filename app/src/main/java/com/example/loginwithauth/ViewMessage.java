    package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.io.File;
import java.io.IOException;


    public class ViewMessage extends AppCompatActivity {


        TextView tvTicket, tvSubject, tvMessage, tvSenderName, tvSenderNumber, tvSenderEmail, tvCategory, tvTime, tvStatus, tvResponse;
        ImageView imageView1, imageView2, imageView3;
        DatabaseReference reference;
        DatabaseReference reference1, reference2, reference3;
        private StorageReference storageReference;
        private FirebaseStorage storage = FirebaseStorage.getInstance();
        private Uri imageUri, imageUri2, imageUri3;


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_view_message);

            Intent intent = getIntent();


            String intentKey = intent.getStringExtra("keyID");
            String intentSubject = intent.getStringExtra("subjectID");
            String intentMessages = intent.getStringExtra("messageID");
            String intentName = intent.getStringExtra("nameID");
            String intentNumber = intent.getStringExtra("numberID");
            String intentEmail = intent.getStringExtra("emailID");
            String intentCategory = intent.getStringExtra("categoryID");
            String intentResponse = intent.getStringExtra("responseID");
            String intentTime = intent.getStringExtra("timeID");
            String intentImage1 = intent.getStringExtra("image1ID");
            String intentImage2 = intent.getStringExtra("image2ID");
            String intentImage3 = intent.getStringExtra("image3ID");

            System.out.println(intentImage1);
            System.out.println(intentImage2);
            System.out.println(intentImage3);


            imageView1 = (ImageView) findViewById(R.id.addImage1);
            imageView2 = (ImageView) findViewById(R.id.addImage2);
            imageView3 = (ImageView) findViewById(R.id.addImage3);


            if (intentImage1 != null) {
                storageReference = storage.getReferenceFromUrl("gs://loginwithauth-20b07.appspot.com/").child("images/" + intentImage1);
                try {
                    final File file1 = File.createTempFile("image", "jpg");
                    storageReference.getFile(file1).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap1 = BitmapFactory.decodeFile(file1.getAbsolutePath());
                            imageView1.setImageBitmap(bitmap1);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }

            if (intentImage2 != null) {
                storageReference = storage.getReferenceFromUrl("gs://loginwithauth-20b07.appspot.com/").child("images/" + intentImage2);
                try {
                    final File file2 = File.createTempFile("image", "jpg");
                    storageReference.getFile(file2).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap2 = BitmapFactory.decodeFile(file2.getAbsolutePath());
                            imageView2.setImageBitmap(bitmap2);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            if (intentImage3 != null) {
                storageReference = storage.getReferenceFromUrl("gs://loginwithauth-20b07.appspot.com/").child("images/" + intentImage3);
                try {
                    final File file3 = File.createTempFile("image", "jpg");
                    storageReference.getFile(file3).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                            Bitmap bitmap3 = BitmapFactory.decodeFile(file3.getAbsolutePath());
                            imageView3.setImageBitmap(bitmap3);

                        }
                    });
                } catch (IOException e) {
                    e.printStackTrace();
                }


                tvMessage = (TextView) findViewById(R.id.tvmessage);
                tvSubject = (TextView) findViewById(R.id.tvsubject);
                tvTicket = (TextView) findViewById(R.id.ticketNumberID);
                tvSenderName = (TextView) findViewById(R.id.name);
                tvSenderNumber = (TextView) findViewById(R.id.number);
                tvSenderEmail = (TextView) findViewById(R.id.email);
                tvCategory = (TextView) findViewById(R.id.category);
                tvResponse = (TextView) findViewById(R.id.tvresponse);
                tvTime = (TextView) findViewById(R.id.time);
                tvStatus = (TextView) findViewById(R.id.status);

                System.out.println(intentResponse);
                System.out.println(intentCategory);


                tvMessage.setText("     " + intentMessages);
                tvSubject.setText(intentSubject);
                tvTicket.setText(intentKey);
                tvSenderName.setText(intentName);
                tvSenderNumber.setText(intentNumber);
                tvSenderEmail.setText(intentEmail);
                tvCategory.setText(intentCategory);
                tvResponse.setText("            " + intentResponse);
                tvTime.setText(intentTime);


            }
        }
    }