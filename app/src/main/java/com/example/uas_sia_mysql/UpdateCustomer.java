package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.util.HashMap;
import java.util.Map;

public class UpdateCustomer extends AppCompatActivity {

    Button simpan, lihatdata;
    EditText input_nama,input_alamat,input_tlp;
    TextView input_id,piutang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_customer);

        input_id = findViewById(R.id.idCustomer);
        input_nama = findViewById(R.id.inputNama);
        input_alamat = findViewById(R.id.inputAlamat);
        input_tlp = findViewById(R.id.inputTlp);
        piutang = findViewById(R.id.inputPiutang);
        simpan = findViewById(R.id.simpan);
        lihatdata = findViewById(R.id.liat_list);
        Intent getdata = getIntent();
        Bundle b = getdata.getExtras();
        String update_id =(String) b.get("update_id");
        String update_nama =(String) b.get("update_nama");
        String update_alamat =(String) b.get("update_alamat");
        String update_telepon =(String) b.get("update_telepon");
        String update_piutang =(String) b.get("update_piutang");
        input_id.setText(update_id);
        input_nama.setText(update_nama);
        input_alamat.setText(update_alamat);
        input_tlp.setText(update_telepon);
        piutang.setText(update_piutang);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_update_nama = input_nama.getText().toString().trim();
                String get_update_alamat = input_alamat.getText().toString().trim();
                String get_update_telepon = input_tlp.getText().toString().trim();
                update_customer(update_id,get_update_nama, get_update_alamat,get_update_telepon,update_piutang);
                Intent i = new Intent(UpdateCustomer.this, MasterCustomer.class);
                startActivity(i);
                finish();
            }
        });

        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(UpdateCustomer.this, MasterCustomer.class);
                startActivity(i);
                finish();
            }
        });
    }

    private void update_customer(String update_id, String get_update_nama, String get_update_alamat, String get_update_telepon, String update_piutang) {
        String URI = "http://192.168.0.101/server-uas-sia/?uas_sia=update";
        RequestQueue queue          = Volley.newRequestQueue(UpdateCustomer.this);
        StringRequest stringRequest = new StringRequest(Request.Method.POST, URI, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                //Toast.makeText(UpdateCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
                if(response.toString().equals("data_berhasil_diupdate")){
                    Intent i_main = new Intent(UpdateCustomer.this, MainActivity.class);
                    startActivity(i_main);
                    finish();
                }else{
                    //Toast.makeText(UpdateCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               // Toast.makeText(UpdateCustomer.this,error.toString(),Toast.LENGTH_SHORT).show();
            }
        }){
            @Override
            protected Map<String, String> getParams() throws AuthFailureError {
                // Posting parameters ke post url
                Map<String, String> params = new HashMap<String, String>();

                params.put("idcustomer", update_id);
                params.put("get_update_nama", get_update_nama);
                params.put("get_update_alamat", get_update_alamat);
                params.put("get_update_telepon", get_update_telepon);
                params.put("get_update_piutang", update_piutang);

                return params;
            }

        };

        queue.add(stringRequest);
    }
}