package com.example.formpendaftaran.database.dao;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Query;

import com.example.formpendaftaran.database.entity.Buku;

import java.util.List;

@Dao
public interface BukuDao {
    @Query("SELECT * FROM buku")
    List<Buku> getAll();

    @Query("INSERT INTO Buku (namalengkap, alamat, jeniskelamin, judulbuku, kategori, hari) VALUES(:namalengkap, :alamat, :jeniskelamin, :judulbuku, :kategori, :hari)")
    void insertUser(String namalengkap, String alamat, String jeniskelamin, String judulbuku, String kategori, String hari);

    @Query("UPDATE Buku SET namalengkap=:nama , alamat=:alamat , jeniskelamin=:jeniskelamin , judulbuku=:judulbuku , kategori=:kategori, hari=:hari WHERE id_peminjam=:id_peminjam")
    void update(int id_peminjam, String nama, String alamat, String jeniskelamin, String judulbuku, String kategori, String hari);

    @Query("SELECT * FROM buku WHERE id_peminjam=:id_peminjam")
    Buku get(int id_peminjam);

    @Delete
    void delete(Buku buku);

}
