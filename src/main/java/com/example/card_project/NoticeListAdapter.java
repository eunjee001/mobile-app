package com.example.card_project;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class NoticeListAdapter extends BaseAdapter {
    private Context context;
    private List<Notice> noticedList;

    public NoticeListAdapter(Context context, List<Notice> noticedList) {

        this.context =context;
        this.noticedList =noticedList;
}

    @Override
    public int getCount() {
        return noticedList.size();
    }

    @Override
    public Object getItem(int position) {
        return noticedList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View v = View.inflate(context, R.layout.notice, null);
        TextView notice = (TextView) v.findViewById(R.id.notice);
        TextView nameText = (TextView) v.findViewById(R.id.nameText);
        TextView dateText = (TextView) v.findViewById(R.id.dateText);
        notice.setText(noticedList.get(position).getNotice());
        nameText.setText(noticedList.get(position).getName());
        dateText.setText(noticedList.get(position).getDate());
        v.setTag(noticedList.get(position).getNotice());
        return v;

    }
}
