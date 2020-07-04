package com.example.project.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.EditActivity;
import com.example.project.R;
import com.example.project.note.Note;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NoteAdapter1 extends RecyclerView.Adapter<NoteAdapter1.ViewHolder> {
    private Context context;
    private List<Note> anote;
    public NoteAdapter1(List<Note> anote, Context context){
        this.anote = anote;
        this.context = context;
    }
    @NonNull
    @Override
    public NoteAdapter1.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item1,parent,false);
        return new NoteAdapter1.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter1.ViewHolder holder, final int position) {
        final Note no=anote.get(position);
        holder.subname1.setText(no.getSubject());
        holder.decimal1.setText("Present: " + no.getPresent());
        holder.decimal2.setText("Total Classes: " + no.getTotal());
        holder.img1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Do you want to delete this subject?")
                        .setTitle("Delete Subject!")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase.getInstance().getReference("Subject").child(no.getId()).removeValue();
                                notifyItemRemoved(position);
                                Toast.makeText(context, "Subject deleted sccessfully!", Toast.LENGTH_SHORT).show();
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new AlertDialog.Builder(context)
                        .setMessage("Do you want to edit this subject?")
                        .setTitle("Edit Subject!")
                        .setCancelable(false)
                        .setIcon(android.R.drawable.ic_dialog_alert)
                        .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                Intent iw = new Intent(context, EditActivity.class);
                                iw.putExtra("Subject",no.getSubject());
                                iw.putExtra("Id",no.getId());
                                iw.putExtra("Present",no.getPresent());
                                iw.putExtra("Total",no.getTotal());
                                iw.putExtra("Min",no.getMin());
                                context.startActivity(iw);
                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                }).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return anote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView subname1,decimal1,decimal2;
        ImageButton img,img1;
        View aview;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            subname1 =  (TextView)itemView.findViewById(R.id.subname1);
            decimal1 =  (TextView)itemView.findViewById(R.id.decimal1);
            decimal2 = (TextView)itemView.findViewById(R.id.decimal2);
            img = (ImageButton)itemView.findViewById(R.id.imageButton);
            img1 = (ImageButton)itemView.findViewById(R.id.imageButton1);
            aview = itemView;
        }
    }
}

