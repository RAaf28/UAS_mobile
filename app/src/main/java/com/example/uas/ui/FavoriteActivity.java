package com.example.uas.ui;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.adapter.EndemikAdapter;
import com.example.uas.data.local.Endemik;
import com.example.uas.data.local.Favorite;

import java.util.ArrayList;
import java.util.List;

public class FavoriteActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_favorite);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        findViewById(R.id.ivFavBack).setOnClickListener(v -> onBackPressed());

        RecyclerView rvFavorite = findViewById(R.id.rvFavorite);
        rvFavorite.setLayoutManager(new GridLayoutManager(this, 2));
        EndemikAdapter adapter = new EndemikAdapter();
        rvFavorite.setAdapter(adapter);

        EndemikViewModel viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);
        viewModel.getAllFavorite().observe(this, favorites -> {
            if (favorites != null) {
                List<Endemik> mappedList = new ArrayList<>();
                for (Favorite fav : favorites) {
                    Endemik endemik = new Endemik();
                    endemik.id = fav.id;
                    endemik.judul = fav.judul;
                    endemik.tipe = fav.tipe;
                    endemik.imageUrl = fav.imageUrl;
                    endemik.deskripsi = fav.deskripsi;
                    mappedList.add(endemik);
                }
                adapter.setEndemikList(mappedList);
            }
        });
    }
}