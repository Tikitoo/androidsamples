package me.tikitoo.androiddemo;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

import me.tikitoo.androiddemo.activity.CanvasActivity;
import me.tikitoo.androiddemo.activity.AnimActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setToolbar();
        initView();
    }

    private void setToolbar() {
//        getSupportActionBar().hide();
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("MainActivity");
        toolbar.setTitle("MainActivity");
    }

    private void initView() {
        int[] ids = new int[] {
                 R.id.anim_btn, R.id.canvas_btn_02

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
            case R.id.canvas_btn_02:
                startActivity(new Intent(this, CanvasActivity.class));
                return;
            default:
                break;
        }
//        Navigator.startView(this, SkyStarActivity.class, v);
    }


}
