package com.uas.mobileuas;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ProfilFragment extends Fragment {

    private FirebaseAuth auth;
    private View rootView;

    public ProfilFragment() {}

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_profil, container, false);

        // ▶ Animasi saat tampil
        Animation slideIn = AnimationUtils.loadAnimation(getContext(), R.anim.slide_in_right);
        rootView.startAnimation(slideIn);

        auth = FirebaseAuth.getInstance();
        FirebaseUser user = auth.getCurrentUser();

        // Bind views
        TextView tvEmail = rootView.findViewById(R.id.tvEmail);
        TextView tvUid = rootView.findViewById(R.id.tvUid);
        TextView tvName = rootView.findViewById(R.id.tvName);
        TextView tvPhone = rootView.findViewById(R.id.tvPhone);
        TextView tvCity = rootView.findViewById(R.id.tvCity);

        Button btnChangePassword = rootView.findViewById(R.id.btnChangePassword);
        Button btnLogout = rootView.findViewById(R.id.btnLogout);
        Button btnEditProfile = rootView.findViewById(R.id.btnEditProfile);

        if (user != null) {
            tvEmail.setText(user.getEmail());
            tvUid.setText(user.getUid());

            // Ambil data dari Firebase
            DatabaseReference userRef = FirebaseDatabase.getInstance()
                    .getReference("Users")
                    .child(user.getUid());

            userRef.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    if (snapshot.exists()) {
                        String name = snapshot.child("name").getValue(String.class);
                        String phone = snapshot.child("phone").getValue(String.class);
                        String city = snapshot.child("city").getValue(String.class);

                        tvName.setText("Nama: " + (name != null ? name : "-"));
                        tvPhone.setText("Telepon: " + (phone != null ? phone : "-"));
                        tvCity.setText("Kota: " + (city != null ? city : "-"));
                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError error) {
                    // Tangani error
                }
            });
        }

        btnChangePassword.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), ChangePasswordActivity.class);
            startActivity(intent);
        });

        btnLogout.setOnClickListener(v -> {
            auth.signOut();
            Intent intent = new Intent(getActivity(), Login.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
            requireActivity().finish();
        });

        btnEditProfile.setOnClickListener(v -> {
            Intent intent = new Intent(getActivity(), EditProfileActivity.class);
            startActivity(intent);
            requireActivity().overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);
        });

        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        // ◀ Animasi saat keluar
        if (rootView != null) {
            Animation slideOut = AnimationUtils.loadAnimation(getContext(), R.anim.slide_out_left);
            rootView.startAnimation(slideOut);
        }
    }
}
