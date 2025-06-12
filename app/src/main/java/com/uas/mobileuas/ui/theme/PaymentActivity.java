package com.uas.mobileuas.ui.theme;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.widget.*;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import com.uas.mobileuas.HistoryActivity;
import com.uas.mobileuas.R;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PaymentActivity extends AppCompatActivity {

    private Spinner spinnerMetode;
    private Button btnBayar;
    private TextView detailPesanan;

    private String namaPO, jamBerangkat, harga, fromCity, toCity, tanggal, kursi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        spinnerMetode = findViewById(R.id.spinnerMetode);
        btnBayar = findViewById(R.id.btnBayarSekarang);
        detailPesanan = findViewById(R.id.textDetailPesanan);

        // Ambil data dari intent
        namaPO = getIntent().getStringExtra("namaPO");
        jamBerangkat = getIntent().getStringExtra("jamBerangkat");
        harga = getIntent().getStringExtra("harga");
        fromCity = getIntent().getStringExtra("fromCity");
        toCity = getIntent().getStringExtra("toCity");
        tanggal = getIntent().getStringExtra("tanggal");
        kursi = getIntent().getStringExtra("kursi");

        // Tampilkan detail pesanan
        detailPesanan.setText(
                "PO: " + namaPO + "\n" +
                        "Berangkat: " + jamBerangkat + "\n" +
                        "Harga: Rp" + harga + "\n" +
                        "Dari: " + fromCity + " → " + toCity + "\n" +
                        "Tanggal: " + tanggal + "\n" +
                        "Jumlah Kursi: " + kursi
        );

        // Spinner metode pembayaran
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_item,
                new String[]{"QRIS", "Transfer BCA", "Transfer BNI", "Transfer Mandiri"});
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerMetode.setAdapter(adapter);

        // Tombol bayar
        btnBayar.setOnClickListener(v -> prosesPembayaran());
    }

    private void prosesPembayaran() {
        Toast.makeText(this, "Proses pembayaran dimulai", Toast.LENGTH_SHORT).show();
        Log.d("PaymentActivity", "Tombol Bayar diklik");

        String metode = spinnerMetode.getSelectedItem().toString();

        // Buat data transaksi
        JSONObject transaksi = new JSONObject();
        try {
            transaksi.put("namaPO", namaPO);
            transaksi.put("jamBerangkat", jamBerangkat);
            transaksi.put("harga", harga);
            transaksi.put("fromCity", fromCity);
            transaksi.put("toCity", toCity);
            transaksi.put("tanggal", tanggal);
            transaksi.put("kursi", kursi);
            transaksi.put("metodePembayaran", metode);
            transaksi.put("status", "Lunas");
        } catch (JSONException e) {
            e.printStackTrace();
            return;
        }

        // Simpan ke SharedPreferences
        SharedPreferences prefs = getSharedPreferences("riwayat_transaksi", MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();

        String existingData = prefs.getString("data", "[]"); // default JSONArray kosong
        try {
            JSONArray transaksiArray = new JSONArray(existingData);
            transaksiArray.put(transaksi);
            editor.putString("data", transaksiArray.toString());
            editor.apply();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        Toast.makeText(this, "Transaksi Berhasil!", Toast.LENGTH_SHORT).show();
        Log.d("PaymentActivity", "Transaksi disimpan lokal");

        if (metode.equals("QRIS")) {
            String qrisUrl = "https://qris.link/uji-coba-123"; // URL QRIS contoh
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(qrisUrl));
            startActivity(intent);
        } else {
            startActivity(new Intent(this, HistoryActivity.class));
            finish();
        }
    }
}
