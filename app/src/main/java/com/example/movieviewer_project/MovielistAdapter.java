package com.example.movieviewer_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MovielistAdapter extends RecyclerView.Adapter<MovielistAdapter.MyViewHolder> {

    String thismovielist[], thismoviedesc[];
    Context thiscontext;

    MovielistAdapter(Context context, String movielist[], String moviedesc[])
    {
        thiscontext = context;
        thismovielist = movielist;
        thismoviedesc = moviedesc;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater = LayoutInflater.from(thiscontext);
        View view = layoutInflater.inflate(R.layout.movielist_template, parent,false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        holder.movielist.setText(thismovielist[position]);
        holder.moviedesc.setText(thismoviedesc[position]);
    }

    @Override
    public int getItemCount() {
        return thismovielist.length;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {

        TextView movielist,moviedesc;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            movielist = itemView.findViewById(R.id.title);
            moviedesc = itemView.findViewById(R.id.desc);
        }
    }
}
