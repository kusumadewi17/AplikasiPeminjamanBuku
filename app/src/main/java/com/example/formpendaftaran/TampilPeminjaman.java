package com.example.formpendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class TampilPeminjaman extends AppCompatActivity {
    Button submit;
    TextView showNama, showAlamat, showJK, showJudul, showkategori, showWaktu;
    String nama, alamat, jk, judul, kategori, waktu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tampil_peminjaman);

        showNama = (TextView) findViewById(R.id.shownama);
        showAlamat = (TextView) findViewById(R.id.showalamat);
        showJK= (TextView) findViewById(R.id.showjk);
        showJudul = (TextView) findViewById(R.id.showjudul);
        showkategori = (TextView) findViewById(R.id.showkategori);
        showWaktu= (TextView) findViewById(R.id.showwaktu);

        //validasi, menyimpan nilai variabel dari intent, set text pada textview untuk menampilkan nilai
        if (getIntent().getStringExtra("nama") != "") {
            nama = getIntent().getStringExtra("nama");
            showNama.setText(nama);
        }
        if (getIntent().getStringExtra("alamat") != "") {
            alamat = getIntent().getStringExtra("alamat");
            showAlamat.setText(alamat);
        }
        if (getIntent().getStringExtra("jeniskelamin") != "") {
            jk = getIntent().getStringExtra("jeniskelamin");
            showJK.setText(jk);
        }
        if (getIntent().getStringExtra("judul") != "") {
            judul = getIntent().getStringExtra("judul");
            showJudul.setText(judul);
        }
        if (getIntent().getStringExtra("kategori") != "") {
            kategori = getIntent().getStringExtra("kategori");
            showkategori.setText(kategori);
        }
        if (getIntent().getStringExtra("hari") != "") {
            waktu = getIntent().getStringExtra("hari");
            showWaktu.setText(waktu);
        }
    }

    //button kembali
    public void ok (View view) {
        Intent intent = new Intent(TampilPeminjaman.this, ListData.class);
        startActivity(intent);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Tampilan profil dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Tampilan profil sedang berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Tampilan profil berhenti sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Tampilan profil berhenti",Toast.LENGTH_SHORT).show();
    }

}
