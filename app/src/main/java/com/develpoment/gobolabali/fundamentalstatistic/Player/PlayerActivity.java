package com.develpoment.gobolabali.fundamentalstatistic.Player;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataPlayer;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.AddTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class PlayerActivity extends AppCompatActivity {

    TextView txtNamaTeam;
    String idteamx, namateamx;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterPlayer adapterPlayer;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<DataPlayer> teamList = new ArrayList<DataPlayer>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        idteamx = getIntent().getStringExtra("id");
        namateamx = getIntent().getStringExtra("nama");

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarPlayer);
        toolbar.setTitle("Player List");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        txtNamaTeam = (TextView) findViewById(R.id.textNamaTeam);
        txtNamaTeam.setText(db.getTeamName(idteamx));

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), TeamActivity.class);
                startActivity(i);
                finish();
            }
        });

        initRecylerView();
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), TeamActivity.class);
        startActivity(i);
        finish();
        super.onBackPressed();
    }

    public void initRecylerView() {
        recyclerView = (RecyclerView) findViewById(R.id.rcPlayer);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        db = new DatabaseHelper(PlayerActivity.this);
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
            String status = row.get(i).get("status");
            String idteam = row.get(i).get("idteam");

            DataPlayer dataPlayer = new DataPlayer();
            dataPlayer.setId(id);
            dataPlayer.setFullname(fullname);
            dataPlayer.setNickname(nickname);
            dataPlayer.setPosisi(posisi);
            dataPlayer.setNopunggung(nopunggung);
            dataPlayer.setStatus(status);
            dataPlayer.setIdteam(idteam);

            teamList.add(dataPlayer);
        }

//        adapterTeam = new AdapterTeam(teamList);
        adapterPlayer = new AdapterPlayer(PlayerActivity.this,teamList);
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

        int id = item.getItemId();

        if (id == R.id.pplayer){
            Intent i = new Intent(getApplicationContext(), AddPlayerActivity.class);
            i.putExtra("idx", idteamx);
            i.putExtra("namax", namateamx);
            startActivity(i);
            finish();

            return true;
        }

        return super.onOptionsItemSelected(item);
    }

}
