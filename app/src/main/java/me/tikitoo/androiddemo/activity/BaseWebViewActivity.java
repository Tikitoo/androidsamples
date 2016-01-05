package me.tikitoo.androiddemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Toast;

import me.tikitoo.androiddemo.utils.Navigator;

/**
 * date: 2016/01/05
 * author: tikitoo
 *
 */
public class BaseWebViewActivity extends AppCompatActivity {
    public static final int MATCH_PARENT = ViewGroup.LayoutParams.MATCH_PARENT;
    private String TAG = "BaseWebViewActivity";
    private WebView mWebView;
    private WebSettings mSettings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setCustomView();
    }

    private void setCustomView() {
        mWebView = new WebView(this);
        mWebView.setLayoutParams(new ViewGroup.LayoutParams(MATCH_PARENT, MATCH_PARENT));

        setContentView(mWebView);
        mSettings = mWebView.getSettings();
        mSettings.setJavaScriptEnabled(true);

        mWebView.addJavascriptInterface(new WebAppInterface(this), "android");
        mWebView.setWebChromeClient(new WebChromeClient());
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public void onPageFinished(WebView view, String url) {
                super.onPageFinished(view, url);
                testJs();
            }

            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                String baseUrl = Navigator.ASSEETS_FOLDER + "open_app://";
                if (url.startsWith(baseUrl)) {
                    int index = url.lastIndexOf(baseUrl);
                    String subStr = url.substring(index + baseUrl.length(), url.length());
                    Navigator.openApp(BaseWebViewActivity.this, subStr);
//                    mWebView.loadUrl("http://www.zhihu.com");
                    return true;
                }
                return false;
            }

        });

        mWebView.loadUrl("file:///android_asset/base_html.html");

    }

    private void testJs() {
        String hello = "javascript:hello()";
        mWebView.loadUrl(hello);
    }



    class WebAppInterface {
        Context mContext;
        public WebAppInterface(Context context) {
            mContext = context;
        }

        // targetSdkVersion to 17 4.2 or higher
        @JavascriptInterface
        public void showToast(String msg) {
            BaseWebViewActivity.this.showToast(msg);
        }
    }

    private void showToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }
}
