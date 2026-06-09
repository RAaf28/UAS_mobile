package com.example.uas.ui;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.uas.data.AppRepository;
import com.example.uas.data.local.Endemik;
import com.example.uas.data.local.Favorite;

import java.util.List;

public class EndemikViewModel extends AndroidViewModel {

    private final AppRepository repository;

    public EndemikViewModel(@NonNull Application application) {
        super(application);
        repository = new AppRepository(application);
    }

    public LiveData<List<Endemik>> getByTipe(String tipe) {
        return repository.getEndemikByTipe(tipe);
    }

    public LiveData<List<Endemik>> searchEndemik(String query) {
        return repository.searchEndemik(query);
    }

    public LiveData<List<Favorite>> getAllFavorite() {
        return repository.getAllFavorite();
    }

    public void insertFavorite(Favorite favorite) {
        repository.insertFavorite(favorite);
    }

    public void deleteFavorite(Favorite favorite) {
        repository.deleteFavorite(favorite);
    }

    public void checkIsFavorite(String id, AppRepository.IsFavoriteCallback callback) {
        repository.checkIsFavorite(id, callback);
    }
}