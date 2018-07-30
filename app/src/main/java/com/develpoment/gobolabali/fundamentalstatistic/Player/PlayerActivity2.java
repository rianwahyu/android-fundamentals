package com.develpoment.gobolabali.fundamentalstatistic.Player;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.database.Cursor;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.PlayerAdapter;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.PlayerMatchAdapter;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.PlayerMatchAdapter2;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Games.GameActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayerMatch;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Player;
import com.develpoment.gobolabali.fundamentalstatistic.Model.Temp;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.google.gson.Gson;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerActivity2 extends AppCompatActivity implements PlayerAdapter.ClickListener, PlayerMatchAdapter.ClickListener {

    TextView txtNamaTeam;
    String idteamx, namateamx;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterPlayer adapterPlayer;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<DataPlayer> teamList = new ArrayList<DataPlayer>();

    String action;
    String cur_player;
    String mode;
    String player_old;
    RecyclerView rv;
    String v_match_id;
    String v_player_id;
    String v_position;
    String v_team_name;

    PlayerMatchAdapter playerMatchAdapter;
    PlayerAdapter playerAdapter;


    @SuppressLint("WrongConstant")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        idteamx = getIntent().getStringExtra("rian_id_team");
        namateamx = getIntent().getStringExtra("rian_nama_team");

        /*idmatch     = getIntent().getStringExtra("idmatch");
        namamatch   = getIntent().getStringExtra("nmmatch");
        idteam1     = getIntent().getStringExtra("idteam1");
        idteam2     = getIntent().getStringExtra("idteam2");
        category    = getIntent().getStringExtra("category");
        namateam1   = getIntent().getStringExtra("nmteam1");
        namateam2   = getIntent().getStringExtra("nmteam2");*/

//        setSupportActionBar((Toolbar) findViewById(R.id.toolbarPlayer));
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbar.setTitle("Player List");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        /*getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().set*/

        txtNamaTeam = (TextView) findViewById(R.id.textNamaTeam);
        txtNamaTeam.setText(db.getTeamName(idteamx));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onBackPressed();
                return;
            }
        });

        v_team_name = getIntent().getStringExtra("v_team_name");
        v_player_id = getIntent().getStringExtra("v_team_id");
        v_match_id = getIntent().getStringExtra("v_match_id");
        v_position = getIntent().getStringExtra("v_position");
        action = getIntent().getStringExtra("action");
        cur_player = getIntent().getStringExtra("cur_player");
        mode = getIntent().getStringExtra("mode");
        player_old = getIntent().getStringExtra("player_old");

        try {
            if ( action.equals("pick_player")) {
                setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
            }
        } catch (Exception e) {
        }

        rv = (RecyclerView) findViewById(R.id.rcPlayer);
        db = new DatabaseHelper(this);
        playerAdapter = new PlayerAdapter(this);
        String teamPos = "";
        if ( v_match_id != null) {
            if ( db.checkSide( v_match_id,  v_player_id).intValue() == 1) {
                teamPos = "left";
            } else {
                teamPos = "right";
            }
            playerMatchAdapter = new PlayerMatchAdapter(this, teamPos);
        }
        getData();
        setMainRecyclerView();

        //initRecylerView();
    }

    public void getData() {
        Cursor c = null;
        List<Player> data = new ArrayList();
        if (action.equals("pick_player")) {
            if (mode.equals("new")){
                c = db.getPlayerDataWhere(v_player_id, cur_player);
            }else if (mode.equals("update")){
                c = db.getPlayerDataWhere2(v_player_id, cur_player);
            }/*else if (mode.equals("old")){
                c = db.getPlayerDataWhere3(v_player_id, cur_player);
            }*/

            playerMatchAdapter.clear();
        } else {
//            playerAdapter.clear();
            //c = db.getTeamPlayer(idteamx);
        }
        if (c != null) {
            while (c.moveToNext()) {
//                String nameText = c.getSt ring(c.getColumnIndex(DBConfig.TOURNAMEN_COL_NAME));
//                String idText = c.getString(c.getColumnIndex(DBConfig.TOURNAMEN_COL_ID));
                String id = c.getString(c.getColumnIndex("id"));
                String fullname = c.getString(c.getColumnIndex("fullname"));
                String playerNumberText = c.getString(c.getColumnIndex("nopunggung"));
                String nicknameText = c.getString(c.getColumnIndex("nickname"));
                Player player = new Player();
                player.setName(fullname);
                player.setId(id);
                player.setPlayer_number(playerNumberText);
                player.setNickname(nicknameText);
                if (action.equals("pick_player")) {
                    String statusText = c.getString(c.getColumnIndex("status"));
                    String positionText = c.getString(c.getColumnIndex("posnomor"));
                    player.setStatus(statusText);
                    player.setPosition(positionText);
                }
                if (action.equals("pick_player")) {
                    playerMatchAdapter.addItem(player);
                } else {
                    //playerAdapter.addItem(player);
                }
            }
        }
    }

    public void setMainRecyclerView() {
        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        if ( action.equals("pick_player")) {
            rv.setAdapter( playerMatchAdapter);
        } else {
            rv.setAdapter( playerAdapter);
        }
    }

 /*   @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), TeamActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }*/

    public void initRecylerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rcPlayer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(PlayerActivity2.this);
