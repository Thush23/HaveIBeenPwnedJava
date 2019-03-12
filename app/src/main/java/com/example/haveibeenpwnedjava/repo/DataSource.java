package com.example.haveibeenpwnedjava.repo;

import java.util.Observer;

public interface DataSource {
    void getPwnedSiteData (String domain);
    void setObserver(Observer observer);
}
