package com.example.project.Adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.R;
import com.example.project.note.Note1;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class NoteAdapterMonday extends RecyclerView.Adapter<NoteAdapterMonday.ViewHolder>{
    private Context context;
    private List<Note1> mnote;
    public NoteAdapterMonday(List<Note1> mnote, Context context){
        this.mnote = mnote;
        this.context = context;
    }
    @NonNull
    @Override
    public NoteAdapterMonday.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item2,parent,false);
        return new NoteAdapterMonday.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapterMonday.ViewHolder holder, final int position) {
        final Note1 no =mnote.get(position);
        holder.sub.setText(no.getS());
        holder.start.setText("Start Time: " + no.getA());
        holder.end.setText("End Time: " + no.getB());
        holder.delete.setOnClickListener(new View.OnClickListener() {
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
                                FirebaseDatabase.getInstance().getReference(no.getDay()).child(no.getId()).removeValue();
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
    }

    @Override
    public int getItemCount() {
        return mnote.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView sub,end,start;
       ImageButton delete;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            sub = (TextView)itemView.findViewById(R.id.subname);
            end = (TextView)itemView.findViewById(R.id.endhour);
            start =(TextView)itemView.findViewById(R.id.starthour);
            delete = (ImageButton)itemView.findViewById(R.id.imageButton1);
        }
    }
}
