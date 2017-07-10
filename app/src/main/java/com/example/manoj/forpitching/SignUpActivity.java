package com.example.manoj.forpitching;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class SignUpActivity extends AppCompatActivity {

    EditText etemail;
    EditText etPass;
    Button btnSignUp;
    TextView tvLogin;

    private FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        firebaseAuth = FirebaseAuth.getInstance();

        etemail = (EditText) findViewById(R.id.et_SignUpEmail);
        etPass = (EditText) findViewById(R.id.et_SignUpPassword);
        btnSignUp = (Button) findViewById(R.id.btn_SignUp);
        tvLogin = (TextView) findViewById(R.id.tv_userLogininSignup);

        tvLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotoLogin = new Intent(SignUpActivity.this,LoginActivity.class);
                startActivity(gotoLogin);
            }
        });

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclicked();

            }
        });

    }
    private void btnclicked()
    {
        String email = etemail.getText().toString().trim();
        String pass = etPass.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //no email enterd so error
            Toast.makeText(SignUpActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass))
        {
            //no pass entered
            Toast.makeText(SignUpActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.createUserWithEmailAndPassword(etemail.getText().toString(),etPass.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //user is sucessfully registerd
                    Intent gotoBasic = new Intent(SignUpActivity.this,BasicDetailsActivity.class);
                    startActivity(gotoBasic);

                }
            }
        });


    }
}
