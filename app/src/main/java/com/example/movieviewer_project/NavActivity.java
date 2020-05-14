package com.example.movieviewer_project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.ConsumeMovieApi;
import com.frogobox.frogothemoviedbapi.callback.MovieResultCallback;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.data.response.Trending;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.boilerplate.viewrclass.FrogoViewAdapterCallback;
import com.google.android.material.navigation.NavigationView;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class NavActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    String movieList[], moviedesc[];
    RecyclerView recyclerView;
    ProgressBar progressBar;
    ViewFlipper v_flipper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav);

        Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);

        if(drawer != null){
            drawer.addDrawerListener(toggle);
        }
        toggle.syncState();

        NavigationView navigationView = (NavigationView)  findViewById(R.id.nav_view);
        if(navigationView != null){
            navigationView.setNavigationItemSelectedListener(this);
        }

        int slider_img[] = {R.drawable.poster_1, R.drawable.poster_2, R.drawable.poster_3};

        getApiData();
        progressBar = findViewById(R.id.progress_bar);
        v_flipper = findViewById(R.id.viewFlipper);

        for (int sldr_image : slider_img) {
            slider_images(sldr_image);
        }
    }

    public void onBackPressed(){
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        if(drawer != null){
            if(drawer.isDrawerOpen(GravityCompat.START)){
                drawer.closeDrawer(GravityCompat.START);
            }else{
                super.onBackPressed();
            }
        }
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
//        int id = item.getItemId();
//
//        if(id == R.id.ic_info){
//            return true;
//        }

        switch (item.getItemId()){
            case R.id.ic_info:
                Intent intentInfo = new Intent(this, AboutActivity.class);
                startActivity(intentInfo);
                return true;
            case R.id.ic_search:
                Intent intentSearch = new Intent(this, SearchActivity.class);
                startActivity(intentSearch);
                return true;
            default:
        }
        return  super.onOptionsItemSelected(item);
    }

    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        DrawerLayout drawer = findViewById(R.id.drawer_layout);

        switch (item.getItemId()){
            case R.id.nav_movie:
                drawer.closeDrawer(GravityCompat.START);
                displayToast("Home");
                return true;
            case R.id.nav_logout:
                drawer.closeDrawer(GravityCompat.START);
                displayToast("Logout");
                Intent intent = new Intent(NavActivity.this, LoginActivity.class);
                startActivity(intent);
                return true;

            default:
                return false;
        }
    }

    public void displayToast(String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    public void slider_images(int images) {
        ImageView sldr_imageView = new ImageView(this);
        sldr_imageView.setBackgroundResource(images);

        v_flipper.addView(sldr_imageView);
        v_flipper.setFlipInterval(4000);
        v_flipper.setAutoStart(true);

        v_flipper.setInAnimation(this, android.R.anim.slide_in_left);
        v_flipper.setOutAnimation(this, android.R.anim.slide_out_right);

    }

    //fungsi mendapatkan data dari api
    private void getApiData() {
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

        FrogoViewAdapterCallback frogoViewAdapterCallback = new FrogoViewAdapterCallback<TrendingMovie>() {

            @Override
            public void setupInitComponent(@NotNull View view, TrendingMovie trendingMovie) {
                //tipedata //variabel
                TextView title = view.findViewById(R.id.tv_title);
                TextView subtitle = view.findViewById(R.id.tv_subtitle);
                TextView description = view.findViewById(R.id.tv_description);
                ImageView poster = view.findViewById(R.id.iv_poster);

                title.setText(trendingMovie.getTitle());
                subtitle.setText(trendingMovie.getRelease_date());
                description.setText(trendingMovie.getOverview());
                Glide.with(view.getContext()).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL + trendingMovie.getPoster_path()).into(poster);

            }

            @Override
            public void onItemLongClicked(TrendingMovie trendingMovie) {

            }

            @Override
            public void onItemClicked(TrendingMovie trendingMovie) {
                setupIntent(trendingMovie);
            }
        };

        frogoRecyclerView
                .injector()
                .addData(dataMovie)
                .addCustomView(R.layout.movielist_template)
                .addEmptyView(null)
                .addCallback(frogoViewAdapterCallback)
                .createLayoutLinearVertical(false)
                .build();

    }

    private void setupIntent(TrendingMovie trendingMovie) {
        String data = new Gson().toJson(trendingMovie);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("EXTRA_TRENDING_MOVIE", data);
        startActivity(intent);
    }

}
