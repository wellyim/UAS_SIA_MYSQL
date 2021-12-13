package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.uas_sia_mysql.adapter.ListMasterCustomerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class PilihCustomer extends AppCompatActivity {

    String server_url_select = "http://192.168.0.101/server-uas-sia/?uas_sia=mastercustomer";
    ListView list;
    com.example.uas_sia_mysql.adapter.ListMasterCustomerAdapter ListMasterCustomerAdapter;
    List<ListDataMasterCustomer> itemList = new ArrayList<ListDataMasterCustomer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pilih_customer);

        list  = (ListView) findViewById(R.id.listCustomer);

        ListMasterCustomerAdapter = new ListMasterCustomerAdapter(PilihCustomer.this, itemList, this);
        list.setAdapter(ListMasterCustomerAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                ListDataMasterCustomer data = itemList.get(i);

                String get_idcustomer = data.getId_customer();
                String get_nama = data.getNama();
                String get_alamat = data.getAlamat();
                String get_telepon = data.getTelepon();
                String get_piutang = data.getPiutang();


                Intent i_detail = new Intent( PilihCustomer.this, TransaksiPenjualan.class);
                i_detail.putExtra("idcustomer", get_idcustomer);
                i_detail.putExtra("get_nama", get_nama);
                i_detail.putExtra("get_alamat", get_alamat);
                i_detail.putExtra("get_telepon", get_telepon);
                i_detail.putExtra("get_piutang", get_piutang);
                startActivity(i_detail);

//                Toast.makeText(MainActivity.this, get_kodebuku,Toast.LENGTH_LONG).show();

            }
        });

        tampilDataCustomer();

    }

    private void tampilDataCustomer() {

//        listBukuAdapter.notifyDataSetChanged();

        // membuat request JSON
        JsonArrayRequest jArr = new JsonArrayRequest(server_url_select, new Response.Listener<JSONArray>() {
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
                        itemList.add(item);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }

                }
                ListMasterCustomerAdapter.notifyDataSetChanged();

//                Toast.makeText(MasterCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();

            }
        }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                Toast.makeText(PilihCustomer.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        });
        // menambah request ke request queue
        RequestQueue mRequestQueue = Volley.newRequestQueue(getApplicationContext());
        mRequestQueue.add(jArr);

    }
}