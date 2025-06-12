package com.uas.mobileuas.ui.theme;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.uas.mobileuas.R;
import com.uas.mobileuas.ui.theme.HotelItem;

import java.util.List;

public class HotelAdapter extends RecyclerView.Adapter<HotelAdapter.HotelViewHolder> {

    private List<HotelItem> hotelList;

    public HotelAdapter(List<HotelItem> hotelList) {
        this.hotelList = hotelList;
    }

    public static class HotelViewHolder extends RecyclerView.ViewHolder {
        ImageView imgHotel;
        TextView txtHotelName, txtHotelLocation, txtHotelPrice;

        public HotelViewHolder(@NonNull View itemView) {
            super(itemView);
            imgHotel = itemView.findViewById(R.id.imgHotel);
            txtHotelName = itemView.findViewById(R.id.txtHotelName);
            txtHotelLocation = itemView.findViewById(R.id.txtHotelLocation);
            txtHotelPrice = itemView.findViewById(R.id.txtHotelPrice);
        }
    }

    @NonNull
    @Override
    public HotelViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_hotel, parent, false);
        return new HotelViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HotelViewHolder holder, int position) {
        HotelItem hotel = hotelList.get(position);
        holder.imgHotel.setImageResource(hotel.getImageResId());
        holder.txtHotelName.setText(hotel.getName());
        holder.txtHotelLocation.setText(hotel.getLocation());
        holder.txtHotelPrice.setText(hotel.getPrice());
    }

    @Override
    public int getItemCount() {
        return hotelList.size();
    }
}