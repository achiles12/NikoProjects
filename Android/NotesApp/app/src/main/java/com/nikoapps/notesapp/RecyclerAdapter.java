package com.nikoapps.notesapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    ArrayList<Note> myNotes = new ArrayList<>();
    OnItemClickListner onItemClickListner;
    OnLongItemClick onLongItemClick;

    public RecyclerAdapter(Context context, ArrayList<Note> myNotes, OnItemClickListner onItemClickListner, OnLongItemClick onLongItemClick){
        this.context = context;
        this.myNotes = myNotes;
        this.onItemClickListner = onItemClickListner;
        this.onLongItemClick = onLongItemClick;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.note, parent,false);
        Notes notes = new Notes(row);

        return notes;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((Notes)holder).note.setText(myNotes.get(position).noteText);

        ((Notes)holder).note.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListner.OnItemClicked(position);
            }
        });

        ((Notes)holder).note.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                onLongItemClick.OnItemLongClicked(position);
                return false;
            }
        });
    }

    @Override
    public int getItemCount() {
        return myNotes.size();
    }

    public class Notes extends RecyclerView.ViewHolder {

        TextView note;

        public Notes(View itemView) {
            super(itemView);

            note = (TextView) itemView.findViewById(R.id.note);
        }
    }
}
