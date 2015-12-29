package me.tikitoo.androiddemo.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.Toast;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.view.DrawView;

/**
 * Created by Tikitoo on 2015/12/22.
 */
public class CustomViewActivity extends Activity {

    private DrawView mDrawView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_view);
        mDrawView = (DrawView) findViewById(R.id.draw_view);
    }

    public void saveBtn(View view) {
        onSaveClicked(this, view);

    }


    public void onSaveClicked(final Context context, View view) {
        try {
            File file = new File(Environment.getExternalStorageDirectory(),
                    System.currentTimeMillis() + ".jpg");
            OutputStream outputStream = new FileOutputStream(file);
            mDrawView.saveBitmap(outputStream);
            outputStream.close();
/*
            Intent intent = new Intent();
            intent.setAction(Intent.ACTION_MEDIA_MOUNTED);
            intent.setData(Uri.fromFile(Environment.getExternalStorageDirectory()));
            sendBroadcast(intent);*/

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
                Intent mediaScanIntent = new Intent(
                        Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
                Uri contentUri = Uri.fromFile(file);
                mediaScanIntent.setData(contentUri);
                this.sendBroadcast(mediaScanIntent);
            } else {
                sendBroadcast(new Intent(
                        Intent.ACTION_MEDIA_MOUNTED,
                        Uri.fromFile(Environment.getExternalStorageDirectory())));
            }
            showToast(context, "save success");
        } catch (Exception e) {
            showToast(context, "save failed");
            e.printStackTrace();
        }

    }

    private static void showToast(Context context, String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_SHORT).show();
    }

}
