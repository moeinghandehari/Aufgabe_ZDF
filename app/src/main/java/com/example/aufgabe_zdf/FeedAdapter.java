package com.example.aufgabe_zdf;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class FeedAdapter extends FragmentPagerAdapter {

    public FeedAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public Fragment getItem(int i) {
        if(i == 0)
            return new FeedFragment();
        else if(i == 1)
            return new TitleFragment();
        else return new ImageFragment();
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        if(position == 0)
            return "Feed";
        else if(position == 1) return "Titles";
        else return "Images";
    }

}
