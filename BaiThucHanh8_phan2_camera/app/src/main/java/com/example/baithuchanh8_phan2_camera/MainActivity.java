package com.example.baithuchanh8_phan2_camera;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import static android.provider.MediaStore.ACTION_IMAGE_CAPTURE;

public class MainActivity extends AppCompatActivity {

    ImageView myimg;
    ImageButton btncapture;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main); // đảm bảo bạn có file res/layout/activity_main.xml

        myimg = findViewById(R.id.myimg);
        btncapture = findViewById(R.id.btncapture);

        btncapture.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Kiểm tra quyền CAMERA
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(MainActivity.this,
                            new String[]{Manifest.permission.CAMERA}, 1);
                    return;
                }
                // Mở camera
                Intent myintent = new Intent(ACTION_IMAGE_CAPTURE);
                startActivityForResult(myintent, 99);
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 1 &&
                grantResults.length > 0 &&
                grantResults[0] == PackageManager.PERMISSION_GRANTED) {

            Intent myintent = new Intent(ACTION_IMAGE_CAPTURE);
            startActivityForResult(myintent, 99);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,
                                    @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 99 && resultCode == RESULT_OK && data != null && data.getExtras() != null) {
            Bitmap photo = (Bitmap) data.getExtras().get("data");
            myimg.setImageBitmap(photo);
        }
    }
}
