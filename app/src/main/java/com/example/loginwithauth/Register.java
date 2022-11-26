package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class Register extends AppCompatActivity implements View.OnClickListener {

    private TextView banner, register;
    private EditText etfirstname, etlastname, etemail, etpassword, etconpassword;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.regtitle);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.regbutton);
        register.setOnClickListener(this);

        etfirstname = (EditText) findViewById(R.id.regfirstname);
        etlastname = (EditText) findViewById(R.id.reglastname);
        etemail = (EditText) findViewById(R.id.regemail);
        etpassword = (EditText) findViewById(R.id.regpassword);
        etconpassword = (EditText) findViewById(R.id.regconfirmpassword);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regtitle:
                startActivity(new Intent(this, MainActivity.class));
                break;

            case R.id.regbutton:
                register();
                startActivity(new Intent(this, MainActivity.class));
                break;
        }

    }

    private void register() {

        String fname = etfirstname.getText().toString().trim();
        String lname = etlastname.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String cpassword = etconpassword.getText().toString().trim();

        if (fname.isEmpty()){
            etfirstname.setError("Please enter your First Name");
            etfirstname.requestFocus();
            return;
        }
        if (lname.isEmpty()){
            etlastname.setError("Pls enter your last name");
            etlastname.requestFocus();
            return;
        }
        if (email.isEmpty()){
            etemail.setError("pls enter your email address");
            etemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("Pls enter a valid email address");
            etemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etpassword.setError("please enter your password");
            etpassword.requestFocus();
            return;
        }
        if (cpassword.isEmpty()){
            etconpassword.setError("pls re enter your password");
            etconpassword.requestFocus();
            return;

        }
        if (!cpassword.equals(password)){
            etconpassword.setError("passwords do not match");
            etconpassword.requestFocus();
            return;
        }
        if (cpassword.length() < 6){
            etpassword.setError("must contain at least 6 characters");
            etpassword.requestFocus();
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {

                if (task.isSuccessful()){
                    Users user = new Users(fname, lname, email);

                    FirebaseDatabase.getInstance().getReference("users")
                            .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                            .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                        @Override
                        public void onComplete(@NonNull Task<Void> task) {

                            if(task.isSuccessful()){
                                Toast.makeText(Register.this, "account created successfully", Toast.LENGTH_LONG).show();

                            }else{
                                Toast.makeText(Register.this, "account failed to register. pls try again", Toast.LENGTH_LONG).show();
                            }
                        }
                    });



                }else{
                    Toast.makeText(Register.this, "account failed to register. pls try again", Toast.LENGTH_LONG).show();
                }
            }
        });



    }
}