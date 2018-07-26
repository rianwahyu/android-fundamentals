package com.develpoment.gobolabali.fundamentalstatistic.Adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentPlayer1;
import com.develpoment.gobolabali.fundamentalstatistic.Fragment.FragmentPlayer2;

public class PagerAdapter extends FragmentStatePagerAdapter {
    private  int num_tabs;
    public PagerAdapter(FragmentManager fm, int
                        num_tabs) {
        super(fm);
        this.num_tabs=num_tabs;

    }

    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return new FragmentPlayer1();
            case 1:
                return new FragmentPlayer2();
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return num_tabs;
    }
}
