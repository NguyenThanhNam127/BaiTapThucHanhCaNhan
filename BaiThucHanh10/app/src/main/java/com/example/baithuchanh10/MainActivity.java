package com.example.baithuchanh10;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat; // Sử dụng ContextCompat cho checkSelfPermission

import android.Manifest;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log; // Thêm Log
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private static final int SMS_PERMISSION_CODE = 1;
    private static final String TAG = "MainActivityReceiver";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity created.");

        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS) != PackageManager.PERMISSION_GRANTED ||
                ContextCompat.checkSelfPermission(this, Manifest.permission.READ_SMS) != PackageManager.PERMISSION_GRANTED) {
            Log.d(TAG, "onCreate: SMS permissions not granted. Requesting permissions.");
            ActivityCompat.requestPermissions(this,
                    new String[]{Manifest.permission.RECEIVE_SMS, Manifest.permission.READ_SMS},
                    SMS_PERMISSION_CODE);
        } else {
            Log.d(TAG, "onCreate: SMS permissions already granted.");
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == SMS_PERMISSION_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: RECEIVE_SMS permission granted.");
                Toast.makeText(this, "Quyền nhận SMS đã được cấp!", Toast.LENGTH_SHORT).show();
            } else {
                Log.d(TAG, "onRequestPermissionsResult: RECEIVE_SMS permission denied.");
                Toast.makeText(this, "Quyền nhận SMS bị từ chối!", Toast.LENGTH_SHORT).show();
            }
            if (grantResults.length > 1 && grantResults[1] == PackageManager.PERMISSION_GRANTED) {
                Log.d(TAG, "onRequestPermissionsResult: READ_SMS permission granted.");
            } else if (grantResults.length > 1) {
                Log.d(TAG, "onRequestPermissionsResult: READ_SMS permission denied.");
            }
        }
    }
}

