package com.example.project.DayFragment;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.AddSubjectActivity;
import com.example.project.note.Note1;
import com.example.project.Adapter.NoteAdapterMonday;
import com.example.project.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class WednesdayFragment extends Fragment {
    private List<Note1> mnote;
    private RecyclerView rv1;
    private NoteAdapterMonday adapter;
    private Context ctx;
    Button addSubject;
    public WednesdayFragment(){}
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
       View view =  inflater.inflate(R.layout.fragment_wednesday, container, false);
       addSubject = (Button) view.findViewById(R.id.addsubject);
        rv1= (RecyclerView) view.findViewById(R.id.recycler_view);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        mnote = new ArrayList<>();
        ctx = getActivity();
        DatabaseReference databasenote = FirebaseDatabase.getInstance().getReference("Wednesday");
        databasenote.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Note1 l = npsnapshot.getValue(Note1.class);
                        mnote.add(l);
                    }
                    adapter = new NoteAdapterMonday(mnote,ctx);
                    rv1.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        addSubject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent iv= new Intent(getActivity(), AddSubjectActivity.class);
                iv.putExtra("Day","Wednesday");
                startActivity(iv);
            }
        });
       return view;
    }
}
