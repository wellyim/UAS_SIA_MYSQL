package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uas_sia_mysql.adapter.ListDaftarPenjualanAdapter;
import com.example.uas_sia_mysql.adapter.ListJurnalPenjualanAdapter;
import com.example.uas_sia_mysql.adapter.ListMasterCustomerAdapter;
import com.example.uas_sia_mysql.adapter.ListPiutangCustomerAdapter;
import com.example.uas_sia_mysql.adapter.ListTotalPenjualanAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JurnalUmum extends AppCompatActivity {
    String server_url_select = "http://192.168.0.101/server-uas-sia/?uas_sia=alltransaksipenjualan";
    String server_customer = "http://192.168.0.101/server-uas-sia/?uas_sia=mastercustomer";
    ListView list, listCustomer;
    ListPiutangCustomerAdapter ListPiutangCustomerAdapter;
    ListJurnalPenjualanAdapter ListJurnalPenjualanAdapter;
    List<ListJurnalPenjualan> itemList = new ArrayList<ListJurnalPenjualan>();
    List<ListDataMasterCustomer> itemCustomer = new ArrayList<ListDataMasterCustomer>();
    Button kembali ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jurnal_umum);

        list  = (ListView) findViewById(R.id.jurnal_penjualan);

        ListJurnalPenjualanAdapter = new ListJurnalPenjualanAdapter(JurnalUmum.this, itemList, this);
        list.setAdapter(ListJurnalPenjualanAdapter);
        JurnalUmumPenjualan();

        listCustomer  = (ListView) findViewById(R.id.jurnal_piutang);
        ListPiutangCustomerAdapter = new ListPiutangCustomerAdapter(JurnalUmum.this, itemCustomer, this);
        listCustomer.setAdapter(ListPiutangCustomerAdapter);
        tampilDataCustomer();



    }

    private void JurnalUmumPenjualan() {
        JsonArrayRequest jArr = new JsonArrayRequest(server_url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        ListJurnalPenjualan item = new ListJurnalPenjualan();

                        item.setId_transaksi_penjualan(obj.getString("id_transaksi_penjualan"));
                        item.setKeterangan(obj.getString("keterangan"));
                        item.setTotal_transaksi(obj.getString("total_transaksi"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ListJurnalPenjualanAdapter.notifyDataSetChanged();
            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                //Toast.makeText(DaftarTransaksiPenjualan.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }

    private void tampilDataCustomer() {
        JsonArrayRequest jArr = new JsonArrayRequest(server_customer, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                // TextView text = findViewById(R.id.text);
                // Parsing json
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        ListDataMasterCustomer item = new ListDataMasterCustomer();

                        item.setId_customer(obj.getString("id_customer"));
                        item.setNama(obj.getString("nama"));
                        item.setAlamat(obj.getString("alamat"));
                        item.setTelepon(obj.getString("telepon"));
                        item.setPiutang(obj.getString("piutang"));

                        // menambah item ke array
                        itemCustomer.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ListPiutangCustomerAdapter.notifyDataSetChanged();

//                Toast.makeText(MasterCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(JurnalUmum.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }

}