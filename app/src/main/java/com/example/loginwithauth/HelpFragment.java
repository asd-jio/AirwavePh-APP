package com.example.loginwithauth;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class HelpFragment extends Fragment implements View.OnClickListener {

    private Button btn1, btn2, btn3;



    Intent intent;
    Activity context;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,Bundle savedInstanceState) {

        context = getActivity();
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_help, container, false);

        return view;
    }

    @Override
    public void onStart() {
        super.onStart();



        btn1 = (Button) context.findViewById(R.id.help_btn1);
        btn1.setOnClickListener(this);

        btn2 = (Button) context.findViewById(R.id.help_btn2);
        btn2.setOnClickListener(this);

        btn3 = (Button) context.findViewById(R.id.help_btn3);
        btn3.setOnClickListener(this);

    }
    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.help_btn1:
                intent = new Intent(context, help1.class);
                startActivity(intent);
                break;

            case R.id.help_btn2:
                intent = new Intent(context, help2.class);
                startActivity(intent);
                break;

            case R.id.help_btn3:elp:
                intent = new Intent(context, help3.class);
                startActivity(intent);
                break;


        }
    }


}