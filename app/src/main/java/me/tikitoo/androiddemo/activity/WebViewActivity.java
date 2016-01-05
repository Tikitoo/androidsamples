package me.tikitoo.androiddemo.activity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Rect;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.JsResult;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import me.tikitoo.androiddemo.utils.Navigator;

/**
 * date: 2016/01/04
 *
 * reference:
 *   https://github.com/android-cn/android-discuss/issues/337
 *   http://developer.android.com/intl/zh-cn/reference/android/webkit/WebView.html
 *   http://stackoverflow.com/questions/19959068/y-height-must-be-bitmap-height-in-android
 *
 */
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

        setContentView(mWebView);
        WebSettings settings = mWebView.getSettings();
        settings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new WebAppInterface(this), "android");

        mWebView.setWebChromeClient(new WebChromeClient());

//        mWebView.setWebViewClient(new JSWebViewClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                testJs();
            }
        });

        mWebView.setWebChromeClient(new JSWebChromeClient());
        mWebView.loadUrl("file:///android_asset/html_js.html");

    }

    private void testJs() {
        String save = "javascript:save()";
        String call = "javascript:call()";

        mWebView.loadUrl(call);
    }

    class JSWebViewClient extends WebViewClient {
        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            if (url.startsWith("apicall://")) {
                showToast("JSWebViewClient" + url);
                return true;
            } else if (url.startsWith("open_app://")){
                int index = url.indexOf("open_app://");
                String subStr = url.substring(index, url.length() - 1);
                Navigator.openApp(WebViewActivity.this, subStr);
                return true;
            }
            return false;
        }

    }

    class JSWebChromeClient extends WebChromeClient {
        @Override
        public boolean onJsAlert(WebView view, String url, String message, JsResult result) {
            showToast("JSWebChromeClient: " + message);
            return true;
        }

    }

    class MyWebViewClient extends WebViewClient {

        @Override
        public void onPageStarted(WebView view, String url, Bitmap favicon) {
            super.onPageStarted(view, url, favicon);
        }

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
            }.execute(view);

        }

        @Override
        public boolean shouldOverrideUrlLoading(WebView view, String url) {
            return super.shouldOverrideUrlLoading(view, url);
        }
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
        File file = new File(
                Environment.getExternalStorageDirectory().toString()
                + "/" + Environment.DIRECTORY_DOWNLOADS,
                mWebView.getTitle() + ".png");
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

    class WebAppInterface {
        Context mContext;
        public WebAppInterface(Context context) {
            mContext = context;
        }

        // targetSdkVersion to 17 4.2 or higher
        @JavascriptInterface
        public void showToast(String msg) {
            WebViewActivity.this.showToast(msg);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
