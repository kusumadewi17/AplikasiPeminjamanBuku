package com.example.formpendaftaran.database.entity;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class Buku {

    @PrimaryKey(autoGenerate = true)
    public int id_peminjam;

    public String namalengkap;
    public String alamat;
    public String jeniskelamin;
    public String judulbuku;
    public String kategori;
    public String hari;

    public int getId_peminjam() {
        return id_peminjam;
    }

    public void setId_peminjam(int id_peminjam) {
        this.id_peminjam = id_peminjam;
    }

    public String getNamalengkap() {
        return namalengkap;
    }

    public void setNamalengkap(String namalengkap) {
        this.namalengkap = namalengkap;
    }

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getJeniskelamin() {
        return jeniskelamin;
    }

    public void setJeniskelamin(String jeniskelamin) {
        this.jeniskelamin = jeniskelamin;
    }

    public String getJudulbuku() {
        return judulbuku;
    }

    public void setJudulbuku(String judulbuku) {
        this.judulbuku = judulbuku;
    }

    public String getKategori() {
        return kategori;
    }

    public void setKategori(String kategori) { this.kategori = kategori; }

    public String getHari() {
        return hari;
    }

    public void setHari(String hari) {
        this.hari = hari;
    }
}
