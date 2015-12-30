package me.tikitoo.androiddemo.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import me.tikitoo.androiddemo.R;

public class MyListAdapter extends ArrayAdapter<String> {

    public MyListAdapter(Context context, int resource, List<String> objects) {
        super(context, resource, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = null;
        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(R.layout.item_item_my_custom, null);
        } else {
            view = convertView;
        }

        TextView textView = (TextView) view.findViewById(R.id.text);
        textView.setText(getItem(position));
        return view;
    }
}
