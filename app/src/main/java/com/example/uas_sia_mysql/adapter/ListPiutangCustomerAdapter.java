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

public class ListPiutangCustomerAdapter extends BaseAdapter {

    Activity activity;
    List<ListDataMasterCustomer> items;
    Context context;
    private LayoutInflater inflater;

    public ListPiutangCustomerAdapter(Activity activity, List<ListDataMasterCustomer> items, Context context) {
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
            view = inflater.inflate(R.layout.list_piutang_customer, null);
        }
        TextView txt_id_customer  = view.findViewById(R.id.no_jurnal1);
        TextView txt_piutang_customerkredit  = view.findViewById(R.id.KREDIT2);
        TextView txt_piutang_customerdebit  = view.findViewById(R.id.DEBIT2);

        ListDataMasterCustomer data = items.get(i);

        txt_id_customer.setText(data.getId_customer());
        txt_piutang_customerkredit.setText(data.getPiutang());
        txt_piutang_customerdebit.setText(data.getPiutang());
        return view;
    }

}
