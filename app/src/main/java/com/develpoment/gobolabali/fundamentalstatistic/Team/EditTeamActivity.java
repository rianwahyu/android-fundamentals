package com.develpoment.gobolabali.fundamentalstatistic.Team;

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
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

public class EditTeamActivity extends AppCompatActivity {

    Button btnSubmit, btnReset;
    EditText etNamaTeam;

    String idx,namax;

    DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_team);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTeam);
        toolbar.setTitle("Edit Team");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                startActivity(i);
                finish();
            }
        });

        btnReset = (Button) findViewById(R.id.buttonResetTeam);
        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(EditTeamActivity.this)
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

        btnSubmit = (Button) findViewById(R.id.buttonSubmitTeam);
        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cekTeam();
            }
        });

        etNamaTeam = (EditText) findViewById(R.id.et_namaTeam);

        idx = getIntent().getStringExtra("id");
        namax = getIntent().getStringExtra("nama");

        etNamaTeam.setText(namax);
    }

    private void cekTeam() {
        if (etNamaTeam.getText().toString().trim().equals("")){
            Toast.makeText(getApplicationContext(),"Isi data Team", Toast.LENGTH_LONG).show();
        }else {
            db.updateTeam(Integer.parseInt(idx),etNamaTeam.getText().toString().trim().toUpperCase());
            Toast.makeText(getApplicationContext(),"Sukses Edit Team", Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(), TeamActivity.class));
            finish();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), TeamActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }
}
