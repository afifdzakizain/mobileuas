package com.uas.mobileuas;

import android.os.Bundle;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.uas.mobileuas.ui.theme.HomeFragment;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        bottomNavigationView = findViewById(R.id.bottomNavigation);

        // Listener bottom navigation
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Fragment selectedFragment = null;
            int itemId = item.getItemId();

            if (itemId == R.id.nav_home) {
                selectedFragment = new HomeFragment();
            } else if (itemId == R.id.nav_transaksi) {
                selectedFragment = new TransaksiFragment();
            } else if (itemId == R.id.nav_pemesanan) {
                selectedFragment = new PemesananFragment();
            } else if (itemId == R.id.nav_profil) {
                selectedFragment = new ProfilFragment();
            }

            if (selectedFragment != null) {
                getSupportFragmentManager().beginTransaction()
                        .replace(R.id.fragment_container, selectedFragment)
                        .commit();
            }

            return true;
        });

        // Tangani intent jika berasal dari tombol "Bayar Sekarang"
        String navigateTo = getIntent().getStringExtra("navigateTo");
        if ("pemesanan".equals(navigateTo)) {
            bottomNavigationView.setSelectedItemId(R.id.nav_pemesanan); // akan trigger listener di atas
        } else {
            bottomNavigationView.setSelectedItemId(R.id.nav_home); // default ke home
        }
    }
}
