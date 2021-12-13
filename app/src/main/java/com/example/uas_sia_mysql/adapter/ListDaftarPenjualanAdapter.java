package com.example.uas_sia_mysql.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;


import com.example.uas_sia_mysql.ListDaftarPenjualan;
import com.example.uas_sia_mysql.ListDataMasterCustomer;
import com.example.uas_sia_mysql.R;

import java.util.List;

public class ListDaftarPenjualanAdapter extends BaseAdapter {

    Activity activity;
    List<ListDaftarPenjualan> items;
    Context context;

    private LayoutInflater inflater;

    public ListDaftarPenjualanAdapter(Activity activity, List<ListDaftarPenjualan> items, Context context) {
        this.activity = activity;
        this.items = items;
        this.context = context;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.list_daftar_transaksi_penjualan, null);
        }
        TextView txt_id_penjualan = view.findViewById(R.id._idPenjualan);
        TextView txt_nama_customer = view.findViewById(R.id.Nama_Customer_Penjualan);
        TextView txt_nama_barang = view.findViewById(R.id.Nama_Barang_Customer_Penjualan);
        TextView txt_harga_barang = view.findViewById(R.id.Harga_Customer_Penjualan);
        TextView txt_jumlah_barang = view.findViewById(R.id.Jumlah_Customer_Penjualan);
        TextView txt_total_transaksi = view.findViewById(R.id.Total_Customer_Penjualan);


        ListDaftarPenjualan data = items.get(i);

        txt_id_penjualan.setText(data.getId_transaksi_penjualan());
        txt_nama_customer.setText(data.getNama_customer());
        txt_nama_barang.setText(data.getNama_barang());
        txt_harga_barang.setText(data.getHarga_barang());
        txt_jumlah_barang.setText(data.getJumlah_barang());
        txt_total_transaksi.setText(data.getTotal_transaksi());

        return view;

    }

}
