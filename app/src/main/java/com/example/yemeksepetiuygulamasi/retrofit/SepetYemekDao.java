package com.example.yemeksepetiuygulamasi.retrofit;

import com.example.yemeksepetiuygulamasi.data.entity.CRUDCevap;
import com.example.yemeksepetiuygulamasi.data.entity.SepetYemekCevap;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface SepetYemekDao {





    @POST("yemekler/sepeteYemekEkle.php")
    @FormUrlEncoded
    Call<CRUDCevap> kaydet(@Field("yemek_adi") String yemek_adi,@Field("yemek_resim_adi") String yemek_resim_adi,
                           @Field("yemek_fiyat")int yemek_fiyat, @Field("yemek_siparis_adet")int yemek_siparis_adet,
                           @Field("kullanici_adi") String kullanici_adi
    );
    @POST("yemekler/sepettekiYemekleriGetir.php")
    @FormUrlEncoded
    Call<SepetYemekCevap> sepetYemekleriGetir(@Field("kullanici_adi") String kullanici_adi);

    @POST("yemekler/sepettenYemekSil.php")
    @FormUrlEncoded
    Call<CRUDCevap> sil(@Field("sepet_yemek_id") String sepet_yemek_id,@Field("kullanici_adi") String kullanici_adi);
}
