package com.uas.mobileuas;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.uas.mobileuas.R;

import java.util.Calendar;

public class BuyPlaneTicketActivity extends AppCompatActivity {

    AutoCompleteTextView editFromCity, editToCity;
    EditText editDate, editSeatCount;
    Button btnOrder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_buy_plane_ticket);

        editFromCity = findViewById(R.id.editFromCity);
        editToCity = findViewById(R.id.editToCity);
        editDate = findViewById(R.id.editDate);
        editSeatCount = findViewById(R.id.editSeatCount);
        btnOrder = findViewById(R.id.btnOrder);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.strings_kota,
                android.R.layout.simple_dropdown_item_1line
        );
        editFromCity.setAdapter(adapter);
        editToCity.setAdapter(adapter);

        editDate.setOnClickListener(v -> {
            Calendar calendar = Calendar.getInstance();
            DatePickerDialog datePickerDialog = new DatePickerDialog(
                    BuyPlaneTicketActivity.this,
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

        btnOrder.setOnClickListener(v -> {
            String dari = editFromCity.getText().toString();
            String tujuan = editToCity.getText().toString();
            String tanggal = editDate.getText().toString();
            String kursi = editSeatCount.getText().toString();

            if (dari.isEmpty() || tujuan.isEmpty() || tanggal.isEmpty() || kursi.isEmpty()) {
                Toast.makeText(this, "Harap lengkapi semua data", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Tiket Pesawat berhasil dipesan!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
