package com.example.nikoapps.memorableplaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    Context context;
    //ArrayList<String> items;
    OnItemClickListner OnItemClickLister;
    ArrayList<MyPlaces> myPlaces;

    public Adapter (Context context, ArrayList<MyPlaces> myPlaces, OnItemClickListner OnItemClickLister) {
        this.context = context;
        //this.items = items;
        this.myPlaces = myPlaces;
        this.OnItemClickLister = OnItemClickLister;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.entry_row, parent,false);
        Item item = new Item(row);
        return item;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((Item)holder).entry.setText(myPlaces.get(position).nameOfPlace);
        ((Item)holder).latlng.setText("Latitude: " +myPlaces.get(position).lat+ " Longitude: " + myPlaces.get(position).lng);
        ((Item)holder).entry.setOnClickListener(new View.OnClickListener() {


            @Override
            public void onClick(View view) {
                // Toast.makeText(context,  items.get(position) +": " + String.valueOf(position), Toast.LENGTH_SHORT).show();
                OnItemClickLister.OnItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPlaces.size();
    }

    public class Item extends RecyclerView.ViewHolder {
        TextView entry;
        TextView latlng;

        public Item(View itemView) {
            super(itemView);
            entry = (TextView) itemView.findViewById(R.id.entry);
            latlng = (TextView) itemView.findViewById(R.id.latlng);

        }



    }

}
