package me.tikitoo.androiddemo.view;

import android.animation.ObjectAnimator;
import android.annotation.TargetApi;
import android.content.Context;
import android.os.Build;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.animation.AccelerateInterpolator;
import android.widget.FrameLayout;

import java.util.Random;

import me.tikitoo.androiddemo.R;

public class MagicProgressBarTest extends FrameLayout {
    private MagicProgressBar mMpbView1;


    public MagicProgressBarTest(Context context) {
        super(context);
        init(context, null);
    }


    public MagicProgressBarTest(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MagicProgressBarTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MagicProgressBarTest(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_magic_progress, this, true);
        mMpbView1 = (MagicProgressBar) findViewById(R.id.mpb_view_01);
        startAnim();

    }

    private final Random mRandom = new Random();

    private void startAnim() {
        final int score = mRandom.nextInt(101);
        ObjectAnimator view1Anim = ObjectAnimator.ofFloat(mMpbView1, "percent", 0, score / 100f);
        view1Anim.setDuration(600);
        view1Anim.setInterpolator(new AccelerateInterpolator());
        view1Anim.start();
    }


}
