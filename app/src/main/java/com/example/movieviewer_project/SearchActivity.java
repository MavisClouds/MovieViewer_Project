package com.example.movieviewer_project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.ConsumeMovieApi;
import com.frogobox.frogothemoviedbapi.callback.MovieResultCallback;
import com.frogobox.frogothemoviedbapi.data.model.SearchMovieResult;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.data.response.SearchMovies;
import com.frogobox.frogothemoviedbapi.data.response.Trending;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.boilerplate.viewrclass.FrogoViewAdapterCallback;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class SearchActivity extends BaseActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        searchMovieListener();
        setupDetailActivity("Search Movie");
    }

    private void searchMovieListener(){
        final EditText etSearch = findViewById(R.id.et_search);

        etSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                getApiData(s.toString());
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }

    private void getApiData(String query) {
        ConsumeMovieApi consumeMovieApi = new ConsumeMovieApi(MovieUrl.API_KEY);
        consumeMovieApi.searchMovies(query,
                null,
                null,
                null,
                null,
                null,
                null,
                new MovieResultCallback<SearchMovies>() {

            @Override
            public void onShowProgress() {

            }

            @Override
            public void onHideProgress() {

            }

            @Override
            public void getResultData(SearchMovies searchMovies) {
                setupFrogoRecyclerView(searchMovies.getResults());
            }

            @Override
            public void failedResult(int i, @Nullable String s) {

            }
        });
    }

    private void setupFrogoRecyclerView(List<SearchMovieResult> dataMovie) {

        FrogoRecyclerView frogoRecyclerView = findViewById(R.id.frogo_rv_search);

        FrogoViewAdapterCallback frogoViewAdapterCallback = new FrogoViewAdapterCallback<SearchMovieResult>() {

            @Override
            public void setupInitComponent(@NotNull View view, SearchMovieResult searchMovies) {

                TextView title = view.findViewById(R.id.tv_title);
                TextView subtitle = view.findViewById(R.id.tv_subtitle);
                TextView description = view.findViewById(R.id.tv_description);
                ImageView poster = view.findViewById(R.id.iv_poster);

                title.setText(searchMovies.getTitle());
                subtitle.setText(searchMovies.getRelease_date());
                description.setText(searchMovies.getOverview());
                Glide.with(view.getContext()).load(MovieUrl.BASE_URL_IMAGE_ORIGNAL + searchMovies.getPoster_path()).into(poster);

            }

            @Override
            public void onItemLongClicked(SearchMovieResult searchMovies) {

            }

            @Override
            public void onItemClicked(SearchMovieResult searchMovies) {
                setupIntent(searchMovies);
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

    private void setupIntent(SearchMovieResult searchMovies) {
        String data = new Gson().toJson(searchMovies);
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("EXTRA_SEARCH_MOVIE", data);
        startActivity(intent);
    }
}
