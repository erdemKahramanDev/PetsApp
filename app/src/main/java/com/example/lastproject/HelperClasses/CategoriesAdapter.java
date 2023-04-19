package com.example.lastproject.HelperClasses;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.lastproject.R;

import java.util.ArrayList;

public class CategoriesAdapter extends RecyclerView.Adapter<CategoriesAdapter.AdapterAllCategoriesViewHolder> {
    ArrayList<CategoriesHelperClass> CategoriesLocations;

    public CategoriesAdapter(ArrayList<CategoriesHelperClass> CategoriesLocations) {
        this.CategoriesLocations = CategoriesLocations;
    }
    @NonNull
    @Override
    public AdapterAllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.categories_card_design, parent, false);
        AdapterAllCategoriesViewHolder lvh = new AdapterAllCategoriesViewHolder(view);
        return lvh;
    }
    @Override
    public void onBindViewHolder(@NonNull AdapterAllCategoriesViewHolder holder, int position) {
        CategoriesHelperClass helperClass = CategoriesLocations.get(position);
        holder.imageView.setImageResource(helperClass.getImage());
        holder.textView.setText(helperClass.getTitile());
    }
    @Override
    public int getItemCount() {
        return CategoriesLocations.size();
    }
    public static class AdapterAllCategoriesViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView textView;
        public AdapterAllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.kategori3);
            textView = itemView.findViewById(R.id.kategori2);
        }
    }
}
