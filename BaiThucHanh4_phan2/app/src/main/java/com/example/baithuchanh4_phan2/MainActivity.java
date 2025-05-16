package com.example.baithuchanh4_phan2;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.text.DecimalFormat;

public class MainActivity extends AppCompatActivity {
    Button btn_tinh;
    EditText edt_ten, edt_chieucao, edt_cannang, edt_bmi, edt_chuandoan;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        btn_tinh = findViewById(R.id.btn_tinh);
        edt_ten = findViewById(R.id.edt_ten);
        edt_chieucao = findViewById(R.id.edt_chieucao);
        edt_cannang = findViewById(R.id.edt_cannang);
        edt_bmi = findViewById(R.id.edt_bmi);
        edt_chuandoan = findViewById(R.id.edt_chuandoan);

        btn_tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double h = Double.parseDouble(edt_chieucao.getText().toString());
                double w = Double.parseDouble(edt_cannang.getText().toString());
                double bmi = w / (h*h);
                String kq = "";
                if (bmi < 18){
                    kq = "Người gầy";
                } else if (bmi >= 18 && bmi <= 24.9) {
                    kq = "Người bình thường";
                } else if (bmi >= 25 && bmi <= 29.9) {
                    kq = "Người béo phì mức 1";
                } else if (bmi >= 30 && bmi <= 34.9) {
                    kq = "Người béo phì mức 2";
                } else if (bmi >= 35) {
                    kq = "Người béo phì mức 3";
                }
                DecimalFormat dcf = new DecimalFormat("#.0");
                edt_bmi.setText(dcf.format(bmi));
                edt_chuandoan.setText(kq);
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}