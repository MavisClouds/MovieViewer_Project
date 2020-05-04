package com.example.movieviewer_project;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.ViewFlipper;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.frogobox.frogothemoviedbapi.ConsumeMovieApi;
import com.frogobox.frogothemoviedbapi.callback.MovieResultCallback;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.data.response.Trending;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.boilerplate.viewrclass.FrogoViewAdapterCallback;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    String movieList[], moviedesc[];
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        int slider_img[] = {R.drawable.img1, R.drawable.img2, R.drawable.img3};

        getApiData();
        progressBar = findViewById(R.id.progress_bar);
        v_flipper = findViewById(R.id.viewFlipper);

        for (int sldr_image:slider_img)
        {
            slider_images(sldr_image);
        }
    }

    public void slider_images(int images)
    {
        ImageView sldr_imageView = new ImageView(this);
        sldr_imageView.setBackgroundResource(images);

        v_flipper.addView(sldr_imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this,android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

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
