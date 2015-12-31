package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;

import me.tikitoo.androiddemo.R;

public class DrawViewTest extends FrameLayout {

    public DrawViewTest(Context context) {
        this(context, null);
    }

    public DrawViewTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DrawViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        LayoutInflater.from(context).inflate(R.layout.view_draw_view_test, this, true);
        ImageView imageView = (ImageView) findViewById(R.id.image_view_base);
        getDrawable();

    }

    private void getDrawable() {
        Resources res = getResources();
        Drawable drawable = res.getDrawable(R.drawable.ic_bug_report_black_24dp);
    }
}