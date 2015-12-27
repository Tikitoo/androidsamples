package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class SkyStarView extends View {

    private Paint mPaint;
    private List<PointF> graphics = new ArrayList<>();

    public SkyStarView(Context context) {
        this(context, null);
    }

    public SkyStarView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SkyStarView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context, attrs, defStyle);
    }

    private void init(Context context, AttributeSet attrs, int defStyle) {
        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setStrokeJoin(Paint.Join.ROUND);
//        mPaint.setStrokeCap(Paint.Cap.BUTT);
        mPaint.setStrokeWidth(30);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        return super.onTouchEvent(event);
        graphics.add(new PointF(event.getX(), event.getY()));
        invalidate();
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        for (PointF pointF : graphics) {
            canvas.drawPoint(pointF.x, pointF.y, mPaint);
        }
    }
}
