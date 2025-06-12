package com.uas.mobileuas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.ui.theme.Transaksi;
import com.uas.mobileuas.ui.theme.TransaksiAdapter;

import java.util.ArrayList;
import java.util.List;

public class TransaksiFragment extends Fragment {

    private RecyclerView rvTransaksi;
    private TransaksiAdapter transaksiAdapter;
    private List<Transaksi> transaksiList;
    private View rootView;

    public TransaksiFragment() {}

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_transaksi, container, false);

        // ▶ Animasi saat fragment ditampilkan
        Animation slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        rootView.startAnimation(slideIn);

        rvTransaksi = rootView.findViewById(R.id.rvTransaksi);
        rvTransaksi.setLayoutManager(new LinearLayoutManager(getContext()));

        // Dummy data
        transaksiList = new ArrayList<>();
        transaksiList.add(new Transaksi(
                "Kereta", "07:00", "150000", "Jakarta", "Bandung", "10 Juni 2025", "1", "BCA", "Lunas"
        ));
        transaksiList.add(new Transaksi(
                "Bus", "09:00", "90000", "Surabaya", "Malang", "12 Juni 2025", "2", "BRI", "Lunas"
        ));
        transaksiList.add(new Transaksi(
                "Pesawat", "12:30", "850000", "Bali", "Jakarta", "15 Juni 2025", "1", "Mandiri", "Lunas"
        ));


        transaksiAdapter = new TransaksiAdapter(transaksiList);
        rvTransaksi.setAdapter(transaksiAdapter);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ◀ Animasi saat keluar dari fragment (opsional)
        if (rootView != null) {
            Animation slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
            rootView.startAnimation(slideOut);
        }
    }
}
