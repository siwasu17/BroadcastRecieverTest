package com.example.yasu.broadcastrecievertest;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.util.Log;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p/>
 * TODO: Customize class - update intent actions, extra parameters and static
 * helper methods.
 */
public class MyIntentService extends IntentService {

    public MyIntentService() {
        super("MyIntentService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            Log.i("MyService","Start!");
            try {
                Thread.sleep(5000);
            }catch(Exception e){
                e.printStackTrace();
            }
            Log.i("MyService","Complete!");
        }
    }

}
