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

import com.google.android.gms.auth.api.signin.SignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity {


    private FirebaseAuth firebaseAuth;
    Button btnLogin;
    EditText etemaillogin,etpasslogin;
    TextView tvSignupmessage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        firebaseAuth = FirebaseAuth.getInstance();
        btnLogin = (Button) findViewById(R.id.btn_Login);
        etemaillogin = (EditText) findViewById(R.id.et_LoginEmail);
        etpasslogin = (EditText) findViewById(R.id.et_LoginPassword);
        tvSignupmessage = (TextView) findViewById(R.id.tv_newUserSignupinLogin);

        tvSignupmessage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gotosignup = new Intent(LoginActivity.this, SignUpActivity.class);
                startActivity(gotosignup);
            }
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnclicked();
            }
        });

    }
    private void btnclicked()
    {
        String email = etemaillogin.getText().toString().trim();
        String pass = etpasslogin.getText().toString().trim();

        if(TextUtils.isEmpty(email))
        {
            //no email enterd so error
            Toast.makeText(LoginActivity.this, "Please enter Email", Toast.LENGTH_SHORT).show();
            return;
        }

        if(TextUtils.isEmpty(pass))
        {
            //no pass entered
            Toast.makeText(LoginActivity.this, "Please Enter Your Password", Toast.LENGTH_SHORT).show();
            return;
        }
        firebaseAuth.signInWithEmailAndPassword(etemaillogin.getText().toString(),etpasslogin.getText().toString()).addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if(task.isSuccessful())
                {
                    //user is sucessfully registerd

                }
            }
        });


    }
}
