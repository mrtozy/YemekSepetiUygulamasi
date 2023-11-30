package com.example.yemeksepetiuygulamasi.retrofit;

public class ApiUtils {

    public static final String URL = "http://kasimadalan.pe.hu/";


    public static YemeklerDao getYemeklerDao(){
        return RetrofitClient.getClient(URL).create(YemeklerDao.class);
    }
    public static SepetYemekDao getSepetYemekDao(){
        return RetrofitClient.getClient(URL).create(SepetYemekDao.class);
    }

}
