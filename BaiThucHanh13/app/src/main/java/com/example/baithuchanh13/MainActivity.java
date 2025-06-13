package com.example.baithuchanh13;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.MultiAutoCompleteTextView;
import android.widget.TextView;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
    TextView selection;
    AutoCompleteTextView singleComplete;
    MultiAutoCompleteTextView multiComplete;
    String arr[]={"hà nội","Huế","Sài gòn","hà giang","Hội an","Kiên giang","Lâm đồng","Long khánh"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        selection = (TextView) findViewById(R.id.selection);
        singleComplete=(AutoCompleteTextView) findViewById(R.id.editauto);
        ArrayAdapter myadapter = new ArrayAdapter<String>
                (
                        this, android.R.layout.simple_list_item_1,arr
                );
        singleComplete.setAdapter(myadapter);
        multiComplete =(MultiAutoCompleteTextView) findViewById(R.id.multiAutoCompleteTextView1);

        multiComplete.setTokenizer(new MultiAutoCompleteTextView.CommaTokenizer());

        singleComplete.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                selection.setText(singleComplete.getText());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
