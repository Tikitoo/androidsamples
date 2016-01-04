package me.tikitoo.androiddemo.activity;

import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class WebViewActivity extends AppCompatActivity {
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private String TAG = "WebViewActivity";
    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView();
    }

    private void setCustomView() {
        mWebView = new WebView(this);
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl("http://www.zhihu.com");


        setContentView(mWebView);
//        getWindow().requestFeature(Window.FEATURE_PROGRESS);

        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                Log.d(TAG, "onPageFinished");

                new AsyncTask<WebView, Bitmap, Bitmap>() {
                    @Override
                    protected Bitmap doInBackground(WebView... params) {
                        return getWebViewBitmap(params[0]);
                    }

                    @Override
                    protected void onPostExecute(Bitmap bitmap) {
                        super.onPostExecute(bitmap);
                        saveBitmap(bitmap);
                    }
                }.execute(mWebView);

//                saveBitmap(getWebViewBitmap());
            }
        });
    }

    private Bitmap getWebViewBitmap(WebView webView) {
        webView.setDrawingCacheEnabled(true);
        webView.buildDrawingCache();
        Bitmap bitmap = webView.getDrawingCache();
        Rect frame = new Rect();
        webView.getWindowVisibleDisplayFrame(frame);
        int statusBarHeight = frame.top;
        int actionBarHeight = getSupportActionBar().getHeight();

        int width = getWindowManager().getDefaultDisplay().getWidth();
        int height = getWindowManager().getDefaultDisplay().getHeight();

        height = height - statusBarHeight - actionBarHeight;
        Bitmap b = Bitmap.createBitmap(bitmap, 0, /*statusBarHeight + actionBarHeight*/0, width, height);
        webView.destroyDrawingCache();
        return b;

    }

    private void saveBitmap(Bitmap bitmap) {
        File file = new File(Environment.getExternalStorageDirectory().toString() + "/" + Environment.DIRECTORY_DOWNLOADS, "hello.jpg");
        if (file.exists()) {
            file.delete();
        }

        try {
            FileOutputStream out = new FileOutputStream(file);
            bitmap.compress(Bitmap.CompressFormat.PNG, 30, out);
            out.flush();
            out.close();
            Log.d(TAG, "save success");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            Log.d(TAG, "save failed");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
