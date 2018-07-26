package com.develpoment.gobolabali.fundamentalstatistic.Games;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.os.Vibrator;
import android.support.annotation.Nullable;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.util.TypedValue;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterPlayerPosition;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterPlayerPosition2;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.CadanganAdapter;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.CadanganAdapter2;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.CSVWritter;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.DataExport;
import com.develpoment.gobolabali.fundamentalstatistic.Helpers.Utils;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer2;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Player;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Temp;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Player.PlayerActivity2;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import java.io.File;
import java.io.FileWriter;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class GameActivity extends AppCompatActivity implements CadanganAdapter.ClickListener, CadanganAdapter2.ClickListener {

    String action;
    String action_name;
    boolean boolCadangan = true;
    int boolMode = 0;
    public Button btnLeft11;
    public Button btnLeft21;
    public Button btnLeft22;
    public Button btnLeft23;
    public Button btnLeft24;
    public Button btnLeft31;
    public Button btnLeft32;
    public Button btnLeft33;
    public Button btnLeft34;
    public Button btnLeft41;
    public Button btnLeft42;
    public Button btnLeft43;
    public Button btnLeft44;
    public Button btnRight11;
    public Button btnRight21;
    public Button btnRight22;
    public Button btnRight23;
    public Button btnRight24;
    public Button btnRight31;
    public Button btnRight32;
    public Button btnRight33;
    public Button btnRight34;
    public Button btnRight41;
    public Button btnRight42;
    public Button btnRight43;
    public Button btnRight44;

    public TextView txtLeft11;
    public TextView txtLeft21;
    public TextView txtLeft22;
    public TextView txtLeft23;
    public TextView txtLeft24;
    public TextView txtLeft31;
    public TextView txtLeft32;
    public TextView txtLeft33;
    public TextView txtLeft34;
    public TextView txtLeft41;
    public TextView txtLeft42;
    public TextView txtLeft43;
    public TextView txtLeft44;
    public TextView txtRight11;
    public TextView txtRight21;
    public TextView txtRight22;
    public TextView txtRight23;
    public TextView txtRight24;
    public TextView txtRight31;
    public TextView txtRight32;
    public TextView txtRight33;
    public TextView txtRight34;
    public TextView txtRight41;
    public TextView txtRight42;
    public TextView txtRight43;
    public TextView txtRight44;

    CadanganAdapter cadanganAdapter1;
    CadanganAdapter2 cadanganAdapter2;
    LinearLayout content_match;
    public CoordinatorLayout coordinatorLayout;
    String curCadanganNickname = "";
    String curCadanganNo = null;
    String curCadanganTeam = "";

    Boolean editMode = Boolean.valueOf(false);
    double hCadangan;

    List<String> kananList = new ArrayList();
    List<String> kiriList = new ArrayList();
    public Button[] listButton = new Button[]{btnLeft11, btnLeft21, btnLeft22, btnLeft23, btnLeft24, btnLeft31, btnLeft32, btnLeft33, btnLeft34, btnLeft41, btnLeft42, btnLeft43, btnLeft44, btnRight11, btnRight21, btnRight22, btnRight23, btnRight24, btnRight31, btnRight32, btnRight33, btnRight34, btnRight41, btnRight42, btnRight43, btnRight44};
    public String[] listPosition = new String[]{"11", "21", "22", "23", "24", "31", "32", "33", "34", "41", "42", "43", "44"};
    public TextView[] listTextView = new TextView[]{txtLeft11, txtLeft21, txtLeft22, txtLeft23, txtLeft24, txtLeft31, txtLeft32, txtLeft33, txtLeft34, txtLeft41, txtLeft42, txtLeft43, txtLeft44,txtRight11, txtRight21, txtRight22, txtRight23, txtRight24, txtRight31, txtRight32, txtRight33, txtRight34, txtRight41, txtRight42, txtRight43, txtRight44};
    LinearLayout llCadangan;
    public LinearLayout llField;
    ProgressDialog mDialog;
//    RestManager mManager;

    RecyclerView rv1;
    RecyclerView rv2;
    Button selButton;
    TextView selText;

    String idtournament,namatournament, idmatch,namamatch, idteam1, idteam2, category, namateam1,namateam2;
    private DatabaseHelper db = new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        idmatch     = getIntent().getStringExtra("idmatch");
        namamatch   = getIntent().getStringExtra("nmmatch");
        idteam1     = getIntent().getStringExtra("idteam1");
        idteam2     = getIntent().getStringExtra("idteam2");
        category    = getIntent().getStringExtra("category");
        namateam1   = getIntent().getStringExtra("nmteam1");
        namateam2   = getIntent().getStringExtra("nmteam2");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGame);
        toolbar.setTitle(namamatch + "   ( " +namateam1 + " " + " vs " +" " + namateam2 +" )");
        toolbar.setSubtitle("Kategori : " + category);
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                //return true;
            }
        });

        llField = (LinearLayout) findViewById(R.id.soccer_field);
        llCadangan = (LinearLayout) findViewById(R.id.llCadangan);
        content_match = (LinearLayout) findViewById(R.id.content_match);
        coordinatorLayout = (CoordinatorLayout) findViewById(R.id.colayoutGame);
        txtLeft11 = (TextView) findViewById(R.id.ltext_1_1);
        txtLeft21 = (TextView) findViewById(R.id.ltext_2_1);
        txtLeft22 = (TextView) findViewById(R.id.ltext_2_2);
        txtLeft23 = (TextView) findViewById(R.id.ltext_2_3);
        txtLeft24 = (TextView) findViewById(R.id.ltext_2_4);
        txtLeft31 = (TextView) findViewById(R.id.ltext_3_1);
        txtLeft32 = (TextView) findViewById(R.id.ltext_3_2);
        txtLeft33 = (TextView) findViewById(R.id.ltext_3_3);
        txtLeft34 = (TextView) findViewById(R.id.ltext_3_4);
        txtLeft41 = (TextView) findViewById(R.id.ltext_4_1);
        txtLeft42 = (TextView) findViewById(R.id.ltext_4_2);
        txtLeft43 = (TextView) findViewById(R.id.ltext_4_3);
        txtLeft44 = (TextView) findViewById(R.id.ltext_4_4);

        txtRight11 = (TextView) findViewById(R.id.rtext_1_1);
        txtRight21 = (TextView) findViewById(R.id.rtext_2_1);
        txtRight22 = (TextView) findViewById(R.id.rtext_2_2);
        txtRight23 = (TextView) findViewById(R.id.rtext_2_3);
        txtRight24 = (TextView) findViewById(R.id.rtext_2_4);
        txtRight31 = (TextView) findViewById(R.id.rtext_3_1);
        txtRight32 = (TextView) findViewById(R.id.rtext_3_2);
        txtRight33 = (TextView) findViewById(R.id.rtext_3_3);
        txtRight34 = (TextView) findViewById(R.id.rtext_3_4);
        txtRight41 = (TextView) findViewById(R.id.rtext_4_1);
        txtRight42 = (TextView) findViewById(R.id.rtext_4_2);
        txtRight43 = (TextView) findViewById(R.id.rtext_4_3);
        txtRight44 = (TextView) findViewById(R.id.rtext_4_4);

        setupFieldMode(boolMode);
        setupButton();
    }


    private void setupFieldMode(int mode) {
        int width = getWindowManager().getDefaultDisplay().getWidth();

        if (mode == 0) {
            llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) width) / 1.7d)));
        }else {
            llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((double) (this.content_match.getHeight() - ((int) TypedValue.applyDimension(1, 62.0f, getResources().getDisplayMetrics()))))));
        }
    }

    private void setupButton() {
        btnLeft11 = (Button) findViewById(R.id.left_1_1);
        btnLeft21 = (Button) findViewById(R.id.left_2_1);
        btnLeft22 = (Button) findViewById(R.id.left_2_2);
        btnLeft23 = (Button) findViewById(R.id.left_2_3);
        btnLeft24 = (Button) findViewById(R.id.left_2_4);
        btnLeft31 = (Button) findViewById(R.id.left_3_1);
        btnLeft32 = (Button) findViewById(R.id.left_3_2);
        btnLeft33 = (Button) findViewById(R.id.left_3_3);
        btnLeft34 = (Button) findViewById(R.id.left_3_4);
        btnLeft41 = (Button) findViewById(R.id.left_4_1);
        btnLeft42 = (Button) findViewById(R.id.left_4_2);
        btnLeft43 = (Button) findViewById(R.id.left_4_3);
        btnLeft44 = (Button) findViewById(R.id.left_4_4);

        btnRight11 = (Button) findViewById(R.id.right_1_1);
        btnRight21 = (Button) findViewById(R.id.right_2_1);
        btnRight22 = (Button) findViewById(R.id.right_2_2);
        btnRight23 = (Button) findViewById(R.id.right_2_3);
        btnRight24 = (Button) findViewById(R.id.right_2_4);
        btnRight31 = (Button) findViewById(R.id.right_3_1);
        btnRight32 = (Button) findViewById(R.id.right_3_2);
        btnRight33 = (Button) findViewById(R.id.right_3_3);
        btnRight34 = (Button) findViewById(R.id.right_3_4);
        btnRight41 = (Button) findViewById(R.id.right_4_1);
        btnRight42 = (Button) findViewById(R.id.right_4_2);
        btnRight43 = (Button) findViewById(R.id.right_4_3);
        btnRight44 = (Button) findViewById(R.id.right_4_4);

        btnLeft11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft11, txtLeft11 );
                return false;
            }
        });

        btnLeft11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                if (category.equals("KEEPER")){
                    executeClick(b, idteam1);
                }else if (editMode.booleanValue()){
                    executeClick(b, idteam1);
                }else {
                    runSnack("Tidak Ada penilaian Untuk Posisi ini");
                }
            }
        });

        btnLeft21.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft21, txtLeft21 );
                return false;
            }
        });

        btnLeft21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft22.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft22, txtLeft22 );
                return false;
            }
        });

        btnLeft22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft23.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft23, txtLeft23 );
                return false;
            }
        });

        btnLeft23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft24.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft24, txtLeft24 );
                return false;
            }
        });

        btnLeft24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft31.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft31, txtLeft31 );
                return false;
            }
        });

        btnLeft31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft32.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft32, txtLeft32 );
                return false;
            }
        });

        btnLeft32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft33.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft33, txtLeft33 );
                return false;
            }
        });

        btnLeft33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft34.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft34, txtLeft34 );
                return false;
            }
        });

        btnLeft34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft41.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft41, txtLeft41 );
                return false;
            }
        });

        btnLeft41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft42.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft42, txtLeft42 );
                return false;
            }
        });

        btnLeft42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft43.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft43, txtLeft43 );
                return false;
            }
        });

        btnLeft43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnLeft44.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam1, btnLeft44, txtLeft44 );
                return false;
            }
        });

        btnLeft44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam1);
            }
        });

        btnRight11.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight11, txtRight11 );
                return false;
            }
        });

        btnRight11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Button b = (Button) view;
                if (category.equals("KEEPER")){
                    executeClick(b, idteam2);
                }else if (editMode.booleanValue()){
                    executeClick(b, idteam2);
                }else {
                    runSnack("Tidak Ada penilaian Untuk Posisi ini");
                }

            }
        });

        btnRight21.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight21, txtRight21 );
                return false;
            }
        });

        btnRight21.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);

            }
        });

        btnRight22.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight22, txtRight22 );
                return false;
            }
        });

        btnRight22.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight23.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight23, txtRight23 );
                return false;
            }
        });

        btnRight23.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight24.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight24, txtRight24 );
                return false;
            }
        });

        btnRight24.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight31.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight31, txtRight31 );
                return false;
            }
        });

        btnRight31.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight32.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight32, txtRight32 );
                return false;
            }
        });

        btnRight32.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });



        btnRight33.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight33, txtRight33 );
                return false;
            }
        });

        btnRight33.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight34.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight34, txtRight34 );
                return false;
            }
        });

        btnRight34.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight41.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight41, txtRight41 );
                return false;
            }
        });

        btnRight41.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight42.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight42, txtRight42 );
                return false;
            }
        });

        btnRight42.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight43.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight43, txtRight43 );
                return false;
            }
        });

        btnRight43.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        btnRight44.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                executeLong(idteam2, btnRight44, txtRight44 );
                return false;
            }
        });

        btnRight44.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                executeClick((Button) view, idteam2);
            }
        });

        if (db.checkMatchStatus(idmatch)) {
            fetchformation(idteam1);
            fetchformation(idteam2);
        }

   /*     String statusmatch = db.getStatusMatch(idmatch);
        if (statusmatch.equals("0")){
            fetchformation(idteam1);
            fetchformation(idteam2);
        }else {
            runSnack("Syudah Ada Pertandingan");
        }*/
        setupRvCadangan();

    }

    private void fetchformation(String team) {

        Button[] listButton = new Button[]{btnLeft11, btnLeft21, btnLeft22, btnLeft23, btnLeft24,
                btnLeft31, btnLeft32, btnLeft33, btnLeft34,
                btnLeft41, btnLeft42, btnLeft43, btnLeft44,
                btnRight11, btnRight21, btnRight22, btnRight23, btnRight24,
                btnRight31, btnRight32, btnRight33, btnRight34,
                btnRight41, btnRight42, btnRight43, btnRight44};

        TextView[] listTextview = new TextView[]{txtLeft11, txtLeft21, txtLeft22, txtLeft23, txtLeft24,
                txtLeft31, txtLeft32, txtLeft33, txtLeft34,
                txtLeft41, txtLeft42, txtLeft43, txtLeft44,
                txtRight11, txtRight21, txtRight22, txtRight23, txtRight24,
                txtRight31, txtRight32, txtRight33, txtRight34,
                txtRight41, txtRight42, txtRight43, txtRight44};

        String[] listPosition = new String[]{"11", "21", "22", "23", "24",
                "31", "32", "33", "34",
                "41", "42", "43", "44"};

        Cursor formation = db.getFormation(idmatch, team);
        String pos = checkTeam(team);
        Log.e("category", category);

        while (formation.moveToNext()){
            String player_id = formation.getString(formation.getColumnIndex("idplayer"));
            String data_pos = formation.getString(formation.getColumnIndex("posnomor"));
            String player_number = formation.getString(formation.getColumnIndex("nopunggung"));
            String player_nick = formation.getString(formation.getColumnIndex("nickname"));

            int POS_INDEX = getIndex(listPosition, data_pos);

            if (!data_pos.equals("")){

                int index;
                if (pos.equals("left")){
                    index = POS_INDEX;
                    if (!category.equals("KEEPER")){
                        kiriList.add(player_number);
                    }else if (data_pos.equals("11")){
                        kiriList.add(player_number);
                    }
                }else {
                    index = 13 + POS_INDEX;
                    if (!category.equals("KEEPER")){
                        kananList.add(player_number);
                    }else if (data_pos.equals("11")){
                        kananList.add(player_number);
                    }
                }
                Button playerButton = listButton[index];
                TextView playerText;
                if (!category.equals("KEEPER")){
                    playerText = listTextview[index];
                    playerButton.setText(player_number);
                    playerText.setText(player_nick);
                }else if (data_pos.equals("11")){
                    playerText = listTextview[index];
                    playerButton.setText(player_number);
                    playerText.setText(player_nick);
                }

                if (!pos.equals("left")){
                    if (!category.equals("KEEPER")){
                        playerButton.setBackgroundResource(R.drawable.touch_blue);
                    }else if (data_pos.equals("11")){
                        playerButton.setBackgroundResource(R.drawable.touch_blue);
                    }
                    runSnack("Formasi kedua tim berhasil di susun");
                }else if (!category.equals("KEEPER")){
                    playerButton.setBackgroundResource(R.drawable.touch_green);
                }else if (category.equals("11")){
                    playerButton.setBackgroundResource(R.drawable.touch_green);
                }
            }
        }
    }


    private void setupRvCadangan() {
        rv1 = (RecyclerView) findViewById(R.id.rvTeamA);
        rv2 = (RecyclerView) findViewById(R.id.rvTeamB);
        cadanganAdapter1 = new CadanganAdapter(this);
        cadanganAdapter2 = new CadanganAdapter2(this);
        getCadangan(idteam1, cadanganAdapter1, null);
        getCadangan(idteam2, null, cadanganAdapter2);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        rv1.setAdapter(cadanganAdapter1);
        rv2.setHasFixedSize(true);
        rv2.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, true));
        rv2.setAdapter(cadanganAdapter2);

    }

    private void getCadangan(String team, CadanganAdapter pa, CadanganAdapter2 pa2) {
        List<Player> dataPlayers = new ArrayList<>();
        Cursor c = db.getPlayerCadangan(team, idmatch);
        if (c !=null){
            if (pa == null){
                pa2.clearItem();
            }else {
                pa.clearItem();
            }

            while (c.moveToNext()){

                String idplayer = c.getString(c.getColumnIndex("id"));
                String status = c.getString(c.getColumnIndex("status"));
                String nopunggung = c.getString(c.getColumnIndex("nopunggung"));
                String nickname = c.getString(c.getColumnIndex("nickname"));

                Player dataPlayer2 = new Player();
                dataPlayer2.setId(idplayer);
                dataPlayer2.setPlayer_number(nopunggung);
                dataPlayer2.setStatus(status);
                dataPlayer2.setNickname(nickname);

                if (pa == null){
                    pa2.addItem(dataPlayer2);
                }else {
                    pa.addItem(dataPlayer2);
                }
            }
        }
    }

    private void executeLong(String idteam, Button btn, TextView txt) {
        String btext = btn.getText().toString();
        Log.e("LOG-TEXT", btext);
        if (btext.equals("")){
            openPlayer("new", idteam, btn, txt);
        }else {
            openPlayerOpt(idteam, btn, txt);
        }
    }

    private void executeClick(Button b, String idteam) {
        String btext = b.getText().toString();
        if (!btext.equals("")){
            if (!editMode.booleanValue()){
                runAction(idteam, btext);
            } else if (curCadanganTeam.equals(idteam)){
                openCadangan(idteam, b, getTextView(getIndexButton(b)));
                runSnack("Berhasil dipilih");
                fieldMode(Boolean.valueOf(true));
                loadCadangan();
            }else {
                runSnack("Pemain ini tidak bisa dipilih. Pilih pemain dari tim asal.");
            }
        }
    }

    private void loadCadangan() {
        getCadangan(idteam1, cadanganAdapter1,null);
        getCadangan(idteam2, null, cadanganAdapter2);
    }

    private void fieldMode(Boolean b) {
        if (b.booleanValue()){
            editMode = Boolean.valueOf(false);
            llField.setBackgroundResource(R.drawable.field);
            curCadanganNickname = "";
            curCadanganTeam ="";
            curCadanganNo ="";
            return;
        }
        editMode = Boolean.valueOf(true);
        llField.setBackgroundResource(R.color.colorGreyDark);

    }

    public void openPlayer(String mode, String idteam, Button btn, TextView txt) {
        String curpos;
        String arrJoined;
        selButton = btn;
        selText = txt;
        String pos = checkTeam(idteam);
        int idxButton = getIndexButton(btn);
        int listLen = listPosition.length;
        if (idxButton >= listLen) {
            curpos = listPosition[idxButton - listLen];
        } else {
            curpos = listPosition[idxButton];
        }
        Intent playerIntent = new Intent(this, PlayerActivity2.class);
        playerIntent.putExtra("v_team_id", idteam);
        playerIntent.putExtra("rian_id_team", idteam);
        playerIntent.putExtra("v_match_id", idmatch);
        playerIntent.putExtra("v_position", curpos);
        playerIntent.putExtra("action", "pick_player");
        playerIntent.putExtra("idmatch", idmatch);
        playerIntent.putExtra("category", category);
        playerIntent.putExtra("nmmatch",namamatch);
            /*menuIntent.putExtra("action", this.action);*/
        playerIntent.putExtra("idteam1", idteam1);
        playerIntent.putExtra("idteam2", idteam2);
        playerIntent.putExtra("nmteam1", namateam1);
        playerIntent.putExtra("nmteam2", namateam2);
        if (pos.equals("left")) {
            arrJoined = joinList(kiriList, ",");
        } else {
            arrJoined = joinList(kananList, ",");
        }
        playerIntent.putExtra("mode", mode);
        playerIntent.putExtra("cur_player", arrJoined);
        playerIntent.putExtra("player_old", btn.getText().toString());
        startActivityForResult(playerIntent, 222);
    }

    private void openCadangan(String idteam, Button b, TextView textView) {
        List<String> list;
        String playerOld = b.getText().toString();
        selButton = b;
        selText = textView;

        if (checkTeam(idteam).equals("left")){
            list = kiriList;
            selButton.setBackgroundResource(R.drawable.touch_green);
        }else {
            list = kananList;
            selButton.setBackgroundResource(R.drawable.touch_blue);
        }
        //openPlayer("old", idteam, b, textView);
        removeItem(list, playerOld);
        String cadanganId = db.getPlayerId(idteam, idmatch, curCadanganNo);
        db.updateFormation(idmatch, idteam, playerOld, cadanganId, getPositionName(selButton));
        selButton.setText(curCadanganNo);
        selText.setText(curCadanganNickname);
        String replace="old";
        Temp.setReplace(replace);

    }

    private void removeItem(List<String> list, String val) {
        for (int i=0; i<list.size(); i++){
            if (((String) list.get(i)).equals(val)){
                list.remove(i);
                return;
            }
        }
    }

    public String checkTeam(String idteam) {
        if (idteam.equals(idteam1)){
            return "left";
        }

        return "right";
    }

    @SuppressLint("WrongConstant")
    private void runAction(String idteam, String playernumber) {
        Log.e("PID",db.getPlayerId(idteam, idmatch, playernumber));
        if (category.equals("PLAYER")){
            optPlayer(idteam, playernumber);
        }else if (category.equals("KEEPER")){
            optKeeper(idteam, playernumber);
        }else if (category.equals("FOULS")){
            optFouls(idteam, playernumber);
        }else {
            runSnack("+1 " + action_name + " untuk pemain nomor " + playernumber);
            ((Vibrator) getApplicationContext().getSystemService("vibrator")).vibrate(500);
            executeDetail(category, idteam, playernumber);
        }
//        Log.d("PID", )
    }

    private void optFouls(String idteam, String playernumber) {
        final String data_team = idteam;
        final String data_player = playernumber;
        Cursor pd = db.getPlayerDetail(idteam, idmatch, playernumber);
        List<String> mOpt = new ArrayList();
        mOpt.add("Fouls");
        mOpt.add("Red Card");
        mOpt.add("Yellow Card");
        final CharSequence[] Opts = (CharSequence[]) mOpt.toArray(new String[mOpt.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle(pd.getString(pd.getColumnIndex("nickname")) + " (" + playernumber + ")");
        dialogBuilder.setItems(Opts, new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                String selectedText = Opts[item].toString();
                String exe_act = "";
                if (selectedText.equals("Fouls")){
                    exe_act = "fouls";
                }else if (selectedText.equals("Red Card")){
                    exe_act = "red_card";
                }else if (selectedText.equals("Yellow Card")){
                    exe_act = "yellow_distribution";
                }
                runSnack("+1 " + selectedText + " untuk pemain nomor " + data_player);
                ((Vibrator) getApplicationContext().getSystemService("vibrator")).vibrate(500);
                if (selectedText.equals("Yellow Card") || selectedText.equals("Red Card")) {
                    executeDetail(exe_act, data_team, data_player);
                    executeDetail("FOULS", data_team, data_player);
                    return;
                }
                executeDetail(exe_act, data_team, data_player);
            }
        });

        dialogBuilder.setNegativeButton((CharSequence) "Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        dialogBuilder.create().show();
    }

    private void optPlayer(String idteam, String playernumber) {
        final String data_team = idteam;
        final String data_player = playernumber;
        Cursor pd = db.getPlayerDetail(idteam, idmatch, playernumber);
        List<String> mOpt = new ArrayList();
        mOpt.add("Touch Ball");
        mOpt.add("False Passing");
        mOpt.add("False Control");
        mOpt.add("Shoot on Target");
        final CharSequence[] Opts = (CharSequence[])mOpt.toArray(new String[mOpt.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(GameActivity.this);
        dialogBuilder.setTitle(pd.getString(pd.getColumnIndex("nickname")) + "("+playernumber+")");
        dialogBuilder.setItems(Opts, new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(DialogInterface dialogInterface, int item) {
                String selectedText = Opts[item].toString();
                String exe_act = "";
                if (selectedText.equals("Touch Ball")){
                    exe_act = "touch_ball";
                }else if (selectedText.equals("False Passing")){
                    exe_act ="false_passing";
                }else if (selectedText.equals("False Control")){
                    exe_act ="false_control";
                }else if (selectedText.equals("Shoot on Target")){
                    exe_act = "sot";
                }

                if (exe_act.equals("goal")){
                    String str = null;
                    try {
                        str = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.US).format(Calendar.getInstance().getTime());
                        Log.e("MY DATETIME", str);
                    }catch (Exception e){
                        Log.e("err", e.getMessage());
                    }
                }

                runSnack("+1" + selectedText+ " untuk pemain nomor " + data_player);
                ((Vibrator) getApplicationContext().getSystemService("vibrator")).vibrate(500);
                executeDetail(exe_act, data_team, data_player );
            }
        });
        dialogBuilder.setPositiveButton((CharSequence) "Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });

        dialogBuilder.create().show();
    }

    private void optKeeper(String idteam, String playernumber) {
        final String data_team = idteam;
        final String data_player = playernumber;
        Cursor pd = db.getPlayerDetail(idteam, idmatch, playernumber);
        List<String> mopt = new ArrayList<>();
        mopt.add("Ball In Goal Keeper Area");
        mopt.add("Good Saving");
        mopt.add("Good Distribution");
        final CharSequence[] Opts = (CharSequence[]) mopt
                .toArray(new String[mopt.size()]);
        AlertDialog.Builder dialog = new AlertDialog.Builder(GameActivity.this);
        dialog.setTitle(pd.getString(pd.getColumnIndex("nickname")));
        dialog.setItems(Opts, new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                String selectedText = Opts[i].toString();
                String exe_act = "";

                if(selectedText.equals("Reaction")){
                    openReaction(data_team, data_player);
                    return;
                }
                if (selectedText.equals("Ball In Goal Keeper Area")){
                    exe_act = "ball_saving_area";
                }else if (selectedText.equals("Good Saving")){
                    exe_act = "good_saving";
                }else if (selectedText.equals("Good Distribution")){
                    exe_act = "good_distribution";
                }
                runSnack("+1" + selectedText + "untuk pemain nomor" + data_player);
                ((Vibrator) getApplicationContext().getSystemService("vibrator")).vibrate(500);
                executeDetail(exe_act, data_team, data_player);
            }
        });
        dialog.setPositiveButton((CharSequence) "Batal", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });
        dialog.create().show();

    }

    private void openReaction(final String data_team, final String data_player) {
        List<String> mOpt = new ArrayList();
        mOpt.add("Good");
        mOpt.add("Enough");
        mOpt.add("Less");
        final CharSequence[] Opts = (CharSequence[]) mOpt.toArray(new String[mOpt.size()]);
        AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(this);
        dialogBuilder.setTitle((CharSequence) "Reaction");
        dialogBuilder.setItems(Opts, new DialogInterface.OnClickListener() {
            @SuppressLint("WrongConstant")
            public void onClick(DialogInterface dialog, int item) {
                String selectedText = Opts[item].toString();
                String str = "";
                str = selectedText;
                runSnack("Reaction " + selectedText + " untuk pemain nomor " + data_player);
                ((Vibrator) getApplicationContext().getSystemService("vibrator")).vibrate(500);
                db.setMatchDetailReaction(selectedText, idmatch, db.getPlayerId(data_team, idmatch, data_player));
            }
        });
        dialogBuilder.setPositiveButton((CharSequence) "Batal", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int id) {
            }
        });
        dialogBuilder.create().show();
    }

    private void executeDetail(String category, String idteam, String playernumber) {
        db = new DatabaseHelper(this);
        db.setMatchDetailData(category, idmatch, db.getPlayerId(idteam, idmatch, playernumber));

    }

    public int getIndex(String[] list, String data) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexButton(Button button) {
        Button[] listButton = new Button[]
                {btnLeft11, btnLeft21, btnLeft22, btnLeft23, btnLeft24,
                        btnLeft31, btnLeft32, btnLeft33, btnLeft34,
                        btnLeft41, btnLeft42, btnLeft43, btnLeft44,
                        btnRight11, btnRight21, btnRight22, btnRight23, btnRight24,
                        btnRight31, btnRight32, btnRight33, btnRight34,
                        btnRight41, btnRight42, btnRight43, btnRight44};
        for (int i = 0; i < listButton.length; i++) {
            if (listButton[i] == button) {
                return i;
            }
        }
        return -1;
    }

    public int getIndexTextView(TextView[] list, String data) {
        for (int i = 0; i < list.length; i++) {
            if (list[i].equals(data)) {
                return i;
            }
        }
        return -1;
    }
    public TextView getTextView(int index) {
        return new TextView[]{txtLeft11, txtLeft21, txtLeft22, txtLeft23, txtLeft24, txtLeft31, txtLeft32, txtLeft33, txtLeft34, txtLeft41, txtLeft42, txtLeft43, txtLeft44, txtRight11, txtRight21, txtRight22, txtRight23, txtRight24, txtRight31, txtRight32, txtRight33, txtRight34, txtRight41, txtRight42, txtRight43, txtRight44}[index];
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), GameChooseActivity.class);
        startActivity(i);
        finish();
    }

    @Override
    public void onClick(int position) {
        Player curPlayer = cadanganAdapter1.getSelectedItem(position);
        fieldMode(editMode);
        curCadanganNo = curPlayer.getPlayer_number();
        curCadanganNickname = curPlayer.getNickname();
        curCadanganTeam = idteam1;
        if (editMode.booleanValue()){
            runSnack("Silahkan pilih pemain yang ingin diganti.");
        }
    }

    @Override
    public void onClick2(int position) {
        Player curPlayer = cadanganAdapter2.getSelectedItem(position);
        fieldMode(editMode);
        curCadanganNo = curPlayer.getPlayer_number();
        curCadanganNickname = curPlayer.getNickname();
        curCadanganTeam = idteam2;
        if (editMode.booleanValue()) {
            runSnack("Silahkan pilih pemain yang ingin diganti.");
        }
    }

    private void runSnack(String s) {
        Snackbar.make(coordinatorLayout, (CharSequence) s,0).show();
    }

    public String getPositionName(Button btn) {
        int btnIndex = -1;
        Button[] listButton = new Button[]{btnLeft11, btnLeft21, btnLeft22, btnLeft23, btnLeft24, btnLeft31, btnLeft32, btnLeft33, btnLeft34, btnLeft41, btnLeft42, btnLeft43, btnLeft44, btnRight11, btnRight21, btnRight22, btnRight23, btnRight24, btnRight31, btnRight32, btnRight33, btnRight34, btnRight41, btnRight42, btnRight43, btnRight44};
        for (int i = 0; i < listButton.length; i++) {
            if (listButton[i] == btn) {
                btnIndex = i;
                break;
            }
        }
        String pos = "";
        if (btnIndex < listPosition.length) {
            return listPosition[btnIndex];
        }
        return listPosition[btnIndex - listPosition.length];
    }

    private void openPlayerOpt(String idteam, Button btn, TextView txt) {
        String valText="";
        String pid = db.getPlayerId(idteam, idmatch, btn.getText().toString());

        if (!category.equals("FOULS") && !category.equals("KEEPER") && !category.equals("PLAYER")){
            valText = CSVWritter.DEFAULT_LINE_END + category + ": " + String.valueOf(db.getMDVal(this.action, idmatch, pid));
        }else if (category.equals("PLAYER")) {
            String touchData = String.valueOf(db.getMDVal("touch_ball", idmatch, pid));
            String controlData = String.valueOf(db.getMDVal("false_Passing", idmatch, pid));
            String passingData = String.valueOf(db.getMDVal("false_control", idmatch, pid));
            String sotData = String.valueOf(db.getMDVal("sot", idmatch, pid));
            valText = ((("\n\nTouch Ball: " + touchData) + "\nFalse Passing: " + passingData) + "\nFalse Control: " + controlData) + "\nShot on Target: " + String.valueOf(db.getMDVal("sot", idmatch, pid));
        }else if (category.equals("KEEPER")){
            String savingData = String.valueOf(db.getMDVal("ball_saving_area", idmatch, pid));
            String distData = String.valueOf(db.getMDVal("good_saving", idmatch, pid));
            valText = (("\n\nBall Saving: " + savingData) + "\nGood Saving: " + distData) + "\nGood Distribution:" + String.valueOf(db.getMDVal("good_distribution", idmatch, pid));
        }


        AlertDialog.Builder builder = new AlertDialog.Builder(GameActivity.this);
        builder.setTitle((CharSequence)"Info Pemain");
        builder.setMessage((CharSequence) "Nama Pemain: " + txt.getText().toString() + "\nNomor Punggung: " + btn.getText().toString() + valText);
        final String str = idteam;
        final Button button = btn;
        final TextView textView = txt;
        builder.setPositiveButton((CharSequence)"Ganti", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openPlayer("update", str, button, textView);
            }
        });

        builder.setNegativeButton((CharSequence)"Hapus", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                removePlayer(str, button, textView);
            }
        });

        builder.setNeutralButton((CharSequence) "Kembali", (DialogInterface.OnClickListener) new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();


    }

    private void removePlayer(String team_id, Button btn, TextView txt) {
        List<String> list;
        if (checkTeam(team_id).equals("left")) {
            list = kiriList;
        } else {
            list = kananList;
        }
        String player = btn.getText().toString();
        db.removeFormation(idmatch, team_id, player);
        removeItem(list, player);
        btn.setText("");
        txt.setText("");
        btn.setBackgroundResource(R.drawable.touch_transparent);
        loadCadangan();
    }

    public static String joinList(List list, String literal) {
        return list.toString().replaceAll(",", literal).replaceAll("[\\[.\\].\\s+]", "");
    }

    public void showCadanganBar(boolean s) {
        boolean z = true;
        if (s) {
            llCadangan.setLayoutParams(new LinearLayout.LayoutParams(-1, -2));
            if (boolMode == 1) {
                llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) content_match.getHeight()) - hCadangan)));
            } else {
                llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) getWindowManager().getDefaultDisplay().getWidth()) / 1.7d)));
            }
        } else {
            llCadangan.setLayoutParams(new LinearLayout.LayoutParams(-1, 0));
            if (boolMode == 1) {
                llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) ((double) content_match.getHeight())));
            } else {
                llField.setLayoutParams(new LinearLayout.LayoutParams(-1, (int) (((double) getWindowManager().getDefaultDisplay().getWidth()) / 1.7d)));
            }
        }
        if (boolCadangan) {
            z = false;
        }
        boolCadangan = z;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode==222){
            if (resultCode == -1){
                List<String> list;
                String playerId = data.getStringExtra("ac_player_id");
                String playerNumber = data.getStringExtra("ac_player_number");
                String playerNick = data.getStringExtra("ac_player_nickname");
                String playerMode = data.getStringExtra("ac_player_mode");
                String playerTeam = data.getStringExtra("ac_player_team");
                if (checkTeam(playerTeam).equals("left")) {
                    list = kiriList;
                    selButton.setBackgroundResource(R.drawable.touch_green);
                } else {
                    list = kananList;
                    selButton.setBackgroundResource(R.drawable.touch_blue);
                }
                selButton.setText(playerNumber);
                selText.setText(playerNick);
                list.add(playerNumber);
                String playerOld;
                if(playerMode.equals("update")){
                    playerOld = data.getStringExtra("ac_player_old");
                    removeItem(list, playerOld);
                    db.updateFormation(idmatch, playerTeam, playerOld, playerId, getPositionName(selButton));
                }else {
                    if (playerMode.equals("cadangan")){
                        playerOld = data.getStringExtra("ac_player_old");
                        removeItem(list, playerOld);
                        db.updateFormation(idmatch, playerTeam, playerOld, playerId, getPositionName(selButton));
                    }else {
                        db.insertFormation(idmatch, playerTeam, playerId, getPositionName(selButton));
                        db.updateMatchStatus(idmatch);
                    }

                }
                runSnack(playerNick + " (" + playerNumber + ") berhasil dimasukkan");
                loadCadangan();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.game, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {



        if (item.getItemId() == R.id.Result){
            Intent menuIntent = new Intent(getApplicationContext(), GameDetailActivity.class);
//            menuIntent.putExtra("match_name", this.match_name);
            menuIntent.putExtra("idmatch", idmatch);
            menuIntent.putExtra("category", category);
            menuIntent.putExtra("nmmatch",namamatch);
            /*menuIntent.putExtra("action", this.action);*/
            menuIntent.putExtra("idteam1", idteam1);
            menuIntent.putExtra("idteam2", idteam2);
            menuIntent.putExtra("nmteam1", namateam1);
            menuIntent.putExtra("nmteam2", namateam2);
            startActivity(menuIntent);
            return true;
        } else if (item.getItemId() == R.id.Field){
            if (boolMode ==1 ){
                boolMode = 0;
            }else {
                boolMode = 1;
            }
            setupFieldMode(boolMode);
        } else if (item.getItemId() == R.id.CSV){
            exportCSV(null);
        }else if (item.getItemId() == R.id.home){
            onBackPressed();
            return true;
        }

        return true;
    }

    private void exportCSV(String act) {
        try {
            String customAct;
            DataExport dataExport = new DataExport();
            Utils.verifyStoragePermissions(this);
            String timestamp_str = String.valueOf(new Timestamp(System.currentTimeMillis()).getTime());
            File dbFile = getDatabasePath("_fundamental_statistic.db");
            DatabaseHelper dbhelper = new DatabaseHelper(getApplicationContext());
            File exportDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "");
            if (!exportDir.exists()) {
                exportDir.mkdirs();
            }
            String fileName = "Export " + category+ " -" + timestamp_str + ".csv";
            File file = new File(exportDir, fileName);
            Uri uri = Uri.fromFile(file);
            file.createNewFile();
            CSVWritter csvWrite = new CSVWritter(new FileWriter(file));
            SQLiteDatabase db = dbhelper.getReadableDatabase();
            if (act == null) {
                customAct = category;
            } else if (act.equals("goaltiming")) {
                customAct = "goal";
            } else {
                customAct = category;
            }
            Cursor curCSV = dataExport.exportCSV(getApplicationContext(), idmatch, customAct);
            csvWrite.writeNext(curCSV.getColumnNames());
            while (curCSV.moveToNext()) {
                int colCount = curCSV.getColumnCount();
                Log.e("colcount", String.valueOf(colCount));
                String[] arrStr = new String[colCount];
                for (int i = 0; i < colCount; i++) {
                    arrStr[i] = curCSV.getString(i);
                }
                csvWrite.writeNext(arrStr);
            }
            csvWrite.close();
            curCSV.close();
            runSnack("File " + fileName + " berhasil diexport. File ini berlokasi di folder Download.");
        } catch (Throwable sqlEx) {
            Log.e("MainActivity", sqlEx.getMessage(), sqlEx);
        }
    }
}
