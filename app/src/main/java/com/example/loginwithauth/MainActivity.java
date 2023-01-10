package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.messaging.FirebaseMessaging;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register;
    private EditText logemail, logpassword;
    private Button login;



    ProgressBar progressBar;
    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_main);


        FirebaseMessaging.getInstance().getToken()
                .addOnCompleteListener(new OnCompleteListener<String>() {
                    @Override
                    public void onComplete(@NonNull Task<String> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        String token = task.getResult();
                        System.out.println("token no:" + token);
                    }

                });


        progressBar = (ProgressBar)findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);
        register = (TextView) findViewById(R.id.register);
        register.setOnClickListener(this);

        login = (Button) findViewById(R.id.loginbutton);
        login.setOnClickListener(this);

        logemail = (EditText) findViewById(R.id.emailinput);
        logpassword = (EditText) findViewById(R.id.passwordinput);


        mAuth = FirebaseAuth.getInstance();


    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.register:
                startActivity(new Intent(this, Register.class));
                break;

            case R.id.loginbutton:
                userLogin();
                break;

        }
    }

    private void userLogin() {

        String email = logemail.getText().toString().trim();
        String password = logpassword.getText().toString().trim();

        if (email.isEmpty()){
            logemail.setError("Please enter you email");
            logemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            logemail.setError("Please enter a valid email address");
            logemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            logpassword.setError("Please enter your password");
            logemail.requestFocus();
            return;
        }
        if(password.length() < 6){
            logpassword.setError("Must have 6 or more characters");
            logpassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);


        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, HomePage.class));
                    finish();

                }else{
                    Toast.makeText(MainActivity.this, "Failed to login, incorrect credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}