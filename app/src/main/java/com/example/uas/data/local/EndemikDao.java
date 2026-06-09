package com.example.uas.data.local;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface EndemikDao {

    @Query("SELECT * FROM endemik")
    LiveData<List<Endemik>> getAllEndemik();

    @Query("SELECT * FROM endemik WHERE id = :id")
    Endemik getEndemikById(String id);

    @Query("SELECT * FROM endemik WHERE tipe = :tipe")
    LiveData<List<Endemik>> getByTipe(String tipe);

    // fitur pencarian
    @Query("SELECT * FROM endemik WHERE nama LIKE '%' || :searchQuery || '%'")
    LiveData<List<Endemik>> searchEndemik(String searchQuery);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertEndemik(List<Endemik> endemikList);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertAll(List<Endemik> endemikList);

    @Query("DELETE FROM endemik")
    void deleteAll();

    //  Query untuk tabel Favorite

    @Query("SELECT * FROM favorite")
    LiveData<List<Favorite>> getAllFavorite();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertFavorite(Favorite favorite);

    @Delete
    void deleteFavorite(Favorite favorite);

    @Query("SELECT EXISTS(SELECT * FROM favorite WHERE id = :id)")
    boolean isFavorite(String id);
}