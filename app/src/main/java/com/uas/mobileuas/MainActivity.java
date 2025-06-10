package com.uas.mobileuas;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uas.mobileuas.R;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Set default fragment (Home)
        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new Fragment())
                .commit();

        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;

            int itemId = item.getItemId();
            if (itemId == R.id.nav_home) {
                selectedFragment = new Fragment();
            } else if (itemId == R.id.nav_transaksi) {
                selectedFragment = new TransaksiFragment();
            } else if (itemId == R.id.nav_profil) {
                selectedFragment = new ProfilFragment();
            } else if (itemId == R.id.navigation_search) {
                selectedFragment = new SearchTripFragment(); // Fragmen search trip sudah public static
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });
    }

    // Perbaikan: Fragment harus public static dan punya konstruktor kosong
    public static class SearchTripFragment extends Fragment {

        public SearchTripFragment() {
            // konstruktor kosong wajib ada
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater,
                                 @Nullable ViewGroup container,
                                 @Nullable Bundle savedInstanceState) {
            // Ganti dengan layout fragment search trip kamu
            return inflater.inflate(R.layout.fragment_search_trip, container, false);
        }
    }
}
