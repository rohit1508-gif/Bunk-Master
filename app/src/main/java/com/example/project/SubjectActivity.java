package com.example.project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class SubjectActivity extends AppCompatActivity {
    EditText editText,editText2,editText3,editText4;
    TextView textView,textView2,textView3;
    DatabaseReference databasenote;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        databasenote = FirebaseDatabase.getInstance().getReference("Subject");
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
    private void saveNote() {
        String id = databasenote.push().getKey();
        String subject = editText.getText().toString();
        int total = Integer.parseInt(editText3.getText().toString());
        int present = Integer.parseInt(editText2.getText().toString());
        int min = Integer.parseInt(editText4.getText().toString());
        if (subject.trim().isEmpty()) {
            return;
        }
        Note note = new Note(id,subject,total,present,min);
        databasenote.child(id).setValue(note);
        Toast.makeText(this, "Subject added", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(getApplicationContext(),Activity2.class);
        startActivity(intent);
    }
}
