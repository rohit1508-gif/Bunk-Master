package com.example.project;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

public class TimetableFragment extends Fragment {
    private TabLayout tabLayout;
    private ViewPager viewpager;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.fragment_timetable, container, false);
        tabLayout =(TabLayout) view.findViewById(R.id.tablayout_id);
        viewpager =(ViewPager) view.findViewById(R.id.viewpager_id);
        ViewPagerAdapter adapter = new ViewPagerAdapter(getFragmentManager());
        adapter.AddFragment(new MondayFragment(),"Mon");
        adapter.AddFragment(new TuesdayFragment(),"Tue");
        adapter.AddFragment(new WednesdayFragment(),"Wed");
        adapter.AddFragment(new ThursdayFragment(),"Thu");
        adapter.AddFragment(new FridayFragment(),"Fri");
        adapter.AddFragment(new SaturdayFragment(),"Sat");
        adapter.AddFragment(new SundayFragment(),"Sun");
        viewpager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewpager);
        return view;
}
}
