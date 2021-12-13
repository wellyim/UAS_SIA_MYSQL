package com.example.uas_sia_mysql;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button jurnal, t_pendapatan_lain, t_piutang, t_penjualan, m_customer, neraca, daftar_t;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        jurnal = findViewById(R.id.btn_jurnal);
        t_pendapatan_lain = findViewById(R.id.btn_t_pendapatanlain);
        t_piutang = findViewById(R.id.btn_m_customer);
        t_penjualan = findViewById(R.id.btn_t_penjualan);
        m_customer = findViewById(R.id.btn_m_customer);
        neraca = findViewById(R.id.btn_neraca_saldo);
        daftar_t = findViewById(R.id.btn_daftar_transaksi);

        jurnal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, JurnalUmum.class);
                startActivity(j);
            }
        });

        t_pendapatan_lain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, PilihCustomerPendapatanLain.class);
                startActivity(j);
            }
        });
//
//        t_piutang.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent j = new Intent(MainActivity.this, T_Piutang.class);
//                startActivity(j);
//            }
//        });
//
        t_penjualan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, PilihCustomer.class);
                startActivity(j);
            }
        });

        m_customer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, MasterCustomer.class);
                startActivity(j);
            }
        });
//
//        neraca.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent j = new Intent(MainActivity.this, Neraca_Saldo.class);
//                startActivity(j);
//            }
//        });
//
        daftar_t.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent j = new Intent(MainActivity.this, DaftarTransaksiPenjualan.class);
                startActivity(j);
            }
        });

    }
}