package com.example.baithuchanh13;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import java.util.ArrayList;

public class MyArrayAdapter2 extends ArrayAdapter<Image> {

    private Activity context = null;
    private ArrayList<Image> myArray = null;
    private int LayoutId;

    public MyArrayAdapter2(Activity context, int LayoutId, ArrayList<Image> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);

        Image myImage = myArray.get(position);

        ImageView imgItem = (ImageView) convertView.findViewById(R.id.imageView1);
        imgItem.setImageResource(myImage.getImg());

        TextView myname = (TextView) convertView.findViewById(R.id.textView1);
        myname.setText(myImage.getName());

        return convertView;
    }
}


