package com.develpoment.gobolabali.fundamentalstatistic.Setting;

import android.app.ProgressDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.Base;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.MatchListOnline;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.PlayerOnline;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.RestManager;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.TeamOnline;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Online.TournamentOnline;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Utils;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchActivity;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.facebook.stetho.server.http.HttpStatus;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SettingActivity extends AppCompatActivity {


    TextView textClearResult, textClearAll, textImport;
    private DatabaseHelper db = new DatabaseHelper(this);
    RestManager mManager;
    ProgressDialog mDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_setting);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarSetting);
        toolbar.setTitle("Setting");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        mManager = new RestManager();

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
        textImport = (TextView) findViewById(R.id.textImport);

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
                            }
                        })
                        .build();
            }
        });

        textImport.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(SettingActivity.this)
                        .setTitle("Import Data ?")
                        .setBackgroundColor(Color.parseColor("#E12929"))
//                        .setIcon(R.drawable.ic_trash, Icon.Visible)
                        .setMessage("Anda Yakin Ingin Import Data ?")
                        .setNegativeBtnText("Tidak")
                        .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                        .setPositiveBtnText("Ya")
                        .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                        .setAnimation(Animation.SIDE)
                        .isCancellable(true)
                        .OnPositiveClicked(new FancyAlertDialogListener() {
                            @Override
                            public void OnClick() {
                                importOnline();
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
    }

    private void importOnline() {
        if (getNetworkAvailability()) {
            mDialog = new ProgressDialog(SettingActivity.this);
            mDialog.setMessage("Importing Data..");
            mDialog.setCancelable(false);
            mDialog.setProgressStyle(0);
            mDialog.show();
            mManager.getDataService().importAndroid().enqueue(new Callback<Base>() {
                @Override
                public void onResponse(Call<Base> call, Response<Base> response) {
                    if (response.isSuccessful()){
                        Base resp = (Base) response.body();
                        List<TeamOnline> importTeam = resp.getTeam();
                        List<TournamentOnline> importTournament = resp.getTournament();
                        List<PlayerOnline> importPlayer = resp.getPlayer();
                        List<MatchListOnline> importMatchlist = resp.getMatchlist();
                        ArrayList<TeamOnline> arrayTeam = new ArrayList<>();
                        ArrayList<TournamentOnline> arrayTournament = new ArrayList<>();
                        ArrayList<PlayerOnline> arrayPlayer = new ArrayList<>();
                        ArrayList<MatchListOnline> arrayMatchList = new ArrayList<>();

                        if (!resp.equals("")){
                            //int i;

                            for (int i=0;i<importTournament.size();i++){
                                TournamentOnline curTournament = (TournamentOnline) importTournament.get(i);
                                arrayTournament.add(new TournamentOnline(curTournament.getIdTournament(), curTournament.getNamaTournament()));
                            }

/*                            for (i=0; i<importTeam.size();i++){
                                TeamOnline curTeam = (TeamOnline) importTeam.get(i);
                                arrayTeam.add(new TeamOnline(curTeam.getIdTeam(), curTeam.getNamaTeam()));
                            }



                            for (i=0;i<importPlayer.size();i++){
                                PlayerOnline curPlayer = (PlayerOnline) importPlayer.get(i);
                                arrayPlayer.add(new PlayerOnline(curPlayer.getNik(), curPlayer.getFullname(),
                                        curPlayer.getNickname(), curPlayer.getPosisi(), curPlayer.getPosnomor(),
                                        curPlayer.getNopunggung(),curPlayer.getStatus(), curPlayer.getIdTeam()));
                            }

                            for (i=0; i<importMatchlist.size();i++){
                                MatchListOnline curMatchList = (MatchListOnline) importMatchlist.get(i);
                                arrayMatchList.add(new MatchListOnline(curMatchList.getNamaPertandingan(),curMatchList.getIdTeam1(),
                                        curMatchList.getIdTeam2(), curMatchList.getIdTournament(),curMatchList.getTanggal(),
                                        curMatchList.getMulai(), curMatchList.getAkhir(),curMatchList.getStatus()));
                            }*/

                        }else {
                            int sc = response.code();
                            switch (sc){
                                case 400:
                                    Log.e("Error 400", "Bad Request");
                                    break;
                                case HttpStatus.HTTP_NOT_FOUND:
                                    Log.e("Error 404", "Not Found");
                                    break;
                                default:
                                    Log.e("Error", "Generic Error");
                                    break;
                            }
                            Toast.makeText(getApplicationContext(),"Failed Import. Error Code :" +sc, Toast.LENGTH_LONG).show();
                        }
                        mDialog.dismiss();
                    }
                }

                @Override
                public void onFailure(Call<Base> call, Throwable t) {
                    mDialog.dismiss();
                    Toast.makeText(getApplicationContext(), t.getMessage(), Toast.LENGTH_LONG).show();
                }
            });
            return;
        }
        mDialog.dismiss();
        Toast.makeText(getApplicationContext(), "Mohon aktifkan koneksi internet anda.", Toast.LENGTH_SHORT).show();
    }

    public boolean getNetworkAvailability() {
        return Utils.isNetworkAvailable(getApplicationContext());
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
