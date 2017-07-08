package com.example.manoj.forpitching;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.manoj.forpitching.Interface.OnReachingChat;
import com.example.manoj.forpitching.Values.TotalDetails;

import java.util.ArrayList;

public class DisplayMyDetailsActivity extends AppCompatActivity {
    final static Integer id= 0;


    public static final String TAG = "DisplayDetails";
    TextView tvDisplaayName;
    Button btnGotoSgChat;
    OnReachingChat onReachingChat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_my_details);
        Log.d(TAG, "onCreate: in Display details");
        tvDisplaayName = (TextView) findViewById(R.id.tv_DisplayName);
        btnGotoSgChat = (Button) findViewById(R.id.btn_gotosgchat);
        Toast.makeText(this, "SignUp Sucessful!", Toast.LENGTH_SHORT).show();
        final Bundle Display = getIntent().getBundleExtra("TotalDetails");
        String name= Display.getString("Name");
        Log.d(TAG, "onCreate: "+name);
        tvDisplaayName.setText(name);

        btnGotoSgChat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.d(TAG, "onClick: buttonClicked");
                Intent gotochats = new Intent(DisplayMyDetailsActivity.this,ChatActivity.class);

                MainActivity.totalDetailses.add(new TotalDetails(id+11,Display.getString("Name"),Display.getString("PhoneNo"),
                        Display.getString("Email"),Display.getString("Address"),Display.getString("Fnmae"),
                        Display.getString("Mname"),Display.getString("Gender")));
              //  onReachingChat.reachedtochatsActivity(MainActivity.totalDetailses);

                startActivity(gotochats);

            }
        });
    }
}
