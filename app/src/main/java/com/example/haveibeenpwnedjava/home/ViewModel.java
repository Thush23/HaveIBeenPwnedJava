package com.example.haveibeenpwnedjava.home;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;

import com.example.haveibeenpwnedjava.model.HaveIBeenPwnedRepo;
import com.example.haveibeenpwnedjava.repo.DataSource;
import com.example.haveibeenpwnedjava.repo.HaveIBeenPwnedRepository;
import com.example.haveibeenpwnedjava.repo.LocalDataSource;
import com.example.haveibeenpwnedjava.repo.RemoteDataSource;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

public class ViewModel implements Observer {

    private final DataSource haveIBeenPwnedRepository;
    private final MutableLiveData<List<HaveIBeenPwnedRepo>> liveData = new MutableLiveData<>();

    public ViewModel() {
        haveIBeenPwnedRepository  = new HaveIBeenPwnedRepository(new LocalDataSource(), new RemoteDataSource());
    }

    @Override
    public void update(Observable observable, Object result) {
        List<HaveIBeenPwnedRepo> haveIBeenPwnedRepos = (List<HaveIBeenPwnedRepo>) result;
        liveData.setValue(haveIBeenPwnedRepos);
    }

    public LiveData<List<HaveIBeenPwnedRepo>> getResponseLiveData(){return liveData;}

    public void getDomain(String domain){
        haveIBeenPwnedRepository.setObserver(this);
        haveIBeenPwnedRepository.getPwnedSiteData(domain);
    }
}
