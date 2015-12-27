package me.tikitoo.androiddemo.anim;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.utils.Navigator;
import me.tikitoo.androiddemo.view.ObjView;
import me.tikitoo.androiddemo.view.ParabolaView;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class AnimShowActivity extends AppCompatActivity {

    private int mResId;
    private View mView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        setCustomView(mResId);
        initView();
    }

    private void setCustomView(int resId) {
        int layoutId = 0;
        mView = null;
        switch (resId) {
            case R.id.anim_obj_btn_custom:
                final ObjView objView = new ObjView(this);
                objView.setLayoutParams(new LinearLayout.LayoutParams(
                        new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)));

                objView.setMaxRadius(100);
                objView.setMinRadius(50);
                objView.onDrawAgain();

                objView.performClick();
                objView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        objView.onDrawAgain();

                    }
                });
                mView = objView;
                break;
            case R.id.anim_parabola_btn:
                ParabolaView parabolaView = new ParabolaView(this);
                parabolaView.setRunModel(ParabolaView.RunModel.PARABOLA);
                parabolaView.setMaxRadius(100);
                mView = parabolaView;

                break;
        }
        setContentView(mView);
    }

    private void parseIntent() {
        mResId = getIntent().getIntExtra(Navigator.EXTRA_RES_ID, 0);
    }

    private void initView() {

        if (mView instanceof ObjView) {

        }
    }

}
