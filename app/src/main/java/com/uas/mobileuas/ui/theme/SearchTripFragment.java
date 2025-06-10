package com.uas.mobileuas.ui.theme;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.uas.mobileuas.R;
import com.uas.mobileuas.RecommendationActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class SearchTripFragment extends Fragment {

    private EditText edtAsal, edtTujuan, edtTanggal;
    private Button btnCari;

    public SearchTripFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_search_trip, container, false);

        // Inisialisasi view
        edtAsal = view.findViewById(R.id.edtAsal);
        edtTujuan = view.findViewById(R.id.edtTujuan);
        edtTanggal = view.findViewById(R.id.edtTanggal);
        btnCari = view.findViewById(R.id.btnCari);

        // Klik EditText tanggal -> tampilkan date picker
        edtTanggal.setOnClickListener(v -> {
            Log.d("DEBUG", "Tanggal diklik");
            showDatePicker();
        });

        // Tombol cari diklik
        btnCari.setOnClickListener(v -> {
            String asal = edtAsal.getText().toString().trim();
            String tujuan = edtTujuan.getText().toString().trim();
            String tanggal = edtTanggal.getText().toString().trim();

            if (asal.isEmpty() || tujuan.isEmpty() || tanggal.isEmpty()) {
                Toast.makeText(getContext(), "Lengkapi semua data", Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(getActivity(), RecommendationActivity.class);
            intent.putExtra("asal", asal);
            intent.putExtra("tujuan", tujuan);
            intent.putExtra("tanggal", tanggal);
            startActivity(intent);
        });

        return view;
    }

    private void showDatePicker() {
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        int month = calendar.get(Calendar.MONTH);
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog datePickerDialog = new DatePickerDialog(
                requireContext(), // Gunakan requireContext() agar tidak null
                (view, selectedYear, selectedMonth, selectedDay) -> {
                    Calendar selectedDate = Calendar.getInstance();
                    selectedDate.set(selectedYear, selectedMonth, selectedDay);

                    SimpleDateFormat sdf = new SimpleDateFormat("EEEE, d MMMM yyyy", new Locale("id", "ID"));
                    String formattedDate = sdf.format(selectedDate.getTime());

                    edtTanggal.setText(formattedDate);
                },
                year, month, day
        );

        // Tanggal minimum = hari ini
        datePickerDialog.getDatePicker().setMinDate(System.currentTimeMillis());

        // Tanggal maksimum = 1 tahun ke depan
        calendar.add(Calendar.YEAR, 1);
        datePickerDialog.getDatePicker().setMaxDate(calendar.getTimeInMillis());

        datePickerDialog.show();
    }
}
