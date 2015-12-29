package me.tikitoo.androiddemo.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PagerFragment extends Fragment {

    private static String mTitle;

    public static PagerFragment newInstance(String title) {
        Bundle args = new Bundle();
        args.putString("title", title);
        PagerFragment fragment = new PagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mTitle = getArguments().getString("title");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View rootView = inflater.inflate(android.R.layout.simple_list_item_1, container, false);
        TextView tv = (TextView) rootView.findViewById(android.R.id.text1);
        tv.setText(mTitle);
        return rootView;

    }
}
