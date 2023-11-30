package com.example.yemeksepetiuygulamasi.data.entity;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "Veritabani")
public class Veri implements Serializable {
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    @NonNull
    private int id;
    @ColumnInfo(name = "begen")
    @NonNull
    private String begen;
    @ColumnInfo(name = "yildiz")
    @NonNull
    private String yildiz;
    @ColumnInfo(name = "yemek_ad")
    @NonNull
    private String yemek_ad;
    @Ignore
    public Veri() {
    }

    public Veri(int id, @NonNull String begen, @NonNull String yildiz, @NonNull String yemek_ad) {
        this.id = id;
        this.begen = begen;
        this.yildiz = yildiz;
        this.yemek_ad = yemek_ad;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getBegen() {
        return begen;
    }

    public void setBegen(@NonNull String begen) {
        this.begen = begen;
    }

    @NonNull
    public String getYildiz() {
        return yildiz;
    }

    public void setYildiz(@NonNull String yildiz) {
        this.yildiz = yildiz;
    }

    @NonNull
    public String getYemek_ad() {
        return yemek_ad;
    }

    public void setYemek_ad(@NonNull String yemek_ad) {
        this.yemek_ad = yemek_ad;
    }
}
