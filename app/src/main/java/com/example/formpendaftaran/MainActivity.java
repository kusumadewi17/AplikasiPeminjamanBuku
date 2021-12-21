package com.example.formpendaftaran;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    ImageButton  btndaftar, listpeminjam;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btndaftar = (ImageButton) findViewById(R.id.btndaftar);
        listpeminjam = (ImageButton) findViewById(R.id.listpeminjam);

        Intent halDaftar = new Intent(this, ActivityForm.class);
        Intent halList = new Intent(this, ListData.class);


        btndaftar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(halDaftar,1);
            }
        });

        listpeminjam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivityForResult(halList, 1);
            }
        });

    }

}