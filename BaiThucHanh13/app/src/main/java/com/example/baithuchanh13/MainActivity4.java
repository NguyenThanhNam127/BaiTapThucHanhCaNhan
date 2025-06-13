package com.example.baithuchanh13;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class MainActivity4 extends AppCompatActivity {

    public static String[] arrName = {"Ảnh 1", "Ảnh 2", "Ảnh 3", "Ảnh 4", "Ảnh 5", "Ảnh 6", "Ảnh 7", "Ảnh 8", "Ảnh 9", "Ảnh 10", "Ảnh 11", "Ảnh 12"};
    public static int[] imageName = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4, R.drawable.img5, R.drawable.img6, R.drawable.img7, R.drawable.img8, R.drawable.img9, R.drawable.img10, R.drawable.img11, R.drawable.img12};

    GridView gridViewDemo;
    MyArrayAdapter2 adapterDanhsach;
    ArrayList<Image> arrimage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main4);

        gridViewDemo = (GridView) findViewById(R.id.grid1);
        arrimage = new ArrayList<Image>();

        ArrayAdapter<Image> adapterDanhSach = new MyArrayAdapter2(MainActivity4.this, R.layout.listitem, arrimage);
        gridViewDemo.setAdapter(adapterDanhSach);

        for (int i = 0; i < arrName.length; i++) {
            Image myImage = new Image();
            myImage.setImg(imageName[i]);
            myImage.setName(arrName[i]);
            arrimage.add(myImage);
        }
        adapterDanhSach.notifyDataSetChanged();

        gridViewDemo.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position, long arg3) {
                Intent intent = new Intent(MainActivity4.this, SubActivity2.class);
                Bundle bundle = new Bundle();
                bundle.putInt("TITLE", position);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}


