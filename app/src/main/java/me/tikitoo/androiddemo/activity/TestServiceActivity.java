package me.tikitoo.androiddemo.activity;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import me.tikitoo.androiddemo.MyApp;
import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.service.TestService;

public class TestServiceActivity extends AppCompatActivity implements View.OnClickListener {
    boolean mBound =false;
    TestService mTestService;
    private String TAG = TestServiceActivity.class.getName();
    private int mMNotificationId;
    private NotificationManager mMNotifyMgr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servcie_test);
        initView();
    }

    private void initView() {
        int[] resIds = {
                R.id.btn_start, R.id.btn_stop, R.id.btn_bind, R.id.btn_unbind,
                R.id.btn_service_random, R.id.btn_controller, R.id.btn_binding
        };
        for (int i = 0; i < resIds.length; i++) {
            Button button = (Button) findViewById(resIds[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        startNotification();
        Intent serviceIntent = new Intent(this, TestService.class);
        bindService(serviceIntent, serviceConn, Context.BIND_AUTO_CREATE);
        Log.d(TAG, "bindService");

    }

    @Override
    protected void onStop() {
        super.onStop();
        if (mBound) {
            unbindService(serviceConn);
            Log.d(TAG, "unbindService");
            mBound = false;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mMNotifyMgr.cancel(mMNotificationId);

    }

    ServiceConnection serviceConn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.d(TAG, "onServiceConnected");
            TestService.TestBinder binder = (TestService.TestBinder) service;
            mTestService = binder.getTestService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.d(TAG, "onServiceDisconnected");
            mBound = false;
        }
    };

    @Override
    public void onClick(View v) {
        Intent serviceIntent = new Intent(this, TestService.class);
        switch (v.getId()) {
            case R.id.btn_start:
                startService(serviceIntent);
                break;
            case R.id.btn_stop:
                stopService(serviceIntent);
                break;
            case R.id.btn_bind:
                bindService(serviceIntent, serviceConn, Context.BIND_AUTO_CREATE);
                break;
            case R.id.btn_unbind:
                unbindService(serviceConn);
                break;
            case R.id.btn_service_random:
                int num = mTestService.getRandom();
                MyApp.showToast(this, "num" + num);
                break;
            case R.id.btn_controller:
//                Navigator.startActivity(TestServiceActivity.this, LocalServiceActivities.Controller.class);
                break;
            case R.id.btn_binding:
//                Navigator.startActivity(TestServiceActivity.this, LocalServiceActivities.Binding.class);
                break;
            default:
                break;
        }
    }

    private void startNotification() {

        Intent notifIntent = new Intent(this, CanvasActivity.class);
        PendingIntent pendingIntent
                = PendingIntent.getActivity(this, 0, notifIntent, PendingIntent.FLAG_UPDATE_CURRENT);

        Notification notificationCompat = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.ic_done_black_24dp)
                .setContentTitle("this is title")
                .setContentText("this is text")
                .setContentIntent(pendingIntent)
                .build();


        mMNotificationId = 001;
// Gets an instance of the NotificationManager service
        mMNotifyMgr = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
// Builds the notification and issues it.
        mMNotifyMgr.notify(mMNotificationId, notificationCompat);


    }
}
