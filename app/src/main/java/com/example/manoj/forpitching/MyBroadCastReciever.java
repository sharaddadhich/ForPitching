package com.example.manoj.forpitching;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by Manoj on 7/8/2017.
 */

public class MyBroadCastReciever extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if(intent.getAction().equals(Intent.ACTION_DREAMING_STOPPED))
        {

        }
    }
}
