package me.tikitoo.androiddemo.view;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.Build;
import android.util.AttributeSet;
import android.view.View;

import me.tikitoo.androiddemo.R;

public class MagicProgressCircle extends View {
    private int fillColor;

    private int bgColor;

    private Paint mFillPaint;
    private Paint mBgPaint;

    private float percent;


    public MagicProgressCircle(Context context) {
        super(context);
        init(context, null);
    }


    public MagicProgressCircle(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public MagicProgressCircle(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    public MagicProgressCircle(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        if (context == null || attrs == null) {
            return;
        }

        TypedArray a = null;
        try {
            a = context.obtainStyledAttributes(attrs, R.styleable.MagicProgressBar);
            percent = a.getFloat(R.styleable.MagicProgressBar_mpb_percent, 0);
            fillColor = a.getColor(R.styleable.MagicProgressBar_mpb_color, 0);
            bgColor = a.getColor(R.styleable.MagicProgressBar_mpb_default_color, 0);
        } finally {
            if (a != null) {
                a.recycle();
            }
        }

        initPaint();
    }

    private void initPaint() {
        mFillPaint = new Paint();
        mFillPaint.setColor(fillColor);
        mFillPaint.setAntiAlias(true);

        mBgPaint = new Paint();
        mBgPaint.setColor(bgColor);
        mBgPaint.setAntiAlias(true);
    }

    private final RectF mRectF = new RectF();

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        float drawPercent = percent;
        final int height = getMeasuredHeight() - getPaddingTop() - getPaddingBottom();
        final int width = getMeasuredWidth() - getPaddingLeft() - getPaddingRight();
        final int fillWidth = (int) (drawPercent * width);
        final float radius = height / 2.0f;

        mRectF.left = 0;
        mRectF.top = 0;
        mRectF.right = width;
        mRectF.bottom = height;

        if (bgColor != 0) {
            canvas.drawRoundRect(mRectF, radius, radius, mBgPaint);
        }

        if (fillColor != 0) {
            mRectF.right = fillWidth;
            canvas.drawRoundRect(mRectF, radius, radius, mFillPaint);
        }

        canvas.restore();

    }

    public float getPercent() {
        return percent;
    }

    public void setPercent(float percent) {
        this.percent = percent;
        invalidate();
    }
}
