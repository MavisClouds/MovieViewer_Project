package com.example.movieviewer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.google.gson.Gson;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        String extra = getIntent().getStringExtra("EXTRA_TRENDING_MOVIE");
        TrendingMovie trendingMovie = new Gson().fromJson(extra, TrendingMovie.class);

        ImageView posterToolbarDetail = findViewById(R.id.img_photo_toolbar);
        Glide.with(this).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL+trendingMovie.getBackdrop_path()).into(posterToolbarDetail);

        TextView releaseDateDetail = findViewById(R.id.tv_release_date_detail);
        releaseDateDetail.setText(trendingMovie.getTitle());

        TextView rateDetail = findViewById(R.id.tv_rate_detail);
        rateDetail.setText(trendingMovie.getTitle());

        TextView popularityDetail = findViewById(R.id.tv_popularity_detail);
        popularityDetail.setText(trendingMovie.getTitle());

        TextView titleDetail = findViewById(R.id.tv_title_detail);
        titleDetail.setText(trendingMovie.getTitle());

        TextView descriptionDetail = findViewById(R.id.tv_description_detail);
        descriptionDetail.setText(trendingMovie.getOverview());

        ImageView posterDetail = findViewById(R.id.iv_poster_detail);
        Glide.with(this).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL+trendingMovie.getPoster_path()).into(posterDetail);


    }


}
