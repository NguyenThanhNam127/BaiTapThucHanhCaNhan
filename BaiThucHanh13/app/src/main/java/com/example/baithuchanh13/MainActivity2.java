package com.example.baithuchanh13;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.GridView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {

    String arr[]={"Ipad","Iphone","New Ipad","SamSung","Nokia","Sony Ericson","LG","Q-Mobile","Blackberry","G Phone","FPT - Phone","HK Phone"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main2);
        final TextView selection=(TextView) findViewById(R.id.selection);
        final GridView gv=(GridView) findViewById(R.id.gridView1);
        ArrayAdapter<String>da=new ArrayAdapter<String>(
                this,android.R.layout.simple_list_item_1,arr
        );
        gv.setAdapter(da);
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                selection.setText(arr[arg2]);
            }
        });
    }
}
