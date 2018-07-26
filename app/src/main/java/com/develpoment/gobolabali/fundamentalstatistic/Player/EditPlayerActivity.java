package com.develpoment.gobolabali.fundamentalstatistic.Player;

import android.annotation.SuppressLint;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
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
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

public class EditPlayerActivity extends AppCompatActivity {

    EditText etfullname, etnickname, etbirth, etpunggung, etteam,etPosisi;

    Button submit,reset;
    MaterialBetterSpinner spinner1;

//    String idteamx,teamx;
    DatabaseHelper db = new DatabaseHelper(this);
    String posisiTampung;

    String idpx,fullpx,nickpx,pospx,birthpx,punggungpx,idteampx;

    String teamnamex;

    Context context = EditPlayerActivity.this;
    Calendar calendar = Calendar.getInstance();
    String dateFormat ="dd.MM.yyyy";
    DatePickerDialog.OnDateSetListener date;
    SimpleDateFormat sdf = new SimpleDateFormat(dateFormat);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_player);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbar.setTitle("Edit Player");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        idpx = getIntent().getStringExtra("id");
        fullpx = getIntent().getStringExtra("fullname");
        nickpx = getIntent().getStringExtra("nickname");
        pospx = getIntent().getStringExtra("posisi");
//        birthpx = getIntent().getStringExtra("birth");
        punggungpx = getIntent().getStringExtra("punggung");
        idteampx = getIntent().getStringExtra("idteam");

        etfullname = (EditText) findViewById(R.id.et_fullname);
        etnickname = (EditText) findViewById(R.id.et_nickname);
//        etbirth = (EditText) findViewById(R.id.et_birth);
        etpunggung = (EditText) findViewById(R.id.et_noPunggung);
        etteam = (EditText) findViewById(R.id.et_team);
        spinner1 = (MaterialBetterSpinner) findViewById(R.id.spinner1);

        submit = (Button) findViewById(R.id.buttonSubmitPlayer);
        reset = (Button) findViewById(R.id.buttonResetPlayer);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekPlayer();
            }
        });

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(EditPlayerActivity.this)
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
                "Goal Keeper",
                "Defender",
                "Midfielder",
                "Forward"
        };

        etfullname.setText(fullpx);
        etnickname.setText(nickpx);
        spinner1.setText(pospx);
//        etbirth.setText(birthpx);
        etpunggung.setText(punggungpx);

        etteam.setText(db.getTeamName(idteampx));
        etteam.setEnabled(false);
        teamnamex = db.getTeamName(idteampx);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
                i.putExtra("id",idteampx);
                i.putExtra("nama", teamnamex);
                startActivity(i);
                finish();
            }
        });

        final List<String> posiisList = new ArrayList<>(Arrays.asList(posisi));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(EditPlayerActivity.this,android.R.layout.simple_spinner_item,posiisList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinnerAdapter);
        spinner1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectPosisi = (String) adapterView.getItemAtPosition(i).toString().toUpperCase();

                posisiTampung =selectPosisi;

            }
        });

        /*
        etbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerFragment dpf = new DatePickerFragment(view);
                android.app.FragmentTransaction ft = getFragmentManager().beginTransaction();
                dpf.show(ft,"DatePicker");
            }

            @SuppressLint("ValidFragment")
            class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener {
                public DatePickerFragment(View view) {
                }

                @Override
                public Dialog onCreateDialog(Bundle savedInstanceState) {
                    final Calendar c = Calendar.getInstance();
                    int year = c.get(Calendar.YEAR);
                    int month = c.get(Calendar.MONTH);
                    int day = c.get(Calendar.DAY_OF_MONTH);
                    return new DatePickerDialog(getActivity(),this,year,month,day);
                }

                @Override
                public void onDateSet(DatePicker view, int year, int monthOfYear,
                                      int dayOfMonth) {
                    int month = monthOfYear + 1;
                    String formattedMonth = "" + month;
                    String formattedDayOfMonth = "" + dayOfMonth;

                    if(month < 10){

                        formattedMonth = "0" + month;
                    }
                    if(dayOfMonth < 10){

                        formattedDayOfMonth = "0" + dayOfMonth;
                    }
                    etbirth.setText(formattedDayOfMonth + "/" + formattedMonth + "/" + year);
                }
            }
        });

*/
        /*date = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, year);
                calendar.set(Calendar.DAY_OF_MONTH, day);
                updateDate();
            }
        };

        etbirth.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new DatePickerDialog(context, date, calendar
                        .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                        calendar.get(Calendar.DAY_OF_MONTH)).show();
            }
        });

*/

    }



    private void cekPlayer() {
        String fullname = etfullname.getText().toString().trim().toUpperCase();
        String nickname = etnickname.getText().toString().trim().toUpperCase();
//        String posisi = etPosisi.getText().toString().trim().toUpperCase();
        String posisi;
        if (posisiTampung==null){
             posisi = pospx;
        }else {
            posisi = posisiTampung;
        }


//        String birth = etbirth.getText().toString().trim().toUpperCase();
        String nopunggung = etpunggung.getText().toString().trim().toUpperCase();
        String status ="0";

        if (fullname.equals("")||nickname.equals("")||nopunggung.equals("")||idteampx.equals("")){
            Toast.makeText(getApplicationContext(),"Harap isi Semua Data", Toast.LENGTH_LONG).show();
        }else {
            db.updatePlayer(Integer.parseInt(idpx),fullname,nickname,posisi,nopunggung,status,Integer.parseInt(idteampx));
            Toast.makeText(getApplicationContext(),"Sukses Edit Player !", Toast.LENGTH_LONG).show();
            Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
            i.putExtra("id",idteampx);
            i.putExtra("nama", teamnamex);
            startActivity(i);
            finish();
        }

    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), PlayerActivity.class);
        i.putExtra("id",idteampx);
        i.putExtra("nama", teamnamex);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
