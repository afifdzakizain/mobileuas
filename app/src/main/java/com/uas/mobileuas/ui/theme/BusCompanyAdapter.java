package com.uas.mobileuas.ui.theme;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;

import java.util.List;

public class BusCompanyAdapter extends RecyclerView.Adapter<BusCompanyAdapter.ViewHolder> {

    private final Context context;
    private final List<BusCompany> busList;

    public BusCompanyAdapter(Context context, List<BusCompany> busList) {
        this.context = context;
        this.busList = busList;
    }

    @NonNull
    @Override
    public BusCompanyAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_bus_company, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BusCompanyAdapter.ViewHolder holder, int position) {
        BusCompany bus = busList.get(position);
        holder.tvNama.setText(bus.namaPO);
        holder.tvJam.setText("Berangkat: " + bus.jamBerangkat);
        holder.tvHarga.setText("Rp " + bus.harga);

        holder.btnPesan.setOnClickListener(v -> {
            // Intent untuk berpindah ke PaymentActivity
            Intent intent = new Intent(context, PaymentActivity.class);
            intent.putExtra("namaPO", bus.namaPO);
            intent.putExtra("jamBerangkat", bus.jamBerangkat);
            intent.putExtra("harga", bus.harga);

            // Cast context ke BuyBusTicketActivity untuk ambil input
            if (context instanceof BuyBusTicketActivity) {
                BuyBusTicketActivity activity = (BuyBusTicketActivity) context;
                intent.putExtra("fromCity", activity.editFromCity.getText().toString());
                intent.putExtra("toCity", activity.editToCity.getText().toString());
                intent.putExtra("tanggal", activity.editDate.getText().toString());
                intent.putExtra("kursi", activity.editSeatCount.getText().toString());
            }

            context.startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        return busList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNama, tvJam, tvHarga;
        Button btnPesan;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvNama = itemView.findViewById(R.id.textNamaBus);
            tvJam = itemView.findViewById(R.id.textJamBus);
            tvHarga = itemView.findViewById(R.id.textHargaBus);
            btnPesan = itemView.findViewById(R.id.btnPesanBus);
        }
    }
}