//        teamList = db.getTeam();
//        adapterTeam = new AdapterTeam(teamList);
//        recyclerView.setAdapter(adapterTeam);

        ArrayList<HashMap<String,String>> row = db.getAllPlayer(Integer.parseInt(idteamx));

        for (int i=0; i <row.size(); i++){
            String id = row.get(i).get("id");
            String fullname = row.get(i).get("fullname");
            String nickname = row.get(i).get("nickname");
            String posisi = row.get(i).get("posisi");
            String nopunggung = row.get(i).get("nopunggung");
//            String status = row.get(i).get("status");
            String idteam = row.get(i).get("idteam");
            DataPlayer dataPlayer = new DataPlayer();
            dataPlayer.setId(id);
            dataPlayer.setFullname(fullname);
            dataPlayer.setNickname(nickname);
            dataPlayer.setPosisi(posisi);
            dataPlayer.setNopunggung(nopunggung);
//            dataPlayer.setStatus(status);
            dataPlayer.setIdteam(idteam);
            teamList.add(dataPlayer);
        }

//        adapterTeam = new AdapterTeam(teamList);
        adapterPlayer = new AdapterPlayer(PlayerActivity2.this,teamList);
        recyclerView.setAdapter(adapterPlayer);
        adapterPlayer.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.player, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case R.id.home:
                onBackPressed();
                return true;
            case R.id.pplayer:
                Intent i = new Intent(getApplicationContext(), EditPlayerActivity.class);
                i.putExtra("rian_id_team", idteamx);
                i.putExtra("rian_nama_team", namateamx);
                startActivity(i);
                finish();

                return true;
        }
        return super.onOptionsItemSelected(item);

        /*int id = item.getItemId();

        if (id == R.id.pplayer){
            Intent i = new Intent(getApplicationContext(), EditPlayerActivity.class);
            i.putExtra("rian_id_team", idteamx);
            i.putExtra("rian_nama_team", namateamx);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);*/
    }

    @Override
    public void onClick(int position) {
        Player selectedItem;
        if (action.equals("pick_player")) {
            selectedItem = playerMatchAdapter.getSelectedItem(position);
            Intent resultIntent = new Intent();
            resultIntent.putExtra("ac_player_id", selectedItem.getId());
            resultIntent.putExtra("ac_player_number", selectedItem.getPlayer_number());
            resultIntent.putExtra("ac_player_nickname", selectedItem.getNickname());
            resultIntent.putExtra("ac_player_mode", mode);
            resultIntent.putExtra("ac_player_old", player_old);
            resultIntent.putExtra("ac_player_team", v_player_id);
            setResult(RESULT_OK, resultIntent);
            finish();
            return;
        } else if (action.equals("pick_match_player")){
            Object selectedItem2 = playerAdapter.getSelectedItem(position);
            Intent returtnIntent = new Intent();
            returtnIntent.putExtra("result", new Gson().toJson(selectedItem2));
            setResult(RESULT_OK, returtnIntent);
            finish();
        }else {
/*            selectedItem = playerAdapter.getSelectedItem(position);
            Intent playerIntent = new Intent(getApplicationContext(), EditPlayerActivity.class);
            playerIntent.putExtra("id", selectedItem.getId());
            playerIntent.putExtra("fullname", selectedItem.getName());
            playerIntent.putExtra("nickname", selectedItem.getNickname());
            playerIntent.putExtra("punggung", selectedItem.getPlayer_number());
            playerIntent.putExtra("rian_id_team", idteamx);
            playerIntent.putExtra("rian_nama_team", namateamx);
            startActivity(playerIntent);*/
        }
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        getData();
    }
}
