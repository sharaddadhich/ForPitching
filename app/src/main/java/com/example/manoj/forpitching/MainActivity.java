package com.example.manoj.forpitching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.manoj.forpitching.Values.TotalDetails;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button btnLogin;
    TextView tvSignUp;
    static ArrayList<TotalDetails> totalDetailses = new ArrayList<TotalDetails>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvSignUp = (TextView) findViewById(R.id.tv_NewUser);
        btnLogin = (Button) findViewById(R.id.btn_MainActivityLogin);
        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin  = new Intent(MainActivity.this,LoginActivity.class);
                startActivity(gotoLogin);
            }
        });
        tvSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignup = new Intent(MainActivity.this,SignUpActivity.class);
                startActivity(gotosignup);
            }
        });


    }
}
