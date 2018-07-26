package com.develpoment.gobolabali.fundamentalstatistic.Setting;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class SettingActivity extends AppCompatActivity {


    TextView textClearResult, textClearAll;
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSetting);
        toolbar.setTitle("Setting");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        textClearAll = (TextView) findViewById(R.id.textClearAll);
        textClearResult = (TextView) findViewById(R.id.textClearResult);

        textClearResult.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(SettingActivity.this)
                        .setTitle("Clear Result ?")
                        .setBackgroundColor(Color.parseColor("#E12929"))
                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                        .setMessage("Anda Yakin Ingin Menghapus Data  Penilaian?")
                        .setNegativeBtnText("Tidak")
                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                        .setPositiveBtnText("Ya")
                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                        .setAnimation(Animation.SIDE)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                db = new DatabaseHelper(SettingActivity.this);
                                db.deleteNilai();
                                Toast.makeText(getApplicationContext(), "Sukses Delete Data Penilaian", Toast.LENGTH_SHORT ).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {


                                /*db = new DatabaseHelper(SettingActivity.this);
                                *//*String mode = "all";
                                db.clear(mode);
                                Toast.makeText(getApplicationContext(), "Sukses Hapus Semua Data", Toast.LENGTH_SHORT).show();*//*

                                db.deleteAll();
                                Toast.makeText(getApplicationContext(), "Sukses Hapus Semua Data", Toast.LENGTH_SHORT).show();*/
                            }
                        })
                        .build();
            }
        });

        textClearAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(SettingActivity.this)
                        .setTitle("Clear All Data ?")
                        .setBackgroundColor(Color.parseColor("#E12929"))
                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                        .setMessage("Anda Yakin Ingin Menghapus Data  Seluruh Data?")
                        .setNegativeBtnText("Tidak")
                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                        .setPositiveBtnText("Ya")
                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                        .setAnimation(Animation.SIDE)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                db = new DatabaseHelper(SettingActivity.this);
                                db.deleteAll();
                                Toast.makeText(getApplicationContext(), "Sukses Delete Semua Data", Toast.LENGTH_SHORT ).show();
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {


                                /*db = new DatabaseHelper(SettingActivity.this);
                                *//*String mode = "all";
                                db.clear(mode);
                                Toast.makeText(getApplicationContext(), "Sukses Hapus Semua Data", Toast.LENGTH_SHORT).show();*//*

                                db.deleteAll();
                                Toast.makeText(getApplicationContext(), "Sukses Hapus Semua Data", Toast.LENGTH_SHORT).show();*/
                            }
                        })
                        .build();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
