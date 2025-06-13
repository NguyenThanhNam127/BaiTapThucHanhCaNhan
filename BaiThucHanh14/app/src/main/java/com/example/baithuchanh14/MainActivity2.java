package com.example.baithuchanh14;

import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TabHost;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import java.util.ArrayList;
import java.util.Locale;

public class MainActivity2 extends Activity {
    EditText edttim;
    ListView lv1, lv2, lv3;
    TabHost tabHost;

    ArrayList<Item> list1, list2, list3;
    ArrayList<Item> originalList1;

    myarrayAdapter myarray1, myarray2, myarray3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        addControl();
        addEvent();
        loadInitialData();
    }

    private void addEvent() {
        tabHost.setOnTabChangedListener(new TabHost.OnTabChangeListener() {
            @Override
            public void onTabChanged(String tabId) {
            }
        });

        edttim.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                filterTab1(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    private void addControl() {
        tabHost = (TabHost) findViewById(R.id.tabhost);
        tabHost.setup();

        Resources res = getResources();

        TabHost.TabSpec tab1Spec = tabHost.newTabSpec("t1");
        tab1Spec.setContent(R.id.tab1);
        Drawable searchIcon = res.getDrawable(R.drawable.search);
        tab1Spec.setIndicator("", searchIcon);
        tabHost.addTab(tab1Spec);

        TabHost.TabSpec tab2Spec = tabHost.newTabSpec("t2");
        tab2Spec.setContent(R.id.tab2);
        Drawable listIcon = res.getDrawable(R.drawable.list);
        tab2Spec.setIndicator("", listIcon);
        tabHost.addTab(tab2Spec);

        TabHost.TabSpec tab3Spec = tabHost.newTabSpec("t3");
        tab3Spec.setContent(R.id.tab3);
        Drawable favouriteIcon = res.getDrawable(R.drawable.favourite);
        tab3Spec.setIndicator("", favouriteIcon);
        tabHost.addTab(tab3Spec);

        edttim = (EditText) findViewById(R.id.edttim);
        lv1 = (ListView) findViewById(R.id.Lv1);
        lv2 = (ListView) findViewById(R.id.Lv2);
        lv3 = (ListView) findViewById(R.id.Lv3);

        list1 = new ArrayList<Item>();
        originalList1 = new ArrayList<Item>();
        list2 = new ArrayList<Item>();
        list3 = new ArrayList<Item>();

        myarray1 = new myarrayAdapter(MainActivity2.this, R.layout.listitem, list1);
        myarray2 = new myarrayAdapter(MainActivity2.this, R.layout.listitem, list2);
        myarray3 = new myarrayAdapter(MainActivity2.this, R.layout.listitem, list3);

        lv1.setAdapter(myarray1);
        lv2.setAdapter(myarray2);
        lv3.setAdapter(myarray3);
    }

    private void loadInitialData() {
        originalList1.clear();
        originalList1.add(new Item("52300", "Em là ai Tôi là ai", 0));
        originalList1.add(new Item("52600", "Bài ca đất Phương Nam", 1));
        originalList1.add(new Item("52567", "Buồn của Anh", 0));

        list1.clear();
        list1.addAll(originalList1);
        myarray1.notifyDataSetChanged();

        list2.clear();
        list2.add(new Item("57236", "Gởi em ở cuối sông hồng", 0));
        list2.add(new Item("51548", "Quê hương tuổi thơ tôi", 1));
        list2.add(new Item("51748", "Em gì ơi", 0));
        myarray2.notifyDataSetChanged();

        list3.clear();
        list3.add(new Item("57689", "Hát với dòng sông", 1));
        list3.add(new Item("58716", "Say tình Remix", 1));
        list3.add(new Item("58916", "Người hãy quên em đi", 0));
        myarray3.notifyDataSetChanged();
    }

    private void filterTab1(String text) {
        ArrayList<Item> filteredList = new ArrayList<>();
        if (text.isEmpty()) {
            filteredList.addAll(originalList1);
        } else {
            String filterPattern = text.toLowerCase(Locale.getDefault()).trim();
            for (Item item : originalList1) {
                if (item.getTieude().toLowerCase(Locale.getDefault()).contains(filterPattern) ||
                        item.getMaso().toLowerCase(Locale.getDefault()).contains(filterPattern)) {
                    filteredList.add(item);
                }
            }
        }
        list1.clear();
        list1.addAll(filteredList);
        myarray1.notifyDataSetChanged();
    }
}

