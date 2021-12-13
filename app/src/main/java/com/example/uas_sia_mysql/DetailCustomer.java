package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.NetworkImageView;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class DetailCustomer extends AppCompatActivity {

    TextView txt_id, txt_nama, txt_alamat, txt_telepon, txt_piutang;
    Button btn_update, btn_hapus, btn_piutang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_customer);

        Intent getdata = getIntent();
        Bundle b = getdata.getExtras();
        String id =(String) b.get("idcustomer");
        String nama =(String) b.get("get_nama");
        String alamat =(String) b.get("get_alamat");
        String telepon =(String) b.get("get_telepon");
        String piutang =(String) b.get("get_piutang");


        txt_id   = findViewById(R.id._id);
        txt_nama = findViewById(R.id.namaCustomer);
        txt_alamat = findViewById(R.id.alamatCustomer);
        txt_telepon = findViewById(R.id.tlpCustomer);
        txt_piutang = findViewById(R.id.piutangCustomer);
        btn_hapus = findViewById(R.id.DeleteCustomer);
        btn_update = findViewById(R.id.UpdateCustomer);
        btn_piutang = findViewById(R.id.ProsesPiutang);

        txt_id.setText(id);
        txt_nama.setText(nama);
        txt_alamat.setText(alamat);
        txt_telepon.setText(telepon);
        txt_piutang.setText(piutang);

        btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                hapus_customer(id);
            }
        });

        btn_update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_update = new Intent(DetailCustomer.this, UpdateCustomer.class);
                i_update.putExtra("update_id", id);
                i_update.putExtra("update_nama", nama);
                i_update.putExtra("update_alamat", alamat);
                i_update.putExtra("update_telepon", telepon);
                i_update.putExtra("update_piutang", piutang);
                startActivity(i_update);

            }
        });

        btn_piutang.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i_update = new Intent(DetailCustomer.this, TransaksiPiutang.class);
                i_update.putExtra("update_id", id);
                i_update.putExtra("update_nama", nama);
                i_update.putExtra("update_alamat", alamat);
                i_update.putExtra("update_telepon", telepon);
                i_update.putExtra("update_piutang", piutang);
                startActivity(i_update);

            }
        });

    }

    private void hapus_customer(String id) {
        String URI = "http://192.168.0.101/server-uas-sia/?uas_sia=hapus";
        RequestQueue queue          = Volley.newRequestQueue(DetailCustomer.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(DetailCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
                if(response.toString().equals("hapus_data_berhasil")){
                    Intent i_main = new Intent(DetailCustomer.this, MainActivity.class);
                    startActivity(i_main);
                    finish();
                }else{
                    //Toast.makeText(DetailCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(DetailCustomer.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("idcustomer", id);

                return params;
            }

        };

        queue.add(stringRequest);
    }
}