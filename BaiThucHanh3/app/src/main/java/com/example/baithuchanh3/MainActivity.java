package com.example.baithuchanh3;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edt1,edt2,edt3;
    Button btntong, btnhieu,btntich,btnthuong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt1 = findViewById(R.id.edt1);
        edt2 = findViewById(R.id.edt2);
        edt3 = findViewById(R.id.edt3);

        btntong = findViewById(R.id.btntong);
        btnhieu = findViewById(R.id.btnhieu);
        btntich = findViewById(R.id.btntich);
        btnthuong = findViewById(R.id.btnthuong);

        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                edt3.setText("a+b = " + (a+b));
            }
        });

        btnhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                edt3.setText("a-b = " + (a-b));
            }
        });

        btntich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                edt3.setText("a*b = " + (a*b));
            }
        });

        btnthuong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int a = Integer.parseInt("0" + edt1.getText());
                int b = Integer.parseInt("0" + edt2.getText());
                if (b == 0){
                    edt3.setText("B phai khac 0");

                }
                else {
                    edt3.setText("a / b = " + (a/b));
                }

            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}