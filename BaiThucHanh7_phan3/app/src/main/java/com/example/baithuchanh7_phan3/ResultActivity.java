package com.example.baithuchanh7_phan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ResultActivity extends AppCompatActivity {
    EditText edtnhana, edtnhanb;
    Button btntong, btnhieu;
    Intent myintent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_result);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        edtnhana = findViewById(R.id.edtnhana);
        edtnhanb = findViewById(R.id.edtnhanb);
        btntong = findViewById(R.id.btntratong);
        btnhieu = findViewById(R.id.btntrahieu);
        myintent = getIntent();
        int a = myintent.getIntExtra("soa",0);
        int b = myintent.getIntExtra("sob",0);
        edtnhana.setText(a+"");
        edtnhanb.setText(b+"");
        btntong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sum = a + b;
                myintent.putExtra("kq",sum);
                setResult(33,myintent);
                finish();
            }
        });
        btnhieu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int sub = a - b;
                myintent.putExtra("kq",sub);
                setResult(34,myintent);
                finish();
            }
        });

    }
}
