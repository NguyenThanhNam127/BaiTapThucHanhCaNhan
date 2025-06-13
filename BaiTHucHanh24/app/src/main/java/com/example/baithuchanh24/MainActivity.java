package com.example.baithuchanh24;

import androidx.appcompat.app.AppCompatActivity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import org.json.JSONArray;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends AppCompatActivity {
    ListView lvTigia;
    TextView txtdate;
    ArrayList<Tygia> dstygia;
    MyArrayAdapter myadapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        lvTigia = (ListView) findViewById(R.id.lv1);
        txtdate = (TextView) findViewById(R.id.txtdate);
        dstygia = new ArrayList<Tygia>();
        myadapter = new MyArrayAdapter(MainActivity.this, R.layout.item, dstygia);
        lvTigia.setAdapter(myadapter);
        getdate();
        TygiaTask task = new TygiaTask();
        task.execute();
    }

    public void getdate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDate = new SimpleDateFormat("dd/MM/yyyy");
        txtdate.setText("Hôm Nay: " + simpleDate.format(calendar.getTime()));
    }

    class TygiaTask extends AsyncTask<Void, Void, ArrayList<Tygia>> {
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected void onPostExecute(ArrayList<Tygia> tygias) {
            super.onPostExecute(tygias);
            myadapter.clear();
            myadapter.addAll(tygias);
        }

        @Override
        protected ArrayList<Tygia> doInBackground(Void... params) {
            ArrayList<Tygia> ds = new ArrayList<>();
            try {
                // BƯỚC 1: SỬ DỤNG CHUỖI JSON GIẢ THAY VÌ GỌI MẠNG
                // Toàn bộ khối code gọi HttpURLConnection đã được thay bằng một chuỗi String.
                String fakeJson = "{\"items\":[" +
                        "{\"type\":\"USD\",\"imageurl\":\"https://www.dongabank.com.vn/images/flag/us.gif\",\"muatienmat\":\"25215\",\"muack\":\"25245\",\"bantienmat\":\"25465\",\"banck\":\"25465\"}," +
                        "{\"type\":\"EUR\",\"imageurl\":\"https://www.dongabank.com.vn/images/flag/eu.gif\",\"muatienmat\":\"26830\",\"muack\":\"26900\",\"bantienmat\":\"27684\",\"banck\":\"27684\"}," +
                        "{\"type\":\"JPY\",\"imageurl\":\"https://www.dongabank.com.vn/images/flag/jp.gif\",\"muatienmat\":\"159\",\"muack\":\"160\",\"bantienmat\":\"167\",\"banck\":\"167\"}," +
                        "{\"type\":\"GBP\",\"imageurl\":\"https://www.dongabank.com.vn/images/flag/gb.gif\",\"muatienmat\":\"31422\",\"muack\":\"31482\",\"bantienmat\":\"32213\",\"banck\":\"32213\"}" +
                        "]}";

                // BƯỚC 2: PHÂN TÍCH DỮ LIỆU GIẢ (Giữ nguyên logic)
                // Dữ liệu giả này có cấu trúc giống API Đông Á cũ, nên ta dùng lại logic cũ
                JSONObject jsonObject = new JSONObject(fakeJson);
                JSONArray jsonArray = jsonObject.getJSONArray("items");

                for (int i = 0; i < jsonArray.length(); i++) {
                    JSONObject item = jsonArray.getJSONObject(i);
                    Tygia tiGia = new Tygia();

                    if (item.has("type")) {
                        tiGia.setType(item.getString("type"));
                    }

                    // Vì dữ liệu giả có imageurl, ta có thể tải ảnh nếu muốn
                    // Hoặc có thể bỏ qua để đơn giản hoá
                    if (item.has("imageurl")) {
                        try {
                            URL url_connection = new URL(item.getString("imageurl"));
                            HttpURLConnection httpURLconnection = (HttpURLConnection) url_connection.openConnection();
                            Bitmap bitmap = BitmapFactory.decodeStream(httpURLconnection.getInputStream());
                            tiGia.setBitmap(bitmap);
                        } catch (Exception e) {
                            // Bỏ qua nếu tải ảnh lỗi
                        }
                    }

                    if (item.has("muatienmat")) {
                        tiGia.setMuatienmat(item.getString("muatienmat"));
                    }
                    if (item.has("muack")) {
                        tiGia.setMuack(item.getString("muack"));
                    }
                    if (item.has("bantienmat")) {
                        tiGia.setBantienmat(item.getString("bantienmat"));
                    }
                    if (item.has("banck")) {
                        tiGia.setBanck(item.getString("banck"));
                    }
                    ds.add(tiGia);
                }

            } catch (Exception ex) {
                Log.e("Loi_JSON_FAKE", ex.toString());
            }
            return ds;
        }
        @Override
        protected void onProgressUpdate(Void... values) {
            super.onProgressUpdate(values);
        }
    }
}
