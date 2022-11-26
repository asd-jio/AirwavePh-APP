package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelStoreOwner;

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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView register, forgot;
    private EditText logemail, logpassword;
    private Button login;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        register = (TextView) findViewById(R.id.registertext);
        register.setOnClickListener(this);

        forgot = (TextView) findViewById(R.id.forgot);
        forgot.setOnClickListener(this);

        login = (Button) findViewById(R.id.loginbutton);
        login.setOnClickListener(this);

        logemail = (EditText) findViewById(R.id.emailinput);
        logpassword = (EditText) findViewById(R.id.passwordinput);

        mAuth = FirebaseAuth.getInstance();

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registertext:
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
            logemail.setError("Pls enter you email");
            logemail.requestFocus();
            return;
        }

        if(!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            logemail.setError("pls enter a valid email address");
            logemail.requestFocus();
            return;
        }
        if(password.isEmpty()){
            logpassword.setError("pls enter your password");
            logemail.requestFocus();
            return;
        }
        if(password.length() < 6){
            logpassword.setError("must have 6 or more characters");
            logpassword.requestFocus();
            return;
        }

        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(MainActivity.this, HomePage.class));


                }else{
                    Toast.makeText(MainActivity.this, "failed to login, incorrect credentials", Toast.LENGTH_LONG).show();
                }
            }
        });

    }
}