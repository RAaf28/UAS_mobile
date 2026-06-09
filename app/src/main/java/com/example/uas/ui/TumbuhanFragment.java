package com.example.uas.ui; // Sesuaikan package

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.uas.R;
import com.example.uas.adapter.EndemikAdapter;

public class TumbuhanFragment extends Fragment {

    private EndemikViewModel viewModel;
    private EndemikAdapter adapter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_tumbuhan, container, false);

        RecyclerView rvTumbuhan = view.findViewById(R.id.rv_tumbuhan);
        rvTumbuhan.setLayoutManager(new GridLayoutManager(getContext(), 2)); // 2 Kolom
        adapter = new EndemikAdapter();
        rvTumbuhan.setAdapter(adapter);

        viewModel = new ViewModelProvider(this).get(EndemikViewModel.class);

        viewModel.getByTipe("Tumbuhan").observe(getViewLifecycleOwner(), endemikList -> {
            if (endemikList != null) {
                Toast.makeText(getContext(), "Data tumbuhan masuk: " + endemikList.size(), Toast.LENGTH_SHORT).show();

                adapter.setEndemikList(endemikList);
            }
        });

        return view;
    }
}