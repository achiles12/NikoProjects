package com.nikoapps.newsreaderapp;

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
    ArrayList<NewsEntry> newsEntries;
    OnItemClickListener onItemClickListener;



    public RecyclerAdapter(Context context, ArrayList<NewsEntry> newsEntries, OnItemClickListener onItemClickListener){
        this.context = context;
        this.newsEntries = newsEntries;
        this.onItemClickListener = onItemClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater inflater = LayoutInflater.from(context);
        View row = inflater.inflate(R.layout.news_item, parent,false);
        News news = new News(row);

        return news;

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {

        ((News)holder).newsHeadline.setText(newsEntries.get(position).newsHeadline);
        ((News)holder).newsScore.setText(newsEntries.get(position).newsScore);
        ((News)holder).newsDate.setText(newsEntries.get(position).newsDate);

        ((News)holder).newsHeadline.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClicked(position);
            }
        });

        ((News)holder).newsScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClicked(position);
            }
        });

        ((News)holder).newsDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onItemClickListener.OnItemClicked(position);
            }
        });

    }

    @Override
    public int getItemCount() {
        return newsEntries.size();
    }

    public class News extends RecyclerView.ViewHolder {

        TextView newsHeadline;
        TextView newsScore;
        TextView newsDate;

        public News(View itemView) {
            super(itemView);

            newsHeadline = (TextView) itemView.findViewById(R.id.newsHeadline);
            newsScore = (TextView) itemView.findViewById(R.id.newsScore);
            newsDate = (TextView) itemView.findViewById(R.id.newsDate);

        }
    }
}
