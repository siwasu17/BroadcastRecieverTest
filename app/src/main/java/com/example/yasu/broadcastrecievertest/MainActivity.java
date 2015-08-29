package com.example.yasu.broadcastrecievertest;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class MainActivity extends ActionBarActivity {
    BroadcastReceiver mReciever = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            TextView t = (TextView)findViewById(R.id.mainTxt);
            String action = intent.getAction();
            t.setText("STAUTS: " + action);
        }
    };



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //インテントフィルタの動的設定
        IntentFilter filter = new IntentFilter();
        filter.addAction(MyStatusService.ACTION_INIT);
        filter.addAction(MyStatusService.ACTION_RUNNING);
        filter.addAction(MyStatusService.ACTION_DOWNLOADING);
        filter.addAction(MyStatusService.ACTION_DONE);
        filter.addAction(MyStatusService.ACTION_DESTROY);
        filter.addCategory(Intent.CATEGORY_DEFAULT);

        registerReceiver(mReciever, filter);

        startService(new Intent(getApplicationContext(),MyStatusService.class));
    }

    @Override
    protected void onDestroy() {
        stopService(new Intent(getApplicationContext(), MyStatusService.class));

        unregisterReceiver(mReciever);
        super.onDestroy();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
