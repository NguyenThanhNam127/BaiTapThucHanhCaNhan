package com.example.baithuchanh4;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt_fa,edt_cel;
    Button btn_cc,btn_cf,btn_clear;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_fa = findViewById(R.id.edt_fa);
        edt_cel = findViewById(R.id.edt_cel);
        btn_cc = findViewById(R.id.btn_cc);
        btn_cf = findViewById(R.id.btn_cf);
        btn_clear = findViewById(R.id.btn_clear);

        btn_cf.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double c = Double.parseDouble(edt_cel.getText().toString());
                double d = c*1.8+32;
                edt_fa.setText(d+"");
            }
        });

        btn_cc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double f = Double.parseDouble(edt_fa.getText().toString());
                double c = (f-32) * 1.8;
                edt_cel.setText(c+"");
            }
        });

        btn_clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_cel.setText("");
                edt_fa.setText("");
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}