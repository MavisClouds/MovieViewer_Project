package com.example.movieviewer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String extra = getIntent().getStringExtra("EXTRA_TRENDING_MOVIE");
        TrendingMovie trendingMovie = new Gson().fromJson(extra, TrendingMovie.class);

        TextView text = findViewById(R.id.textView);
        text.setText(trendingMovie.getOverview());


    }
}
