package com.apkb.reportapps.ui.history;

import static com.apkb.reportapps.StatusLaporanActivity.DATA_STATUS;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.apkb.reportapps.R;
import com.apkb.reportapps.StatusLaporanActivity;
import com.apkb.reportapps.model.ModelDatabase;

import java.util.List;


public class HistoryAdapter extends RecyclerView.Adapter<HistoryAdapter.ViewHolder> {
    String res;
    public static final String SHARED_PREFS = "sharedPrefs";
    public static final String TEXT = "text";
    public static final String status = "STATUS";
    List<ModelDatabase> modelDatabase;
    Context mContext;
    private String text;

    HistoryAdapterCallback mAdapterCallback;

    public HistoryAdapter(Context context, List<ModelDatabase> modelDatabaseList,
                          HistoryAdapterCallback adapterCallback) {
        this.mContext = context;
        this.modelDatabase = modelDatabaseList;
        this.mAdapterCallback = adapterCallback;
    }

    public void setDataAdapter(List<ModelDatabase> items) {
        modelDatabase.clear();
        modelDatabase.addAll(items);
        notifyDataSetChanged();
    }

    @Override
    public HistoryAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.list_item_history, parent, false);
        return new HistoryAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(HistoryAdapter.ViewHolder holder, int position) {
        final ModelDatabase data = modelDatabase.get(position);
        holder.tvKategori.setText(data.getKategori());
        holder.tvHP.setText(data.getTelepon());
        holder.tvDate.setText(data.getTanggal());
        holder.tvLaporan.setText(data.getIsiLaporan());

        if (text==null) {
            SharedPreferences sharedPreferences = mContext.getSharedPreferences(SHARED_PREFS, Context.MODE_PRIVATE);
            text = sharedPreferences.getString(TEXT, "");
            holder.tvstatus.setText(text);
            holder.tvstatus.setTextColor(Color.GREEN);
        }

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

    public interface HistoryAdapterCallback {
        void onDelete(ModelDatabase modelDatabase);
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView tvKategori;
        public TextView tvDate;
        public TextView tvLaporan;
        public TextView tvHP;
        public TextView tvstatus;
        public CardView cvHistory;
        public LinearLayout layoutHeader;

        public ViewHolder(View itemView) {
            super(itemView);

            tvKategori = itemView.findViewById(R.id.tvKategori);
            tvLaporan = itemView.findViewById(R.id.tvLaporan);
            tvDate = itemView.findViewById(R.id.tvDate);
            tvHP = itemView.findViewById(R.id.tvHP);
            cvHistory = itemView.findViewById(R.id.cvHistory);
            layoutHeader = itemView.findViewById(R.id.layoutHeader);
            tvstatus = itemView.findViewById(R.id.statuslapor);
            cvHistory.setOnClickListener(view -> {
                ModelDatabase modelLaundry = modelDatabase.get(getAdapterPosition());
                mAdapterCallback.onDelete(modelLaundry);
            });
            //        holder.tvstatus = mContext.().getExtras().getString(DATA_STATUS);
        }
    }

}
