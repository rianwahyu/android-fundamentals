package com.develpoment.gobolabali.fundamentalstatistic.Player;

import android.content.ContentValues;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Match.MatchPlayerListActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Player;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.google.gson.Gson;
import com.shashank.sony.fancydialoglib.Animation;
import com.shashank.sony.fancydialoglib.FancyAlertDialogListener;
import com.shashank.sony.fancydialoglib.Icon;

import org.apache.commons.cli.HelpFormatter;

import java.util.ArrayList;
import java.util.List;

public class PlayerMatchEditActivity extends AppCompatActivity {



    Spinner spinpos, spinstat;
    EditText etnopunggung;
    Button submit;

    EditText edtName;
    EditText edtNickname;
    EditText edtPlayer;
    EditText edtNoPunggung;

    String ID_PLAYER;

    Button btnDelete;
    Button btnUpdate;

    String idmatch;
    String idteam;
    Player player;
    String idplayer;
    String player_name;
    String player_nickname;
    String player_number;
    String pos;
    String selectedData;
    String selectedDataName;
    String selectedStatus;
    String selectedStatusName;

    List<SpinnerData> list;
    ArrayAdapter<SpinnerData> listAdapter;
    ArrayAdapter<SpinnerData> listStatusAdapter;
    List<SpinnerData> statusList;
    DatabaseHelper db =  new DatabaseHelper(this);

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_match_edit);

        idmatch = getIntent().getStringExtra("idmatch");
        idplayer = getIntent().getStringExtra("idplayer");
        idteam = getIntent().getStringExtra("idteam");

        edtPlayer = (EditText) findViewById(R.id.edtName);
        Player dataPlayer = (Player) new Gson().fromJson(getIntent().getStringExtra("extra_player"), Player.class);
        idteam = getIntent().getStringExtra("idteam");
        idmatch = getIntent().getStringExtra("idmatch");
        pos = getIntent().getStringExtra("pos");
        idplayer = getIntent().getStringExtra("idplayer");
        player_name = getIntent().getStringExtra("fullname");
        player_nickname = getIntent().getStringExtra("nickname");
        player_number = getIntent().getStringExtra("nopunggung");
        player = (Player) new Gson().fromJson(getIntent().getStringExtra("DATA"), Player.class);
        if (idplayer != null) {
            //titleBar = "Edit Team Player";
        } else {
            //titleBar = "Add Team Player";
        }

        edtNoPunggung = (EditText) findViewById(R.id.edtPlayerNumber);
        spinpos = (Spinner) findViewById(R.id.spPosition);
        spinstat = (Spinner) findViewById(R.id.spStatus);
        btnUpdate = (Button) findViewById(R.id.btnUpdate);
        btnDelete = (Button) findViewById(R.id.btnDelete);


        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbar.setTitle("Player Match Edit");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (idplayer == null) {
                    saveData();
                } else {
                    updateData();
                }
            }
        });
        setupPosition();
        listAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, list );
        spinpos.setAdapter(listAdapter);
        spinpos.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SpinnerData s = (SpinnerData) adapterView.getItemAtPosition(position);
                Object tag = s.tag;
                String tagname = s.string;
                selectedData = String.valueOf(tag);
                selectedDataName = String.valueOf(tagname);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        listStatusAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, statusList);
        spinstat.setAdapter(listStatusAdapter);
        spinstat.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long l) {
                SpinnerData s = (SpinnerData) adapterView.getItemAtPosition(position);
                Object tag = s.tag;
                String tagname = s.string;
                selectedStatus = String.valueOf(tag);
                selectedStatusName = String.valueOf(tagname);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        edtPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getApplicationContext(), PlayerActivity.class);
                intent.putExtra("action", "pick_match_player");
                intent.putExtra("rian_id_team", idteam);
                intent.putExtra("idmatch", idmatch);
                startActivityForResult(intent, 101);
            }
        });

        if (getIntent().getStringExtra("DATA") !=null){
            btnDelete.setVisibility(View.VISIBLE);
            setupEdit();
        }
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handleDelete();
            }
        });


    }

    private void handleDelete() {
        new com.shashank.sony.fancydialoglib.FancyAlertDialog.Builder(PlayerMatchEditActivity.this)
                .setTitle("Delete Player !")
                .setBackgroundColor(Color.parseColor("#E12929"))
                .setIcon(R.drawable.ic_trash, Icon.Visible)
                .setMessage("Apakah Anda Yakin Ingin Menghapus " + player_name + " ?")
                .setNegativeBtnText("Tidak")
                .setNegativeBtnBackground(Color.parseColor("#ffc371"))
                .setPositiveBtnText("Ya")
                .setPositiveBtnBackground(Color.parseColor("#0d488a"))
                .setAnimation(Animation.SIDE)
                .isCancellable(true)
                .OnPositiveClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {
                        db.deleteData("matchplayer", "id='" + idplayer + "'");
                        onBackPressed();
                    }
                })
                .OnNegativeClicked(new FancyAlertDialogListener() {
                    @Override
                    public void OnClick() {

                    }
                })
                .build();


    }

    private void updateData() {
        String data_number = edtNoPunggung.getText().toString();
        ContentValues cv = new ContentValues();
        cv.put("posnomor", this.selectedData);
        cv.put("nopunggung", data_number);
        cv.put("status", this.selectedStatus);
        Log.e("dataa", "player:" + idplayer + " => " + idmatch + " => " + idteam);
        if (new DatabaseHelper(this).updateData("matchplayer", cv, "id='" + idplayer + "'")) {
//            runSnack("Data berhasil disimpan");
            Toast.makeText(getApplicationContext(), "Data Berhasil Disimpan", Toast.LENGTH_SHORT).show();
            super.onBackPressed();
            return;
        }
//        runSnack("Gagal melakukan proses update");
    }

    private void saveData() {
        boolean valid = true;
        if (ID_PLAYER == null) {
            edtPlayer.setError("Required!");
            valid = false;
        }
        if (edtNoPunggung.getText().length() < 1) {
            edtNoPunggung.setError("Required!");
            valid = false;
        }
        if (valid) {
            DatabaseHelper dbConfig = new DatabaseHelper(this);
            ContentValues cv1 = new ContentValues();
            cv1.put("idmatch", idmatch);
            cv1.put("idteam", idteam);
            cv1.put("idplayer", ID_PLAYER);
            cv1.put("posnomor", selectedData);
            cv1.put("status", selectedStatus);
            cv1.put("nopunggung", edtNoPunggung.getText().toString());
            dbConfig.saveData("matchplayer", cv1);
            super.onBackPressed();
        }
    }

    private void setupEdit() {
        ((LinearLayout) findViewById(R.id.llForm)).setVisibility(View.GONE);
        edtNoPunggung.setText(player_number);
        if (player.getStatus() != null) {
            spinstat.setSelection(getIndexStatus(player.getStatus()));
        }
        if (player.getPosition() != null) {
            spinpos.setSelection(getIndexPosition(player.getPosition()));
        }
    }

    private int getIndexStatus(String myString) {
        for (int i = 0; i < this.listStatusAdapter.getCount(); i++) {
            if (((SpinnerData) this.listStatusAdapter.getItem(i)).tag.equals(myString)) {
                return i;
            }
        }
        return 0;
    }

    private int getIndexPosition(String myString) {
        for (int i = 0; i < listAdapter.getCount(); i++) {
            if (((SpinnerData) listAdapter.getItem(i)).tag.equals(myString)) {
                return i;
            }
        }
        return 0;
    }

    private void setupPosition() {
        list = new ArrayList();
        list.add(new SpinnerData(HelpFormatter.DEFAULT_OPT_PREFIX, " "));
        list.add(new SpinnerData("Defender (DF)", "21"));
        list.add(new SpinnerData("Midfielder (MF)", "31"));
        list.add(new SpinnerData("Forward (FD)", "41"));
        statusList = new ArrayList();
        statusList.add(new SpinnerData("CADANGAN", "0"));
        statusList.add(new SpinnerData("INTI", "1"));
    }

    private class SpinnerData {
        public String string;
        public Object tag;

        public SpinnerData(String stringPart, Object tagPart) {
            this.string = stringPart;
            this.tag = tagPart;
        }

        @Override
        public String toString() {
            return string;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == 101) {
            if (resultCode == RESULT_OK) {
                Player curPlayer = (Player) new Gson().fromJson(data.getStringExtra("result"), Player.class);
                edtPlayer.setText(curPlayer.getName());
                ID_PLAYER = curPlayer.getId();
                edtNoPunggung.setText(curPlayer.getPlayer_number());
            }
            if (resultCode != RESULT_CANCELED) {
            }
        }
    }
}
