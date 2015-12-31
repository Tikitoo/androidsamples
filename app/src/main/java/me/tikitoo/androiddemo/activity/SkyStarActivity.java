package me.tikitoo.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.utils.Navigator;
import me.tikitoo.androiddemo.view.CanvasView;
import me.tikitoo.androiddemo.view.ClipView;
import me.tikitoo.androiddemo.view.ClipView2;
import me.tikitoo.androiddemo.view.LayerView;
import me.tikitoo.androiddemo.view.SkyStarView;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class SkyStarActivity extends AppCompatActivity {

    private int mResId;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        setCustomView(mResId);
        initView();
    }

    private void setCustomView(int resId) {
        int layoutId = 0;
        View view = null;
        switch (resId) {
            case R.id.sky_star_btn:
                view = new SkyStarView(this);
                break;
            case R.id.canvas_btn:
                view = new CanvasView(this);
                break;
            case R.id.layer_btn:
                view = new LayerView(this);
                break;
            case R.id.clip_btn:
                view = new ClipView(this);
                break;
            case R.id.clip_btn_02:
                view = new ClipView2(this);
                break;
        }
        setContentView(view);
    }

    private void parseIntent() {
        mResId = getIntent().getIntExtra(Navigator.EXTRA_RES_ID, 0);
    }

    private void initView() {
    }
}
