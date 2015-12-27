package me.tikitoo.androiddemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/27.
 */
public class ObjView extends View {

    int maxRadius;
    int minRadius;
    private Paint mPaint;

    public ObjView(Context context) {
        this(context, null);
    }

    public ObjView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ObjView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, maxRadius, mPaint);
        canvas.drawCircle(getWidth() / 2, getHeight() / 2, minRadius, mPaint);
    }

    public void onDrawAgain() {
        ObjectAnimator maxRadiusAnim = ObjectAnimator.ofInt(this, "maxRadius", 50, 200, 50);
//        maxRadiusAnim.setDuration(1000 * 4);
        ObjectAnimator minRadiusAnim = ObjectAnimator.ofInt(this, "minRadius", 90, 300, 90);
//        minRadiusAnim.setDuration(1000 * 4);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(maxRadiusAnim).with(minRadiusAnim);
        animatorSet.setDuration(5000);
//        animatorSet.setInterpolator(new CycleInterpolator(5));
        animatorSet.start();

//        postInvalidate();

    }

    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
        invalidate();
    }

    public int getMinRadius() {
        return minRadius;
    }

    public void setMinRadius(int minRadius) {
        this.minRadius = minRadius;
        invalidate();
    }
}
