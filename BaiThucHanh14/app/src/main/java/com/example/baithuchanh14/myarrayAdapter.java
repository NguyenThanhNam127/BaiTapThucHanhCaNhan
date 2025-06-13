package com.example.baithuchanh14;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class myarrayAdapter extends ArrayAdapter<Item> {
    Activity context = null;
    ArrayList<Item> myArray = null;
    int LayoutId;

    public myarrayAdapter(Activity context, int LayoutId, ArrayList<Item> arr) {
        super(context, LayoutId, arr);
        this.context = context;
        this.LayoutId = LayoutId;
        this.myArray = arr;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        convertView = inflater.inflate(LayoutId, null);

        if (myArray.size() > 0 && position < myArray.size()) {
            final Item myItem = myArray.get(position);

            final ImageButton btnlike = (ImageButton) convertView.findViewById(R.id.btnlike);

            if (myItem.getThich() == 1) {
                btnlike.setImageResource(R.drawable.like);
            } else {
                btnlike.setImageResource(R.drawable.unlike);
            }

            TextView tieude = (TextView) convertView.findViewById(R.id.txttieude);
            tieude.setText(myItem.getTieude());

            TextView maso = (TextView) convertView.findViewById(R.id.txtmaso);
            maso.setText(myItem.getMaso());

            btnlike.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItem.getThich() == 1) {
                        myItem.setThich(0);
                        btnlike.setImageResource(R.drawable.unlike);
                        Toast.makeText(context, "Bạn đã bỏ thích '" + myItem.getTieude() + "'", Toast.LENGTH_SHORT).show();
                    } else {
                        myItem.setThich(1);
                        btnlike.setImageResource(R.drawable.like);
                        Toast.makeText(context, "Bạn đã thích '" + myItem.getTieude() + "'", Toast.LENGTH_SHORT).show();
                    }

                }
            });
        }
        return convertView;
    }
}


