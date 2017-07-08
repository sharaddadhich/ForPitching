package com.example.manoj.forpitching;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.manoj.forpitching.Values.TotalDetails;

import java.util.ArrayList;

public class PersonalDetailsActivity extends AppCompatActivity {


    EditText etaddress,etemail,etfname,etmname;
    Button btnfinish;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_personal_details);
        etaddress = (EditText) findViewById(R.id.et_address);
        etemail = (EditText) findViewById(R.id.et_email);
        etfname = (EditText) findViewById(R.id.et_fname);
        etmname= (EditText) findViewById(R.id.et_mname);
        btnfinish = (Button) findViewById(R.id.btn_forpersonal);

         final Bundle basicdetails=  getIntent().getBundleExtra("Basic");

        btnfinish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String Name,PhoneNo,Gender,Mail,Fname,Mname,Address;
                Name = basicdetails.getString("Name","Name");
                PhoneNo = basicdetails.getString("PhoneNo","00");
                Gender =  basicdetails.getString("Gender","Gender");
                Mail = etemail.getText().toString();
                Fname = etfname.getText().toString();
                Mname = etmname.getText().toString();
                Address = etaddress.getText().toString();
                Bundle personal = new Bundle();
                personal.putString("Address",Address);
                personal.putString("Email",Mail);
                personal.putString("Fname",Fname);
                personal.putString("Mname",Mname);
                personal.putString("Name",Name);
                personal.putString("PhoneNo",PhoneNo);
                personal.putString("Gender",Gender);
                Log.d("ad", "onClick: "+Name +PhoneNo+Mail+Address+Fname+Mname+Gender);



                Intent gotoDetails = new Intent(PersonalDetailsActivity.this,DisplayMyDetailsActivity.class);

                gotoDetails.putExtra("TotalDetails",personal);

                startActivity(gotoDetails);

            }
        });

    }
}
