package com.example.baithuchanh13;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class SubActivity extends AppCompatActivity {
    TextView txt_subphone;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_sub);
        txt_subphone = findViewById(R.id.txtsubphone);
        Intent myintent = getIntent();
        String namephone = myintent.getStringExtra("name");
        txt_subphone.setText(namephone);
    }
}
