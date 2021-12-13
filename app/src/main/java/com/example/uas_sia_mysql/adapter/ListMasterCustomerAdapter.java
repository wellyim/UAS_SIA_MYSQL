package com.example.uas_sia_mysql.adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.uas_sia_mysql.ListDataMasterCustomer;
import com.example.uas_sia_mysql.R;

import java.util.List;

public class ListMasterCustomerAdapter extends BaseAdapter {

    Activity activity;
    List<ListDataMasterCustomer> items;
    Context context;

    private LayoutInflater inflater;

    public ListMasterCustomerAdapter(Activity activity, List<ListDataMasterCustomer> items, Context context) {
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
        if(inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (view == null) {
            view = inflater.inflate(R.layout.list_master_customer, null);
        }
        TextView txt_id_customer  = view.findViewById(R.id._id);
        TextView txt_nama_customer  = view.findViewById(R.id.namaCustomer);
        TextView txt_alamat_customer  = view.findViewById(R.id.alamatCustomer);
        TextView txt_telepon_customer  = view.findViewById(R.id.tlpCustomer);
        TextView txt_piutang_customer  = view.findViewById(R.id.piutangCustomer);

        ListDataMasterCustomer data = items.get(i);

        txt_id_customer.setText(data.getId_customer());
        txt_nama_customer.setText(data.getNama());
        txt_alamat_customer.setText(data.getAlamat());
        txt_telepon_customer.setText(data.getTelepon());
        txt_piutang_customer.setText(data.getPiutang());
        return view;
    }
}
