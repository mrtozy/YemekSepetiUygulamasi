package com.example.yemeksepetiuygulamasi.ui.viewmodel;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.data.repo.SepetYemeklerDaoRepository;
import com.example.yemeksepetiuygulamasi.data.repo.VeriDaoRepository;

import java.util.List;

import javax.inject.Inject;

import dagger.hilt.android.lifecycle.HiltViewModel;

@HiltViewModel
public class SepeteEkleViewModel extends ViewModel {
    public SepetYemeklerDaoRepository yrepo;
    public VeriDaoRepository vrepo;


    public  MutableLiveData<List<Veri>> veriListesi;
    @Inject
    public SepeteEkleViewModel(SepetYemeklerDaoRepository yrepo,VeriDaoRepository vrepo ) {

        this.vrepo=vrepo;
        this.yrepo = yrepo;
        veriYukle();
       this.veriListesi=vrepo.veriListesi;


    }


    public void veriGuncel(int id,String begen,String yildiz,String yemek_ad){
        vrepo.guncelle(id,begen,yildiz,yemek_ad);
    }
    public void veriYukle(){
        vrepo.veriYukle();
    }

    public void kaydet(String yemek_adi, String yemek_resim_adi, int yemek_fiyat, int yemek_siparis_adet, String kullanici_adi) {
        yrepo.kaydet(yemek_adi, yemek_resim_adi, yemek_fiyat, yemek_siparis_adet, kullanici_adi);
    }
public void yemek_adAra(String yemek_ad)
{
    vrepo.yemek_adAra(yemek_ad);
}





}