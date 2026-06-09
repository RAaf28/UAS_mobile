package com.example.uas.ui;

import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.adapter.EndemikAdapter;

public class SearchActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setTitle("Pencarian");

        SearchView searchView = findViewById(R.id.searchView);
        RecyclerView rvSearch = findViewById(R.id.rvSearch);

        rvSearch.setLayoutManager(new GridLayoutManager(this, 2));
        EndemikAdapter adapter = new EndemikAdapter();
        rvSearch.setAdapter(adapter);

        EndemikViewModel viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                viewModel.searchEndemik(newText).observe(SearchActivity.this, endemikList -> {
                    if (endemikList != null) {
                        adapter.setEndemikList(endemikList);
                    }
                });
                return true;
            }
        });
    }
}