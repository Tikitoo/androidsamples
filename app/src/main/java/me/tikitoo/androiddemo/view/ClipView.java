package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Region;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class ClipView extends View {

    private Paint mPaint;

    public ClipView(Context context) {
        this(context, null);
    }

    public ClipView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public ClipView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        /*canvas.save();
        canvas.drawColor(Color.BLUE);
        canvas.clipRect(100, 100, 200, 300);
        canvas.drawRect(new Rect(100, 100, 200, 200), mPaint);
        canvas.drawCircle(200, 200, 100, mPaint);
        canvas.restore();*/

        canvas.scale(0.5F, 0.5F);
        canvas.save();
        canvas.clipRect(new Rect(100, 100, 200, 200));
        canvas.drawColor(Color.RED);
        canvas.restore();
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);
        canvas.clipRegion(new Region(new Rect(300, 300, 400, 400)));
        canvas.drawColor(Color.BLACK);


    }
}
