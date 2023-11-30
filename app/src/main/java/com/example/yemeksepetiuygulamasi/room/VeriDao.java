package com.example.yemeksepetiuygulamasi.room;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.yemeksepetiuygulamasi.data.entity.Veri;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;

@Dao
public interface VeriDao {
    @Query("SELECT * FROM Veritabani")
    Single<List<Veri>> veriYukle();

    @Insert
    Completable kaydet(Veri kisi);

    @Update
    Completable guncelle(Veri kisi);

    @Delete
    Completable sil(Veri kisi);

    @Query("SELECT * FROM Veritabani WHERE yemek_ad = :yemek_ad")
    Single<List<Veri>> idAra(String yemek_ad);
}
