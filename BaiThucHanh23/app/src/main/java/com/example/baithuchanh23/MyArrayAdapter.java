package com.example.baithuchanh23;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class MyArrayAdapter extends ArrayAdapter<List> {
    private Activity context;
    private int layoutID;
    private ArrayList<List> arr;

    public MyArrayAdapter(Activity context, int layoutID, ArrayList<List> arr) {
        super(context, layoutID, arr);
        this.context = context;
        this.layoutID = layoutID;
        this.arr = arr;
    }

    static class ViewHolder {
        ImageView imgView;
        TextView txtTitle, txtInfo;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if (convertView == null) {
            LayoutInflater inflater = context.getLayoutInflater();
            convertView = inflater.inflate(layoutID, null);

            holder = new ViewHolder();
            holder.imgView = convertView.findViewById(R.id.imgView);
            holder.txtTitle = convertView.findViewById(R.id.txtTitle);
            holder.txtInfo = convertView.findViewById(R.id.txtInfo);

            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        List lst = arr.get(position);
        holder.imgView.setImageBitmap(lst.getImg());
        holder.txtTitle.setText(lst.getTitle());
        holder.txtInfo.setText(lst.getInfo());

        return convertView;
    }
}
