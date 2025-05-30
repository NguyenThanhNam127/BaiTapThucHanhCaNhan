package com.example.baithuchanh7_phan3;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    EditText edtnhapa, edtnhapb,edtkq;
    Button btnrequest;

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

        edtnhapa = findViewById(R.id.edtnhapa);
        edtnhapb = findViewById(R.id.edtnhapb);
        btnrequest = findViewById(R.id.btnyckq);
        edtkq = findViewById(R.id.edtkq);

        btnrequest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent myintent = new Intent(MainActivity.this,ResultActivity.class);
                int a = Integer.parseInt(edtnhapa.getText()+"");
                int b = Integer.parseInt(edtnhapb.getText()+"");
                myintent.putExtra("soa",a);
                myintent.putExtra("sob",b);
                startActivityForResult(myintent,99);
            }


        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 99 && resultCode == 33){
            int sum = data.getIntExtra("kq",0);
            edtkq.setText("Tong 2 so la: " + sum);
        }
        if(requestCode == 99 && resultCode == 34){
            int sub = data.getIntExtra("kq",0);
            edtkq.setText("Hieu hai so la: " + sub);
        }
    }
}
