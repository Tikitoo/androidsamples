package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class ClipView2 extends View {

    private Paint mPaint;
    private Path mPath;

    public ClipView2(Context context) {
        this(context, null);
    }

    public ClipView2(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView2(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
        mPaint.setAntiAlias(true);
        mPaint.setStrokeWidth(6);
        mPaint.setTextSize(16);
        mPaint.setTextAlign(Paint.Align.RIGHT);
        mPath = new Path();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        canvas.drawColor(Color.GRAY);
        canvas.save();
        canvas.translate(10, 10);
        drawScene(canvas);

        canvas.restore();
        canvas.save();
        canvas.translate(160, 10);
        canvas.clipRect(10, 10, 90, 90);
        canvas.clipRect(30, 30, 70, 70, Region.Op.DIFFERENCE);
        drawScene(canvas);

        canvas.restore();
        canvas.save();
        canvas.translate(10, 160);
        mPath.reset();
        canvas.clipPath(mPath);
        mPath.addCircle(50, 50, 50, Path.Direction.CCW);
        canvas.clipPath(mPath, Region.Op.REPLACE);
        drawScene(canvas);

        canvas.restore();
        canvas.save();
        canvas.translate(160, 160);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.UNION);
        drawScene(canvas);

        canvas.restore();
        canvas.save();
        canvas.translate(10, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.XOR);
        drawScene(canvas);

        canvas.restore();
        canvas.save();
        canvas.translate(160, 310);
        canvas.clipRect(0, 0, 60, 60);
        canvas.clipRect(40, 40, 100, 100, Region.Op.REVERSE_DIFFERENCE);
        drawScene(canvas);
        canvas.restore();

    }

    private void drawScene(Canvas canvas) {
        canvas.clipRect(0, 0, 100, 100);
        canvas.drawColor(Color.WHITE);
        mPaint.setColor(Color.RED);
        canvas.drawLine(0, 0, 100, 100, mPaint);
        mPaint.setColor(Color.GREEN);
        canvas.drawCircle(30, 70, 30, mPaint);
        mPaint.setColor(Color.BLUE);
        canvas.drawText("Drawing", 100, 30, mPaint);
    }
}
