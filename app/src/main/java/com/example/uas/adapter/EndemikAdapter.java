package com.example.uas.adapter;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.uas.R;
import com.example.uas.data.local.Endemik;
import com.example.uas.ui.DetailActivity;

import java.util.ArrayList;
import java.util.List;

public class EndemikAdapter extends RecyclerView.Adapter<EndemikAdapter.ViewHolder> {

    private List<Endemik> endemikList = new ArrayList<>();

    public void setEndemikList(List<Endemik> list) {
        this.endemikList = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_grid, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Endemik endemik = endemikList.get(position);

        holder.tvItemTitle.setText(endemik.judul);

        Glide.with(holder.itemView.getContext())
                .load(endemik.imageUrl)
                .placeholder(android.R.drawable.ic_menu_gallery)
                .into(holder.ivItem);

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(v.getContext(), DetailActivity.class);
            intent.putExtra("ID", endemik.id);
            intent.putExtra("JUDUL", endemik.judul);
            intent.putExtra("TIPE", endemik.tipe);
            intent.putExtra("GAMBAR", endemik.imageUrl);
            intent.putExtra("DESKRIPSI", endemik.deskripsi);
            v.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return endemikList == null ? 0 : endemikList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivItem;
        TextView tvItemTitle;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivItem = itemView.findViewById(R.id.ivItem);
            tvItemTitle = itemView.findViewById(R.id.tvItemTitle);
        }
    }
}