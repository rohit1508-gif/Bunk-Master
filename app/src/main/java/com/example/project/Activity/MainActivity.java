package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.FrameLayout;

import com.example.project.Fragment.AttendanceFragment;
import com.example.project.Fragment.LoginFragment;
import com.example.project.R;


public class MainActivity extends AppCompatActivity {

    protected boolean _active = true;
    protected int _splashTime = 3000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            public void run() {
                finish();
                Intent i3 = new Intent(MainActivity.this, Activity2.class);
               startActivity(i3);
            }
        }, _splashTime);
//        if (savedInstanceState == null) {
//            getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container1,
//                    new LoginFragment()).commit();
//        }
}
}
