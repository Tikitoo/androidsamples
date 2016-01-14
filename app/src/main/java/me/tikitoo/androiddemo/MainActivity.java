package me.tikitoo.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import me.tikitoo.androiddemo.activity.AnimActivity;
import me.tikitoo.androiddemo.activity.BaseWebViewActivity;
import me.tikitoo.androiddemo.activity.CanvasActivity;
import me.tikitoo.androiddemo.activity.EventActivity;
import me.tikitoo.androiddemo.activity.TestServiceActivity;
import me.tikitoo.androiddemo.activity.ViewPagerIndicatorActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
    }

    private void setToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MainActivity");
        toolbar.setTitle("MainActivity");
    }

    private void initView() {
        int[] ids = new int[] {
                 R.id.anim_btn, R.id.canvas_btn_02, R.id.event_touch_btn, R.id.webview_btn,
                R.id.tab_host_btn, R.id.indicator_btn, R.id.service_btn

        };
        for (int i = 0; i < ids.length; i++) {
            Button button = (Button) findViewById(ids[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        Class<?> tClass = null;
        switch (v.getId()) {
            case R.id.anim_btn:
                tClass = AnimActivity.class;
                break;
            case R.id.canvas_btn_02:
                tClass = CanvasActivity.class;
                break;
            case R.id.event_touch_btn:
                tClass = EventActivity.class;
                break;
            case R.id.webview_btn:
                tClass = BaseWebViewActivity.class;
                break;
            case R.id.indicator_btn:
                tClass = ViewPagerIndicatorActivity.class;
                break;
            case R.id.service_btn:
                tClass = TestServiceActivity.class;
                break;
            default:
                break;
        }
        if (tClass == null) return;

        startActivity(new Intent(this, tClass));
    }


}
