package com.uas.mobileuas.ui.theme;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.biometric.BiometricManager;
import androidx.biometric.BiometricPrompt;
import androidx.core.content.ContextCompat;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.uas.mobileuas.Login;
import com.uas.mobileuas.MainActivity;
import com.uas.mobileuas.R;

import java.util.concurrent.Executor;

public class SplashScreen extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private BiometricPrompt biometricPrompt;
    private BiometricPrompt.PromptInfo promptInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        mAuth = FirebaseAuth.getInstance();

        authenticateUser();
    }

    private void authenticateUser() {
        BiometricManager biometricManager = BiometricManager.from(this);

        int canAuthenticate = biometricManager.canAuthenticate(
                BiometricManager.Authenticators.BIOMETRIC_STRONG | BiometricManager.Authenticators.DEVICE_CREDENTIAL);

        if (canAuthenticate == BiometricManager.BIOMETRIC_SUCCESS) {
            showBiometricPrompt();
        } else {
            // Jika biometric tidak tersedia, langsung cek login dan pindah
            moveToNextScreen();
        }
    }

    private void showBiometricPrompt() {
        Executor executor = ContextCompat.getMainExecutor(this);

        biometricPrompt = new BiometricPrompt(this, executor,
                new BiometricPrompt.AuthenticationCallback() {

                    @Override
                    public void onAuthenticationSucceeded(BiometricPrompt.AuthenticationResult result) {
                        super.onAuthenticationSucceeded(result);
                        Toast.makeText(getApplicationContext(), "Autentikasi berhasil", Toast.LENGTH_SHORT).show();
                        moveToNextScreen();
                    }

                    @Override
                    public void onAuthenticationFailed() {
                        super.onAuthenticationFailed();
                        Toast.makeText(getApplicationContext(), "Autentikasi gagal, coba lagi", Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onAuthenticationError(int errorCode, CharSequence errString) {
                        super.onAuthenticationError(errorCode, errString);
                        // Jika user coba batalkan, kita ulangi autentikasi, jangan keluar
                        if (errorCode == BiometricPrompt.ERROR_USER_CANCELED || errorCode == BiometricPrompt.ERROR_CANCELED) {
                            Toast.makeText(getApplicationContext(), "Autentikasi wajib dilakukan", Toast.LENGTH_SHORT).show();
                            biometricPrompt.authenticate(promptInfo);
                        } else {
                            // Jika error lain, bisa tampilkan pesan atau handle lain
                            Toast.makeText(getApplicationContext(), "Error: " + errString, Toast.LENGTH_SHORT).show();
                            // Bisa pilih tetap di sini, atau pindah ke login jika perlu
                        }
                    }
                });

        promptInfo = new BiometricPrompt.PromptInfo.Builder()
                .setTitle("Login dengan Sidik Jari atau PIN")
                .setSubtitle("Autentikasi diperlukan untuk membuka aplikasi")
                .setAllowedAuthenticators(BiometricManager.Authenticators.BIOMETRIC_STRONG
                        | BiometricManager.Authenticators.DEVICE_CREDENTIAL)
                // Jangan set tombol negatif agar tombol cancel tidak muncul
                //.setNegativeButtonText("Cancel")  --> DIHILANGKAN
                .build();

        biometricPrompt.authenticate(promptInfo);
    }

    private void moveToNextScreen() {
        FirebaseUser currentUser = mAuth.getCurrentUser();
        if (currentUser != null) {
            startActivity(new Intent(SplashScreen.this, MainActivity.class));
        } else {
            startActivity(new Intent(SplashScreen.this, Login.class));
        }
        finish();
    }

    // Override back button supaya tidak bisa dibatalkan
    @Override
    public void onBackPressed() {
        // Kosongkan agar tombol back tidak keluar dari splash screen saat autentikasi
        Toast.makeText(this, "Autentikasi wajib dilakukan", Toast.LENGTH_SHORT).show();
    }
}

