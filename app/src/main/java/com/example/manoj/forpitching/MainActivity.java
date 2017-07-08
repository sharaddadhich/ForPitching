package com.example.manoj.forpitching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.manoj.forpitching.Values.TotalDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnSignup;
    static ArrayList<TotalDetails> totalDetailses = new ArrayList<TotalDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnSignup = (Button) findViewById(R.id.btn_SignUp);
        btnSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotobasic  = new Intent(MainActivity.this,BasicDetailsActivity.class);
                startActivity(gotobasic);
            }
        });

    }
}
