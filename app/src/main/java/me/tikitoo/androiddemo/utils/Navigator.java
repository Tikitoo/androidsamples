package me.tikitoo.androiddemo.utils;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/27.
 */
public class Navigator {
    public static final String EXTRA_RES_ID = "extra_res_id";

    public static final String ASSEETS_FOLDER = "file:///android_asset/";

    public static void startView(Context context, Class<?> tClass, View v) {
        startView(context, tClass, v.getId());
    }

    public static void startView(Context context, Class<?> tClass, int resId) {
        Intent intent = new Intent(context, tClass);
        intent.putExtra(EXTRA_RES_ID, resId);
        context.startActivity(intent);
    }

    public static void startActivity(Context context, Class<?> tClass) {
        context.startActivity(new Intent(context, tClass));
    }

    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent intent = manager.getLaunchIntentForPackage(packageName);
            if (intent == null) {
                return false;
            }
            intent.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(intent);
            return true;
        } catch (Exception e) {
            return false;
        }

    }

}
