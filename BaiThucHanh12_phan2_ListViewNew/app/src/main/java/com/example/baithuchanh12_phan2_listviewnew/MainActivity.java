package com.example.baithuchanh12_phan2_listviewnew;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class MainActivity extends AppCompatActivity {
    ListView lv;
    ArrayList<String> arrayWork;
    ArrayAdapter<String> arrayAdapter;
    EditText edtwork,edth, edtm;
    TextView txtdate;
    Button btnwork;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtwork = findViewById(R.id.edtwork);
        edth = findViewById(R.id.edth);
        edtm = findViewById(R.id.edtm);
        btnwork = findViewById(R.id.btnw);
        lv = findViewById(R.id.lv);
        txtdate = findViewById(R.id.txtdate);

        arrayWork = new ArrayList<>();
        arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, arrayWork);
        lv.setAdapter(arrayAdapter);
        Date currentDate = Calendar.getInstance().getTime();
        java.text.SimpleDateFormat simpleDateFormat = new java.text.SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm nay" + simpleDateFormat.format(currentDate));
        btnwork.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(edtwork.getText().toString().equals("") || edth.getText().toString().equals("") || edtm.getText().toString().equals("")){
                    AlertDialog.Builder builer = new AlertDialog.Builder(MainActivity.this);
                    builer.setTitle("Info missing");
                    builer.setMessage("Please enter all information of work");
                    builer.setPositiveButton("Continue", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    });
                    builer.show();
                }else {
                    String str = edtwork.getText().toString() + " - " + edth.getText().toString() + ":" + edtm.getText().toString();
                    arrayWork.add(str);
                    arrayAdapter.notifyDataSetChanged();
                    edtwork.setText("");
                    edth.setText("");
                    edtm.setText("");
                }
            }
        });
    }
}
