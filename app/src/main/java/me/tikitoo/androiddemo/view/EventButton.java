package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.Button;

public class EventButton extends Button {
    private String TAG = "EventButton";

    public EventButton(Context context) {
        this(context, null);
    }

    public EventButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public EventButton(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[EventButton] -> [dispatchTouchEvent] -> ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[EventButton] -> [dispatchTouchEvent] -> ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[EventButton] -> [dispatchTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[EventButton] -> [dispatchTouchEvent] -> ACTION_CANCEL");
                break;
        }
        boolean superReturn = super.dispatchTouchEvent(ev);
        Log.i(TAG, "[EventButton] -> [dispatchTouchEvent] return super. = " + superReturn);
        return superReturn;
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[EventButton] -> [onTouchEvent] -> ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[EventButton] -> [onTouchEvent] -> ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[EventButton] -> [onTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[EventButton] -> [onTouchEvent] -> ACTION_CANCEL");
                break;
        }
        boolean superReturn = super.dispatchTouchEvent(ev);
        Log.i(TAG, "[EventButton] -> [onTouchEvent] return super. = " + superReturn);
        return superReturn;
    }
}
