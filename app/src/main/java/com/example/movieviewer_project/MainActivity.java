package com.example.movieviewer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    String movieList[], moviedesc[];
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerV);
        movieList = getResources().getStringArray(R.array.movie_list);
        moviedesc = getResources().getStringArray(R.array.movie_desc);

        MovielistAdapter movielistAdapter = new MovielistAdapter(this,movieList,moviedesc);
        recyclerView.setAdapter(movielistAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }
}
