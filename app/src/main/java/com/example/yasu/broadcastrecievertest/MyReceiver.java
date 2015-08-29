package com.example.yasu.broadcastrecievertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {
    public MyReceiver() {
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        Log.i("MyReciever","Something catched");
        if(Intent.ACTION_TIMEZONE_CHANGED.equals(action)){
            Log.i("MyReciever", "GET Broadcast Event @TIMEZONE_CHANGED");
            Intent itt = new Intent();
            itt.setClass(context, MyIntentService.class);
            context.startService(itt);
        }
    }
}
