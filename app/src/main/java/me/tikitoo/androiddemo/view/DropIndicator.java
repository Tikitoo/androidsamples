package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

public class DropIndicator extends View {

    private int mWidth;
    private int mHeight;
    private int mPageCount;
    private float leftCircleRadius;
    private float slectCircleRadius;
    private Paint mPaint, mSelectPaint;
    private int mPosition;
    private float mOffset;

    public DropIndicator(Context context) {
        this(context, null);
    }

    public DropIndicator(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DropIndicator(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setColor(Color.RED);

        mSelectPaint = new Paint();
        mSelectPaint.setAntiAlias(true);
        mSelectPaint.setStyle(Paint.Style.FILL);
        mSelectPaint.setColor(Color.RED);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        for (int i = 0; i < mPageCount; i++) {
            canvas.drawCircle(getWidth2() / (mPageCount + 1) * (i + 1) , getHeight2() / 2, leftCircleRadius, mPaint);
        }

        canvas.drawCircle(getWidth2() / (mPageCount + 1) * (getPosition() + 1) , getHeight2() / 2, getSelctCircleRadius(), mSelectPaint);

    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;


    }

    public int getPageCount() {
        return mPageCount;
    }

    public void setPageCount(int pageCount) {
        mPageCount = pageCount;
    }

    public float getLeftCircleRadius() {
        return leftCircleRadius;
    }

    public void setLeftCircleRadius(float leftCircleRadius) {
        this.leftCircleRadius = leftCircleRadius;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public void setHeight(int height) {
        mHeight = height;
    }

    public int getHeight2() {
        return mHeight;
    }

    public int getWidth2() {
        return mWidth;
    }

    public float getSelctCircleRadius() {
        return slectCircleRadius;
    }

    public void setSelectCircleRadius(float slectCircleRadius) {
        this.slectCircleRadius = slectCircleRadius;
    }

    public void setPositionAndOffset(int position, float positionOffset) {
        mPosition = position;
        mOffset = positionOffset;
        postInvalidate();
    }

    public int getPosition() {
        return mPosition;
    }

}
