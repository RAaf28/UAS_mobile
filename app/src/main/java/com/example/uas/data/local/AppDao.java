package com.example.uas.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface AppDao {
    //  Query Tabel Endemik
    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    LiveData<List<Endemik>> getEndemikByTipe(String tipe);

    @Query("SELECT * FROM endemik WHERE judul LIKE '%' || :query || '%'")
    LiveData<List<Endemik>> searchEndemik(String query);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAllEndemik(List<Endemik> endemikList);

    @Query("DELETE FROM endemik")
    void deleteAllEndemik();

    //  Query Tabel Favorite
    @Query("SELECT * FROM favorite")
    LiveData<List<Favorite>> getAllFavorite();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Favorite favorite);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id)")
    boolean isFavorite(String id);
}