package com.uas.mobileuas;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.ui.theme.RiwayatModel;

import java.util.ArrayList;

public class RiwayatAdapter extends RecyclerView.Adapter<RiwayatAdapter.ViewHolder> {

    public interface OnItemClickListener {
        void onItemClick(RiwayatModel item);
    }

    private ArrayList<RiwayatModel> list;
    private OnItemClickListener listener;

    public RiwayatAdapter(ArrayList<RiwayatModel> list, OnItemClickListener listener) {
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public RiwayatAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_riwayat, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RiwayatAdapter.ViewHolder holder, int position) {
        RiwayatModel item = list.get(position);
        holder.bind(item, listener);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNamaPo, tvRute, tvTanggal, tvHarga, tvKursi, tvMetode, tvStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNamaPo = itemView.findViewById(R.id.tvNamaPo);
            tvRute = itemView.findViewById(R.id.tvRute);
            tvTanggal = itemView.findViewById(R.id.tvTanggal);
            tvHarga = itemView.findViewById(R.id.tvHarga);
            tvKursi = itemView.findViewById(R.id.tvKursi);
            tvMetode = itemView.findViewById(R.id.tvMetode);
            tvStatus = itemView.findViewById(R.id.tvStatus);
        }

        public void bind(RiwayatModel item, OnItemClickListener listener) {
            tvNamaPo.setText(item.getNamaPo());
            tvRute.setText(item.getOrigin() + " → " + item.getDestination());
            tvTanggal.setText(item.getTanggal());
            tvHarga.setText("Rp" + item.getHarga());
            tvKursi.setText("Kursi: " + item.getKursi());
            tvMetode.setText("Metode: " + item.getMetode());
            tvStatus.setText("Status: " + item.getStatus());

            itemView.setOnClickListener(v -> listener.onItemClick(item));
        }
    }
}
