package com.uas.mobileuas.ui.theme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;

import java.util.List;

public class TransaksiAdapter extends RecyclerView.Adapter<TransaksiAdapter.ViewHolder> {

    private List<Transaksi> transaksiList;

    public TransaksiAdapter(List<Transaksi> transaksiList) {
        this.transaksiList = transaksiList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_transaksi, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Transaksi transaksi = transaksiList.get(position);

        holder.txtNamaPO.setText(transaksi.getNamaPO());
        holder.txtJadwal.setText("Berangkat: " + transaksi.getJamBerangkat() + ", Tanggal: " + transaksi.getTanggal());
        holder.txtRute.setText("Dari " + transaksi.getFromCity() + " ke " + transaksi.getToCity());
        holder.txtHarga.setText("Harga: Rp" + transaksi.getHarga());
        holder.txtKursi.setText("Jumlah Kursi: " + transaksi.getKursi());
        holder.txtMetode.setText("Metode: " + transaksi.getMetode());
        holder.txtStatus.setText("Status: " + transaksi.getStatus());
    }

    @Override
    public int getItemCount() {
        return transaksiList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView txtNamaPO, txtJadwal, txtRute, txtHarga, txtKursi, txtMetode, txtStatus;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            txtNamaPO = itemView.findViewById(R.id.txtNamaPO);
            txtJadwal = itemView.findViewById(R.id.txtJadwal);
            txtRute = itemView.findViewById(R.id.txtRute);
            txtHarga = itemView.findViewById(R.id.txtHarga);
            txtKursi = itemView.findViewById(R.id.txtKursi);
            txtMetode = itemView.findViewById(R.id.txtMetode);
            txtStatus = itemView.findViewById(R.id.txtStatus);
        }
    }
}
