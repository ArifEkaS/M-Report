package com.apkb.reportapps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.apkb.reportapps.model.ModelDatabase;
import com.apkb.reportapps.ui.AdminActivity;
import com.apkb.reportapps.ui.StatusLaporanAdapter;
import com.apkb.reportapps.ui.history.HistoryAdapter;
import com.apkb.reportapps.viewmodel.HistoryViewModel;
import com.google.android.gms.common.api.Status;

import java.util.ArrayList;
import java.util.List;

public class StatusLaporanActivity extends AppCompatActivity {
    public static final String DATA_STATUS = "STATUS";

    List<ModelDatabase> modelDatabaseList = new ArrayList<>();

    StatusLaporanAdapter statusLaporanAdapterlaporanAdapter;
    HistoryViewModel historyViewModel;
    RecyclerView rvStatusLaporan;
    TextView tvNotFound;
    ImageView back;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_status_laporan);

        setInitLayout();
        setViewModel();
    }

    private void setInitLayout() {

        rvStatusLaporan = findViewById(R.id.rvStatusLaporan);
        tvNotFound = findViewById(R.id.tvNotFound);

        tvNotFound.setVisibility(View.GONE);

        statusLaporanAdapterlaporanAdapter = new StatusLaporanAdapter(this, modelDatabaseList);
        rvStatusLaporan.setHasFixedSize(true);
        rvStatusLaporan.setLayoutManager(new LinearLayoutManager(this));
        rvStatusLaporan.setAdapter(statusLaporanAdapterlaporanAdapter);


        back = findViewById(R.id.back);
        back.setOnClickListener(v -> {
            Intent intent = new Intent(StatusLaporanActivity.this, AdminActivity.class);
            startActivity(intent);
        });
    }

    private void setViewModel() {
        historyViewModel = ViewModelProviders.of(this).get(HistoryViewModel.class);
        historyViewModel.getDataLaporan().observe(this, modelDatabases -> {
            if (modelDatabases.isEmpty()) {
                tvNotFound.setVisibility(View.VISIBLE);
                rvStatusLaporan.setVisibility(View.GONE);
            } else {
                tvNotFound.setVisibility(View.GONE);
                rvStatusLaporan.setVisibility(View.VISIBLE);
            }
            statusLaporanAdapterlaporanAdapter.setDataAdapter(modelDatabases);
        });
    }

}
