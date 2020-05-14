package com.example.movieviewer_project;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.frogobox.frogothemoviedbapi.data.model.TrendingMovie;
import com.frogobox.frogothemoviedbapi.util.MovieUrl;
import com.frogobox.recycler.FrogoRecyclerView;
import com.frogobox.recycler.boilerplate.viewrclass.FrogoViewAdapterCallback;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        setupDetailActivity("Tentang Kami");
        setupFrogoRecyclerView();
    }

    private ArrayList<Kelompok> dataKelompok() {
        ArrayList<Kelompok> kelompoks = new ArrayList<>();
        kelompoks.add(new Kelompok("Maulana Idris", "17081010040", R.drawable.frogo_rv_bg_amber_8dp));
        kelompoks.add(new Kelompok("Guntur Adhi Prasetya", "17081010043", R.drawable.frogo_rv_bg_amber_8dp));
        kelompoks.add(new Kelompok("Parisya Shidqi Yusyarnanda", "17081010060", R.drawable.frogo_rv_bg_amber_8dp));
        kelompoks.add(new Kelompok("Feronika Nur Maghfiro", "17081010067", R.drawable.frogo_rv_bg_amber_8dp));
        kelompoks.add(new Kelompok("Mohammad Amir Fanani", "17081010071", R.drawable.frogo_rv_bg_amber_8dp));
        return kelompoks;
    }


    private void setupFrogoRecyclerView() {

        FrogoRecyclerView frogoRecyclerView = findViewById(R.id.recycler_view);

        FrogoViewAdapterCallback frogoViewAdapterCallback = new FrogoViewAdapterCallback<Kelompok>() {

            @Override
            public void setupInitComponent(@NotNull View view, Kelompok kelompok) {
                //tipedata //variabel
                TextView name = view.findViewById(R.id.tv_name);
                TextView npm = view.findViewById(R.id.tv_npm);
                ImageView photo = view.findViewById(R.id.img_photo);

                name.setText(kelompok.getNama());
                npm.setText(kelompok.getNpm());
                photo.setImageResource(kelompok.getFoto());
            }

            @Override
            public void onItemLongClicked(Kelompok kelompok) {

            }

            @Override
            public void onItemClicked(Kelompok kelompok) {

            }
        };

        frogoRecyclerView
                .injector()
                .addData(dataKelompok())
                .addCustomView(R.layout.aboutlist_template)
                .addEmptyView(null)
                .addCallback(frogoViewAdapterCallback)
                .createLayoutLinearVertical(false)
                .build();

    }


}
