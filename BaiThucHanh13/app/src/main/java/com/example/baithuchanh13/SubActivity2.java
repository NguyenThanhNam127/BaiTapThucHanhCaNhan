package com.example.baithuchanh13;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class SubActivity2 extends Activity {
    private Bundle extra;
    TextView txtname2;
    ImageView img2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sub2);

        txtname2 = (TextView) findViewById(R.id.textView2);
        img2 = (ImageView) findViewById(R.id.imageView2);

        extra = getIntent().getExtras();
        int position = extra.getInt("TITLE");

        txtname2.setText(MainActivity4.arrName[position]);
        img2.setImageResource(MainActivity4.imageName[position]);
    }
}

