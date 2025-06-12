package com.example.baithuchanh22;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;

public class MainActivity extends Activity {

    Button btnparse;
    ListView lv1;
    ArrayAdapter<String> myadapter;
    ArrayList<String> mylist;
    String URL = "https://vnexpress.net/rss/tin-moi-nhat.rss"; // Sử dụng URL VnExpress cho ổn định
    private static final String TAG = "MainActivityInternet";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.d(TAG, "onCreate: Activity started.");

        btnparse = (Button) findViewById(R.id.btnparse);
        lv1 = (ListView) findViewById(R.id.lv1);

        if (lv1 == null) {
            Log.e(TAG, "onCreate: ListView lv1 not found. Check activity_main.xml ID.");
            Toast.makeText(this, "Lỗi: ListView không tìm thấy!", Toast.LENGTH_LONG).show();
            return;
        }
        if (btnparse == null) {
            Log.e(TAG, "onCreate: Button btnparse not found. Check activity_main.xml ID.");
            Toast.makeText(this, "Lỗi: Button không tìm thấy!", Toast.LENGTH_LONG).show();
            // Có thể không return ở đây nếu bạn muốn ứng dụng vẫn chạy mà không có nút
        }

        mylist = new ArrayList<>();
        myadapter = new ArrayAdapter<>(MainActivity.this,
                android.R.layout.simple_list_item_1, mylist);
        lv1.setAdapter(myadapter);

        if (btnparse != null) {
            btnparse.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.d(TAG, "Parse button clicked. Starting LoadExampleTask.");
                    LoadExampleTask task = new LoadExampleTask();
                    task.execute();
                }
            });
        } else {
            // Fallback: Tự động tải nếu không có button (phòng trường hợp layout thay đổi)
            Log.d(TAG, "onCreate: Button btnparse not found, starting LoadExampleTask automatically.");
            LoadExampleTask task = new LoadExampleTask();
            task.execute();
        }
    }

    class LoadExampleTask extends AsyncTask<Void, Void, ArrayList<String>> {
        private String currentTitle = "";
        private String currentLink = "";

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
            Toast.makeText(MainActivity.this, "Đang tải dữ liệu từ VnExpress...", Toast.LENGTH_SHORT).show();
            Log.d(TAG, "onPreExecute: Adapter cleared, showing loading toast.");
        }

        @Override
        protected ArrayList<String> doInBackground(Void... voids) {
            Log.d(TAG, "doInBackground: Starting background task.");
            ArrayList<String> list = new ArrayList<>();
            XMLParser myparser = new XMLParser();
            String xml = null;
            try {
                Log.d(TAG, "doInBackground: Calling getXmlFromUrl with URL: " + URL);
                xml = myparser.getXmlFromUrl(URL);

                if (xml == null) {
                    Log.e(TAG, "doInBackground: Failed to get XML from URL. XML string is null.");
                    list.add("Lỗi: Không tải được dữ liệu từ URL.");
                    return list;
                }
                Log.d(TAG, "doInBackground: XML fetched successfully. Length: " + xml.length());

                XmlPullParserFactory fc = XmlPullParserFactory.newInstance();
                XmlPullParser parser = fc.newPullParser();
                parser.setInput(new StringReader(xml));

                int eventType = -1;
                String nodeName;

                Log.d(TAG, "doInBackground: Starting XML parsing for VnExpress RSS.");
                while (eventType != XmlPullParser.END_DOCUMENT) {
                    eventType = parser.next();
                    switch (eventType) {
                        case XmlPullParser.START_DOCUMENT:
                            break;
                        case XmlPullParser.END_DOCUMENT:
                            break;
                        case XmlPullParser.START_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equalsIgnoreCase("item")) {
                                currentTitle = "";
                                currentLink = "";
                            } else if (nodeName.equalsIgnoreCase("title")) {
                                currentTitle = parser.nextText();
                            } else if (nodeName.equalsIgnoreCase("link")) {
                                currentLink = parser.nextText();
                            }
                            break;
                        case XmlPullParser.END_TAG:
                            nodeName = parser.getName();
                            if (nodeName.equalsIgnoreCase("item")) {
                                if (!currentTitle.isEmpty()) {
                                    String displayString = "Tiêu đề: " + currentTitle;
                                    if (!currentLink.isEmpty()) {
                                        displayString += "\nLink: " + currentLink;
                                    }
                                    Log.d(TAG, "doInBackground: Parsed item: " + displayString);
                                    list.add(displayString);
                                }
                            }
                            break;
                    }
                }
                Log.d(TAG, "doInBackground: XML parsing finished. Items found: " + list.size());
            } catch (XmlPullParserException e) {
                Log.e(TAG, "doInBackground: XmlPullParserException: " + e.getMessage());
                e.printStackTrace();
                list.add("Lỗi Parse XML: " + e.getMessage());
            } catch (IOException e) {
                Log.e(TAG, "doInBackground: IOException: " + e.getMessage());
                e.printStackTrace();
                list.add("Lỗi IO: " + e.getMessage());
            } catch (Exception e) {
                Log.e(TAG, "doInBackground: Generic Exception: " + e.getMessage());
                e.printStackTrace();
                list.add("Lỗi không xác định: " + e.getMessage());
            }
            return list;
        }

        @Override
        protected void onPostExecute(ArrayList<String> result) {
            super.onPostExecute(result);
            if (result != null && !result.isEmpty()) {
                myadapter.addAll(result);
                if (result.size() == 1 && result.get(0).startsWith("Lỗi:")) {
                    Toast.makeText(MainActivity.this, result.get(0), Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(MainActivity.this, "Tải dữ liệu thành công! " + result.size() + " mục.", Toast.LENGTH_SHORT).show();
                }
                Log.d(TAG, "onPostExecute: Data loaded. Items: " + result.size());
            } else {
                Toast.makeText(MainActivity.this, "Không có dữ liệu hoặc lỗi khi tải.", Toast.LENGTH_LONG).show();
                Log.w(TAG, "onPostExecute: No data or error during loading. Result list is null or empty.");
            }
        }
    }
}

