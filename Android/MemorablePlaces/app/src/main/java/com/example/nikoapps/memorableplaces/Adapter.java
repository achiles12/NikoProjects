package com.example.nikoapps.memorableplaces;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;

// adapter to create recycler view
public class Adapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    // define variables to be adapted
    Context context;
    OnItemClickListner OnItemClickLister;
    ArrayList<MyPlaces> myPlaces;

    // get variables and assign to in adapter variables
    public Adapter (Context context, ArrayList<MyPlaces> myPlaces, OnItemClickListner OnItemClickLister) {
        this.context = context;
        this.myPlaces = myPlaces;
        this.OnItemClickLister = OnItemClickLister;
    }

    // create view holders
    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        // assign newly created layout onto the row of the recycler
        View row = inflater.inflate(R.layout.entry_row, parent,false);
        // create a new instance of the items contained on the row of the recycler
        Item item = new Item(row);
        return item;
    }

    // set values to the views within rows of the recycler
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((Item)holder).entry.setText(myPlaces.get(position).nameOfPlace);
        ((Item)holder).latlng.setText("Latitude: " +myPlaces.get(position).lat+ " Longitude: " + myPlaces.get(position).lng);

        // this is the on item click listner to be binded to recycler
        ((Item)holder).entry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                OnItemClickLister.OnItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return myPlaces.size();
    }

    // get ids of variables within a row of the recycler
    // what are the contents of the newly created layout?
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
