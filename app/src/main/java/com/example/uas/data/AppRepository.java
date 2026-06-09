package com.example.uas.data;

import android.app.Application;
import android.util.Log;
import androidx.lifecycle.LiveData;
import com.example.uas.data.local.AppDao;
import com.example.uas.data.local.AppDatabase;
import com.example.uas.data.local.Endemik;
import com.example.uas.data.local.Favorite;
import com.example.uas.data.remote.RetrofitClient;
import java.util.List;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AppRepository {
    private final AppDao appDao;

    public AppRepository(Application application) {
        AppDatabase db = AppDatabase.getDatabase(application);
        appDao = db.appDao();
    }

    public LiveData<List<Endemik>> getEndemikByTipe(String tipe) {
        return appDao.getEndemikByTipe(tipe);
    }

    public LiveData<List<Endemik>> searchEndemik(String query) {
        return appDao.searchEndemik(query);
    }

    public void fetchAndSaveData(Runnable onSuccess, Runnable onError) {
        RetrofitClient.getApiService().getEndemikList().enqueue(new Callback<List<Endemik>>() {
            @Override
            public void onResponse(Call<List<Endemik>> call, Response<List<Endemik>> response) {
                if (response.isSuccessful() && response.body() != null) {
                    AppDatabase.databaseWriteExecutor.execute(() -> {
                        appDao.deleteAllEndemik();
                        appDao.insertAllEndemik(response.body());
                        if (onSuccess != null) onSuccess.run();
                    });
                } else {
                    if (onError != null) onError.run();
                }
            }

            @Override
            public void onFailure(Call<List<Endemik>> call, Throwable t) {
                Log.e("AppRepository", "Gagal tarik API: ", t);
                if (onError != null) onError.run();
            }
        });
    }

    // Favorite
    public LiveData<List<Favorite>> getAllFavorite() {
        return appDao.getAllFavorite();
    }

    public void insertFavorite(Favorite favorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> appDao.insertFavorite(favorite));
    }

    public void deleteFavorite(Favorite favorite) {
        AppDatabase.databaseWriteExecutor.execute(() -> appDao.deleteFavorite(favorite));
    }

    public void checkIsFavorite(String id, IsFavoriteCallback callback) {
        AppDatabase.databaseWriteExecutor.execute(() -> {
            boolean isFav = appDao.isFavorite(id);
            callback.onResult(isFav);
        });
    }

    public interface IsFavoriteCallback {
        void onResult(boolean isFavorite);
    }
}