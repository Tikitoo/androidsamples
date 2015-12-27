package me.tikitoo.androiddemo.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.TypeEvaluator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.LinearInterpolator;

/**
 * Created by Tikitoo on 2015/12/27.
 */
public class ParabolaView extends View {

    public enum RunModel {
        VERTICAL,
        PARABOLA
    }

    RunModel mRunModel;

    int maxRadius;
    private Paint mPaint;

    public ParabolaView(Context context) {
        this(context, null);
    }

    public ParabolaView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ParabolaView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawCircle(maxRadius, maxRadius, maxRadius, mPaint);

        switch (getRunModel()) {
            case VERTICAL:
                verticalRun();
                break;
            case PARABOLA:
                parabolaRun();
                break;
        }
    }

    public void onDrawAgain() {
        ObjectAnimator maxRadiusAnim = ObjectAnimator.ofInt(this, "maxRadius", 50, 200, 50);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.play(maxRadiusAnim);
        animatorSet.setDuration(5000);
//        animatorSet.setInterpolator(new CycleInterpolator(5));
        animatorSet.start();
    }

    private void verticalRun() {
        ValueAnimator animator = ValueAnimator.ofFloat(0, getHeight());
        animator.setTarget(this);
        animator.setDuration(1000).start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                setTranslationY((Float) animation.getAnimatedValue());
            }
        });

    }

    private void parabolaRun() {
        ValueAnimator animator = ValueAnimator.ofFloat();
        animator.setTarget(this);
        animator.setObjectValues(new PointF(0, 0));
        animator.setInterpolator(new LinearInterpolator());
        animator.setEvaluator(new TypeEvaluator<PointF>() {
            @Override
            public PointF evaluate(float fraction, PointF startValue, PointF endValue) {
                PointF pointF = new PointF();
                pointF.x = 200 * fraction * 3;
                pointF.y = 0.5f * 200 * (fraction * 3) * (fraction * 3);
                return pointF;
            }
        });
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                PointF pointF = (PointF) animation.getAnimatedValue();
                setX(pointF.x);
                setY(pointF.y);
            }
        });
        animator.setDuration(3000).start();
    }



    public int getMaxRadius() {
        return maxRadius;
    }

    public void setMaxRadius(int maxRadius) {
        this.maxRadius = maxRadius;
        invalidate();
    }


    public RunModel getRunModel() {
        return mRunModel;
    }

    public void setRunModel(RunModel runModel) {
        mRunModel = runModel;
        invalidate();
    }
}
