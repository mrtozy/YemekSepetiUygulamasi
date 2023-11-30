package com.example.yemeksepetiuygulamasi.data.repo;

import androidx.lifecycle.MutableLiveData;

import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.room.VeriDao;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

public class VeriDaoRepository {
    public MutableLiveData<List<Veri>> veriListesi = new MutableLiveData<>();
    private VeriDao kdao;

    public VeriDaoRepository(VeriDao kdao){
        this.kdao = kdao;
    }

  public void kaydet(String begen,String yildiz,String yemek_ad){
        Veri veri = new Veri(0,begen,yildiz,yemek_ad);
        kdao.kaydet(veri).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onComplete() {}
                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void guncelle(int id,String begen,String yildiz,String yemek_ad){
        Veri guncellenenVeri = new Veri(id,begen,yildiz,yemek_ad);
        kdao.guncelle(guncellenenVeri).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onComplete() {}
                    @Override
                    public void onError(Throwable e) {}
                });
    }

    public void yemek_adAra(String yemek_ad){
        kdao.idAra(yemek_ad).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Veri>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onSuccess(List<Veri> veris) {
                        veriListesi.setValue(veris);
                    }
                    @Override
                    public void onError(Throwable e) {}
                });
    }
/*
    public void sil(int kisi_id){
        Kisiler silinenKisi = new Kisiler(kisi_id,"","","");
        kdao.sil(silinenKisi).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new CompletableObserver() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onComplete() {
                        kisileriYukle();
                    }
                    @Override
                    public void onError(Throwable e) {}
                });
    }*/

    public void veriYukle(){
        kdao.veriYukle().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<Veri>>() {
                    @Override
                    public void onSubscribe(Disposable d) {}
                    @Override
                    public void onSuccess(List<Veri> veris) {
                        veriListesi.setValue(veris);
                    }
                    @Override
                    public void onError(Throwable e) {}
                });
    }
}
