package com.example.yemeksepetiuygulamasi.data.entity;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class SepetYemekCevap  {
    @SerializedName("sepet_yemekler")
    private List<sepet_yemekler> sepetYemekler;
    @SerializedName("success")
    private int success;

    public SepetYemekCevap() {
    }

    public SepetYemekCevap(List<sepet_yemekler> sepetYemekler, int success) {
        this.sepetYemekler = sepetYemekler;
        this.success = success;
    }

    public List<sepet_yemekler> getSepetYemekler() {
        return sepetYemekler;
    }

    public void setSepetYemekler(List<sepet_yemekler> sepetYemekler) {
        this.sepetYemekler = sepetYemekler;
    }

    public int getSuccess() {
        return success;
    }

    public void setSuccess(int success) {
        this.success = success;
    }
}

