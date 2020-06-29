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

import com.google.firebase.database.FirebaseDatabase;

public class EditActivity extends AppCompatActivity {
    String Id;
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
        Id = iw.getStringExtra("Id");
        String Subject = iw.getStringExtra("Subject");
        int Total = iw.getIntExtra("Total",0);
        int Present = iw.getIntExtra("Present",0);
        int Min = iw.getIntExtra("Min",0);
        editText.setText(Subject);
        editText3.setText(Total);
        editText4.setText(Min);
        editText2.setText(Present);
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
        FirebaseDatabase.getInstance().getReference("Subject").child(Id).child("subject").setValue(a);
        FirebaseDatabase.getInstance().getReference("Subject").child(Id).child("present").setValue(b);
        FirebaseDatabase.getInstance().getReference("Subject").child(Id).child("total").setValue(c);
        FirebaseDatabase.getInstance().getReference("Subject").child(Id).child("min").setValue(d);
        Toast.makeText(this, "Subject edited", Toast.LENGTH_SHORT).show();
        Intent ir= new Intent(EditActivity.this,Activity2.class);
        startActivity(ir);
    }
}
