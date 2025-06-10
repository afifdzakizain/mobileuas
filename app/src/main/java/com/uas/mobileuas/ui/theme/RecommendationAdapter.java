package com.uas.mobileuas.ui.theme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;

import java.util.ArrayList;
import java.util.List;

public class RecommendationAdapter extends RecyclerView.Adapter<RecommendationAdapter.ViewHolder> {
    private ArrayList<RecommendationItem> data;
    private OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItemClick(RecommendationItem item);
    }

    public RecommendationAdapter(ArrayList<RecommendationItem> data, OnItemClickListener listener) {
        this.data = data;
        this.listener = listener;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title, desc, price;

        public ViewHolder(View itemView) {
            super(itemView);
            img = itemView.findViewById(R.id.imgDestination);
            title = itemView.findViewById(R.id.tvTitle);
            desc = itemView.findViewById(R.id.tvDesc);
            price = itemView.findViewById(R.id.tvPrice);
        }

        public void bind(RecommendationItem item, OnItemClickListener listener) {
            img.setImageResource(item.getImageResId());
            title.setText(item.getDestinationName());
            desc.setText(item.getDescription());
            price.setText(item.getPrice());
            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_recommendation, parent, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int pos) {
        holder.bind(data.get(pos), listener);
    }

    @Override
    public int getItemCount() { return data.size(); }
}
