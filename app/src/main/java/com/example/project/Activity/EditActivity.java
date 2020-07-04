package com.example.project.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.project.R;
import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    String id;
    EditText editText,editText2,editText3,editText4;
    TextView textView,textView2,textView3;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit);
        editText = findViewById(R.id.editText);
        editText2 = findViewById(R.id.editText2);
        editText3 = findViewById(R.id.editText3);
        editText4 = findViewById(R.id.editText4);
        Intent iw = getIntent();
        id = iw.getStringExtra("Id");
        String subject = iw.getStringExtra("Subject");
        String total = iw.getStringExtra("Total");
        String present = iw.getStringExtra("Present");
        String min = iw.getStringExtra("Min");
        editText3.setText(total);
        editText4.setText(min);
        editText2.setText(present);
        editText.setText(subject);
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.new_note_menu1, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh_note:
                refreshNote();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }
    private void refreshNote(){
        String a = editText.getText().toString();
        int b = Integer.parseInt(editText2.getText().toString());
        int c = Integer.parseInt(editText3.getText().toString());
        int d = Integer.parseInt(editText4.getText().toString());
        FirebaseDatabase.getInstance().getReference("Subject").child(id).child("subject").setValue(a);
        FirebaseDatabase.getInstance().getReference("Subject").child(id).child("present").setValue(b);
        FirebaseDatabase.getInstance().getReference("Subject").child(id).child("total").setValue(c);
        FirebaseDatabase.getInstance().getReference("Subject").child(id).child("min").setValue(d);
        Toast.makeText(this, "Subject edited", Toast.LENGTH_SHORT).show();
        finish();
    }
}
