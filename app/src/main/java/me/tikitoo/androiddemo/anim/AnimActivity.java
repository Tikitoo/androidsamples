package me.tikitoo.androiddemo.anim;

import android.animation.AnimatorInflater;
import android.animation.AnimatorSet;
import android.animation.Keyframe;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.ValueAnimator;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.view.animation.AnimationUtils;
import android.view.animation.CycleInterpolator;
import android.widget.Button;
import android.widget.ImageView;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.utils.Navigator;
import me.tikitoo.androiddemo.view.ObjView;

/**
 * Created by Tikitoo on 2015/12/26.
 */
public class AnimActivity extends AppCompatActivity implements View.OnClickListener {
    private ImageView mImageViewBase;
    private ObjView mObjView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_anim);
        initView();
    }

    private void initView() {
        int[] ids = new int[] {
                R.id.anim_tween_btn, R.id.anim_frame_btn,
                R.id.anim_scale_btn, R.id.anim_translation_btn, R.id.anim_rotation_btn, R.id.anim_alpha_btn,
                R.id.anim_value_btn, R.id.anim_obj_btn, R.id.anim_obj_btn_custom, R.id.anim_parabola_btn,
                R.id.anim_key_frame_btn, R.id.anim_xml_btn

        };
        for (int i = 0; i < ids.length; i++) {
            Button button = (Button) findViewById(ids[i]);
            button.setOnClickListener(this);
        }
        
        mImageViewBase = (ImageView) findViewById(R.id.base_image_view);
        mImageViewBase.performClick();




    }

    private void loadTweenAnim() {
        mImageViewBase.setAnimation(AnimationUtils.loadAnimation(this, R.anim.anim_set));
    }

    private void loadFrameAnim() {
        mImageViewBase.setBackgroundResource(R.drawable.anim_frame_anim_list);
        final AnimationDrawable mAnimationDrawable = (AnimationDrawable) mImageViewBase.getBackground();
        mImageViewBase.post(new Runnable() {
            @Override
            public void run() {
                mAnimationDrawable.start();
            }
        });
    }

    private void loadValueAnim() {
        ValueAnimator animator = ValueAnimator.ofFloat(0f, 1f);
        animator.setDuration(1000);
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                Log.d("update", animation.getAnimatedValue().toString());
            }
        });

        animator.setInterpolator(new CycleInterpolator(3));
        animator.start();
    }

    private void loadObjAnimAlpha() {
        ObjectAnimator animtorObj = ObjectAnimator.ofFloat(mImageViewBase, "alpha", 1f, 0f, 1f);
        objAnimStart(animtorObj);

    }

    private void loadObjAnimRotation() {
        ObjectAnimator animtorObj = ObjectAnimator.ofFloat(mImageViewBase, "rotation", 0F, 360f);
        objAnimStart(animtorObj);
    }

    private void loadObjAnimTranslation() {
        float curTranslationX = mImageViewBase.getTranslationX();
        ObjectAnimator animtorObj = ObjectAnimator.ofFloat(mImageViewBase, "translationX", curTranslationX, -500, curTranslationX);
        objAnimStart(animtorObj);
    }

    private void loadObjAnimScale() {
        ObjectAnimator animtorObj = ObjectAnimator.ofFloat(mImageViewBase, "scaleX", 1f, 4f, 1f);
        ObjectAnimator animtorObjY = ObjectAnimator.ofFloat(mImageViewBase, "scaleY", 1f, 4f, 1f);
        objAnimStart(animtorObj);
        objAnimStart(animtorObjY);
    }

    private void loadObjAnimAndSet() {
        ObjectAnimator animX = ObjectAnimator.ofFloat(mImageViewBase, "x", 500f, mImageViewBase.getTranslationX());
        ObjectAnimator animY = ObjectAnimator.ofFloat(mImageViewBase, "y", 100f, mImageViewBase.getTranslationY());

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(animX).with(animY);
        animatorSet.setDuration(5000);
        animatorSet.start();
//        mImageViewBase.animate().x(50f).y(100f);

    }

    private void loadObjAnimAndSet2() {
        PropertyValuesHolder pvhX = PropertyValuesHolder.ofFloat("x", 300f);
        PropertyValuesHolder pvhY = PropertyValuesHolder.ofFloat("y", 100f);
        ObjectAnimator.ofPropertyValuesHolder(mImageViewBase, pvhX, pvhY).start();
    }

    private void objAnimStart(ObjectAnimator animtorObj) {
        animtorObj.setDuration(5000);
        animtorObj.start();
    }


    private void loadObjAnimKeyframe() {
        Keyframe kf0 = Keyframe.ofFloat(0f, 0f);
        Keyframe kf1 = Keyframe.ofFloat(0.5f, 360f);
        Keyframe kf2 = Keyframe.ofFloat(1f, 0f);
        PropertyValuesHolder pvbRotation
                = PropertyValuesHolder.ofKeyframe("rotation", kf0, kf1, kf2);
        ObjectAnimator rotationAnim
                = ObjectAnimator.ofPropertyValuesHolder(mImageViewBase, pvbRotation);
        rotationAnim.setDuration(5000);
        rotationAnim.start();
    }

    private void loadObjAnimXml() {
        AnimatorSet set = (AnimatorSet) AnimatorInflater.loadAnimator(this,
                R.animator.animator_set_test);
        set.setTarget(mImageViewBase);
        set.start();
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
//        loadFrameAnim();
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.anim_obj_btn_custom || v.getId() == R.id.anim_parabola_btn) {
            Navigator.startView(this, AnimShowActivity.class, v);
            return;
        }
        switch (v.getId()) {
            case R.id.anim_tween_btn:
                loadTweenAnim();
                break;
            case R.id.anim_frame_btn:
//                loadFrameAnim();
                break;
            case R.id.anim_alpha_btn:
                loadObjAnimAlpha();
                break;
            case R.id.anim_rotation_btn:
                loadObjAnimRotation();
                break;
            case R.id.anim_translation_btn:
                loadObjAnimTranslation();
                break;
            case R.id.anim_scale_btn:
                loadObjAnimScale();
                break;
            case R.id.anim_value_btn:
                loadValueAnim();
                break;
            case R.id.anim_obj_btn:
                loadObjAnimAndSet();
//                loadObjAnimAndSet2();
                break;
            case R.id.anim_key_frame_btn:
                loadObjAnimKeyframe();
                break;
            case R.id.anim_xml_btn:
                loadObjAnimXml();
                break;

            default:
                break;
        }
    }
}
