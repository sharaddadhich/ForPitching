package com.example.manoj.forpitching;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import org.w3c.dom.Text;

public class AlarmActivity extends AppCompatActivity {

    TextView tvUserAlarm,tvTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_alarm);

        tvUserAlarm = (TextView) findViewById(R.id.tv_UserAlarm);
        tvTime = (TextView) findViewById(R.id.tv_time);

        Bundle details = getIntent().getBundleExtra("Details");
        tvUserAlarm.setText(details.getString("Username"));
        tvTime.setText(details.getString("Time"));


    }
}
