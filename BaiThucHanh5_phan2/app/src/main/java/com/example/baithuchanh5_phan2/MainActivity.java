package com.example.baithuchanh5_phan2;

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

    Button btn_tieptuc, btn_giai, btn_thoat;
    EditText edt_a,edt_b,edt_c ;
    TextView edt_kq;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        btn_tieptuc = findViewById(R.id.btn_tieptuc);
        btn_giai = findViewById(R.id.btn_giai);
        btn_thoat = findViewById(R.id.btn_thoat);
        edt_a = findViewById(R.id.edt_a);
        edt_b = findViewById(R.id.edt_b);
        edt_c = findViewById(R.id.edt_c);
        edt_kq = findViewById(R.id.edt_kq);

        btn_giai.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt(edt_a.getText().toString());
                int b = Integer.parseInt(edt_b.getText().toString());
                int c = Integer.parseInt(edt_c.getText().toString());
                String kq = "";
                DecimalFormat dcf = new DecimalFormat("0.00");
                if (a == 0){
                    if (b == 0){
                        if (c == 0){
                            kq = "Phương trình vô số nghiệm";
                        }
                        else {
                            kq = "Phương trình vô nghiệm";
                        }
                    }
                    else {
                        kq = "Phương trình có 1 nghiệm, x = " + dcf.format(-c/b);
                    }
                }
                else {
                    double delta = b*b - 4*a*c;
                    if (delta < 0){
                        kq = "Phương trình vô nghiệm";
                    } else if (delta == 0) {
                        kq = "Phương trình có 2 nghiệm kép, x1 = x2 = "+ dcf.format(-b/(2*a));
                    }
                    else{
                        kq = "Phương trình có 2 nghiệm, x1 = "+ dcf.format((-b+Math.sqrt(delta))/(2*a)) + " va x2 = " + dcf.format((-b-Math.sqrt(delta))/(2*a));
                    }
                }
                edt_kq.setText(kq);
            }
        });
        btn_thoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        btn_tieptuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edt_a.setText("");
                edt_b.setText("");
                edt_c.setText("");
                edt_a.requestFocus();

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}