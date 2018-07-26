package com.develpoment.gobolabali.fundamentalstatistic.Games;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Main.MainActivity;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataMatchList;
import com.develpoment.gobolabali.fundamentalstatistic.Model.DataTournament;
import com.develpoment.gobolabali.fundamentalstatistic.R;
import com.weiwangcn.betterspinner.library.material.MaterialBetterSpinner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class GameChooseActivity extends AppCompatActivity {

    MaterialBetterSpinner spinnerTurnament, spinnerMatch, spinnerCategory;
    LinearLayout linearMatch;
    DatabaseHelper db = new DatabaseHelper(this);
    Button btnNext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_list);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarGame);
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

        btnNext = (Button) findViewById(R.id.buttonNext);

        spinnerTurnament = (MaterialBetterSpinner) findViewById(R.id.spinnerTurnament);
        spinnerMatch = (MaterialBetterSpinner) findViewById(R.id.spinnerMatch);
        spinnerCategory = (MaterialBetterSpinner) findViewById(R.id.spinnerCategory);
        linearMatch = (LinearLayout) findViewById(R.id.linearShowGame);

        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toNext();
            }
        });

        spinnerTurnament.setVisibility(View.VISIBLE);
        spinnerMatch.setVisibility(View.GONE);
        linearMatch.setVisibility(View.GONE);
        spinnerCategory.setVisibility(View.GONE);

        btnNext.setVisibility(View.GONE);

        loadTurnament();

    }


    String idturnament;
    String namaturnament;
    private void loadTurnament() {
        ArrayList spinnHomeList = db.getSpinnerTournament();

        DataTournament dataTeam = new DataTournament();
        dataTeam.getNamaTournament();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(GameChooseActivity.this,android.R.layout.simple_spinner_item,spinnHomeList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTurnament.setAdapter(spinnerAdapter);
        spinnerTurnament.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "You selected: " + label,
//                        Toast.LENGTH_LONG).show();

                DataTournament dataTeam = (DataTournament) adapterView.getItemAtPosition(i);
                idturnament = dataTeam.getId();
                namaturnament = dataTeam.getNamaTournament();

                loadMatch(idturnament);
            }
        });
    }

    String idMatch;
    String nmMatch;
    private void loadMatch(String idturnament) {
        int idtournament = Integer.parseInt(idturnament);
        spinnerMatch.setVisibility(View.VISIBLE);

        ArrayList spinnHomeList = db.getSpinnerMatch(idtournament);

        DataMatchList dataTeam = new DataMatchList();
        dataTeam.getNamaPertandingan();

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(GameChooseActivity.this,android.R.layout.simple_spinner_item,spinnHomeList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMatch.setAdapter(spinnerAdapter);
        spinnerMatch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String label = adapterView.getItemAtPosition(i).toString();
//                Toast.makeText(getApplicationContext(), "You selected: " + label,
//                        Toast.LENGTH_LONG).show();

                DataMatchList dataTeam = (DataMatchList) adapterView.getItemAtPosition(i);
                idMatch = dataTeam.getId();
                nmMatch = dataTeam.getNamaPertandingan();
                loadLinearGame(idMatch);

            }
        });
    }

    String idteam1, idteam2,categorytemp, namateam1, namateam2;
    private void loadLinearGame(String idMatch) {
        String _idMatch = idMatch;
        linearMatch.setVisibility(View.VISIBLE);
        spinnerCategory.setVisibility(View.VISIBLE);

        TextView textHome = (TextView) findViewById(R.id.textHome);
        TextView textAway = (TextView) findViewById(R.id.textAway);

        idteam1 = db.getTeam1(_idMatch);
        idteam2 = db.getTeam2(_idMatch);
        namateam1 = db.getTeamName(idteam1);
        namateam2 = db.getTeamName(idteam2);
        textHome.setText(namateam1);
        textAway.setText(namateam2);

        final String[] posisi = new String[]{
                "Keeper",
                "Player",
                "Fouls"
        };

        final List<String> posiisList = new ArrayList<>(Arrays.asList(posisi));
        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<String>(GameChooseActivity.this,android.R.layout.simple_spinner_item,posiisList);
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerCategory.setAdapter(spinnerAdapter);
        spinnerCategory.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String selectPosisi = (String) adapterView.getItemAtPosition(i).toString().toUpperCase();
                categorytemp =selectPosisi;
                btnNext.setVisibility(View.VISIBLE);
            }
        });

    }

    private void toNext() {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
        intent.putExtra("idtournament", idturnament);
        intent.putExtra("namatournament", namaturnament);
        intent.putExtra("idmatch", idMatch);
        intent.putExtra("nmmatch",nmMatch);
        intent.putExtra("idteam1",idteam1);
        intent.putExtra("idteam2",idteam2);
        intent.putExtra("nmteam1",namateam1);
        intent.putExtra("nmteam2",namateam2);
        intent.putExtra("category",categorytemp);

        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MainActivity.class);
        startActivity(i);
        finish();
    }
}
