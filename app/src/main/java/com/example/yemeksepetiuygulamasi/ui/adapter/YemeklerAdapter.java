package com.example.yemeksepetiuygulamasi.ui.adapter;



import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.yemeksepetiuygulamasi.R;
import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.data.entity.Yemekler;
import com.example.yemeksepetiuygulamasi.databinding.CardTasarimBinding;
import com.example.yemeksepetiuygulamasi.ui.fragment.AnasayfaFragmentDirections;
import com.example.yemeksepetiuygulamasi.ui.viewmodel.AnasayfaViewModel;

import java.util.List;

public class YemeklerAdapter extends RecyclerView.Adapter<YemeklerAdapter.CardTasarimTutucu> {
    private Context mContext;
    private List<Yemekler> yemeklerListesi;
    private List<Veri> veriListesi;
    private Integer list;
    private  String yildiz;
private   boolean isKirmiziKalp = true;
    private AnasayfaViewModel viewModel;

    public YemeklerAdapter(Context mContext, List<Yemekler> yemeklerListesi, List<Veri> veriListesi,AnasayfaViewModel viewModel) {
        this.mContext = mContext;
        this.yemeklerListesi = yemeklerListesi;
        this.veriListesi=veriListesi;
        this.viewModel = viewModel;
    }


    @NonNull
    @Override
    public YemeklerAdapter.CardTasarimTutucu onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CardTasarimBinding binding =
                CardTasarimBinding.inflate(LayoutInflater.from(mContext), parent, false);
        return new YemeklerAdapter.CardTasarimTutucu(binding);
    }
    public class CardTasarimTutucu extends RecyclerView.ViewHolder{
        private CardTasarimBinding tasarim;

        public CardTasarimTutucu(CardTasarimBinding tasarim) {
            super(tasarim.getRoot());
            this.tasarim = tasarim;
        }
    }

    @Override
    public void onBindViewHolder(@NonNull YemeklerAdapter.CardTasarimTutucu holder, int position) {//0,1,2
        Yemekler yemek = yemeklerListesi.get(position);

        CardTasarimBinding t = holder.tasarim;
        String url = "http://kasimadalan.pe.hu/yemekler/resimler/"+yemek.getYemek_resim_adi();
        Glide.with(mContext).load(url).override(500,750
        ).into(t.imageView);
        for (int i=0;i<veriListesi.size();i++){
            list=veriListesi.get(i).getId();
            yildiz=veriListesi.get(i).getYildiz();
            if(veriListesi.get(i).getYemek_ad().contains(yemek.getYemek_adi())&&veriListesi.get(i).getBegen().contains("true")){


                t.imageView2.setImageResource(R.drawable.kirmizi_kalp);
               t. imageView2.setTag("kirmizi_kalp");


            }else if(veriListesi.get(i).getYemek_ad().contains(yemek.getYemek_adi())==true){
                t.imageView2.setImageResource(R.drawable.kara_kalp);
                t. imageView2.setTag("kara_kalp");
            }
            Log.e("KKK",+veriListesi.get(i).getId()+" "+veriListesi.get(i).getYildiz()+" "+veriListesi.get(i).getYemek_ad()+" "+veriListesi.get(i).getBegen());
        }


        t.textViewFilmAd.setText(yemek.getYemek_adi());

        t.textViewFiyat.setText(yemek.getYemek_fiyat());

      t.imageView.setOnClickListener(view -> {
          AnasayfaFragmentDirections.Gecis gecis = AnasayfaFragmentDirections.gecis(yemek);
            Navigation.findNavController(view).navigate(gecis);
        });

        t.imageView2.setOnClickListener(view -> {

            String resimAdi = (String) t.imageView2.getTag();
            if (resimAdi != null) {
                if (resimAdi.equals("kirmizi_kalp")) {
                    t.imageView2.setImageResource(R.drawable.kara_kalp);
                    t. imageView2.setTag("kara_kalp");

                    viewModel.veriGuncel(list,"false",yildiz,t.textViewFilmAd.getText().toString());
                } else if(resimAdi.equals("kara_kalp")){

                    t.imageView2.setImageResource(R.drawable.kirmizi_kalp);
                    t. imageView2.setTag("kirmizi_kalp");

                        viewModel.veriGuncel(list,"true",yildiz,t.textViewFilmAd.getText().toString());



                }
            }



        });


    }




    @Override
    public int getItemCount() {
        return yemeklerListesi.size();
    }
}
