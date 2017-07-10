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
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
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
    String PhotoUrl;

    FirebaseDatabase basicFirebaseDatabase;
    DatabaseReference basicDatabaseReference;

    FirebaseStorage basicFireBaseStorage;
    StorageReference basicStorageRef;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basic_details);

        basicFirebaseDatabase = FirebaseDatabase.getInstance();
        basicDatabaseReference = basicFirebaseDatabase.getReference().child("Users");

        basicFireBaseStorage = FirebaseStorage.getInstance();
        basicStorageRef = basicFireBaseStorage.getReference().child("profile_pics");

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
                Intent intent = new Intent(Intent.ACTION_PICK,android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                intent.setType("image/*");
                startActivityForResult(intent,1);
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
                    String Name = etname.getText().toString();
                    String PhoneNo = etphoneno.getText().toString();

                    Basic user = new Basic(Name,PhoneNo,gender,PhotoUrl);

                    basicDatabaseReference.push().setValue(user);

                    Intent gotoChatActivity = new Intent(BasicDetailsActivity.this,
                            ChatActivity.class);

                    startActivity(gotoChatActivity);


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

        if(requestCode == 1)
        {
            Uri TargetUri = data.getData();
            Picasso.with(BasicDetailsActivity.this).load(TargetUri).into(profielImage);
            StorageReference stref = basicStorageRef.child(etname.getText().toString());

            stref.putFile(TargetUri).addOnSuccessListener(
                    this, new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Toast.makeText(BasicDetailsActivity.this, "Profile Photo Uploaded", Toast.LENGTH_SHORT).show();
                            PhotoUrl = taskSnapshot.getDownloadUrl().toString();
                        }

                    }
            );
        }
    }
}
