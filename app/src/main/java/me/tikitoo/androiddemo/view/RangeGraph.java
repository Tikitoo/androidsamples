package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.widget.FrameLayout;

import me.tikitoo.androiddemo.R;

public class RangeGraph extends FrameLayout {
    private static final String TAG = "RangeGraph";

    // State
    private int mMin = 0, mMax = 100;
    private int mValue = 50;

    // Graphics
    protected int colorNeutral = Color.BLACK;
    protected int colorOutOfRange = Color.RED;
    protected int colorInRange = Color.GREEN;
    private int mFontSize = 16;
    private Paint mPaint;

    // Layout
    //
    private int mWidth = 0;
    private int mHeight = 0;

    public RangeGraph(Context context) {
        this(context, null);
    }

    /**
     * @param context
     * @param attrs
     */
    public RangeGraph(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public RangeGraph(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        commonSetup();
        // Then allow overrides from XML
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.RangeGraph);

        try {
            colorNeutral = a.getColor(R.styleable.RangeGraph_color_neutral, Color.BLACK);
            colorInRange = a.getColor(R.styleable.RangeGraph_color_in_range, Color.GREEN);
            colorOutOfRange = a.getColor(R.styleable.RangeGraph_color_out_range, Color.RED);
            mMin = a.getInteger(R.styleable.RangeGraph_num_min, 0);
            mMax = a.getInteger(R.styleable.RangeGraph_num_max, 100);
        } finally {
            a.recycle();
        }


    }

    private void commonSetup() {

        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setColor(colorNeutral);
        // Scale the desired text size to match screen density
        mPaint.setTextSize(mFontSize * getResources().getDisplayMetrics().density);
        mPaint.setStrokeWidth(5F);
        setPadding(5, 5, 5, 5);
    }

    /*@Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        setMeasuredDimension(widthMeasureSpec, heightMeasureSpec);
    }*/

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        // Label it`
        float oneThirdHeight = getPaddingTop() + (2f * mHeight / 3f);
        float twoThirdsHeight = getPaddingTop() + (mHeight / 3f);

        String stringMax = Integer.toString(mMax);
        String stringMin = Integer.toString(mMin);
        String stringValue = Integer.toString(mValue);
        Paint.FontMetrics fm = mPaint.getFontMetrics();
        final float fontHeight = fm.ascent + fm.descent;

        canvas.drawText(stringMax,
                getPaddingLeft(), twoThirdsHeight - fontHeight / 2, mPaint);
        canvas.drawText(stringMin,
                getPaddingLeft(), oneThirdHeight - (fontHeight / 2), mPaint);

        /*canvas.drawText("Hello", getWidth() / 2, getHeight() / 2, mPaint);
        canvas.drawRect(0, 0, 300 ,300, mPaint);
*/

        // Draw the bar outline, at 1/2 and 2/3 of the width
        int top = getPaddingTop();
        int bot = mHeight - getPaddingBottom();
        int leftSide = (int) (mWidth * 0.40f);
        int rightSide = (int) (mWidth * 0.60f);
        Paint.Style oldStyle = mPaint.getStyle();
        mPaint.setStyle(Paint.Style.STROKE);
        canvas.drawRect(leftSide, top, rightSide, bot, mPaint);
        mPaint.setStyle(oldStyle);

        // Now draw the bar graph.
        // The distance from min to max fills the middle third of the graph
        int oneThirdValue = mMax - mMin;
        int valueRange = oneThirdValue * 3;
        int valueAtBottom = mMin - oneThirdValue;
        int visibleValue = Math.max(0, mValue - valueAtBottom);
        int barHeight = Math.min(mHeight, (int) (mHeight * (1f * visibleValue / valueRange)));

        // First put in three tick marks before changing the color
        canvas.drawLine(leftSide - 20, oneThirdHeight, leftSide, oneThirdHeight, mPaint);
        canvas.drawLine(leftSide - 20, twoThirdsHeight, leftSide, twoThirdsHeight, mPaint);
        canvas.drawLine(rightSide, mHeight - barHeight, rightSide + 20, mHeight - barHeight, mPaint);

        mPaint.setColor(isInRange() ? colorInRange : colorOutOfRange);
        Log.d(TAG,
                String.format("drawRect(%d %d %d %d)",
                        leftSide, mHeight - barHeight, rightSide, bot));
        canvas.drawRect(leftSide, mHeight - barHeight, rightSide, bot, mPaint);

        // Draw the actual reading beside the bar top
        mPaint.setColor(isInRange() ? colorNeutral : colorOutOfRange);
        canvas.drawText(stringValue,
                mWidth * 0.65f, mHeight - barHeight - (fontHeight / 2), mPaint);
    }

    public boolean isInRange() {
        return mValue >= mMin && mValue <= mMax;
    }

    // Simple accessors

    public int getMin() {
        return mMin;
    }

    public void setMin(int min) {
        this.mMin = min;
        invalidate();
    }

    public int getMax() {
        return mMax;
    }

    public void setMax(int max) {
        this.mMax = max;
        invalidate();
    }

    public int getValue() {
        return mValue;
    }

    public void setValue(int value) {
        this.mValue = value;
        invalidate();
    }

    public int getColorOutOfRange() {
        return colorOutOfRange;
    }

    public void setColorOutOfRange(int colorOutOfRange) {
        this.colorOutOfRange = colorOutOfRange;
        invalidate();
    }

    public int getColorInRange() {
        return colorInRange;
    }

    public void setColorInRange(int colorInRange) {
        this.colorInRange = colorInRange;
        invalidate();
    }

    public int getColorNeutral() {
        return colorNeutral;
    }

    public void setColorNeutral(int colorNeutral) {
        this.colorNeutral = colorNeutral;
        invalidate();
    }
}