package com.example.uas.data.remote;

import com.example.uas.data.local.Endemik;
import java.util.List;
import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("data_endemik/endemik.json")
    Call<List<Endemik>> getEndemikList();
}