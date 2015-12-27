package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Tikitoo on 2015/12/23.
 */
public class CanvasView extends View {

    private Paint mPaint;

    public CanvasView(Context context) {
        this(context, null);
    }

    public CanvasView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public CanvasView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        mPaint = new Paint();

        Bitmap bitmap = Bitmap.createBitmap(100, 100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas();
        canvas.setBitmap(bitmap);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.translate(100, 100);
        canvas.drawColor(Color.RED);

        canvas.drawRect(new Rect(-100, -100, 0, 0), mPaint);
        canvas.scale(0.5F, 0.5F);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);

        canvas.translate(200, 0);
        canvas.rotate(30);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);

        canvas.translate(200, 0);
        canvas.skew(.5f, .5f);
        canvas.drawRect(new Rect(0, 0, 100, 100), mPaint);
    }
}
