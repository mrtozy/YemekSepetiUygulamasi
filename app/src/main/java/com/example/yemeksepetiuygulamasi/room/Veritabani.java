package com.example.yemeksepetiuygulamasi.room;

import androidx.room.Database;
import androidx.room.RoomDatabase;


import com.example.yemeksepetiuygulamasi.data.entity.Veri;

@Database(entities = {Veri.class},version = 1)
public abstract class Veritabani extends RoomDatabase {
    public abstract VeriDao getVeriDao();
}
