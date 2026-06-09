package com.example.uas.ui;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.uas.R;
import com.example.uas.data.local.Favorite;

public class DetailActivity extends AppCompatActivity {

    private EndemikViewModel viewModel;
    private boolean isFavorite = false;
    private Favorite currentFavorite;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        if (getSupportActionBar() != null) {
            getSupportActionBar().hide();
        }

        viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);

        ImageView ivDetailBack = findViewById(R.id.ivDetailBack);
        ImageView ivDetailFoto = findViewById(R.id.ivDetailFoto);
        TextView tvDetailNama = findViewById(R.id.tvDetailNama);
        TextView tvDetailDeskripsi = findViewById(R.id.tvDetailDeskripsi);
        ImageView ivDetailFavorite = findViewById(R.id.ivDetailFavorite);

        String id = getIntent().getStringExtra("ID");
        String judul = getIntent().getStringExtra("JUDUL");
        String tipe = getIntent().getStringExtra("TIPE");
        String gambar = getIntent().getStringExtra("GAMBAR");
        String deskripsi = getIntent().getStringExtra("DESKRIPSI");

        tvDetailNama.setText(judul);
        tvDetailDeskripsi.setText(deskripsi);
        Glide.with(this).load(gambar).into(ivDetailFoto);

        currentFavorite = new Favorite(id, judul, tipe, gambar, deskripsi);

        viewModel.checkIsFavorite(id, isFav -> {
            isFavorite = isFav;
            runOnUiThread(() -> {
                if (isFavorite) {
                    ivDetailFavorite.setImageResource(R.drawable.ic_favorite); // Hati penuh
                } else {
                    ivDetailFavorite.setImageResource(R.drawable.ic_favorite_border); // Hati kosong
                }
            });
        });

        ivDetailBack.setOnClickListener(v -> onBackPressed());

        ivDetailFavorite.setOnClickListener(v -> {
            if (isFavorite) {
                viewModel.deleteFavorite(currentFavorite);
                ivDetailFavorite.setImageResource(R.drawable.ic_favorite_border);
                Toast.makeText(this, "Dihapus dari Favorit", Toast.LENGTH_SHORT).show();
                isFavorite = false;
            } else {
                viewModel.insertFavorite(currentFavorite);
                ivDetailFavorite.setImageResource(R.drawable.ic_favorite);
                Toast.makeText(this, "Tersimpan ke Favorit!", Toast.LENGTH_SHORT).show();
                isFavorite = true;
            }
        });
    }
}