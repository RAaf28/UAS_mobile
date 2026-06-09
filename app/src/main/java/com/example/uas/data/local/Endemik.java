package com.example.uas.data.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "endemik")
public class Endemik {
    @PrimaryKey
    @NonNull
    @SerializedName("id")
    public String id = "";

    @SerializedName("nama")
    public String judul;

    @SerializedName("tipe")
    public String tipe;

    @SerializedName("foto")
    public String imageUrl;

    @SerializedName("deskripsi")
    public String deskripsi;
}