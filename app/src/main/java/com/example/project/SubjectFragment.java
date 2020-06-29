package com.example.project;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import java.util.ArrayList;
import java.util.List;

public class SubjectFragment extends Fragment {
    private List<Note> anote;
    private RecyclerView rv1;
    private NoteAdapter1 adapter;
    private Context ctx;
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_subject, container, false);
        rv1= (RecyclerView) view.findViewById(R.id.recycler_view1);
        rv1.setHasFixedSize(true);
        rv1.setLayoutManager(new LinearLayoutManager(getActivity()));
        anote = new ArrayList<>();
        ctx = getActivity();
        DatabaseReference databasenote = FirebaseDatabase.getInstance().getReference("Subject");
        databasenote.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot npsnapshot : dataSnapshot.getChildren()) {
                        Note l = npsnapshot.getValue(Note.class);
                        anote.add(l);
                    }
                    adapter = new NoteAdapter1(anote,ctx);
                    rv1.setAdapter(adapter);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
            }
        });
        return view;
    }
}
