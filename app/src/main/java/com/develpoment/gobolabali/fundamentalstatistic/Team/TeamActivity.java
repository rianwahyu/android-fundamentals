package com.develpoment.gobolabali.fundamentalstatistic.Team;

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
import android.widget.LinearLayout;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.AdapterTeam;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTeam;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class TeamActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private LinearLayoutManager layoutManager;
    private AdapterTeam adapterTeam;
    private DatabaseHelper db = new DatabaseHelper(this);
    private List<DataTeam> teamList = new ArrayList<DataTeam>();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarTeam);
        toolbar.setTitle("Team List");
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

        recyclerView = (RecyclerView) findViewById(R.id.rcTeam);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        initRecylerView();

    }

    public void initRecylerView() {

        db = new DatabaseHelper(TeamActivity.this);

        ArrayList<HashMap<String,String>> row = db.getAllTeam();

        for (int i=0; i <row.size(); i++){
            String id = row.get(i).get("id");
            String nama = row.get(i).get("nama");

            DataTeam dataTeam = new DataTeam();
            dataTeam.setId(id);
            dataTeam.setNama(nama);

            teamList.add(dataTeam);
        }

//        adapterTeam = new AdapterTeam(teamList);
        adapterTeam = new AdapterTeam(TeamActivity.this,teamList);
        recyclerView.setAdapter(adapterTeam);
        adapterTeam.notifyDataSetChanged();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.team, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        int id = item.getItemId();

        if (id == R.id.pteam){
            Intent i = new Intent(getApplicationContext(), AddTeamActivity.class);
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
        super.onBackPressed();
    }
}
