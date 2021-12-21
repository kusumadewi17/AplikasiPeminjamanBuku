package com.example.formpendaftaran;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.formpendaftaran.database.AppDatabase;
import com.example.formpendaftaran.database.entity.Buku;

import java.util.ArrayList;
import java.util.List;

public class ListData extends AppCompatActivity {
    Button btnTambah;
    private RecyclerView rvIndex;
    private AppDatabase database;
    private ListDataAdapter DataAdapter;
    private List<Buku> list = new ArrayList<>();
    private AlertDialog.Builder dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_data);

        rvIndex = findViewById(R.id.rv_buku);

        btnTambah = (Button) findViewById(R.id.btn_tambah);
        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = null;
                intent = new Intent(getApplicationContext(), ActivityForm.class);
                startActivity(intent);
            }
        });

        database = AppDatabase.getInstance(getApplicationContext());
        list.clear();
        list.addAll(database.bukuDao().getAll());
        DataAdapter = new ListDataAdapter(getApplicationContext(),list);
        DataAdapter.setDialog(new ListDataAdapter.Dialog() {
            @Override
            public void onClick(int position) {
                final CharSequence[] dialogItem = {"Edit", "Hapus"};
                dialog = new AlertDialog.Builder(ListData.this);
                dialog.setItems(dialogItem, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        switch (i) {
                            case 0:
                                Intent intent = new Intent(ListData.this, ActivityForm.class);
                                intent.putExtra("id_peminjam", list.get(position).id_peminjam);
                                startActivity(intent);
                                break;
                            case 1:
                                Buku buku = list.get(position);
                                database.bukuDao().delete(buku);
                                onStart();
                                break;

                        }
                    }
        });
        dialog.show();

        }
    });
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(),RecyclerView.VERTICAL,false);
        rvIndex.setLayoutManager(layoutManager);
        rvIndex.setAdapter(DataAdapter);


        btnTambah.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(ListData.this,ActivityForm.class));
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        list.clear();
        list.addAll(database.bukuDao().getAll());
        DataAdapter.notifyDataSetChanged();
    }
}