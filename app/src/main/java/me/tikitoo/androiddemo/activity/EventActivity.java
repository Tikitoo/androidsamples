package me.tikitoo.androiddemo.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.widget.LinearLayout;

import me.tikitoo.androiddemo.R;

/**
 * Created by Tikitoo on 2015/12/31.
 */
public class EventActivity extends AppCompatActivity {

    private String TAG = "EventActivity";
    private LinearLayout mEventLayoutView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event);
        mEventLayoutView = (LinearLayout) findViewById(R.id.event_layout_view);


    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[dispatchTouchEvent] -> ACTION_CANCEL");
                break;
        }
        boolean superReturn = super.dispatchTouchEvent(ev);
        Log.i(TAG, "[dispatchTouchEvent] return super. = " + superReturn);
        return superReturn;
    }


    @Override
    public boolean onTouchEvent(MotionEvent ev) {

        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                Log.d(TAG, "[onTouchEvent] -> ACTION_DOWN");
                break;
            case MotionEvent.ACTION_MOVE:
                Log.d(TAG, "[onTouchEvent] -> ACTION_MOVE");
                break;
            case MotionEvent.ACTION_UP:
                Log.d(TAG, "[onTouchEvent] -> ACTION_UP");
                break;
            case MotionEvent.ACTION_CANCEL:
                Log.d(TAG, "[onTouchEvent] -> ACTION_CANCEL");
                break;
        }
        boolean superReturn = super.dispatchTouchEvent(ev);
        Log.i(TAG, "[onTouchEvent] return super. = " + superReturn);
        return superReturn;
    }


}
