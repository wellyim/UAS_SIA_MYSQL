package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
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

public class TransaksiPenjualan extends AppCompatActivity {

    EditText nama_barang, jumlah_barang, harga_barang, keterangan_barang;
    TextView txt_id,txt_nama, total_transaksi;
    Button buat_transaksi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transaksi_penjualan);
        Intent getdata = getIntent();
        Bundle b = getdata.getExtras();
        String nama = (String) b.get("get_nama");

        txt_id = findViewById(R.id._id);
        txt_nama = findViewById(R.id.NamaCT);
        nama_barang = findViewById(R.id.nama_barang);
        jumlah_barang = findViewById(R.id.jumlah_barang);
        harga_barang = findViewById(R.id.harga_barang);
        keterangan_barang = findViewById(R.id.keterangan_barang);
        total_transaksi = findViewById(R.id.T_transaksi);
        buat_transaksi = findViewById(R.id.btn_tambah_transaksi_penjualan);

        txt_nama.setText(nama);

        calculationTransaksi();

        buat_transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String get_nama_barang = nama_barang.getText().toString().trim();
                String get_harga_barang = harga_barang.getText().toString().trim();
                String get_jumlah_barang = jumlah_barang.getText().toString();
                String get_keterangan_barang = keterangan_barang.getText().toString().trim();
                String get_total_transaksi_barang = total_transaksi.getText().toString().trim();

                if (get_nama_barang.isEmpty()) {
                    nama_barang.setError("Tidak boleh kosong!");
                    nama_barang.requestFocus();
                } else if (get_harga_barang.isEmpty()) {
                    harga_barang.setError("Tidak boleh kosong!");
                    harga_barang.requestFocus();
                } else if (get_jumlah_barang.isEmpty()) {
                    jumlah_barang.setError("Tidak boleh kosong!");
                    jumlah_barang.requestFocus();
                } else if (get_jumlah_barang.isEmpty()) {
                    keterangan_barang.setError("Tidak boleh kosong!");
                    keterangan_barang.requestFocus();
                } else {
                    insert_data_transaksi_ke_server(nama, get_nama_barang, get_harga_barang, get_jumlah_barang, get_keterangan_barang, get_total_transaksi_barang);
                    Intent i = new Intent(TransaksiPenjualan.this, DaftarTransaksiPenjualan.class);
                    startActivity(i);
                    finish();
                }
            }

            private void insert_data_transaksi_ke_server(String nama, String get_nama_barang, String get_harga_barang, String get_jumlah_barang, String get_keterangan_barang, String get_total_transaksi_barang) {
                String URI = "http://192.168.0.101/server-uas-sia/?uas_sia=tambahtransaksipenjualan";
                RequestQueue queue = Volley.newRequestQueue(TransaksiPenjualan.this);
                StringRequest stringRequest = new StringRequest(Request.Method.POST, URI, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String response) {

//                        Toast.makeText(TambahKomik.this,response.toString(),Toast.LENGTH_SHORT).show();
                        if(response.toString().equals("insert_data_berhasil")){
                            Intent i_main = new Intent(TransaksiPenjualan.this, MainActivity.class);
                            startActivity(i_main);
                            finish();
                        }else{
                            //Toast.makeText(TransaksiPenjualan.this,response.toString(),Toast.LENGTH_SHORT).show();
                        }

                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                     //   Toast.makeText(TransaksiPenjualan.this,error.toString(),Toast.LENGTH_SHORT).show();
                    }
                }){
                    @Override
                    protected Map<String, String> getParams() throws AuthFailureError {
                        // Posting parameters ke post url
                        Map<String, String> params = new HashMap<String, String>();

                        params.put("tambah_nama_customer", nama);
                        params.put("tambah_nama_barang", get_nama_barang);
                        params.put("tambah_harga_barang", get_harga_barang);
                        params.put("tambah_jumlah_barang", get_jumlah_barang);
                        params.put("tambah_keterangan_barang", get_keterangan_barang);
                        params.put("tambah_total_transaksi", get_total_transaksi_barang);

                        return params;
                    }

                };

                queue.add(stringRequest);

            }

        });

    }

    public void calculationTransaksi() {
        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if (!harga_barang.getText().toString().equals("") && !jumlah_barang.getText().toString().equals("")){
                    int harga_brg_trans = Integer.parseInt(harga_barang.getText().toString());
                    int jumlah_brg_trans = Integer.parseInt(jumlah_barang.getText().toString());
                    int calcul =  harga_brg_trans * jumlah_brg_trans;
                    total_transaksi.setText(String.valueOf(calcul));
                }
            }
            @Override
            public void afterTextChanged(Editable editable) {

            }
        };
        harga_barang.addTextChangedListener(textWatcher);
        jumlah_barang.addTextChangedListener(textWatcher);
    }

}
