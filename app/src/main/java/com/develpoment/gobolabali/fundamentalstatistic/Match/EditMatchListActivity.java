package com.develpoment.gobolabali.fundamentalstatistic.Match;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.app.TimePickerDialog;
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
import android.widget.TimePicker;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

/**
 * Created by Rian on 01/06/2018.
 */

public class EditMatchListActivity extends AppCompatActivity {

    MaterialBetterSpinner spinnerHome, spinnerAway;

    EditText etnamapertandingan, ettanggalpertandigan, etstat,etend;

    Button submit, reset;

    DatabaseHelper db = new DatabaseHelper(this);
    DatePickerDialog datePickerDialog;
    String idx,idtournamentx,namapertanidnganx,idteam1x,idteam2x,tanggalx,mulaix,akhirx;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_match_list);

        idtournamentx = getIntent().getStringExtra("idtournament");
        idx = getIntent().getStringExtra("id");
        namapertanidnganx=getIntent().getStringExtra("namapertandingan");
        idteam1x = getIntent().getStringExtra("idteam1");
        idteam2x = getIntent().getStringExtra("idteam2");
        tanggalx = getIntent().getStringExtra("tanggal");
        mulaix = getIntent().getStringExtra("mulai");
        akhirx = getIntent().getStringExtra("akhir");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMatch);
        toolbar.setTitle("Edit Match List");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MatchListActivity.class);
                i.putExtra("id",idtournamentx);
//                i.putExtra("namatournament",namatournamentx);
                startActivity(i);
                finish();
            }
        });

        spinnerHome = (MaterialBetterSpinner) findViewById(R.id.spinnerHome);
        spinnerAway = (MaterialBetterSpinner) findViewById(R.id.spinnerAway);

        etnamapertandingan = (EditText) findViewById(R.id.et_namaPertandingan);
        ettanggalpertandigan = (EditText) findViewById(R.id.et_jadwalPertandingan);
        etstat = (EditText) findViewById(R.id.et_start);
        etend = (EditText) findViewById(R.id.et_end);

        etnamapertandingan.setText(namapertanidnganx);
        spinnerHome.setText(db.getTeamName(idteam1x));
        spinnerAway.setText(db.getTeamName(idteam2x));
        ettanggalpertandigan.setText(tanggalx);
        etstat.setText(mulaix);
        etend.setText(akhirx);

        submit = (Button) findViewById(R.id.buttonSubmitMatch);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekMatch();
            }
        });

        loadSpinHome();
        loadSpinAway();

        reset = (Button) findViewById(R.id.buttonResetMatch);
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(EditMatchListActivity.this)
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
                                etnamapertandingan.setText("");
                                ettanggalpertandigan.setText("");
                                etstat.setText("");
                                etend.setText("");
                                spinnerHome.setText("");
                                spinnerAway.setText("");
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


        ettanggalpertandigan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showDateDialog();
            }

        });

        etstat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditMatchListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etstat.setText(String.format("%02d:%02d", selectedHour, selectedMinute));

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });

        etend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SimpleDateFormat sdf = new SimpleDateFormat("hh:mm");
                Calendar mcurrentTime = Calendar.getInstance();
                int hour = mcurrentTime.get(Calendar.HOUR_OF_DAY);
                int minute = mcurrentTime.get(Calendar.MINUTE);
                TimePickerDialog mTimePicker;
                mTimePicker = new TimePickerDialog(EditMatchListActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker timePicker, int selectedHour, int selectedMinute) {
                        etend.setText(String.format("%02d:%02d", selectedHour, selectedMinute));

                    }
                }, hour, minute, true);//Yes 24 hour time
                mTimePicker.setTitle("Select Time");
                mTimePicker.show();
            }
        });
    }

    private void showDateDialog() {
        Calendar newCalendar = Calendar.getInstance();

        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {

                Calendar newDate = Calendar.getInstance();
                newDate.set(year, month,dayOfMonth);

                String formatTanggal = "dd-MM-yyyy";
                SimpleDateFormat sdf = new SimpleDateFormat(formatTanggal);

                ettanggalpertandigan.setText(sdf.format(newDate.getTime()));
            }
        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

        datePickerDialog.show();
    }

    String idteam1;
    String namateam1;
    private void loadSpinHome() {

        ArrayList spinnHomeList = db.getSpinnerTeam();

        DataTeam dataTeam = new DataTeam();
        dataTeam.getNama();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(EditMatchListActivity.this,android.R.layout.simple_spinner_item,spinnHomeList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerHome.setAdapter(spinnerAdapter);
        spinnerHome.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "You selected: " + label,
                        Toast.LENGTH_LONG).show();

                DataTeam dataTeam = (DataTeam) adapterView.getItemAtPosition(i);
                idteam1 = dataTeam.getId();
                namateam1 = dataTeam.getNama();
            }
        });

    }

    String idteam2;
    String namateam2;
    private void loadSpinAway() {
        ArrayList spinnAwayList = db.getSpinnerTeam();

        DataTeam dataTeam = new DataTeam();
        dataTeam.getNama();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(EditMatchListActivity.this,android.R.layout.simple_spinner_item,spinnAwayList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAway.setAdapter(spinnerAdapter);
        spinnerAway.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = adapterView.getItemAtPosition(i).toString();
                Toast.makeText(getApplicationContext(), "You selected: " + label,
                        Toast.LENGTH_LONG).show();

                DataTeam dataTeam = (DataTeam) adapterView.getItemAtPosition(i);
                idteam2 = dataTeam.getId();
                namateam2 = dataTeam.getNama();
            }
        });
    }

    private void cekMatch() {
        String namapertandingan = etnamapertandingan.getText().toString().trim().toUpperCase();
        String tanggalpertandingan = ettanggalpertandigan.getText().toString().trim().toUpperCase();
        String mulai = etstat.getText().toString().trim().toUpperCase();
        String akhir = etend.getText().toString().trim().toUpperCase();
        String home = spinnerHome.getText().toString();
        String away = spinnerAway.getText().toString();

        if (namapertandingan.equals("") || tanggalpertandingan.equals("") || mulai.equals("")||
                akhir.equals("") || home.equals("") || away.equals("")){
            Toast.makeText(getApplicationContext(),"Isi Semua Data ! ", Toast.LENGTH_LONG).show();
        }else {
            db.updateMatch(Integer.parseInt(idx),namapertandingan,idtournamentx,idteam1,idteam2,tanggalpertandingan,mulai,akhir);
            Toast.makeText(getApplicationContext(),"Sukses Edit Match ! ", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), MatchListActivity.class);
            i.putExtra("id",idtournamentx);
//            i.putExtra("namatournament",namatournamentx);
            startActivity(i);
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MatchListActivity.class);
        i.putExtra("id",idtournamentx);
        /*i.putExtra("namatournament",namatournamentx);*/
        startActivity(i);
        finish();
//        super.onBackPressed();
    }
}
