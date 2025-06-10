package com.uas.mobileuas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.SearchView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;
import com.uas.mobileuas.ui.theme.RecommendationAdapter;
import com.uas.mobileuas.ui.theme.RecommendationItem;

import java.util.ArrayList;

public class RecommendationActivity extends AppCompatActivity {

    private RecyclerView rvRecommendation;
    private SearchView searchView;
    private RecommendationAdapter adapter;
    private ArrayList<RecommendationItem> recommendationList, filteredList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendation);

        searchView = findViewById(R.id.searchView);
        rvRecommendation = findViewById(R.id.rvRecommendation);
        rvRecommendation.setLayoutManager(new LinearLayoutManager(this));

        recommendationList = new ArrayList<>();
        filteredList = new ArrayList<>();

        recommendationList.add(new RecommendationItem(R.drawable.bali, "Bali", "Pantai dan budaya Bali", "Rp1.000.000"));
        recommendationList.add(new RecommendationItem(R.drawable.lombok, "Lombok", "Alam yang memukau", "Rp900.000"));
        recommendationList.add(new RecommendationItem(R.drawable.yogyakarta, "Yogyakarta", "Kota budaya", "Rp800.000"));

        filteredList.addAll(recommendationList);

        adapter = new RecommendationAdapter(filteredList, item -> {
            Intent intent = new Intent(this, RecommendationDetailActivity.class);
            intent.putExtra("img", item.getImageResId());
            intent.putExtra("title", item.getDestinationName());
            intent.putExtra("desc", item.getDescription());
            intent.putExtra("price", item.getPrice());
            startActivity(intent);
        });
        rvRecommendation.setAdapter(adapter);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            public boolean onQueryTextSubmit(String q) {
                filterList(q); return true;
            }

            public boolean onQueryTextChange(String newText) {
                filterList(newText); return true;
            }
        });
    }

    private void filterList(String query) {
        filteredList.clear();
        for (RecommendationItem item : recommendationList) {
            if (item.getDestinationName().toLowerCase().contains(query.toLowerCase())) {
                filteredList.add(item);
            }
        }
        adapter.notifyDataSetChanged();
    }
}
