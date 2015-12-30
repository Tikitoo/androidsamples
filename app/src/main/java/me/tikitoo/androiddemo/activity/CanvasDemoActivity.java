package me.tikitoo.androiddemo.activity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.fragment.PagerFragment;
import me.tikitoo.androiddemo.view.CanvasView;
import me.tikitoo.androiddemo.view.ClipView;
import me.tikitoo.androiddemo.view.ClipView2;
import me.tikitoo.androiddemo.view.DropIndicator;
import me.tikitoo.androiddemo.view.LabelView;
import me.tikitoo.androiddemo.view.MagicProgressBarTest;
import me.tikitoo.androiddemo.view.MyListViewTest;
import me.tikitoo.androiddemo.view.SubView;
import me.tikitoo.androiddemo.view.LayerView;
import me.tikitoo.androiddemo.view.SimpleLayout;
import me.tikitoo.androiddemo.view.SkyStarView;

public class CanvasDemoActivity extends AppCompatActivity {

    private int mResId;
    private String[] mTitles;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        parseIntent();
        init();
        setCustomView(mResId);
    }

    private void init() {
        mTitles = new String[] {"One", "Two", "Three", "Four"};
    }

    private void parseIntent() {
        mResId = getIntent().getIntExtra(CanvasActivity.EXTRA_RES_ID, 0);
    }

    private void setCustomView(int resId) {
        View view = null;
        switch (resId) {
            case R.id.view_btn_drop_indicator:
                setContentView(R.layout.view_indicator);
                setDropIndicator();
                return;
            case R.id.sky_star_btn:
                view = new SkyStarView(this);
                break;
            case R.id.canvas_btn:
                view = new CanvasView(this);
                break;
            case R.id.layer_btn:
                view = new LayerView(this);
                break;
            case R.id.clip_btn:
                view = new ClipView(this);
                break;
            case R.id.clip_btn_02:
                view = new ClipView2(this);
                break;
            case R.id.view_btn_draw_layout:
                SimpleLayout simpleLayout = new SimpleLayout(this);
                simpleLayout.addView(new SubView(this));
                view = simpleLayout;
                break;
            case R.id.view_btn_round_progress:
//                view = getLayoutInflater().inflate(R.layout.view_round_progress_bar, null);
                setContentView(R.layout.view_round_progress_bar);
                return;
            case R.id.view_btn_label:
                view = getLabelView();
                break;
            case R.id.view_magic_progress:
                final MagicProgressBarTest magicProgressBarTest = new MagicProgressBarTest(this);
                view = magicProgressBarTest;
                break;
            case R.id.view_magic_list_view:
                final MyListViewTest myListViewTest = new MyListViewTest(this);
                view = myListViewTest;
                break;
            default:
                view = new CustomView(this, resId);
                break;
        }
        setContentView(view);

    }

    @NonNull
    private LabelView getLabelView() {
        LabelView labelView = new LabelView(this);
        labelView.setTextSize(12);
        labelView.setTextColor(Color.GRAY);
        labelView.setText("Hello World");
        return labelView;
    }


    private void setDropIndicator() {
        final DropIndicator indicator = (DropIndicator) findViewById(R.id.drop_indicator_view);
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        int width = indicator.getLayoutParams().width;
        int height = indicator.getLayoutParams().height;
        indicator.setWidth(width);
        indicator.setHeight(height);
        indicator.setPageCount(mTitles.length);
        indicator.setLeftCircleRadius(0.15F * height);
        indicator.setSelectCircleRadius(0.15F * height);
        pager.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));

        pager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                indicator.setPositionAndOffset(position, positionOffset);
            }

            @Override
            public void onPageSelected(int position) {

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    class MyPagerAdapter extends FragmentPagerAdapter {

        public MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public int getCount() {
            return mTitles.length;
        }

        @Override
        public Fragment getItem(int position) {
                return PagerFragment.newInstance(mTitles[position]);

        }

    }

    class CustomView extends View {
        Paint mPaint;
        int mResId;
        private RectF mRectF;
        private Path mPath;

        public CustomView(Context context, int resId) {
            super(context);
            mPaint = new Paint();
            mResId = resId;
            mPaint.setColor(Color.BLACK);
            mPaint.setStrokeJoin(Paint.Join.ROUND);
            mPaint.setStrokeCap(Paint.Cap.ROUND);
            mPaint.setStrokeWidth(3);
        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);
            switch (mResId) {
                case R.id.view_btn_circle:
                    canvas.drawCircle(100, 100, 90, mPaint);
                    break;
                case R.id.view_btn_02:
                    mRectF = new RectF(0, 0, 100, 100);
                    canvas.drawArc(mRectF, 90, 90, true, mPaint);
                    break;
                case R.id.view_btn_color:
                    canvas.drawColor(Color.BLUE);
                    break;
                case R.id.view_btn_line:
                    canvas.drawLine(10, 10, 100, 100, mPaint);
                    break;
                case R.id.view_btn_oval:
                    mRectF = new RectF(10, 10, 500, 700);
                    canvas.drawOval(mRectF, mPaint);
                    break;
                case R.id.view_btn_text_location:
                    canvas.drawPosText(
                            "tikitoo",
                            new float[] {
                                10, 10,
                                20, 20,
                                30, 30,
                                40, 40,
                                50, 50,
                                60, 60,
                                70, 70,
                            },
                            mPaint);
                    break;
                case R.id.view_btn_rect:
                    mRectF = new RectF(50, 50 ,300 ,300);
                    canvas.drawRect(mRectF, mPaint);
                    break;
                case R.id.view_btn_round_rect:
                    mRectF = new RectF(50, 50 ,300 ,300);
                    canvas.drawRoundRect(mRectF, 30, 30, mPaint);
                    break;
                case R.id.view_btn_path:
                    mPath = new Path();
                    mPath.moveTo(100, 100);
                    mPath.lineTo(56, 200);
                    mPath.lineTo(400, 100);
                    mPath.lineTo(100, 40);
                    mPath.close();
                    canvas.drawPath(mPath, mPaint);
                    break;
                case R.id.view_btn_anim:
                    mPath = new Path(); //定义一条路径
                    mPath.moveTo(10, 10); //移动到 坐标10,10
                    mPath.lineTo(300, 300);
                    mPath.lineTo(500, 80);
                    mPath.lineTo(700, 10);
                    canvas.drawTextOnPath("Tikitoo Blog", mPath, 10, 10, mPaint);
                    break;
                case R.id.view_btn_clock:
                    drawClock(canvas);
                    break;
                default:
                    break;
            }
        }

        private void drawClock(Canvas canvas) {
            mPaint.setAntiAlias(true);
            mPaint.setStyle(Paint.Style.STROKE);
            canvas.translate(canvas.getWidth() / 2, canvas.getHeight() / 2);
            canvas.drawCircle(0, 0, 300, mPaint);

            canvas.save();
            canvas.translate(-150, -150);

            mPath = new Path();
            mPath.addArc(new RectF(0, 0, 300, 300), -180, 180);

            Paint citePaint = new Paint(mPaint);
            citePaint.setTextSize(30);
            citePaint.setStrokeWidth(1);
            citePaint.setStyle(Paint.Style.FILL);

            canvas.drawTextOnPath("http://tikitoo.me/blog", mPath, 100, 0, citePaint);
            canvas.restore();

            Paint tmpPaint = new Paint(mPaint);
            tmpPaint.setStrokeWidth(3);
            tmpPaint.setTextSize(30F);

            float y = 300;
            int count = 60;
            for (int i = 0; i < count; i++) {
                if (i % 5 == 0) {
                    canvas.drawLine(0F, y, 0, y + 12F, mPaint);
                    canvas.drawText(String.valueOf(i / 5 + 1), -15F, y + 50F, tmpPaint);
                } else {
                    canvas.drawLine(0F, y, 0F, y + 5F, tmpPaint);
                }
                canvas.rotate(360 / count, 0F, 0F);
            }

            tmpPaint.setColor(Color.GRAY);
            tmpPaint.setStrokeWidth(4);
            canvas.drawCircle(0, 0, 7, tmpPaint);
            tmpPaint.setStyle(Paint.Style.FILL);

            // 指针
            tmpPaint.setColor(Color.YELLOW);
            canvas.drawCircle(0, 0, 5, tmpPaint);
            canvas.drawLine(0, 10, 0, -65, mPaint);
        }
    }


}
