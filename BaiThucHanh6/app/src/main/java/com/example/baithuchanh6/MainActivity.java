package com.example.baithuchanh6;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btn_gui;
    CheckBox checkBox_docbao, checkBox_docsach, checkBox_doccode;
    RadioGroup group;
    EditText edt_hoten, edt_cmnd, edt_bosung;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        edt_hoten = findViewById(R.id.edt_hoten);
        edt_cmnd = findViewById(R.id.edt_cmnd);
        edt_bosung = findViewById(R.id.edt_bosung);
        checkBox_docbao = findViewById(R.id.checkBox_docbao);
        checkBox_docsach = findViewById(R.id.checkBox_docsach);
        checkBox_doccode = findViewById(R.id.checkBox_doccode);
        group = findViewById(R.id.group);
        btn_gui = findViewById(R.id.btn_gui);

        btn_gui.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String hoten = edt_hoten.getText().toString();
                if (hoten.length() < 3){
                    Toast.makeText(MainActivity.this,"Ho ten phai >= 3 ki tu",Toast.LENGTH_LONG).show();
                    edt_hoten.requestFocus();
                    edt_hoten.selectAll();
                    return;
                }

                String cmnd = edt_cmnd.getText().toString();
                if (cmnd.length() != 9){
                    Toast.makeText(MainActivity.this,"Cmnd phai dung 9 so",Toast.LENGTH_LONG).show();
                    edt_cmnd.requestFocus();
                    edt_cmnd.selectAll();
                    return;
                }
                int idselect = group.getCheckedRadioButtonId();
                RadioButton radselect = findViewById(idselect);
                String bangcap = radselect.getText().toString();
                //so thich
                String sothich = "";
                if (checkBox_docbao.isChecked()){
                    sothich += checkBox_docbao.getText().toString() + "  ";
                } if (checkBox_doccode.isChecked()){
                    sothich += checkBox_doccode.getText().toString()+ "  ";
                }if (checkBox_docsach.isChecked()){
                    sothich += checkBox_docsach.getText().toString()+ "  ";
                }

                String bosung = edt_bosung.getText().toString();
                String tonghop = hoten + "\n" + bangcap + "\n" + sothich + "\n";
                    tonghop += "--------Thong tin bo sung--------- \n";
                    tonghop += bosung + "\n";
                    tonghop += "----------------------------------";


                //tao dialog
                AlertDialog.Builder mydialog = new AlertDialog.Builder(MainActivity.this);
                mydialog.setTitle("Thong tin ca nhan");
                mydialog.setMessage(tonghop);
                mydialog.setPositiveButton("Dong", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface DialogInterface, int i) {
                        DialogInterface.cancel();
                    }
                });
                        mydialog.create().show();

            }
        });

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}