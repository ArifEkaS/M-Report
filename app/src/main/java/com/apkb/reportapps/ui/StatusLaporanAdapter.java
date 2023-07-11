package com.apkb.reportapps.ui;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.apkb.reportapps.R;
import com.apkb.reportapps.Sharedpref;
import com.apkb.reportapps.StatusLaporanActivity;
import com.apkb.reportapps.model.ModelDatabase;
import com.apkb.reportapps.ui.history.HistoryActivity;
import com.apkb.reportapps.ui.history.HistoryAdapter;
import com.apkb.reportapps.ui.main.MainActivity;
import com.apkb.reportapps.ui.report.ReportActivity;
import com.apkb.reportapps.viewmodel.InputDataViewModel;
import com.google.android.gms.common.api.Status;

import java.util.List;

public class StatusLaporanAdapter extends RecyclerView.Adapter<StatusLaporanAdapter.ViewHolder> {
    String strStatus = "Laporan sudah diterima";
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";

    List<ModelDatabase> modelDatabase;
    Context mContext;
    Sharedpref sharedpref;

    public StatusLaporanAdapter(Context context, List<ModelDatabase> modelDatabaseList) {
        this.mContext = context;
        this.modelDatabase = modelDatabaseList;
    }

    public void setDataAdapter(List<ModelDatabase> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public StatusLaporanAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_statuslaporan, parent, false);
        return new StatusLaporanAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull StatusLaporanAdapter.ViewHolder holder, int position) {
        final ModelDatabase data = modelDatabase.get(position);
        holder.tvKategorist.setText(data.getKategori());
        holder.tvHPst.setText(data.getTelepon());
        holder.tvDatest.setText(data.getTanggal());
        holder.ivdone.setOnClickListener(view -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(mContext);
            builder.setMessage("Apakah Anda sudah menerima laporan ?");
            builder.setPositiveButton("Ya, sudah", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
                    SharedPreferences.Editor editor = sharedPreferences.edit();
                    editor.putString(TEXT, strStatus);
                    editor.apply();
                    Toast.makeText(mContext, "Sukses", Toast.LENGTH_SHORT).show();
                }
            }).setNegativeButton("Belum", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    dialog.dismiss();
                }
            });
            builder.show();
        });
        switch (data.getKategori()) {
            case "Laporan Kerusakan Barang Sekertariat":
                holder.layoutHeader.setBackgroundResource(R.color.red);
                break;
            case "Laporan Kerusakan Barang Lakwas":
                holder.layoutHeader.setBackgroundResource(R.color.blue);
                break;
            case "Laporan Kerusakan Barang Turbin":
                holder.layoutHeader.setBackgroundResource(R.color.purple_700);
                break;
            case "Laporan Kerusakan Barang P2":
                holder.layoutHeader.setBackgroundResource(R.color.teal_200);
                break;
            case "Laporan Kerusakan Barang P3":
                holder.layoutHeader.setBackgroundResource(R.color.green);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return modelDatabase.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKategorist, tvDatest, tvLaporanst, tvHPst;
        public CardView cv;
        public LinearLayout layoutHeader;

        public ImageView ivdone;

        public ViewHolder(View itemView) {
            super(itemView);
            tvKategorist = itemView.findViewById(R.id.tvKategoristat);
            tvLaporanst = itemView.findViewById(R.id.tvLaporanstat);
            tvDatest = itemView.findViewById(R.id.tvDatestat);
            tvHPst = itemView.findViewById(R.id.tvHPstat);
            cv = itemView.findViewById(R.id.cvStatusLaporan);
            ivdone = itemView.findViewById(R.id.btnDone);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);
        }
    }


}


