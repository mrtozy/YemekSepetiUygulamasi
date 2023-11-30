package com.example.yemeksepetiuygulamasi.data.repo;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.yemeksepetiuygulamasi.data.entity.CRUDCevap;

import com.example.yemeksepetiuygulamasi.data.entity.SepetYemekCevap;
import com.example.yemeksepetiuygulamasi.data.entity.sepet_yemekler;
import com.example.yemeksepetiuygulamasi.retrofit.SepetYemekDao;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class SepetYemeklerDaoRepository {
    public MutableLiveData<List<sepet_yemekler>> sepetYemeklerListe = new MutableLiveData<>();
    private SepetYemekDao sYdao;

    private int toplam = 0;
    private int tutar = 0;
    private List<sepet_yemekler> gelenYemekler;

    public SepetYemeklerDaoRepository(SepetYemekDao sYdao) {
        this.gelenYemekler = new ArrayList<>();
        this.sYdao = sYdao;
    }

    public void kaydet(String yemek_adi, String yemek_resim_adi
            , int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {


        sYdao.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int basari = response.body().getSuccess();
                String mesaj = response.body().getMessage();
                Log.e("Sepet", basari + " - " + mesaj);
            }

            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {
            }
        });
    }
    public void sil(String kisi_id,String ka){
        sYdao.sil(kisi_id,ka).enqueue(new Callback<CRUDCevap>() {
            @Override
            public void onResponse(Call<CRUDCevap> call, Response<CRUDCevap> response) {
                int basari = response.body().getSuccess();
                String mesaj = response.body().getMessage();
                Log.e("Sil",basari+" - "+mesaj);
                sepetGetir("mrtozy");
            }
            @Override
            public void onFailure(Call<CRUDCevap> call, Throwable t) {}
        });
    }
    public void sepetGetir(String kullanici_adi) {
        sYdao.sepetYemekleriGetir("mrtozy").enqueue(new Callback<SepetYemekCevap>()  {
            @Override
            public void onResponse(Call<SepetYemekCevap> call, Response<SepetYemekCevap> response) {
                toplam = 0;
                tutar = 0;
                List<sepet_yemekler> liste = response.body().getSepetYemekler();

                gelenYemekler.clear();
                gelenYemekler.addAll(liste);

                for (int i = 0; i < gelenYemekler.size(); i++) {
                    tutar = gelenYemekler.get(i).getYemek_fiyat() * gelenYemekler.get(i).getYemek_siparis_adet();
                    toplam += tutar;
                }

                Log.e("KUUU", response.message().toString());
                sepetYemeklerListe.setValue(liste);

            }

            @Override
            public void onFailure(Call<SepetYemekCevap> call, Throwable t) {

                Log.e("Hata", "İstek başarısız: " + t.getMessage());
                t.printStackTrace();
            }
        });
    }



    public String sepetTutari() {


        return String.valueOf(toplam);
    }
}
