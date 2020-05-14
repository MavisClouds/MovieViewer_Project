package com.example.movieviewer_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.google.gson.Gson;

public class DetailActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Toolbar toolbar = findViewById(R.id.tv_toolbar);

        ImageView posterToolbarDetail = findViewById(R.id.img_photo_toolbar);
        ImageView posterDetail = findViewById(R.id.iv_poster_detail);

        TextView releaseDateDetail = findViewById(R.id.tv_release_date_detail);
        TextView rateDetail = findViewById(R.id.tv_rate_detail);
        TextView popularityDetail = findViewById(R.id.tv_popularity_detail);
        TextView descriptionDetail = findViewById(R.id.tv_description_detail);
        TextView titleDetail = findViewById(R.id.tv_title_detail);

        String extra = getIntent().getStringExtra("EXTRA_TRENDING_MOVIE");
        TrendingMovie trendingMovie = new Gson().fromJson(extra, TrendingMovie.class);

        setupDetailActivity(toolbar, trendingMovie.getTitle());

        releaseDateDetail.setText(trendingMovie.getTitle());
        rateDetail.setText(trendingMovie.getTitle());
        popularityDetail.setText(trendingMovie.getTitle());
        titleDetail.setText(trendingMovie.getTitle());
        descriptionDetail.setText(trendingMovie.getOverview());

        Glide.with(this).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL + trendingMovie.getBackdrop_path()).into(posterToolbarDetail);
        Glide.with(this).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL + trendingMovie.getPoster_path()).into(posterDetail);

    }


}
