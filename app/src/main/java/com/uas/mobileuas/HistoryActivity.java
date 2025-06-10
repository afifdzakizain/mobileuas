package com.uas.mobileuas;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView rvHistory;
    private HistoryAdapter adapter;
    private ArrayList<HistoryItem> historyList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        rvHistory = findViewById(R.id.rvHistory);
        rvHistory.setLayoutManager(new LinearLayoutManager(this));

        // Contoh data dummy, nanti bisa ambil dari database / API
        historyList = new ArrayList<>();
        historyList.add(new HistoryItem("Jakarta - Bali", "01 Juni 2025", "Selesai"));
        historyList.add(new HistoryItem("Surabaya - Bandung", "15 Mei 2025", "Dibatalkan"));
        historyList.add(new HistoryItem("Yogyakarta - Semarang", "10 Mei 2025", "Selesai"));

        adapter = new HistoryAdapter(historyList, item -> {
            // Saat klik item, tampilkan toast atau pindah ke detail
            Toast.makeText(HistoryActivity.this,
                    "Klik: " + item.getDestination(), Toast.LENGTH_SHORT).show();

            // Contoh pindah ke detail, buat HistoryDetailActivity jika ingin
            // Intent intent = new Intent(HistoryActivity.this, HistoryDetailActivity.class);
            // intent.putExtra("destination", item.getDestination());
            // startActivity(intent);
        });

        rvHistory.setAdapter(adapter);
    }
}
