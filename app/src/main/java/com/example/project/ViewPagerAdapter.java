package com.example.project;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.List;
public class ViewPagerAdapter extends FragmentPagerAdapter {
    int mNumOfTabs;
    public ViewPagerAdapter(FragmentManager fm, int NoofTabs){
        super(fm);
        this.mNumOfTabs = NoofTabs;
    }
    @Override
    public int getCount() {
        return mNumOfTabs;
    }
    @Override
    public Fragment getItem(int position){
        switch (position){
            case 0:
                MondayFragment Mon = new MondayFragment();
                return Mon;
            case 1:
                TuesdayFragment Tue = new TuesdayFragment();
                return Tue;
            case 2:
                WednesdayFragment Wed = new WednesdayFragment();
                return Wed;
            case 3:
                ThursdayFragment Thu = new ThursdayFragment();
                return Thu;
            case 4:
                FridayFragment Fri = new FridayFragment();
                return Fri;
            case 5:
                SaturdayFragment Sat = new SaturdayFragment();
                return Sat;
            case 6:
                SundayFragment Sun = new SundayFragment();
                return Sun;
            default:
                return null;
        }
    }
}
