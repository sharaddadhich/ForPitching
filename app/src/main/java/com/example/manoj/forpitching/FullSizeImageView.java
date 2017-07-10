package com.example.manoj.forpitching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class FullSizeImageView extends AppCompatActivity {

    TextView tvUsername;
    ImageView ivFullSize;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.full_size_photo);

        tvUsername = (TextView) findViewById(R.id.tv_fullsizedisplay);
        ivFullSize = (ImageView) findViewById(R.id.iv_fullsize);

        Bundle data = getIntent().getBundleExtra("Data");
        tvUsername.setText(data.getString("Username"));
        Picasso.with(this).load(data.getString("Url")).into(ivFullSize);


    }

}
