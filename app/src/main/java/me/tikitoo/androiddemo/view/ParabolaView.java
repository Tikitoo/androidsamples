package me.tikitoo.androiddemo.view;

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
        PARABOLA,
        EVALUATOR
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
            case EVALUATOR:
                parabolaEvaluator();
                break;
        }
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

    private void parabolaEvaluator() {
        Point p1 = new Point(0, 0);
        Point p2 = new Point(300, 300);
        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), p1, p2);
        anim.setTarget(this);
        anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                invalidate();
            }
        });
        anim.setDuration(3000).start();
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

    public class PointEvaluator implements TypeEvaluator<Point> {

        @Override
        public Point evaluate(float fraction, Point startValue, Point endValue) {
            float x = startValue.getX() + fraction * (endValue.getX() - startValue.getX());
            float y = startValue.getY() + fraction * (endValue.getY() - startValue.getY());
            Point point = new Point(x, y);
            return point;
        }
    }

    public class Point {
        private float x;
        private float y;

        public Point(float x, float y) {
            this.x = x;
            this.y = y;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }
    }
}
