package com.example.uas_sia_mysql.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas_sia_mysql.ListDaftarPenjualan;
import com.example.uas_sia_mysql.ListTotalPenjualan;
import com.example.uas_sia_mysql.R;

import java.util.List;

public class ListTotalPenjualanAdapter extends BaseAdapter {

    Activity activity;
    List<ListTotalPenjualan> items;
    Context context;

    private LayoutInflater inflater;

    public ListTotalPenjualanAdapter(Activity activity, List<ListTotalPenjualan> items, Context context) {
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
            view = inflater.inflate(R.layout.list_total_transaksi_penjualan, null);
        }
        TextView txt_total_semua_transaksi_penjualan = view.findViewById(R.id.total_semua_transaksi);

        ListTotalPenjualan data = items.get(i);

        txt_total_semua_transaksi_penjualan.setText(data.getTotalsemuatransaksi());

        return view;

    }

}
