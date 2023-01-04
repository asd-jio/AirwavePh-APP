package com.example.loginwithauth;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
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
    private EditText etfullname, etcontactnumber, etaccountnumber, etemail, etpassword, etconpassword, ethousenumber, etstreet, etbarangay, ettown, etprovince;
    private FirebaseAuth mAuth;
    Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.activity_register);

        mAuth = FirebaseAuth.getInstance();

        banner = (TextView) findViewById(R.id.regtitle);
        banner.setOnClickListener(this);

        register = (Button) findViewById(R.id.regbutton);
        register.setOnClickListener(this);

        etfullname = (EditText) findViewById(R.id.regfullname);
        etcontactnumber = (EditText) findViewById(R.id.regcontactnumber);
        etaccountnumber = (EditText) findViewById(R.id.regaccountnumber);
        etemail = (EditText) findViewById(R.id.regemail);
        etpassword = (EditText) findViewById(R.id.regpassword);
        etconpassword = (EditText) findViewById(R.id.regconfirmpassword);
        ethousenumber = (EditText) findViewById(R.id.reghousenumber);
        etstreet = (EditText) findViewById(R.id.regstreet);
        etbarangay = (EditText) findViewById(R.id.regbarangay);
        ettown = (EditText) findViewById(R.id.regtown);
        etprovince = (EditText) findViewById(R.id.regprovince);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.regbutton:
                register();


                break;
        }

    }

    private void register() {

        String fname = etfullname.getText().toString().trim();
        String Cnum = etcontactnumber.getText().toString().trim();
        String Anum = etaccountnumber.getText().toString().trim();
        String email = etemail.getText().toString().trim();
        String password = etpassword.getText().toString().trim();
        String cpassword = etconpassword.getText().toString().trim();
        String Hnum = ethousenumber.getText().toString().trim();
        String street = etstreet.getText().toString().trim();
        String barangay = etbarangay.getText().toString().trim();
        String town = ettown.getText().toString().trim();
        String province = etprovince.getText().toString().trim();
        String userPlan = "";

        if (fname.isEmpty()){
            etfullname.setError("Please enter your Full Name");
            etfullname.requestFocus();
            return;
        }
        if (Cnum.isEmpty()){
            etcontactnumber.setError("Please enter your Contact Number");
            etcontactnumber.requestFocus();
            return;
        }
        if (Anum.isEmpty()){
            etaccountnumber.setError("Please enter your Account Number");
            etaccountnumber.requestFocus();
            return;
        }
        if (email.isEmpty()){
            etemail.setError("Please enter your email address");
            etemail.requestFocus();
            return;
        }
        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()){
            etemail.setError("Please enter a valid email address");
            etemail.requestFocus();
            return;
        }
        if (password.isEmpty()){
            etpassword.setError("Please enter your password");
            etpassword.requestFocus();
            return;
        }
        if (cpassword.isEmpty()){
            etconpassword.setError("Re-enter password");
            etconpassword.requestFocus();
            return;

        }
        if (!cpassword.equals(password)){
            etconpassword.setError("Passwords do not match");
            etconpassword.requestFocus();
            return;
        }
        if (cpassword.length() < 6){
            etpassword.setError("Password must contain at least 6 characters");
            etpassword.requestFocus();
            return;
        }
        if (Hnum.isEmpty()){
            ethousenumber.setError("Please enter your House Number");
            ethousenumber.requestFocus();
            return;
        }
        if (street.isEmpty()){
            etstreet.setError("Please enter your Street Address");
            etstreet.requestFocus();
            return;
        }
        if (barangay.isEmpty()){
            etbarangay.setError("Please enter your Barangay");
            etbarangay.requestFocus();
            return;
        }
        if (town.isEmpty()){
            ettown.setError("Please enter your Town/City");
            ettown.requestFocus();
            return;
        }
        if (province.isEmpty()){
            etprovince.setError("Please enter your Province");
            etprovince.requestFocus();
            return;
        }
        switch (Anum) {
            case "AW0000":
                userPlan = "Wireless 25mbps";
                break;
            case "AW0001":
                userPlan = "Wireless 10mbps";
                break;
            case "AW0002":
                userPlan = "Wired 50mbps";
                break;
            case "AW0003":
                userPlan = "Wired 15mbps";
                break;
            case "AW0004":
                userPlan = "Wired 20mbps";
                break;
        }
        switch (Anum){
            case "AW0000":
            case "AW0001":
            case "AW0002":
            case "AW0003":
            case "AW0004":
            case "AW0005":
                String plan = userPlan;
                mAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        if (task.isSuccessful()){

                            Users user = new Users(fname, email, Cnum, Anum, password, cpassword, Hnum, street, barangay, town, province, plan);

                            FirebaseDatabase.getInstance().getReference("users")
                                    .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                    .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                @Override
                                public void onComplete(@NonNull Task<Void> task) {

                                    if(task.isSuccessful()){

                                        Toast.makeText(Register.this, "Account Created Successfully", Toast.LENGTH_LONG).show();
                                        Intent intent = new Intent(context, MainActivity.class);
                                        startActivity(intent);
                                        finish();


                                    }else{
                                        Toast.makeText(Register.this, "Account failed to register. Please try again", Toast.LENGTH_LONG).show();
                                    }
                                }
                            });
                        }else{
                            Toast.makeText(Register.this, "Account failed to register. Please try again qweqwe", Toast.LENGTH_LONG).show();
                        }
                    }
                });

                break;
            default:
                etaccountnumber.setError("Please provide a valid account number");
                etaccountnumber.requestFocus();
        }








    }
}