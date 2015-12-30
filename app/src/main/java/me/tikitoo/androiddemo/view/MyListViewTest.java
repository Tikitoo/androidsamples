package me.tikitoo.androiddemo.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;

import java.util.ArrayList;
import java.util.List;

import me.tikitoo.androiddemo.R;
import me.tikitoo.androiddemo.adapter.MyListAdapter;

public class MyListViewTest extends FrameLayout {

    private List<String> mDatas;

    public MyListViewTest(Context context) {
        this(context, null);
    }

    public MyListViewTest(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public MyListViewTest(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context, attrs);
    }

    private void init(Context context, AttributeSet attrs) {
        initList();
        LayoutInflater.from(context).inflate(R.layout.view_list_view_my, this, true);
        MyListView myListView = (MyListView) findViewById(R.id.list_view_my);
        final MyListAdapter myListAdapter = new MyListAdapter(getContext(), 0, mDatas);
        myListView.setAdapter(myListAdapter);
        myListView.setDeleteListener(new MyListView.OnDeleteListener() {
            @Override
            public void onDelete(int index) {
                mDatas.remove(index);
                myListAdapter.notifyDataSetChanged();
            }
        });
    }

    private void initList() {
        mDatas = new ArrayList<>();
        for (int i = 0; i < 30; i++) {
            mDatas.add("content: " + i);
        }
    }
}
