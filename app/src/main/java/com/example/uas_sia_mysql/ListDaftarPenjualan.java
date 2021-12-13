package com.example.uas_sia_mysql;

public class ListDaftarPenjualan {
    String id_transaksi_penjualan,nama_customer,nama_barang,harga_barang,jumlah_barang,keterangan,total_transaksi;

    public ListDaftarPenjualan(){}

    public String getId_transaksi_penjualan() {
        return id_transaksi_penjualan;
    }

    public void setId_transaksi_penjualan(String id_transaksi_penjualan) {
        this.id_transaksi_penjualan = id_transaksi_penjualan;
    }

    public String getNama_customer() {
        return nama_customer;
    }

    public void setNama_customer(String nama_customer) {
        this.nama_customer = nama_customer;
    }

    public String getNama_barang() {
        return nama_barang;
    }

    public void setNama_barang(String nama_barang) {
        this.nama_barang = nama_barang;
    }

    public String getHarga_barang() {
        return harga_barang;
    }

    public void setHarga_barang(String harga_barang) {
        this.harga_barang = harga_barang;
    }

    public String getJumlah_barang() {
        return jumlah_barang;
    }

    public void setJumlah_barang(String jumlah_barang) {
        this.jumlah_barang = jumlah_barang;
    }

    public String getKeterangan() {
        return keterangan;
    }

    public void setKeterangan(String keterangan) {
        this.keterangan = keterangan;
    }

    public String getTotal_transaksi() {
        return total_transaksi;
    }

    public void setTotal_transaksi(String total_transaksi) {
        this.total_transaksi = total_transaksi;
    }
}
