package com.example.baithuchanh23;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.widget.AdapterView;
import android.widget.ListView;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.StringReader;
import java.net.URL;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ListView lv1;
    MyArrayAdapter myadapter;
    ArrayList<List> mylist;

    // RSS link
    String RSS_URL = "https://vnexpress.net/rss/tin-moi-nhat.rss";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        lv1 = findViewById(R.id.lv1);
        mylist = new ArrayList<>();
        myadapter = new MyArrayAdapter(this, R.layout.layout_listview, mylist);
        lv1.setAdapter(myadapter);

        // 👉 Gắn sự kiện click item
        lv1.setOnItemClickListener((adapterView, view, position, id) -> {
            List item = mylist.get(position);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(item.getLink()));
            startActivity(intent);
        });

        // 👉 Tải dữ liệu RSS
        new LoadRssTask().execute();
    }

    // 👉 AsyncTask để tải & parse RSS
    class LoadRssTask extends AsyncTask<Void, Void, ArrayList<List>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            myadapter.clear();
        }

        @Override
        protected ArrayList<List> doInBackground(Void... voids) {
            ArrayList<List> items = new ArrayList<>();
            try {
                XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
                XmlPullParser parser = factory.newPullParser();

                XMLParser myparser = new XMLParser();
                String xml = myparser.getXmlFromUrl(RSS_URL);
                parser.setInput(new StringReader(xml));

                int eventType = parser.getEventType();
                boolean insideItem = false;

                String title = "";
                String link = "";
                String description = "";
                String imageUrl = "";
                Bitmap imageBitmap = null;

                while (eventType != XmlPullParser.END_DOCUMENT) {
                    String tagName = parser.getName();
                    switch (eventType) {
                        case XmlPullParser.START_TAG:
                            if (tagName.equalsIgnoreCase("item")) {
                                insideItem = true;
                                title = "";
                                link = "";
                                description = "";
                                imageUrl = "";
                                imageBitmap = null;
                            } else if (insideItem) {
                                if (tagName.equalsIgnoreCase("title")) {
                                    title = parser.nextText();
                                } else if (tagName.equalsIgnoreCase("link")) {
                                    link = parser.nextText();
                                } else if (tagName.equalsIgnoreCase("description")) {
                                    description = parser.nextText();
                                    try {
                                        // Lấy URL ảnh
                                        int start = description.indexOf("src=") + 5;
                                        int end = description.indexOf(".jpg") + 4;
                                        imageUrl = description.substring(start, end);

                                        // Lấy nội dung text
                                        description = description.substring(description.indexOf("</br>") + 5);

                                        // Tải ảnh
                                        URL imgUrl = new URL(imageUrl);
                                        imageBitmap = BitmapFactory.decodeStream(imgUrl.openConnection().getInputStream());
                                    } catch (Exception e) {
                                        e.printStackTrace();
                                    }
                                }
                            }
                            break;

                        case XmlPullParser.END_TAG:
                            if (tagName.equalsIgnoreCase("item") && insideItem) {
                                items.add(new List(imageBitmap, title, description, link));
                                insideItem = false;
                            }
                            break;
                    }
                    eventType = parser.next();
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
            return items;
        }

        @Override
        protected void onPostExecute(ArrayList<List> result) {
            super.onPostExecute(result);
            myadapter.clear();
            myadapter.addAll(result);
        }
    }
}
