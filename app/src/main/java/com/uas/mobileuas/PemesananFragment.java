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

public class PemesananFragment extends Fragment {

    private View rootView;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_pemesanan, container, false);

        // ▶ Animasi saat fragment tampil
        Animation slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        rootView.startAnimation(slideIn);

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ◀ Animasi saat fragment ditutup
        if (rootView != null) {
            Animation slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
            rootView.startAnimation(slideOut);
        }
    }
}
