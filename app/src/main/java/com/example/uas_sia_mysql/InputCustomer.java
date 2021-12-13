package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

public class InputCustomer extends AppCompatActivity {

    Button simpan, lihatdata;
    EditText input_nama,input_alamat,input_tlp, input_piutang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_customer);

        simpan = findViewById(R.id.simpan);
        input_nama = findViewById(R.id.inputNama);
        input_alamat = findViewById(R.id.inputAlamat);
        input_tlp = findViewById(R.id.inputTlp);
        input_piutang = findViewById(R.id.inputPiutang);
        lihatdata = findViewById(R.id.liat_list);


        simpan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String get_nama = input_nama.getText().toString().trim();
                String get_alamat =  input_alamat.getText().toString().trim();
                String get_tlp = input_tlp.getText().toString();
                String get_piutang = input_piutang.getText().toString().trim();

                if(get_nama.isEmpty()){
                    input_nama.setError("Kode tidak boleh kosong!");
                    input_nama.requestFocus();
                }else if(get_alamat.isEmpty()){
                    input_alamat.setError("Judul tidak boleh kosong!");
                    input_alamat.requestFocus();
                }else if(get_tlp.isEmpty()){
                    input_tlp.setError("Tipe tidak boleh kosong!");
                    input_tlp.requestFocus();
                }else{
                    insert_data_ke_server(get_nama,get_alamat,get_tlp,get_piutang);
                    Intent i = new Intent(InputCustomer.this, MasterCustomer.class);
                    startActivity(i);
                    finish();
                }
            }

            private void insert_data_ke_server(String get_nama, String get_alamat, String get_tlp, String get_piutang) {
                String URI = "http://192.168.0.101/server-uas-sia/?uas_sia=tambah";
                RequestQueue queue = Volley.newRequestQueue(InputCustomer.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URI, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {
//                        Toast.makeText(TambahKomik.this,response.toString(),Toast.LENGTH_SHORT).show();
                        if(response.toString().equals("insert_data_berhasil")){
                            Intent i_main = new Intent(InputCustomer.this, MainActivity.class);
                            startActivity(i_main);
                            finish();
                        }else{
                           // Toast.makeText(InputCustomer.this,response.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        //Toast.makeText(InputCustomer.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Posting parameters ke post url
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("nama", get_nama);
                        params.put("alamat", get_alamat);
                        params.put("telepon", get_tlp);
                        params.put("piutang", get_piutang);

                        return params;
                    }

                };

                queue.add(stringRequest);

            }
        });
        lihatdata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(InputCustomer.this, MasterCustomer.class);
                startActivity(i);
                finish();
            }
        });

    }
}