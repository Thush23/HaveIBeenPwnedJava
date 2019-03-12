package com.example.haveibeenpwnedjava;

import android.arch.lifecycle.Observer;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.haveibeenpwnedjava.home.HaveIBeenPwnedAdapter;
import com.example.haveibeenpwnedjava.home.ViewModel;
import com.example.haveibeenpwnedjava.model.HaveIBeenPwnedRepo;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button button = findViewById(R.id.btnGetResult);
        RecyclerView recyclerView = findViewById(R.id.rvData);

        final EditText etDomain = findViewById(R.id.etDomain);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.addItemDecoration(new DividerItemDecoration
                (this, linearLayoutManager.getOrientation()));

        final HaveIBeenPwnedAdapter haveIBeenPwnedAdapter = new HaveIBeenPwnedAdapter();
        recyclerView.setAdapter(haveIBeenPwnedAdapter);

        final ViewModel ViewModel = new ViewModel();
        ViewModel.getResponseLiveData().observe(this, new Observer<List<HaveIBeenPwnedRepo>>() {
            @Override
            public void onChanged(@Nullable List<HaveIBeenPwnedRepo> haveIBeenPwnedRepos) {
                haveIBeenPwnedAdapter.setData(haveIBeenPwnedRepos);
            }
        });

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ViewModel.getDomain(etDomain.getText().toString());
            }
        });
    }
}
