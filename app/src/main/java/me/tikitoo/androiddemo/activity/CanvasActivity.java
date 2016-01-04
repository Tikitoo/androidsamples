package me.tikitoo.androiddemo.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.utils.Navigator;

public class CanvasActivity extends AppCompatActivity implements View.OnClickListener {

    public static int[] mIds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_canvas);
        init();
        initView();
    }

    private void init() {

    }

    private void initView() {

        mIds = new int[] {
                R.id.view_btn_circle, R.id.view_btn_02, R.id.view_btn_color, R.id.view_btn_line,
                R.id.view_btn_oval, R.id.view_btn_text_location, R.id.view_btn_rect, R.id.view_btn_round_rect,
                R.id.canvas_btn, R.id.layer_btn, R.id.clip_btn, R.id.clip_btn_02,
                R.id.view_btn_path, R.id.view_btn_anim, R.id.view_btn_round_progress, R.id.view_btn_label,
                R.id.view_btn_clock, R.id.view_btn_drop_indicator, R.id.sky_star_btn, R.id.view_btn_draw_layout,
                R.id.view_magic_progress, R.id.view_magic_list_view, R.id.view_magic_range_graph, R.id.view_draw_save

        };

        for (int i = 0; i < mIds.length; i++) {
            Button button = (Button) findViewById(mIds[i]);
            button.setOnClickListener(this);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.view_draw_save:
                Navigator.startActivity(this, DrawViewActivity.class);
                return;
            default:
                break;
        }
        Intent intent = new Intent(this, CanvasDemoActivity.class);
        intent.putExtra(Navigator.EXTRA_RES_ID, v.getId());
        startActivity(intent);
    }
}
