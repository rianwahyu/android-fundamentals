package com.develpoment.gobolabali.fundamentalstatistic.Match;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterMatch;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterTournament;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataMatchList;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTournament;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Rian on 01/06/2018.
 */

public class MatchListActivity extends AppCompatActivity {

    String idtournamentx, namatournamentx;
    TextView textNamaTournament;

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterMatch adapterMatch;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<DataMatchList> matchLists = new ArrayList<DataMatchList>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_list);

        idtournamentx = getIntent().getStringExtra("id");
        namatournamentx =  getIntent().getStringExtra("namatournament");

        textNamaTournament = (TextView) findViewById(R.id.textNamaTournament);
        textNamaTournament.setText(db.getTournamentName(idtournamentx));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMatch);
        toolbar.setTitle("Match List");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
            }
        });

        recyclerView = (RecyclerView) findViewById(R.id.rcMatch);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);

        initRecylerView();

    }

    private void initRecylerView() {
        db = new DatabaseHelper(MatchListActivity.this);

        ArrayList<HashMap<String,String>> row = db.getAllMatch(Integer.parseInt(idtournamentx));

        for (int i=0; i <row.size(); i++){
            String id = row.get(i).get("id");
            String nama = row.get(i).get("namapertandingan");
            String idtournament = row.get(i).get("idtournament");
            String idteam1 = row.get(i).get("idteam1");
            String idteam2 = row.get(i).get("idteam2");
            String tanggal = row.get(i).get("tanggal");
            String mulai = row.get(i).get("mulai");
            String akhir = row.get(i).get("akhir");

            DataMatchList dataTeam = new DataMatchList();
            dataTeam.setId(id);
            dataTeam.setNamaPertandingan(nama);
            dataTeam.setIdtournament(idtournament);
            dataTeam.setIdteam1(idteam1);
            dataTeam.setIdteam2(idteam2);
            dataTeam.setTanggal(tanggal);
            dataTeam.setMulai(mulai);
            dataTeam.setAkhir(akhir);

            matchLists.add(dataTeam);
        }

//        adapterTeam = new AdapterTeam(teamList);
        adapterMatch = new AdapterMatch(MatchListActivity.this,matchLists);
        recyclerView.setAdapter(adapterMatch);
        adapterMatch.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.match_list, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pmatchList){
            Intent oi = new Intent(getApplicationContext(), AddMatchListActivity.class);
            oi.putExtra("id",idtournamentx);
            oi.putExtra("namatournament",namatournamentx);
            startActivity(oi);
            finish();
//            Toast.makeText(getApplicationContext(),"Add Team Clicked",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MatchActivity.class);
        startActivity(i);
        finish();
    }
}
