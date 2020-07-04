package com.example.project.Adapter;


import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.project.Activity.CalenderActivity;
import com.example.project.R;
import com.example.project.note.Note;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class NoteAdapter extends RecyclerView.Adapter<NoteAdapter.ViewHolder>{
    private Context context;
    private List<Note> note;
   public NoteAdapter(List<Note> note, Context context){
        this.note = note;
        this.context = context;
    }

    @NonNull
    @Override
    public NoteAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.note_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NoteAdapter.ViewHolder holder, final int position) {
        final Note no=note.get(position);
        int i = (no.getPresent()*100)/no.getTotal();
        int d = no.getMin();
        holder.txtsub.setText(no.getSubject());
        holder.txtdecimal.setText("Attendance = " + no.getPresent() + "/" + no.getTotal());
        holder.txtpercentage.setText(i + "%");
        if(i==d)
        {
            holder.txtstatus.setText("Status: On Track");
        }
        else if(i>d)
        {
            int r=i;
            int a=no.getPresent();
            int b= no.getTotal();
            int c=0;
            while(r>d)
            {
                b++;
                r=(a*100)/b;
                if(r>=d)
                {c++;}
            }
            if(c==0)
            {
                holder.txtstatus.setText("Status: You can't leave next class");
            }
            else if(c==1)
            {
                holder.txtstatus.setText("Status: You can leave next class");
            }
           else
            {holder.txtstatus.setText("Status: You can leave next " + c + " classes");}
        }
        else
        {
            int r=i;
            int a=no.getPresent();
            int b= no.getTotal();
            int c=0;
            while(r<d)
            {
                b++;
                a++;
                r=(a*100)/b;
                if(r<=d)
                {c++;}
            }
            if(c!=1)
            {holder.txtstatus.setText("Status: You have to attend next " + c + " classes");}
            else
            {
                holder.txtstatus.setText("Status: You have to attend next class");
            }
        }
        holder.imgAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Subject").child(no.getId())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    int p = no.getPresent();
                                    int t = no.getTotal();
                                    p=p+1;
                                    t=t+1;
                                    FirebaseDatabase.getInstance().getReference("Subject").child(no.getId()).child("present").setValue(p);
                                    FirebaseDatabase.getInstance().getReference("Subject").child(no.getId()).child("total").setValue(t);
                                    notifyDataSetChanged();
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });
        holder.imgSub.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseDatabase.getInstance().getReference("Subject").child(no.getId())
                        .addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                if(dataSnapshot.exists()){
                                    int t = no.getTotal();
                                    t=t+1;
                                    FirebaseDatabase.getInstance().getReference("Subject").child(no.getId()).child("total").setValue(t);
                                }
                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        });
            }
        });
        holder.mview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(context, CalenderActivity.class);
                context.startActivity(i);
            }
        });
        holder.mview.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
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
                return true;
            }
        });
    }

    @Override
    public int getItemCount() {
        return note.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder{
        TextView txtsub,txtdecimal,txtpercentage,txtstatus;
        Button imgAdd,imgSub;
        View mview;
        ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtsub = (TextView)itemView.findViewById(R.id.subname);
            txtdecimal = (TextView)itemView.findViewById(R.id.decimal);
            txtstatus = (TextView)itemView.findViewById(R.id.status);
            txtpercentage = (TextView)itemView.findViewById(R.id.percentage);
            imgAdd = (Button) itemView.findViewById(R.id.add);
            imgSub = (Button)itemView.findViewById(R.id.sub);
            mview =itemView;
        }
    }
}