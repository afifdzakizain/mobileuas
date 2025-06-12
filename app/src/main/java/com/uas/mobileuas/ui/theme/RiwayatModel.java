package com.uas.mobileuas.ui.theme;

public class RiwayatModel {
    private String namaPo, origin, destination, tanggal, harga, kursi, metode, status;

    public RiwayatModel(String namaPo, String origin, String destination, String tanggal,
                        String harga, String kursi, String metode, String status) {
        this.namaPo = namaPo;
        this.origin = origin;
        this.destination = destination;
        this.tanggal = tanggal;
        this.harga = harga;
        this.kursi = kursi;
        this.metode = metode;
        this.status = status;
    }

    public String getNamaPo() { return namaPo; }
    public String getOrigin() { return origin; }
    public String getDestination() { return destination; }
    public String getTanggal() { return tanggal; }
    public String getHarga() { return harga; }
    public String getKursi() { return kursi; }
    public String getMetode() { return metode; }
    public String getStatus() { return status; }
}
