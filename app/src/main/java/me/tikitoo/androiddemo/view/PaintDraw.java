package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/22.
 */
public class PaintDraw extends View {

    private Paint mPaint;

    public PaintDraw(Context context) {
        this(context, null);
    }

    public PaintDraw(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPaint = new Paint();
        mPaint.setColor(Color.BLUE);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(10);
        canvas.drawCircle(120, 80, 60, mPaint);

        mPaint = new Paint();
        mPaint.setColor(Color.YELLOW);
        mPaint.setTextSize(40);
        canvas.drawText("My name is Tikitoo", 300, 500, mPaint);

        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        canvas.drawLine(250, 400, 500, 678, mPaint);

    }
}
