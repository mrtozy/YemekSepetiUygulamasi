package com.example.yemeksepetiuygulamasi.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.data.entity.Yemekler;
import com.example.yemeksepetiuygulamasi.data.entity.YemeklerCevap;
import com.example.yemeksepetiuygulamasi.retrofit.YemeklerDao;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class YemeklerDaoRepository {
    public MutableLiveData<List<Yemekler>> yemeklerListesi = new MutableLiveData<>();
    public MutableLiveData<List<Veri>> veriListesi = new MutableLiveData<>();
    private YemeklerDao ydao;

    public YemeklerDaoRepository(YemeklerDao ydao){
        this.ydao = ydao;
    }

  /*  public void kaydet(String kisi_ad,String kisi_tel){
        ydao.kaydet(kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int basari = response.body().getSuccess();
                String mesaj = response.body().getMessage();
                Log.e("Kişi Kaydet",basari+" - "+mesaj);
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }

    public void guncelle(int kisi_id,String kisi_ad,String kisi_tel){
        kdao.guncelle(kisi_id,kisi_ad,kisi_tel).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int basari = response.body().getSuccess();
                String mesaj = response.body().getMessage();
                Log.e("Kişi Güncelle",basari+" - "+mesaj);
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }
      public void sil(int kisi_id){
        kdao.sil(kisi_id).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int basari = response.body().getSuccess();
                String mesaj = response.body().getMessage();
                Log.e("Kişi Sil",basari+" - "+mesaj);
                kisileriYukle();
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }
*/
  public void ara(String aramaKelimesi) {
      List<Yemekler> yemekler = yemeklerListesi.getValue(); // Mevcut yemek listesini alın
      List<Yemekler> bulunanYemekler = new ArrayList<>();


          for (Yemekler yemek : yemekler) {
              if (yemek.getYemek_adi().toLowerCase().contains(aramaKelimesi.toLowerCase())) {
                  bulunanYemekler.add(yemek);
              }
          }


      yemeklerListesi.setValue(bulunanYemekler); // Sonuçları MutableLiveData'ye atayın
  }

          public void yemekleriYukle() {
              ydao.yemekleriYukle().enqueue(new Callback<YemeklerCevap>() {
                  @Override
                  public void onResponse(Call<YemeklerCevap> call, Response<YemeklerCevap> response) {
                      List<Yemekler> yemek = response.body().getYemekler();
                      yemeklerListesi.setValue(yemek);
                  }

                  @Override
                  public void onFailure(Call<YemeklerCevap> call, Throwable t) {

                  }


              });
          }
      }
