package com.uas.mobileuas.ui.theme;

import android.os.Parcel;
import android.os.Parcelable;

public class Transaksi implements Parcelable {
    private String namaPO;
    private String jamBerangkat;
    private String harga;
    private String fromCity;
    private String toCity;
    private String tanggal;
    private String kursi;
    private String metode;
    private String status;

    public Transaksi(String namaPO, String jamBerangkat, String harga,
                     String fromCity, String toCity, String tanggal,
                     String kursi, String metode, String status) {
        this.namaPO = namaPO;
        this.jamBerangkat = jamBerangkat;
        this.harga = harga;
        this.fromCity = fromCity;
        this.toCity = toCity;
        this.tanggal = tanggal;
        this.kursi = kursi;
        this.metode = metode;
        this.status = status;
    }

    protected Transaksi(Parcel in) {
        namaPO = in.readString();
        jamBerangkat = in.readString();
        harga = in.readString();
        fromCity = in.readString();
        toCity = in.readString();
        tanggal = in.readString();
        kursi = in.readString();
        metode = in.readString();
        status = in.readString();
    }

    public static final Creator<Transaksi> CREATOR = new Creator<Transaksi>() {
        @Override
        public Transaksi createFromParcel(Parcel in) {
            return new Transaksi(in);
        }

        @Override
        public Transaksi[] newArray(int size) {
            return new Transaksi[size];
        }
    };

    public String getNamaPO() {
        return namaPO;
    }

    public String getJamBerangkat() {
        return jamBerangkat;
    }

    public String getHarga() {
        return harga;
    }

    public String getFromCity() {
        return fromCity;
    }

    public String getToCity() {
        return toCity;
    }

    public String getTanggal() {
        return tanggal;
    }

    public String getKursi() {
        return kursi;
    }

    public String getMetode() {
        return metode;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(namaPO);
        dest.writeString(jamBerangkat);
        dest.writeString(harga);
        dest.writeString(fromCity);
        dest.writeString(toCity);
        dest.writeString(tanggal);
        dest.writeString(kursi);
        dest.writeString(metode);
        dest.writeString(status);
    }
}
