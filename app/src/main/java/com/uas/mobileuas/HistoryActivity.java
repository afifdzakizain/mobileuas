package com.uas.mobileuas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.uas.mobileuas.ui.theme.TransaksiAdapter;
import com.uas.mobileuas.ui.theme.Transaksi;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class HistoryActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private TransaksiAdapter adapter;
    private ArrayList<Transaksi> transaksiList;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_history);

        recyclerView = findViewById(R.id.recyclerHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        transaksiList = new ArrayList<>();

        // Ambil data dari SharedPreferences
        SharedPreferences prefs = getSharedPreferences("riwayat_transaksi", MODE_PRIVATE);
        String jsonData = prefs.getString("data", "[]");

        try {
            JSONArray array = new JSONArray(jsonData);
            for (int i = 0; i < array.length(); i++) {
                JSONObject obj = array.getJSONObject(i);

                Transaksi transaksi = new Transaksi(
                        obj.getString("namaPO"),
                        obj.getString("jamBerangkat"),
                        obj.getString("harga"),
                        obj.getString("fromCity"),
                        obj.getString("toCity"),
                        obj.getString("tanggal"),
                        obj.getString("kursi"),
                        obj.getString("metodePembayaran"),
                        obj.getString("status")
                );

                transaksiList.add(transaksi);
            }

            adapter = new TransaksiAdapter(transaksiList);
            recyclerView.setAdapter(adapter);

        } catch (JSONException e) {
            e.printStackTrace();
            Toast.makeText(this, "Gagal memuat riwayat", Toast.LENGTH_SHORT).show();
        }
    }
}
