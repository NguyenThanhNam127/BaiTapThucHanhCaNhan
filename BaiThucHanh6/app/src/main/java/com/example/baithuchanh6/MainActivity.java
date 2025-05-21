package com.example.baithuchanh6;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_gui;
    CheckBox checkBox_docbao, checkBox_docsach, checkBox_doccode;
    RadioGroup group;
    EditText edt_hoten, edt_cmnd, edt_bosung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_hoten = findViewById(R.id.edt_hoten);
        edt_cmnd = findViewById(R.id.edt_cmnd);
        edt_bosung = findViewById(R.id.edt_bosung);
        checkBox_docbao = findViewById(R.id.checkBox_docbao);
        checkBox_docsach = findViewById(R.id.checkBox_docsach);
        checkBox_doccode = findViewById(R.id.checkBox_doccode);
        btn_gui = findViewById(R.id.btn_gui);

        btn_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}