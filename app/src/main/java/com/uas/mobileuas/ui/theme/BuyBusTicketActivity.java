package com.uas.mobileuas.ui.theme;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class BuyBusTicketActivity extends AppCompatActivity {

    AutoCompleteTextView editFromCity, editToCity;
    EditText editDate, editSeatCount;
    Button btnOrder;
    RecyclerView recyclerView;
    TextView textTitlePO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_bus_ticket);

        // Inisialisasi view
        editFromCity = findViewById(R.id.editFromCity);
        editToCity = findViewById(R.id.editToCity);
        editDate = findViewById(R.id.editDate);
        editSeatCount = findViewById(R.id.editSeatCount);
        btnOrder = findViewById(R.id.btnOrder);
        recyclerView = findViewById(R.id.recyclerPOBus);
        textTitlePO = findViewById(R.id.textTitlePO);

        // Daftar kota
        String[] daftarKota = new String[]{
                "Jakarta", "Bandung", "Surabaya", "Yogyakarta", "Medan", "Semarang", "Makassar", "Palembang",
                "Balikpapan", "Padang", "Manado", "Pontianak", "Banjarmasin", "Malang", "Batam", "Denpasar",
                "Pekanbaru", "Cirebon", "Solo", "Mataram", "Kupang", "Jayapura", "Ambon", "Ternate", "Samarinda",
                "Bogor", "Depok", "Tangerang", "Bekasi", "Cilegon", "Cimahi", "Tasikmalaya", "Sukabumi", "Jambi",
                "Banda Aceh", "Palu", "Kendari", "Bengkulu", "Tanjungpinang", "Pangkal Pinang", "Serang", "Lubuklinggau"
        };

        // Adapter kota
        ArrayAdapter<String> adapterKota = new ArrayAdapter<>(
                this,
                android.R.layout.simple_dropdown_item_1line,
                daftarKota
        );
        editFromCity.setAdapter(adapterKota);
        editToCity.setAdapter(adapterKota);

        // Tanggal picker
        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    BuyBusTicketActivity.this,
                    (view, year, month, dayOfMonth) -> {
                        String selectedDate = dayOfMonth + "/" + (month + 1) + "/" + year;
                        editDate.setText(selectedDate);
                    },
                    calendar.get(Calendar.YEAR),
                    calendar.get(Calendar.MONTH),
                    calendar.get(Calendar.DAY_OF_MONTH)
            );
            datePickerDialog.show();
        });

        // Tombol pesan
        btnOrder.setOnClickListener(v -> {
            String dari = editFromCity.getText().toString();
            String tujuan = editToCity.getText().toString();
            String tanggal = editDate.getText().toString();
            String kursi = editSeatCount.getText().toString();

            if (dari.isEmpty() || tujuan.isEmpty() || tanggal.isEmpty() || kursi.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
            } else {
                // Tampilkan daftar PO Bus
                textTitlePO.setVisibility(View.VISIBLE);
                recyclerView.setVisibility(View.VISIBLE);

                // Contoh data PO Bus
                List<BusCompany> busList = new ArrayList<>();
                busList.add(new BusCompany("Sinar Jaya", "08:00", "150000"));
                busList.add(new BusCompany("Rosalia Indah", "10:00", "165000"));
                busList.add(new BusCompany("Harapan Jaya", "13:00", "140000"));
                busList.add(new BusCompany("Lorena", "15:30", "180000"));

                // Atur adapter dan layout manager
                recyclerView.setLayoutManager(new LinearLayoutManager(this));
                recyclerView.setAdapter(new BusCompanyAdapter(this, busList));
            }
        });
    }
}
