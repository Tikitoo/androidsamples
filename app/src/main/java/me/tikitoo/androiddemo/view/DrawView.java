package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import java.io.OutputStream;

/**
 * Created by Tikitoo on 2015/12/22.
 */
public class DrawView extends View {
    private Paint mPaint = null;
    private static Bitmap mBitmap = null;
    private Canvas mBitmapcanvas = null;

    private float startX;
    private float startY;
    private static final String TAG = "DrawView";

    private int mWidth;
    private int mHeight;
    public DrawView(Context context) {
        this(context, null);
    }

    public DrawView(Context context, AttributeSet attrs) {
        super(context, attrs);
//        initialize();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mWidth = w;
        mHeight = h;

        initialize();
    }

    private void initialize() {
        mBitmap = Bitmap.createBitmap(getWidth(), getHeight2(), Bitmap.Config.ARGB_8888);
        mBitmapcanvas = new Canvas(mBitmap);
        mBitmapcanvas.drawColor(Color.GREEN);
        mPaint = new Paint();
        mPaint.setColor(Color.RED);
        mPaint.setStrokeWidth(6);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                startX = event.getX();
                startY = event.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                float stopX = event.getX();
                float stopY = event.getY();
                Log.d(TAG, "onTouchEvent ACTION_MOVE\n "
                        + "startX: " + startX
                        + "startY: " + startY
                        + "stopX: " + stopX
                        + "stopY: " + stopY
                );
                mBitmapcanvas.drawLine(startX, startY, stopX, stopY, mPaint);
                startX = event.getX();
                startY = event.getY();
                invalidate();
                break;
            default:
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
//        super.onDraw(canvas);
        if (mBitmap != null) {
            canvas.drawBitmap(mBitmap, 0, 0, mPaint);
        }
    }




    public static void saveBitmap(OutputStream stream) {
        if (mBitmap != null) {
            mBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream);
        }
    }

    public int getWidth2() {
        return mWidth;
    }

    public void setWidth(int width) {
        mWidth = width;
    }

    public int getHeight2() {
        return mHeight;
    }

    public void setHeight(int height) {
        mHeight = height;
    }
}
