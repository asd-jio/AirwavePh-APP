package com.example.loginwithauth;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.example.loginwithauth.databinding.ActivityHomePageBinding;
import com.example.loginwithauth.databinding.ActivityMainBinding;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomePage extends AppCompatActivity {

    private Button planupdown, newsletter, request, ticket;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_home_page);

        replaceFragment(new HomeFragment());
        Log.d("MYTAG", "Fragment Initiated");

        BottomNavigationView botnav = (BottomNavigationView) findViewById(R.id.botNav);
        botnav.setOnItemSelectedListener( item -> {

            switch (item.getItemId()) {

                case R.id.botHome:
                    replaceFragment(new HomeFragment());
                    Log.d("MYTAG", "home pressed");
                    break;

                case R.id.botHelp:
                    replaceFragment(new HelpFragment());
                    Log.d("MYTAG", "help pressed");
                    break;

                case R.id.botInbox:
                    replaceFragment(new InboxFragment());
                    Log.d("MYTAG", "inbox pressed");
                    break;

                case R.id.botProfile:
                    replaceFragment(new ProfileFragment());
                    Log.d("MYTAG", "profile pressed");
                    break;

            }

            return true;
        });

    }


    private void replaceFragment(Fragment fragment){
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction =fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.frame_layout, fragment);
        fragmentTransaction.commit();
    }



}