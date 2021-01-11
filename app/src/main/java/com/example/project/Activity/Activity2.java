package com.example.project.Activity;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.project.Fragment.AttendanceFragment;
import com.example.project.Fragment.SubjectFragment;
import com.example.project.Fragment.TimetableFragment;
import com.example.project.R;
import com.google.android.material.navigation.NavigationView;



public class Activity2 extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DrawerLayout drawer;
    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_2);
        if (getIntent().getBooleanExtra("EXIT", false)) {
            finish();
        }
        Intent i  = getIntent();
        username = i.getStringExtra("username");
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
        drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
                    new AttendanceFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_message);
        }
    }
    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);}
            else{super.onBackPressed();
        Intent intent = new Intent(Activity2.this, Activity2.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("EXIT", true);
        startActivity(intent);
    }}
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId()){
            case R.id.nav_message:
            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new AttendanceFragment()).commit();
                break;
            case R.id.nav_subject:
         getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new SubjectFragment()).commit();
         break;
            case R.id.nav_timetable:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new TimetableFragment()).commit();
                break;
        }
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}