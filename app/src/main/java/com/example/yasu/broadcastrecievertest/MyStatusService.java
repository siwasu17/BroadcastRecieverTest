package com.example.yasu.broadcastrecievertest;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;


//ブロードキャストインテントを使って、アクティビティに状態を通知するサービス
public class MyStatusService extends Service {
    public static final String ACTION_INIT = "INIT";
    public static final String ACTION_RUNNING = "RUNNING";
    public static final String ACTION_DOWNLOADING = "DONWLOADING";
    public static final String ACTION_DONE = "DONE";
    public static final String ACTION_DESTROY = "DESTROY";


    public enum MyServiceState{
        INIT,
        RUNNING,
        DOWNLOADING,
        DONE,
        DESTROY,
    }

    public MyStatusService() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i("MyStatusService", "StartCommand");
        //UIスレッド止めないようにしているので、一瞬で終わってしまうが、一応ブロードキャストインテントは遅れているぽい
        int rand = (int) (Math.floor(Math.random() * 5) + 1);
        switch(rand){
            case 1:
                postBroadcast(MyServiceState.INIT);
                break;
            case 2:
                postBroadcast(MyServiceState.RUNNING);
                break;
            case 3:
                postBroadcast(MyServiceState.DOWNLOADING);
                break;
            case 4:
                postBroadcast(MyServiceState.DONE);
                break;
            case 5:
                postBroadcast(MyServiceState.DESTROY);
                break;
        }

        return super.onStartCommand(intent, flags, startId);
    }

    private void postBroadcast(MyServiceState state){
        Intent broadcast = new Intent();
        switch(state){
            case INIT:
                broadcast.setAction(ACTION_INIT);
                break;
            case RUNNING:
                broadcast.setAction(ACTION_RUNNING);
                break;
            case DOWNLOADING:
                broadcast.setAction(ACTION_DOWNLOADING);
                break;
            case DONE:
                broadcast.setAction(ACTION_DONE);
                break;
            case DESTROY:
                broadcast.setAction(ACTION_DESTROY);
                break;
            default:
                broadcast = null;
                break;
        }

        if(broadcast != null){
            sendBroadcast(broadcast);
        }
    }

}
