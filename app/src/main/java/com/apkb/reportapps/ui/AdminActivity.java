package com.apkb.reportapps.ui;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.apkb.reportapps.LoginActivity;
import com.apkb.reportapps.R;
import com.apkb.reportapps.StatusLaporanActivity;
import com.apkb.reportapps.ui.history.HistoryActivity;
import com.apkb.reportapps.ui.main.MainActivity;

public class AdminActivity extends AppCompatActivity {

    CardView cvHistory,cvStatusLaporan;
    ImageView btnLogout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin);

        setInitLayout();
    }

    private void setInitLayout() {
        cvHistory = findViewById(R.id.cvRiwayatLaporan);
        btnLogout = findViewById(R.id.ivLogout);
        cvStatusLaporan = findViewById(R.id.cvStatusLaporan);
        btnLogout.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, LoginActivity.class);
            startActivity(intent);
        });
        cvHistory.setOnClickListener(v -> {
            Intent intent = new Intent(AdminActivity.this, HistoryActivity.class);
            startActivity(intent);
        });
        cvStatusLaporan.setOnClickListener(v->{
            Intent intent = new Intent(AdminActivity.this, StatusLaporanActivity.class);
            startActivity(intent);
        });



    }
}
