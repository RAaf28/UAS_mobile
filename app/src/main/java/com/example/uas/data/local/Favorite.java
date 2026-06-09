package com.example.uas.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "favorite")
public class Favorite {

    @PrimaryKey
    @NonNull
    public String id = "";

    public String judul;
    public String tipe;
    public String imageUrl;
    public String deskripsi;

    public Favorite(@NonNull String id, String judul, String tipe, String imageUrl, String deskripsi) {
        this.id = id;
        this.judul = judul;
        this.tipe = tipe;
        this.imageUrl = imageUrl;
        this.deskripsi = deskripsi;
    }
}