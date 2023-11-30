package com.example.yemeksepetiuygulamasi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.data.entity.Yemekler;
import com.example.yemeksepetiuygulamasi.data.repo.VeriDaoRepository;
import com.example.yemeksepetiuygulamasi.data.repo.YemeklerDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class AnasayfaViewModel extends ViewModel {

    public YemeklerDaoRepository yrepo;
    public VeriDaoRepository vrepo;

public  MutableLiveData<List<Veri>> veriListesi;
    public MutableLiveData<List<Yemekler>> yemeklerListesi;

    @Inject
  //  public AnasayfaViewModel(KisilerDaoRepository krepo){
    public AnasayfaViewModel(YemeklerDaoRepository yrepo,VeriDaoRepository vrepo){
      //  this.krepo = krepo;
      //  kisileriYukle();
        this.yrepo=yrepo;
this.vrepo=vrepo;
        yemekleriYukle();
veriYukle();
        yemeklerListesi = yrepo.yemeklerListesi;
        veriListesi=vrepo.veriListesi;

    }

    public void ara(String aramaKelimesi){

        yrepo.ara(aramaKelimesi);
    }
/*
    public void sil(int kisi_id){
        krepo.sil(kisi_id);
    }

    public void kisileriYukle(){
        krepo.kisileriYukle();
    }
*/
    public void yemekleriYukle(){
        yrepo.yemekleriYukle();
    }
    public void veriYukle(){
        vrepo.veriYukle();
    }
    public void veriBegen(String begen,String yildiz,String yemek_ad){
        vrepo.kaydet(begen,yildiz,yemek_ad);
    }
    public void veriGuncel(int id,String begen,String yildiz,String yemek_ad){
        vrepo.guncelle(id,begen,yildiz,yemek_ad);
    }
}
