package com.develpoment.gobolabali.fundamentalstatistic.Match;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.EditTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

/**
 * Created by Rian on 01/06/2018.
 */

public class AddTournamentActivity extends AppCompatActivity {
    Button btnSubmit, btnReset;
    EditText etNamaTeam;

    DatabaseHelper db = new DatabaseHelper(this);    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_tournament);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTournament);
        toolbar.setTitle("Add Nama Tournament");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MatchActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnReset = (Button) findViewById(R.id.buttonResetTournament);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(AddTournamentActivity.this)
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
                                etNamaTeam.setText("");
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

        btnSubmit = (Button) findViewById(R.id.buttonSubmitTournament);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekTournament();
            }
        });

        etNamaTeam = (EditText) findViewById(R.id.et_namaTournament);
    }

    private void cekTournament() {
        String team = etNamaTeam.getText().toString();

        if (team.equals("")){
            Toast.makeText(getApplicationContext(),"Isi Nama Tournament !", Toast.LENGTH_LONG).show();
        }else {
            db.insertTournament(etNamaTeam.getText().toString().trim());
            Toast.makeText(getApplicationContext(),"Sukses Tambah Tournament", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), MatchActivity.class));
            finish();
        }
    }
}
