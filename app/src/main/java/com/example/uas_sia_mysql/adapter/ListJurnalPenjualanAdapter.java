package com.example.uas_sia_mysql.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas_sia_mysql.ListDaftarPenjualan;
import com.example.uas_sia_mysql.ListJurnalPenjualan;
import com.example.uas_sia_mysql.R;

import java.util.List;

public class ListJurnalPenjualanAdapter extends BaseAdapter {

    Activity activity;
    List<ListJurnalPenjualan> items;
    Context context;

    private LayoutInflater inflater;

    public ListJurnalPenjualanAdapter(Activity activity, List<ListJurnalPenjualan> items, Context context) {
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
            view = inflater.inflate(R.layout.list_jurnal_penjualan, null);
        }
        TextView txt_id = view.findViewById(R.id.no_jurnal1);
        TextView txt_kredit = view.findViewById(R.id.DEBIT2);
        TextView txt_debit= view.findViewById(R.id.KREDIT2);
        TextView txt_keterangan = view.findViewById(R.id.Ket_Transaksi);


        ListJurnalPenjualan data = items.get(i);

        txt_id.setText(data.getId_transaksi_penjualan());
        txt_kredit.setText(data.getTotal_transaksi());
        txt_debit.setText(data.getTotal_transaksi());
        txt_keterangan.setText(data.getKeterangan());

        return view;

    }

}
