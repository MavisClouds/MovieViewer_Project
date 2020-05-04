package com.example.movieviewer_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.ConsumeMovieApi;
import com.frogobox.frogothemoviedbapi.callback.MovieResultCallback;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.data.response.Trending;
import com.frogobox.frogothemoviedbapi.util.MovieConstant;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.boilerplate.viewrclass.FrogoViewAdapterCallback;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

public class MainActivity extends AppCompatActivity {

    String movieList[], moviedesc[];
    RecyclerView recyclerView;
    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        getApiData();
        progressBar = findViewById(R.id.progress_bar);


    }

    private void getApiData(){
        ConsumeMovieApi consumeMovieApi = new ConsumeMovieApi(MovieUrl.API_KEY);
        consumeMovieApi.getTrendingMovieWeek(new MovieResultCallback<Trending<TrendingMovie>>() {
            @Override
            public void getResultData(Trending<TrendingMovie> trendingMovieTrending) {
                setupFrogoRecyclerView(trendingMovieTrending.getResults());
            }

            @Override
            public void failedResult(int i, @Nullable String s) {

            }

            @Override
            public void onShowProgress() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressBar.setVisibility(View.VISIBLE);
//                    }
//                });
//                progressBar.setVisibility(View.VISIBLE);
            }

            @Override
            public void onHideProgress() {
//                runOnUiThread(new Runnable() {
//                    @Override
//                    public void run() {
//                        progressBar.setVisibility(View.GONE);
//                    }
//                });

//                progressBar.setVisibility(View.GONE);
            }
        });
    }

    private void setupFrogoRecyclerView(List<TrendingMovie> dataMovie) {

        FrogoRecyclerView frogoRecyclerView = findViewById(R.id.recycler_view);

        FrogoViewAdapterCallback frogoViewAdapterCallback = new FrogoViewAdapterCallback<TrendingMovie>(){

            @Override
            public void setupInitComponent(@NotNull View view, TrendingMovie trendingMovie) {
                //tipedata //variabel
                TextView title = view.findViewById(R.id.title);
                TextView subtitle = view.findViewById(R.id.desc);

//                TextView title = view.findViewById(R.id.frogo_rv_type_6_tv_title);
//                TextView subtitle = view.findViewById(R.id.frogo_rv_type_6_tv_subtitle);
//                TextView description = view.findViewById(R.id.frogo_rv_type_6_tv_description);
//                ImageView poster = view.findViewById(R.id.frogo_rv_type_6_iv_poster);


                title.setText(trendingMovie.getTitle());
                subtitle.setText(trendingMovie.getRelease_date());
//                description.setText(trendingMovie.getOverview());
//                Glide.with(view.getContext()).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL+trendingMovie.getPoster_path()).into(poster);

            }

            @Override
            public void onItemLongClicked(TrendingMovie trendingMovie) {

            }

            @Override
            public void onItemClicked(TrendingMovie trendingMovie) {
                setupIntent(trendingMovie);
            }
        } ;

        frogoRecyclerView
                .injector()
                .addData(dataMovie)
                .addCustomView(R.layout.movielist_template)
                .addEmptyView(null)
                .addCallback(frogoViewAdapterCallback)
                .createLayoutLinearVertical(false)
                .build();

    }

    private void setupIntent(TrendingMovie trendingMovie){
        String data = new Gson().toJson(trendingMovie);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("EXTRA_TRENDING_MOVIE", data);
        startActivity(intent);
    }


}
