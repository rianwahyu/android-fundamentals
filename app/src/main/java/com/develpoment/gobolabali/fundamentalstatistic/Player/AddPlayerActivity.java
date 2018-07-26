package com.develpoment.gobolabali.fundamentalstatistic.Player;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;


import android.app.DialogFragment;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Match.AddMatchListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import org.apache.commons.cli.HelpFormatter;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;

public class AddPlayerActivity extends AppCompatActivity {

    EditText etfullname, etnickname, etbirth, etpunggung, etteam,etPosisi;

    Button submit,reset;
    MaterialBetterSpinner spinner1;

    String idteamx,teamx;
    DatabaseHelper db = new DatabaseHelper(this);
    String posisiTampung, posisiNomor;

    Context context = AddPlayerActivity.this;
    Calendar calendar = Calendar.getInstance();
    String dateFormat ="dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    String selectedData;
    String selectedDataName;
    List<SpinnerData> list;
    ArrayAdapter<SpinnerData> listAdapter;
    ArrayAdapter<SpinnerData> listStatusAdapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbar.setTitle("Add Player");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        idteamx = getIntent().getStringExtra("idx");
        teamx = getIntent().getStringExtra("namax");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
                i.putExtra("id",idteamx);
                i.putExtra("nama", teamx);
                startActivity(i);
                finish();
            }
        });


        etfullname = (EditText) findViewById(R.id.et_fullname);
        etnickname = (EditText) findViewById(R.id.et_nickname);
//        etbirth = (EditText) findViewById(R.id.et_birth);
        etpunggung = (EditText) findViewById(R.id.et_noPunggung);
        etteam = (EditText) findViewById(R.id.et_team);
        spinner1 = (MaterialBetterSpinner) findViewById(R.id.spinner1);
//        etPosisi = (EditText) findViewById(R.id.et_posisi);

        etteam.setText(teamx);
        etteam.setEnabled(false);

        submit = (Button) findViewById(R.id.buttonSubmitPlayer);
        reset = (Button) findViewById(R.id.buttonResetPlayer);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekPlayer();
            }
        });

        setupPosition();

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(AddPlayerActivity.this)
                        .setTitle("Reset Form !")
                        .setBackgroundColor(Color.parseColor("#E12929"))
                        .setIcon(R.drawable.reset_icon_white, Icon.Visible)
                        .setMessage("Apakah Anda Yakin Reset Form ?")
                        .setNegativeBtnText("Tidak")
                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                        .setPositiveBtnText("Ya")
                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                        .setAnimation(Animation.SIDE)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                etfullname.setText("");
                                etnickname.setText("");
                                etbirth.setText("");
                                etpunggung.setText("");
//                                etteam.setText("");
                                spinner1.setText("");
                            }
                        })
                        .OnNegativeClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {

                            }
                        })
                        .build();
            }
        });

        final String[] posisi = new String[]{
                "Keeper",
                "Defender",
                "Midfielder",
                "Forward"
        };

        final List<String> posiisList = new ArrayList<>(Arrays.asList(posisi));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(AddPlayerActivity.this,android.R.layout.simple_spinner_item,posiisList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerAdapter);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectPosisi = (String) adapterView.getItemAtPosition(i).toString().toUpperCase();
                posisiTampung =selectPosisi;

                if (posisiTampung.equals("DEFENDER")){
                    posisiNomor = "21";
                }else if (posisiTampung.equals("MIDFIELDER")){
                    posisiNomor = "31";
                }else if (posisiTampung.equals("FORWARD")){
                    posisiNomor = "41";
                }else {
                    posisiNomor = "21";
                }

                String hasil = posisiNomor;

            }
        });

    }



    private void cekPlayer() {
        String fullname = etfullname.getText().toString().trim().toUpperCase();
        String nickname = etnickname.getText().toString().trim().toUpperCase();
//        String posisi = etPosisi.getText().toString().trim().toUpperCase();
        String posisi = posisiTampung;
        String posnom = posisiNomor;
//        String birth = etbirth.getText().toString().trim().toUpperCase();
        String nopunggung = etpunggung.getText().toString().trim().toUpperCase();
        String status ="0";

        if (fullname.equals("")||nickname.equals("")||posisi.equals("")||nopunggung.equals("")){
            Toast.makeText(getApplicationContext(),"Harap isi Semua Data", Toast.LENGTH_SHORT).show();
        }else {
            db.insertPlayer(fullname,nickname,posisi,posnom,nopunggung,status,Integer.valueOf(idteamx));

            ArrayList<HashMap<String,String>> row = db.getPlayerLast(Integer.parseInt(idteamx));

            for (int i=0; i <row.size(); i++){
                String idplayer = row.get(i).get("id");

                String nickname1 = row.get(i).get("nickname");
                String posisi1 = row.get(i).get("posisi");
                String posisinomor = row.get(i).get("posnomor");
                String nopunggung1 = row.get(i).get("nopunggung");
                String idteam = row.get(i).get("idteam");

                db.insertPlayerMatch2(Integer.parseInt(idteam),Integer.parseInt(idplayer),posisi1, posisinomor,nopunggung1,nickname1);

            }


            Toast.makeText(getApplicationContext(),"Sukses Tambah Player !", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
            i.putExtra("id",idteamx);
            i.putExtra("nama", teamx);
            startActivity(i);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
        i.putExtra("id",idteamx);
        i.putExtra("nama", teamx);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    public class SpinnerData {
        public String string;
        public Object tag;

        public SpinnerData(String string, Object tag) {
            this.string = string;
            this.tag = tag;
        }

        @Override
        public String toString() {
            return this.string;
        }
    }

    private void setupPosition() {
        list = new ArrayList();
        list.add(new SpinnerData(HelpFormatter.DEFAULT_OPT_PREFIX, " "));
        list.add(new SpinnerData("Defender (DF)", "21"));
        list.add(new SpinnerData("Midfielder (MF)", "31"));
        list.add(new SpinnerData("Forward (FD)", "41"));
    }
}
