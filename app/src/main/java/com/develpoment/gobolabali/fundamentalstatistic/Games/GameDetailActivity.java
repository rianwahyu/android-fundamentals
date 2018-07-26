package com.develpoment.gobolabali.fundamentalstatistic.Games;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
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

import com.develpoment.gobolabali.fundamentalstatistic.Database.DatabaseHelper;
import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentResultTeam;
import com.develpoment.gobolabali.fundamentalstatistic.R;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Rian on 11/07/2018.
 */

public class GameDetailActivity extends AppCompatActivity {
    Integer NUM_ROW = Integer.valueOf(20);
    String category;
    String idmatch;
    private TabLayout tabLayout;
    String idteam1;
    String idteam2;
    String namateam1, namateam2, namamatch;
    String th;
    String tr = "";
    private ViewPager viewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match_detail);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMatchDetail);
        toolbar.setTitle("Game Detail");
        toolbar.setNavigationIcon(R.drawable.ic_back_white);
        toolbar.setTitleTextColor(Color.WHITE);
        setSupportActionBar(toolbar);

        idmatch = getIntent().getStringExtra("idmatch");
        idteam1 = getIntent().getStringExtra("idteam1");
        idteam2 = getIntent().getStringExtra("idteam2");
        namateam1 = getIntent().getStringExtra("nmteam1");
        namateam2 = getIntent().getStringExtra("nmteam2");
        category = getIntent().getStringExtra("category");
        namamatch = getIntent().getStringExtra("nmmatch");

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), GameActivity.class);
//                intent.putExtra("idtournament", idturnament);
//                intent.putExtra("namatournament", namaturnament);
                intent.putExtra("idmatch", idmatch);
                intent.putExtra("nmmatch", namamatch);
//                intent.putExtra("nmmatch",namamatch);
                intent.putExtra("idteam1",idteam1);
                intent.putExtra("idteam2",idteam2);
                intent.putExtra("nmteam1",namateam1);
                intent.putExtra("nmteam2",namateam2);
                intent.putExtra("category",category);

                startActivity(intent);
            }
        });

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

    }

    DatabaseHelper db = new DatabaseHelper(this);
    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        Bundle bundleA = new Bundle();
        bundleA.putString("category", category);
        bundleA.putString("team", idteam1);
        bundleA.putString("teampos", "left");
        bundleA.putString("idmatch", idmatch);
        FragmentResultTeam teamA = new FragmentResultTeam();
        teamA.setArguments(bundleA);
        adapter.addFragment(teamA, namateam1);
        new FragmentResultTeam().setArguments(bundleA);
        Bundle bundle = new Bundle();
        bundle.putString("team", idteam2);
        bundle.putString("teampos", "right");
        bundle.putString("category", category);
        bundle.putString("idmatch", idmatch);
        FragmentResultTeam fragB = new FragmentResultTeam();
        fragB.setArguments(bundle);
        adapter.addFragment(fragB, namateam2);
        adapter.notifyDataSetChanged();
        viewPager.setAdapter(adapter);
    }

    private class ViewPagerAdapter extends FragmentPagerAdapter {

        private final List<Fragment> mFragmentList = new ArrayList();
        private final List<String> mFragmentTitleList = new ArrayList();

        public ViewPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return  (Fragment) mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFragment(FragmentResultTeam fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        public CharSequence getPageTitle(int position) {
            return (CharSequence) mFragmentTitleList.get(position);
        }
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(getApplicationContext(), GameActivity.class);
//                intent.putExtra("idtournament", idturnament);
//                intent.putExtra("namatournament", namaturnament);
        intent.putExtra("idmatch", idmatch);
        intent.putExtra("nmmatch", namamatch);
//                intent.putExtra("nmmatch",namamatch);
        intent.putExtra("idteam1",idteam1);
        intent.putExtra("idteam2",idteam2);
        intent.putExtra("nmteam1",namateam1);
        intent.putExtra("nmteam2",namateam2);
        intent.putExtra("category",category);

        startActivity(intent);
    }
}
