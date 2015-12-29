package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.view.View;

import me.tikitoo.androiddemo.R;

/**
 * from: http://blog.csdn.net/xiaanming/article/details/10298163
 * date: 2015/12/29
 */
public class RoundProgressBar extends View {
    private Paint mPaint;

    private int roundColor;
    private int roundProgressColor;

    private int textColor;
    private float textSize;

    private float roundWidth;
    private int max;
    private int progress;

    private boolean textisDisaplayable;
    private int style;

    public static final int STROKE = 0;
    public static final int FILL = 1;
    public RoundProgressBar(Context context) {
        this(context, null);
    }

    public RoundProgressBar(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RoundProgressBar(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs, defStyleAttr);
    }

    private void init(Context context, AttributeSet attrs, int defStyleAttr) {
        mPaint = new Paint();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RoundProgressBar);
        roundColor = a.getColor(R.styleable.RoundProgressBar_roundColor, Color.RED);
        roundProgressColor = a.getColor(R.styleable.RoundProgressBar_roundProgressColor, Color.BLACK);
        textColor = a.getColor(R.styleable.RoundProgressBar_textColor, Color.BLUE);
        textSize = a.getDimension(R.styleable.RoundProgressBar_textSize, 150);
        roundWidth = a.getDimension(R.styleable.RoundProgressBar_roundWidth, 5);
        max = a.getInteger(R.styleable.RoundProgressBar_max, 100);
        textisDisaplayable = a.getBoolean(R.styleable.RoundProgressBar_textIsDisplayable, false);
        style = a.getInt(R.styleable.RoundProgressBar_style, 0);

        a.recycle();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        int center = getWidth() / 2;
        int radius = (int) (center - roundWidth / 2);
        mPaint.setColor(roundColor);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(roundWidth);
        mPaint.setAntiAlias(true);
        canvas.drawCircle(center, center, radius, mPaint);

        mPaint.setStrokeWidth(0);
        mPaint.setColor(textColor);
        mPaint.setTextSize(textSize);
        mPaint.setTypeface(Typeface.DEFAULT_BOLD);

        setProgress(30);
        int percent = (int) (((float)progress / (float)max) * 100);
        float textWidth = mPaint.measureText(percent + "%");
        if (textisDisaplayable && percent != 0 && style == STROKE) {
            canvas.drawText(percent + "%", center - textWidth / 2, center + textWidth / 2, mPaint);
        }

        mPaint.setStrokeWidth(roundWidth);
        mPaint.setColor(roundProgressColor);
        RectF oval = new RectF(center - radius, center - radius, center + radius, center + radius);

        mPaint.setStyle(style == STROKE ? Paint.Style.STROKE : Paint.Style.FILL_AND_STROKE);
        canvas.drawArc(oval, 0, 360 * progress / max, style == STROKE ? false : true, mPaint);
    }

    public float getRoundWidth() {
        return roundWidth;
    }

    public void setRoundWidth(float roundWidth) {
        this.roundWidth = roundWidth;
    }

    public int getTextColor() {
        return textColor;
    }

    public void setTextColor(int textColor) {
        this.textColor = textColor;
    }

    public float getTextSize() {
        return textSize;
    }

    public void setTextSize(float textSize) {
        this.textSize = textSize;
    }

    public int getRoundProgressColor() {
        return roundProgressColor;
    }

    public void setRoundProgressColor(int roundProgressColor) {
        this.roundProgressColor = roundProgressColor;
    }

    public int getRoundColor() {
        return roundColor;
    }

    public void setRoundColor(int roundColor) {
        this.roundColor = roundColor;
    }

    public synchronized int getProgress() {
        return progress;
    }

    public synchronized void setProgress(int progress) {
        normalThrowException(progress, "progress not less 0");
        if (progress > max) {
            progress = max;
        }
        if (progress <= max) {
            this.progress = progress;
            postInvalidate();
        }
    }

    public synchronized int getMax() {
        return max;
    }

    public synchronized void setMax(int max) {
        normalThrowException(max, "max not less 0");
        this.max = max;
    }

    private void normalThrowException(int max, String msg) {
        if (max < 0) {
            throw  new IllegalArgumentException(msg);
        }
    }
}
