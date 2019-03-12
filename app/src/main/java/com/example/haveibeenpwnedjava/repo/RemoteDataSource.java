package com.example.haveibeenpwnedjava.repo;

import com.example.haveibeenpwnedjava.model.HaveIBeenPwnedRepo;
import com.example.haveibeenpwnedjava.net.Constants;
import com.example.haveibeenpwnedjava.net.HaveIBeenPwnedService;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RemoteDataSource extends Observable implements DataSource {

    private final HaveIBeenPwnedService haveIBeenPwnedService;

    public RemoteDataSource() {
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .connectTimeout(20, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .client(okHttpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        haveIBeenPwnedService = retrofit.create(HaveIBeenPwnedService.class);

        HaveIBeenPwnedService haveIBeenPawnedService = retrofit.create(HaveIBeenPwnedService.class);
    }

    @Override
    public void getPwnedSiteData(String domain) {
        final List<HaveIBeenPwnedRepo> haveIBeenPwnedRepos = new ArrayList<>();
        haveIBeenPwnedService.getRepository(domain).enqueue(new Callback<List<HaveIBeenPwnedRepo>>() {

            @Override
            public void onResponse(Call<List<HaveIBeenPwnedRepo>> call, Response<List<HaveIBeenPwnedRepo>> response) {
                if(response.isSuccessful() && response.body() != null){
                    haveIBeenPwnedRepos.clear();
                    haveIBeenPwnedRepos.addAll(response.body());
                    setChanged();
                    notifyObservers(haveIBeenPwnedRepos);
                }
            }

            @Override
            public void onFailure(Call<List<HaveIBeenPwnedRepo>> call, Throwable throwable) {
                throwable.printStackTrace();
            }
        });
    }

    @Override
    public void setObserver(Observer observer) {
        addObserver(observer);
    }
}

