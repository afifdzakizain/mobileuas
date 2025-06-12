package com.uas.mobileuas.ui.theme;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.ViewPager2;

import com.uas.mobileuas.BuyPlaneTicketActivity;
import com.uas.mobileuas.BuyTrainTicketActivity;
import com.uas.mobileuas.R;
import com.uas.mobileuas.ui.theme.BuyBusTicketActivity;

import java.util.Arrays;
import java.util.List;

public class HomeFragment extends Fragment {

    private ViewPager2 imageSlider;
    private ImageSliderAdapter imageSliderAdapter;
    private Handler sliderHandler;
    private int currentPage = 0;

    private RecyclerView recyclerHotel;
    private HotelAdapter hotelAdapter;

    private LinearLayout layoutKereta, layoutBus, layoutPesawat;
    private View rootView;

    private final List<Integer> sliderImages = Arrays.asList(
            R.drawable.slide1,
            R.drawable.slide2,
            R.drawable.slide3
    );

    private final List<HotelItem> hotelList = Arrays.asList(
            new HotelItem(R.drawable.hotel1, "The Luxury Hotel", "Jakarta Pusat", "Rp 750.000"),
            new HotelItem(R.drawable.hotel2, "Budget Stay", "Bandung", "Rp 250.000"),
            new HotelItem(R.drawable.hotel3, "Cozy Inn", "Yogyakarta", "Rp 300.000"),
            new HotelItem(R.drawable.hotel4, "Skyline Hotel", "Surabaya", "Rp 600.000"),
            new HotelItem(R.drawable.hotel5, "Ocean View Resort", "Bali", "Rp 1.200.000"),
            new HotelItem(R.drawable.hotel6, "Hilltop Hotel", "Malang", "Rp 500.000"),
            new HotelItem(R.drawable.hotel7, "Grand Palace", "Medan", "Rp 850.000"),
            new HotelItem(R.drawable.hotel8, "City Light Hotel", "Makassar", "Rp 450.000")
    );

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_home, container, false);

        // ⏬ Tambahkan animasi saat fragment tampil
        Animation slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        rootView.startAnimation(slideIn);

        imageSlider = rootView.findViewById(R.id.imageSlider);
        imageSliderAdapter = new ImageSliderAdapter(sliderImages);
        imageSlider.setAdapter(imageSliderAdapter);

        sliderHandler = new Handler(Looper.getMainLooper());
        sliderHandler.postDelayed(sliderRunnable, 3000);

        imageSlider.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                currentPage = position;
                sliderHandler.removeCallbacks(sliderRunnable);
                sliderHandler.postDelayed(sliderRunnable, 3000);
            }
        });

        recyclerHotel = rootView.findViewById(R.id.recyclerHotel);
        recyclerHotel.setLayoutManager(new LinearLayoutManager(requireContext()));
        hotelAdapter = new HotelAdapter(hotelList);
        recyclerHotel.setAdapter(hotelAdapter);

        layoutKereta = rootView.findViewById(R.id.layoutKereta);
        layoutBus = rootView.findViewById(R.id.layoutBus);
        layoutPesawat = rootView.findViewById(R.id.layoutPesawat);

        layoutKereta.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), BuyTrainTicketActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        layoutBus.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), BuyBusTicketActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
        });

        layoutPesawat.setOnClickListener(v -> {
            Intent intent = new Intent(requireContext(), BuyPlaneTicketActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        return rootView;
    }

    private final Runnable sliderRunnable = new Runnable() {
        @Override
        public void run() {
            if (imageSlider.getAdapter() != null) {
                int itemCount = imageSlider.getAdapter().getItemCount();
                currentPage = (currentPage + 1) % itemCount;
                imageSlider.setCurrentItem(currentPage, true);
                sliderHandler.postDelayed(this, 3000);
            }
        }
    };

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        sliderHandler.removeCallbacks(sliderRunnable);

        // ⏫ Tambahkan animasi saat fragment keluar
        if (rootView != null) {
            Animation slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
            rootView.startAnimation(slideOut);
        }
    }
}
