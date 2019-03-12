package com.example.haveibeenpwnedjava.repo;

import java.util.Observable;
import java.util.Observer;

public class HaveIBeenPwnedRepository extends Observable implements Observer, DataSource {

    private final DataSource localDataSource;
    private final DataSource remoteDataSource;

    public HaveIBeenPwnedRepository(DataSource localDataSource, DataSource remoteDataSource) {
        this.localDataSource = localDataSource;
        this.remoteDataSource = remoteDataSource;
    }

    @Override
    public void getPwnedSiteData(String domain) {
        remoteDataSource.setObserver(this);
        remoteDataSource.getPwnedSiteData(domain);
    }

    @Override
    public void setObserver(Observer observer) { addObserver(observer); }

    @Override
    public void update(java.util.Observable observable, Object result) {
        setChanged();
        notifyObservers(result);
    }
}
