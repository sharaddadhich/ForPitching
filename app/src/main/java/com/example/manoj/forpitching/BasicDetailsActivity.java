package com.example.manoj.forpitching;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import com.example.manoj.forpitching.Values.Basic;
import com.squareup.picasso.Picasso;

import java.io.FileNotFoundException;

public class BasicDetailsActivity extends AppCompatActivity {
    EditText etname,etphoneno;
    RadioGroup rggender;
    RadioButton rbmale,rbfemlaae;
    Button btnPersonal;
    String gender;
    Button btnGalleryUpload;
    ImageView profielImage;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        profielImage  = (ImageView) findViewById(R.id.iv_profileImage);
        btnGalleryUpload = (Button) findViewById(R.id.btn_UploadFromGallery);
        etname = (EditText) findViewById(R.id.et_name);
        etphoneno = (EditText) findViewById(R.id.et_phoneno);

        rggender = (RadioGroup) findViewById(R.id.rg_Gender);
        rbmale = (RadioButton) findViewById(R.id.rb_Male);
        rbfemlaae = (RadioButton) findViewById(R.id.rb_Female);
        btnPersonal = (Button) findViewById(R.id.btn_forbasic);

        btnGalleryUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent importGallery= new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(importGallery,0);
            }
        });



        btnPersonal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int genderselected = rggender.getCheckedRadioButtonId();
                if(genderselected!=-1)
                {
                    RadioButton radioButton = (RadioButton) findViewById(genderselected);
                    gender = radioButton.getText().toString();
                    Bundle basic = new Bundle();
                    basic.putString("Name",etname.getText().toString());
                    basic.putString("Gender",gender);
                    basic.putString("PhoneNo",String.valueOf(etphoneno.getText()));
                    Intent gotoPersonal = new Intent(BasicDetailsActivity.this,PersonalDetailsActivity.class);
                    gotoPersonal.putExtra("Basic",basic);
                    startActivity(gotoPersonal);

                }
                else
                {
                    Toast.makeText(BasicDetailsActivity.this, "No Gender Selected", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if(requestCode == RESULT_OK)
        {
            Uri TargetUri = data.getData();
            Bitmap bitmap;
            try {
                bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(TargetUri));
                profielImage.setImageBitmap(bitmap);
                Picasso.with(BasicDetailsActivity.this).load(TargetUri).into(profielImage);

            } catch (FileNotFoundException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }
}
