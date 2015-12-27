package me.tikitoo.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import me.tikitoo.androiddemo.anim.AnimActivity;
import me.tikitoo.androiddemo.utils.Navigator;
import me.tikitoo.androiddemo.view.SkyStarActivity;

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
        toolbar.setTitle("MainActivity");
        setSupportActionBar(toolbar);
        getSupportActionBar().hide();
    }

    private void initView() {
        int[] ids = new int[] {
                R.id.sky_star_btn, R.id.canvas_btn, R.id.layer_btn, R.id.clip_btn,
                R.id.clip_btn_02, R.id.anim_btn

        };
        for (int i = 0; i < ids.length; i++) {
            Button button = (Button) findViewById(ids[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.anim_btn:
                startActivity(new Intent(this, AnimActivity.class));
                return;
            default:
                break;
        }
        Navigator.startView(this, SkyStarActivity.class, v);
    }


}
