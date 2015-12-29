package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import me.tikitoo.androiddemo.R;

public class LabelView extends View {
    private Paint mPaint;
    private String mText;
    private int mAscent;

    public LabelView(Context context) {
        this(context, null);
    }

    public LabelView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public LabelView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initLabelView();

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.LabelView);
        mText = a.getString(R.styleable.LabelView_text);
        setTextColor(a.getColor(R.styleable.LabelView_textColor, Color.RED));
        int textSize = a.getDimensionPixelOffset(R.styleable.LabelView_textSize, 0);
        if (textSize < 0) {
            setTextSize(textSize);
        }
        a.recycle();

    }

    private void initLabelView() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setTextSize(16 * getResources().getDisplayMetrics().density);
        mPaint.setColor(0xff000000);
        setPadding(3, 3, 3, 3);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        setMeasuredDimension(measureWidth(widthMeasureSpec), measureHeight(heightMeasureSpec));
    }

    private int measureWidth(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (mPaint.measureText(mText) + getPaddingLeft() + getPaddingRight());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    private int measureHeight(int measureSpec) {
        int result = 0;
        int specMode = MeasureSpec.getMode(measureSpec);
        int specSize = MeasureSpec.getSize(measureSpec);

        mAscent = (int) mPaint.ascent();
        if (specMode == MeasureSpec.EXACTLY) {
            result = specSize;
        } else {
            result = (int) (-mAscent + mPaint.descent() + getPaddingTop() + getPaddingBottom());
            if (specMode == MeasureSpec.AT_MOST) {
                result = Math.min(result, specSize);
            }
        }
        return result;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawText(mText, getPaddingLeft(), getPaddingTop() - mAscent, mPaint);
    }

    public void setText(String text) {
        mText = text;
        requestLayout();
        invalidate();
    }

    public void setTextColor(int colorRes) {
        mPaint.setColor(colorRes);
        invalidate();
    }



    public void setTextSize(int textSize) {
        mPaint.setTextSize(textSize);
        requestLayout();
        invalidate();
    }
}
