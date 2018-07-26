package com.develpoment.gobolabali.fundamentalstatistic.Match;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.develpoment.gobolabali.fundamentalstatistic.Adapter.PagerAdapter;
import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentPlayer1;
import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentPlayer2;
import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentPlayer3;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

public class MatchPlayerListActivity extends AppCompatActivity {

    private Toolbar toolbar;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    String idteam1, idteam2,idtournamentx,namatournamentx, idmatch;
    String idmatch2, idtournament2;
    Button btnNext;
    DatabaseHelper db = new DatabaseHelper(this);
    String nmteam1, nmteam2;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_player);

        idtournamentx = getIntent().getStringExtra("idtournament");
        namatournamentx = db.getTournamentName(idtournamentx);
        idmatch = getIntent().getStringExtra("id");
        idteam1 = getIntent().getStringExtra("idteam1");
        idteam2 = getIntent().getStringExtra("idteam2");

        idmatch2 = getIntent().getStringExtra("id");
        idtournament2 = getIntent().getStringExtra("idtournament");


        nmteam1 = db.getTeamName(idteam1);
        nmteam2 = db.getTeamName(idteam2);


        toolbar = (Toolbar) findViewById(R.id.toolbarMatch);
        toolbar.setTitle(nmteam1 + " vs " + nmteam2);
        toolbar.setSubtitle("Player List");
        toolbar.setTitleTextColor(Color.WHITE);
        toolbar.setSubtitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        /*btnNext = (Button) findViewById(R.id.buttonNext);
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"ini tes next", Toast.LENGTH_SHORT).show();
            }
        });*/

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);

        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(getApplicationContext(), MatchListActivity.class);
                i.putExtra("id",idtournamentx);
                i.putExtra("namatournament",namatournamentx);
                startActivity(i);
                finish();
            }
        });

    }


    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundle =new Bundle();
        bundle.putString("welcome", idteam1);
        bundle.putString("idmatch", idmatch);
        bundle.putString("idtournament", idtournamentx);
        bundle.putString("pos", "left");



        FragmentPlayer1 fragobj = new FragmentPlayer1();
        fragobj.setArguments(bundle);
        adapter.addFragment(fragobj, nmteam1);

        Bundle bundle1 =new Bundle();
        bundle1.putString("welcome2", idteam2);
        bundle1.putString("idmatch2", idmatch2);
        bundle1.putString("idtournament2", idtournament2);
        bundle1.putString("pos2", "right");
        FragmentPlayer3 fragobj1 = new FragmentPlayer3();
        fragobj1.setArguments(bundle1);
        adapter.addFragment(fragobj1, nmteam2);
        viewPager.setAdapter(adapter);
    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();

        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(getApplicationContext(), MatchListActivity.class);
        i.putExtra("id",idtournamentx);
        i.putExtra("namatournament",namatournamentx);
        startActivity(i);
        finish();
    }
}
