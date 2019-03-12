package com.example.haveibeenpwnedjava.net;

import com.example.haveibeenpwnedjava.model.HaveIBeenPwnedRepo;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

import static com.example.haveibeenpwnedjava.net.Constants.ENDPOINT;

public interface HaveIBeenPwnedService {
    @GET(ENDPOINT)
    Call<List<HaveIBeenPwnedRepo>> getRepository(@Query("domain") String domain);

}
