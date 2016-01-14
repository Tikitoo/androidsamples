package me.tikitoo.androiddemo.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Random;

public class TestService extends Service {

    IBinder mIBinder = new TestBinder();
    private final Random mRandom = new Random();
    private String TAG = TestService.class.getName();

    public class TestBinder extends Binder {

        public TestService getTestService() {
            return TestService.this;
        }
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG, "onCreate");
    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId stopSelf(id) same
     * @return
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
//        stopSelf();
        Log.d(TAG, "onStartCommand");
        return super.onStartCommand(intent, flags, startId);
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.d(TAG, "onBind");
        return mIBinder;
    }

    public int getRandom() {
        return mRandom.nextInt(100);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.d(TAG, "onBind");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG, "onDestroy");
    }
}
