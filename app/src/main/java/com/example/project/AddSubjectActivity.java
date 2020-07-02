package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.FirebaseDatabase;

public class AddSubjectActivity extends AppCompatActivity {
    EditText editText5,editText6,editText7;
    String day;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_subject);
        editText5 = findViewById(R.id.editText5);
        editText7 = findViewById(R.id.editText7);
        editText6 = findViewById(R.id.editText6);
        Intent iv = getIntent();
        day = iv.getStringExtra("Day");
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_note:
                saveNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    public void saveNote(){
        String id = FirebaseDatabase.getInstance().getReference(day).push().getKey();
        String s = editText5.getText().toString();
        String a = editText6.getText().toString();
        String b =editText7.getText().toString();
        if (a.trim().isEmpty()) {
            Toast.makeText(this,"Class start time is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (s.trim().isEmpty()) {
            Toast.makeText(this,"Subject is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        if (b.trim().isEmpty()) {
            Toast.makeText(this,"Class end time is empty",Toast.LENGTH_SHORT).show();
            return;
        }
        Note1 note = new Note1(id,s,a,b,day);
        FirebaseDatabase.getInstance().getReference(day).child(id).setValue(note);
        Toast.makeText(this,"Subject Added",Toast.LENGTH_SHORT).show();
        finish();
    }
}
