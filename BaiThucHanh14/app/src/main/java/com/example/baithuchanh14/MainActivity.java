package com.example.baithuchanh14;

import android.app.Activity;
import android.os.Bundle;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends Activity {

    EditText edita, edtb;
    Button btncong;
    ListView lv1;
    List<String> list;
    ArrayAdapter<String> myarray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addControl();
        addEvent();
    }
    private void addEvent() {
        btncong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Xulycong();
            }
        });
    }
    private void Xulycong() {
        try {
            int a = Integer.parseInt(edita.getText().toString());
            int b = Integer.parseInt(edtb.getText().toString());

            String s = a + " + " + b + " = " + (a + b);

            list.add(s);
            myarray.notifyDataSetChanged();

            edita.setText("");
            edtb.setText("");
            edita.requestFocus();

            Toast.makeText(this, "Kết quả: " + (a + b), Toast.LENGTH_SHORT).show();
        } catch (NumberFormatException e) {
            Toast.makeText(this, "Vui lòng nhập số hợp lệ!", Toast.LENGTH_SHORT).show();
        }
    }

    private void addControl() {
        TabHost tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        Resources res = getResources();

        TabHost.TabSpec tab1Spec = tabHost.newTabSpec("t1");
        tab1Spec.setIndicator("Phép cộng", getResources().getDrawable(R.drawable.cong));
        tab1Spec.setContent(R.id.tab1);
        tabHost.addTab(tab1Spec);

        TabHost.TabSpec tab2Spec = tabHost.newTabSpec("t2");
        tab2Spec.setIndicator("Lịch sử", getResources().getDrawable(R.drawable.lichsu));
        tab2Spec.setContent(R.id.tab2);
        tabHost.addTab(tab2Spec);

        edita = (EditText) findViewById(R.id.edita);
        edtb = (EditText) findViewById(R.id.editb);
        btncong = (Button) findViewById(R.id.btncong);

        lv1 = (ListView) findViewById(R.id.lv1);

        list = new ArrayList<String>();
        myarray = new ArrayAdapter<String>(
                this,
                android.R.layout.simple_list_item_1,
                list
        );
        lv1.setAdapter(myarray);
    }
}

