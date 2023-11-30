package com.example.yemeksepetiuygulamasi.ui.fragment;

import androidx.lifecycle.ViewModelProvider;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.yemeksepetiuygulamasi.R;
import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.data.entity.Yemekler;
import com.example.yemeksepetiuygulamasi.databinding.FragmentSepeteEkleBinding;
import com.example.yemeksepetiuygulamasi.ui.viewmodel.SepeteEkleViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepeteEkleFragment extends Fragment {
    private int  yemek_siparis_adet=1;
    private FragmentSepeteEkleBinding binding;
private int topla=0,yemek_deger_tut=0,id;
    private SepeteEkleViewModel v;
    private List<Veri> veriListesi;
    private String begen,yildiz,yemek_ad;




    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSepeteEkleBinding.inflate(inflater, container, false);

        SepeteEkleFragmentArgs bundle = SepeteEkleFragmentArgs.fromBundle(getArguments());
        Yemekler yemek = bundle.getYemek();
        binding.textFiyat.setText( yemek.getYemek_fiyat()+" ₺");
        binding.textViewUrunAd.setText(yemek.getYemek_adi());
        binding.textViewUrunAdet.setText(String.valueOf(yemek_siparis_adet) );
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/" + yemek.getYemek_resim_adi();
        Glide.with(this).load(url).override(500, 750).into(binding.ivFilm);



     yemek_deger_tut=  Integer.parseInt(yemek.getYemek_fiyat());

        v = new ViewModelProvider(this).get(SepeteEkleViewModel.class);
        binding.textViewSonuc.setText(String.valueOf(yemek.getYemek_fiyat())+" ₺");
        List<Veri> veriListesi = v.veriListesi != null ? v.veriListesi.getValue() : new ArrayList<>();
        binding.buttonSepetEkle.setOnClickListener(view -> {

            v.kaydet(yemek.getYemek_adi(), yemek.getYemek_resim_adi(), Integer.parseInt(yemek.getYemek_fiyat()), yemek_siparis_adet, "mrtozy");

        });

        for (int i=0;i<veriListesi.size();i++){

            if(veriListesi.get(i).getYemek_ad().contains(yemek.getYemek_adi())&&veriListesi.get(i).getBegen().contains("true")){
begen=veriListesi.get(i).getBegen();
yildiz=veriListesi.get(i).getYildiz();
yemek_ad=veriListesi.get(i).getYemek_ad();
id=veriListesi.get(i).getId();



            }

        }
        veriListesi.clear();

binding.imageView4.setOnClickListener(view -> {
    guncelleYildizDerecesi(1);
 v.veriGuncel(id,begen,"1",yemek_ad);
});
        binding.imageView5.setOnClickListener(view -> {
            guncelleYildizDerecesi(2);
            v.veriGuncel(id,begen,"2",yemek_ad);
        });
        binding.imageView6.setOnClickListener(view -> {
            guncelleYildizDerecesi(3);
            v.veriGuncel(id,begen,"3",yemek_ad);
        });
        binding.imageView7.setOnClickListener(view -> {
            guncelleYildizDerecesi(4);
            v.veriGuncel(id,begen,"4",yemek_ad);
        });
        binding.imageView8.setOnClickListener(view -> {
            guncelleYildizDerecesi(5);
            v.veriGuncel(id,begen,"5",yemek_ad);
        });

        binding.buttonArttir.setOnClickListener(view -> {

            arttir(binding.textViewUrunAdet);
        });

        binding.buttonAzalt.setOnClickListener(view -> {

            azalt(binding.textViewUrunAdet);
        });
        guncelleYildizDerecesi(Integer.parseInt(yildiz));



        return binding.getRoot();

    }


    public void azalt(TextView textView){
        if (yemek_siparis_adet > 1){
            yemek_siparis_adet--;
            textView.setText(Integer.toString(yemek_siparis_adet));
            topla=yemek_deger_tut/Integer.parseInt(String.valueOf(yemek_siparis_adet));
            binding.textViewSonuc.setText(String.valueOf(topla)+" ₺");
        }
    }
    private void guncelleYildizDerecesi(int yildizDerecesi) {

        binding.imageView4.setImageResource(R.drawable.karayildiz);
        binding.imageView5.setImageResource(R.drawable.karayildiz);
        binding.imageView6.setImageResource(R.drawable.karayildiz);
        binding.imageView7.setImageResource(R.drawable.karayildiz);
        binding.imageView8.setImageResource(R.drawable.karayildiz);

        for (int i = 1; i <= yildizDerecesi; i++) {
            switch (i) {
                case 1:
                    binding.imageView4.setImageResource(R.drawable.sariyildiz);
                    break;
                case 2:
                    binding.imageView5.setImageResource(R.drawable.sariyildiz);
                    break;
                case 3:
                    binding.imageView6.setImageResource(R.drawable.sariyildiz);
                    break;
                case 4:
                    binding.imageView7.setImageResource(R.drawable.sariyildiz);
                    break;
                case 5:
                    binding.imageView8.setImageResource(R.drawable.sariyildiz);
                    break;

            }
        }
    }
    public void arttir(TextView textView){
        yemek_siparis_adet++;
        textView.setText(Integer.toString(yemek_siparis_adet));
        topla=yemek_deger_tut*Integer.parseInt(String.valueOf(yemek_siparis_adet));
        binding.textViewSonuc.setText(String.valueOf(topla)+" ₺");

    }

}
