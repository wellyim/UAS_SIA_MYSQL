package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uas_sia_mysql.adapter.ListDaftarPenjualanAdapter;
import com.example.uas_sia_mysql.adapter.ListTotalPenjualanAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class DaftarTransaksiPenjualan extends AppCompatActivity {

    String server_url_select = "http://192.168.0.101/server-uas-sia/?uas_sia=alltransaksipenjualan";
    String server_url_select1 = "http://192.168.0.101/server-uas-sia/?uas_sia=totaltransaksipenjualan";
    ListView list, list1;
    ListDaftarPenjualanAdapter ListDaftarPenjualanAdapter;
    ListTotalPenjualanAdapter ListTotalPenjualanAdapter;
    List<ListDaftarPenjualan> itemList = new ArrayList<ListDaftarPenjualan>();
    List<ListTotalPenjualan> itemList1 = new ArrayList<ListTotalPenjualan>();
    Button btn_tambah_transaksi, kembali ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_transaksi_penjualan);

        list  = (ListView) findViewById(R.id.listPenjualan);
        list1  = (ListView) findViewById(R.id.listTotalPenjualan);
        btn_tambah_transaksi = findViewById(R.id.btn_tambah_transaksi_lain);
        kembali = findViewById(R.id.btn_menu_utama);


        ListDaftarPenjualanAdapter = new ListDaftarPenjualanAdapter(DaftarTransaksiPenjualan.this, itemList, this);
        list.setAdapter(ListDaftarPenjualanAdapter);

        ListTotalPenjualanAdapter = new ListTotalPenjualanAdapter(DaftarTransaksiPenjualan.this, itemList1, this);
        list1.setAdapter(ListTotalPenjualanAdapter);
        
        tampilDataPenjualan();

        tampilTotalPenjualan();

        btn_tambah_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaftarTransaksiPenjualan.this, PilihCustomer.class);
                startActivity(i);
                finish();
            }
        });

        kembali.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(DaftarTransaksiPenjualan.this, MainActivity.class);
                startActivity(i);
                finish();
            }
        });

    }



    private void tampilDataPenjualan() {
        JsonArrayRequest jArr = new JsonArrayRequest(server_url_select, new Response.Listener<JSONArray>() {
            @Override
            public void onResponse(JSONArray response) {
                for (int i = 0; i < response.length(); i++) {
                    try {
                        JSONObject obj = response.getJSONObject(i);

                        ListDaftarPenjualan item = new ListDaftarPenjualan();

                        item.setId_transaksi_penjualan(obj.getString("id_transaksi_penjualan"));
                        item.setNama_customer(obj.getString("nama_customer"));
                        item.setNama_barang(obj.getString("nama_barang"));
                        item.setHarga_barang(obj.getString("harga_barang"));
                        item.setJumlah_barang(obj.getString("jumlah_barang"));
                        item.setKeterangan(obj.getString("keterangan"));
                        item.setTotal_transaksi(obj.getString("total_transaksi"));

                        // menambah item ke array
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ListDaftarPenjualanAdapter.notifyDataSetChanged();
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

    private void tampilTotalPenjualan() {
        JsonArrayRequest jArr = new JsonArrayRequest(server_url_select1, new Response.Listener<JSONArray>() {
        @Override
        public void onResponse(JSONArray response) {
            for (int i = 0; i < response.length(); i++) {
                try {
                    JSONObject obj = response.getJSONObject(i);

                    ListTotalPenjualan item1 = new ListTotalPenjualan();
                    item1.setTotalsemuatransaksi(obj.getString("totalsemuatransaksi"));

                    // menambah item ke array
                    itemList1.add(item1);
                } catch (JSONException e) {
                    e.printStackTrace();
                }

            }
            ListTotalPenjualanAdapter.notifyDataSetChanged();
//                Toast.makeText(MasterCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
        }
    }, new Response.ErrorListener() {

        @Override
        public void onErrorResponse(VolleyError error) {
          //  Toast.makeText(DaftarTransaksiPenjualan.this,error.toString(),Toast.LENGTH_SHORT).show();
        }
    });
        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
}