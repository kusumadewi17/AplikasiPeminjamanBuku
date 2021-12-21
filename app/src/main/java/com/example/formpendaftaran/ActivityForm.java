package com.example.formpendaftaran;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.formpendaftaran.database.AppDatabase;
import com.example.formpendaftaran.database.entity.Buku;

public class ActivityForm extends AppCompatActivity {

    EditText nama, alamat, judulbuku;
    RadioButton radioPria, radioWanita, rb;
    Button btnsubmit;
    RadioGroup rg;
    SeekBar seekbarHari;
    TextView seekbarWaktu;
    CheckBox cb1, cb2, cb3, cb4;
    String kategori = "";

    private AppDatabase database;
    private boolean isEdit = false;
    private int id_peminjam = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_form);
        database = AppDatabase.getInstance(getApplicationContext());

        nama = (EditText) findViewById(R.id.editnama);
        alamat = (EditText) findViewById(R.id.editalamat);
        judulbuku = (EditText) findViewById(R.id.judlbuku);

        rg = (RadioGroup) findViewById(R.id.rg);
        radioPria = (RadioButton) findViewById(R.id.radioPria);
        radioWanita = (RadioButton) findViewById(R.id.radioWanita);


        cb1 = (CheckBox) findViewById(R.id.kategori1);
        cb2 = (CheckBox) findViewById(R.id.kategori2);
        cb3 = (CheckBox) findViewById(R.id.kategori3);
        cb4 = (CheckBox) findViewById(R.id.kategori4);


        seekbarHari = (SeekBar) findViewById(R.id.seekBarHari);
        seekbarWaktu = (TextView) findViewById(R.id.seekbarWaktu);

        btnsubmit = (Button) findViewById(R.id.btn_submit);

        Intent intent = getIntent();
        id_peminjam = intent.getIntExtra("id_peminjam", 0);
        if (id_peminjam>0) {
            isEdit = true;
            Buku buku = database.bukuDao().get(id_peminjam);
            nama.setText(buku.namalengkap);
            alamat.setText(buku.alamat);

            if (buku.jeniskelamin.toString().equals("Laki - laki")) {
                radioPria.setChecked(true);
            } else if (buku.jeniskelamin.toString().equals("Perempuan")) {
                radioWanita.setChecked(true);
            }

            judulbuku.setText(buku.judulbuku);
            seekbarWaktu.setText(buku.hari);
        }else{
            isEdit = false;
        }

        btnsubmit.setOnClickListener(this::OnClick);


        seekbarHari.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                seekbarWaktu.setText(String.valueOf(progress + " Hari"));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }



    public void OnClick(View view) {

        int radio = rg.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Nama = nama.getText().toString();
        String Alamat = alamat.getText().toString();
        String JudulBuku = judulbuku.getText().toString();
        String seekbarHari = seekbarWaktu.getText().toString();
        String kelamin = rb.getText().toString();


        //Check Box
        if (cb1.isChecked()) {
            kategori+="" + cb1.getText().toString();
        }
        if (cb2.isChecked()) {
            kategori+="" + cb2.getText().toString();
        }
        if (cb3.isChecked()) {
            kategori+="" + cb3.getText().toString();
        }
        if (cb4.isChecked()) {
            kategori+="" + cb4.getText().toString();
        }

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Data Peminjaman Anda");
        builder.setMessage("Nama : "+String.valueOf(Nama) +"\n"
                +"Alamat :" + String.valueOf(Alamat)+ "\n"
                + "Judul Buku :"+ String.valueOf(JudulBuku)
                + "\n" + "Jenis Kelamin :" + String.valueOf(kelamin) + "\n"
                + "Kategori :" + String.valueOf(kategori) + "\n"
                + "Waktu Peminjaman :" + String.valueOf(seekbarHari)+"\n"
                +"Silahkan Periksa Data Peminjaman Buku Anda!").setPositiveButton("Simpan",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(getApplicationContext(), TampilPeminjaman.class);

                            if (isEdit){
                                database.bukuDao().update(id_peminjam, Nama, Alamat ,kelamin, JudulBuku , kategori, seekbarHari);
                                intent.putExtra("nama", Nama);
                                intent.putExtra("alamat", Alamat);
                                intent.putExtra("judul", JudulBuku);
                                intent.putExtra("hari", seekbarHari);
                                intent.putExtra("jeniskelamin", kelamin);
                                  intent.putExtra("kategori", kategori.toString());
                            }else{
                                database.bukuDao().insertUser(Nama, Alamat, kelamin, JudulBuku, kategori, seekbarHari);
                                intent.putExtra("nama", Nama);
                                intent.putExtra("alamat", Alamat);
                                intent.putExtra("judul", JudulBuku);
                                intent.putExtra("hari", seekbarHari);
                                intent.putExtra("jeniskelamin", kelamin);
                                intent.putExtra("kategori", kategori.toString());
                            }
                            startActivity(intent);
                            finish();
                        }

                    });
        builder.setNegativeButton(
                "Batalkan", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(ActivityForm.this, "Mohon Data Peminjaman Dengan Benar", Toast.LENGTH_SHORT).show();
                    }
                });
        AlertDialog dialoghasil = builder.create();
        dialoghasil.show();
    }

    public void openHasilRegistrasi() {
        Intent intent = new Intent(this, TampilPeminjaman.class);

        int radio = rg.getCheckedRadioButtonId();
        rb = findViewById(radio);

        String Nama = nama.getText().toString();
        String Alamat = alamat.getText().toString();
        String JudulBuku = judulbuku.getText().toString();
        String seekbarHari = seekbarWaktu.getText().toString();
        String kelamin = rb.getText().toString();
        String kategori = "";

        //Check Box
        if (cb1.isChecked()) {
            kategori+="" + cb1.getText().toString();
        }
        if (cb2.isChecked()) {
            kategori+="" + cb2.getText().toString();
        }
        if (cb3.isChecked()) {
            kategori+="" + cb3.getText().toString();
        }
        if (cb4.isChecked()) {
            kategori+="" + cb4.getText().toString();
        }

        database.bukuDao().insertUser(Nama, Alamat, kelamin, JudulBuku, kategori, seekbarHari);

        intent.putExtra("nama", Nama);
        intent.putExtra("alamat", Alamat);
        intent.putExtra("judul", JudulBuku);
        intent.putExtra("hari", seekbarHari);
        intent.putExtra("jeniskelamin", kelamin);
        intent.putExtra("kategori", kategori.toString());
        startActivity(intent);

    }

    @Override
    protected void onStart() {
        super.onStart();
        Toast.makeText(this, "Registrasi dimulai",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        Toast.makeText(this, "Registrasi sedang berjalan",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Toast.makeText(this, "Registrasi berhenti sementara",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Registrasi berhenti",Toast.LENGTH_SHORT).show();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Aplikasi ditutup, selamat tinggal",Toast.LENGTH_SHORT).show();
    }
}