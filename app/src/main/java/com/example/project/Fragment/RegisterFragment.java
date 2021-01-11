package com.example.project.Fragment;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.project.Activity.Activity2;
import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RegisterFragment extends Fragment {
    private EditText editText9,editText10,editText8;
    private Button button;
    private TextView textView11;
    private String name,username,password;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_register, container, false);
        editText8 =  view.findViewById(R.id.editText8);
        editText9 =  view.findViewById(R.id.editText9);
        editText10 =  view.findViewById(R.id.editText10);
        button = view.findViewById(R.id.button);
        textView11 = view.findViewById(R.id.textView11);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = editText8.getText().toString();
                username = editText9.getText().toString();
                password = editText10.getText().toString();
                FirebaseDatabase.getInstance().getReference("Users").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if(snapshot.exists()){
                            for(DataSnapshot dataSnapshot:snapshot.getChildren()){
                                if(username.equals(dataSnapshot.getValue()))
                                    Toast.makeText(getActivity(),"Username Is Already Registered",Toast.LENGTH_SHORT).show();
                                else{
                                    FirebaseDatabase.getInstance().getReference("Users").child(username).setValue(username);
                                    FirebaseDatabase.getInstance().getReference(username).child("Name").setValue(name);
                                    FirebaseDatabase.getInstance().getReference(username).child("Password").setValue(password);
                                    Intent i = new Intent(getActivity(), Activity2.class);
                                    i.putExtra("username",username);
                                    startActivity(i);
                                }
                            }
                        }
                        else
                        {   FirebaseDatabase.getInstance().getReference("Users").child(username).setValue(username);
                        FirebaseDatabase.getInstance().getReference(username).child("Name").setValue(name);
                        FirebaseDatabase.getInstance().getReference(username).child("Password").setValue(password);
                        Intent i = new Intent(getActivity(), Activity2.class);
                        i.putExtra("username",username);
                        startActivity(i);}
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            }
        });
        textView11.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Fragment someFragment = new LoginFragment();
                assert getFragmentManager() != null;
                FragmentTransaction transaction = getFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container1, someFragment );
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
        return view;}
}
