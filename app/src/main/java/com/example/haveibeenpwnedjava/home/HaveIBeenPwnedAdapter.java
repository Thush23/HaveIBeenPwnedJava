package com.example.haveibeenpwnedjava.home;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.haveibeenpwnedjava.R;
import com.example.haveibeenpwnedjava.model.HaveIBeenPwnedRepo;

import java.util.ArrayList;
import java.util.List;

public class HaveIBeenPwnedAdapter extends RecyclerView.Adapter<HaveIBeenPwnedAdapter.PwnedViewHolder> {

    private final List<HaveIBeenPwnedRepo> haveIBeenPwnedRepos= new ArrayList<>();

    public void setData(List<HaveIBeenPwnedRepo> data){
        haveIBeenPwnedRepos.clear();
        haveIBeenPwnedRepos.addAll(data);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PwnedViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View rootview = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.recycler_view, viewGroup, false);
        return new PwnedViewHolder(rootview);
    }

    @Override
    public void onBindViewHolder(@NonNull PwnedViewHolder pwnedViewHolder, int position) {
        HaveIBeenPwnedRepo haveIBeenPwnedRepo = haveIBeenPwnedRepos.get(position);
        pwnedViewHolder.tvName.setText(haveIBeenPwnedRepo.getName());
        pwnedViewHolder.tvDomain.setText(haveIBeenPwnedRepo.getDomain());
        pwnedViewHolder.tvBreachDate.setText(haveIBeenPwnedRepo.getBreachDate());
        pwnedViewHolder.tvAddedDate.setText(haveIBeenPwnedRepo.getAddedDate());
        pwnedViewHolder.tvModiDate.setText(haveIBeenPwnedRepo.getModifiedDate());
    }

    @Override
    public int getItemCount() {
        return haveIBeenPwnedRepos.size();
    }

    static class PwnedViewHolder extends RecyclerView.ViewHolder {
        TextView tvName;
        TextView tvDomain;
        TextView tvBreachDate;
        TextView tvDescription;
        TextView tvAddedDate;
        TextView tvModiDate;

        public PwnedViewHolder(@NonNull View itemView) {
            super(itemView);
            tvName = itemView.findViewById(R.id.tvName);
            tvDomain = itemView.findViewById(R.id.tvDomain);
            tvBreachDate = itemView.findViewById(R.id.tvBreachDate);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvAddedDate = itemView.findViewById(R.id.tvAddedDate);
            tvModiDate = itemView.findViewById(R.id.tvModiDate);
        }
    }
}