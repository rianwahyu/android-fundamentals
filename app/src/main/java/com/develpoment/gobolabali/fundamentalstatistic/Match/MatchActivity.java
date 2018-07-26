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

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterTournament;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTournament;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.develpoment.gobolabali.fundamentalstatistic.Team.AddTeamActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Team.TeamActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class MatchActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterTournament adapterTeam;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<DataTournament> tournamentList = new ArrayList<DataTournament>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

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
        db = new DatabaseHelper(MatchActivity.this);

        ArrayList<HashMap<String,String>> row = db.getAllTournament();

        for (int i=0; i <row.size(); i++){
            String id = row.get(i).get("id");
            String nama = row.get(i).get("namatournament");

            DataTournament dataTeam = new DataTournament();
            dataTeam.setId(id);
            dataTeam.setNamaTournament(nama);

            tournamentList.add(dataTeam);
        }

//        adapterTeam = new AdapterTeam(teamList);
        adapterTeam = new AdapterTournament(MatchActivity.this,tournamentList);
        recyclerView.setAdapter(adapterTeam);
        adapterTeam.notifyDataSetChanged();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.match, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pmatch){
            Intent i = new Intent(getApplicationContext(), AddTournamentActivity.class);
            startActivity(i);
            finish();
//            Toast.makeText(getApplicationContext(),"Add Team Clicked",Toast.LENGTH_LONG).show();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
