package com.example.baithuchanh21;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets; // Sử dụng StandardCharsets cho UTF-8
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    Button btnparse;
    ListView lv;
    ArrayList<String> mylist;
    ArrayAdapter<String> myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnparse = findViewById(R.id.btnparse);
        btnparse.setBackgroundColor(Color.parseColor("#9E9E9E"));
        lv = findViewById(R.id.lv);
        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, mylist);
        lv.setAdapter(myadapter);

        btnparse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                parsejson();
            }
        });
    }

    private void parsejson() {
        String json = null;
        mylist.clear();

        try {
            InputStream inputStream = getAssets().open("computer.json");
            int size = inputStream.available();
            byte[] buffer = new byte[size];
            inputStream.read(buffer);
            inputStream.close();
            json = new String(buffer, StandardCharsets.UTF_8);

            JSONObject reader = new JSONObject(json);
            mylist.add(reader.getString("MaDM"));
            mylist.add(reader.getString("TenDM"));

            JSONArray sanphamsArray = reader.getJSONArray("Sanphams");
            for (int i = 0; i < sanphamsArray.length(); i++) {
                JSONObject myobj = sanphamsArray.getJSONObject(i);
                mylist.add(myobj.getString("MaSP") + " - " + myobj.getString("TenSP"));
                mylist.add(myobj.getInt("SoLuong") + " * " + myobj.getInt("DonGia") + " = " + myobj.getInt("ThanhTien")); // Sử dụng getInt cho số
                mylist.add(myobj.getString("Hinh"));
            }
            myadapter.notifyDataSetChanged();

            if (mylist.isEmpty()) {
                Toast.makeText(this, "Không có dữ liệu hoặc lỗi parse JSON.", Toast.LENGTH_LONG).show();
            }

        } catch (IOException e1) {
            e1.printStackTrace();
            Toast.makeText(this, "Lỗi đọc file JSON: " + e1.getMessage(), Toast.LENGTH_LONG).show();
        } catch (JSONException e2) {
            e2.printStackTrace();
            Toast.makeText(this, "Lỗi Parse JSON: " + e2.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}
