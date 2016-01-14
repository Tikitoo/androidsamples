package me.tikitoo.androiddemo;

import android.app.Application;
import android.content.Context;
import android.widget.Toast;

public class MyApp extends Application {
    static Context mContext;
    @Override
    public void onCreate() {
        super.onCreate();
        mContext = getApplicationContext();
    }
    public static void showToast(Context context, int resId) {
        showToast(context, context.getString(resId));
    }
    public static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }
}
