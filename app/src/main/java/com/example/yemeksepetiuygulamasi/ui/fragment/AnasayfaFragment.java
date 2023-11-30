package com.example.yemeksepetiuygulamasi.ui.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.StaggeredGridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;

import com.example.yemeksepetiuygulamasi.R;
import com.example.yemeksepetiuygulamasi.data.entity.Veri;
import com.example.yemeksepetiuygulamasi.databinding.FragmentAnasayfaBinding;
import com.example.yemeksepetiuygulamasi.ui.adapter.YemeklerAdapter;
import com.example.yemeksepetiuygulamasi.ui.viewmodel.AnasayfaViewModel;

import java.util.ArrayList;
import java.util.List;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class AnasayfaFragment extends Fragment {
    private FragmentAnasayfaBinding binding;
    private AnasayfaViewModel viewModel;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAnasayfaBinding.inflate(inflater, container, false);

        binding.toolbarAnasayfa.setTitle("Yemekler");

        binding.rv.setLayoutManager(
                new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL));


        viewModel.yemeklerListesi.observe(getViewLifecycleOwner(),yemeklerListesi -> {

            List<Veri> veriListesi = viewModel.veriListesi != null ? viewModel.veriListesi.getValue() : new ArrayList<>();


            YemeklerAdapter yAdapter = new YemeklerAdapter(requireContext(), yemeklerListesi, veriListesi, viewModel);
            binding.rv.setAdapter(yAdapter);
        });

        binding.fab.setOnClickListener(view -> {
            Navigation.findNavController(view).navigate(R.id.sepetGetir);
        });

      binding.searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                if (s.isEmpty()) {
                    // Arama kelimesi boşsa tüm yemekleri yeniden yükle
                    viewModel.yemekleriYukle();
                } else {
                    // Arama yap
                    viewModel.ara(s);
                }

                return true;
            }

          public boolean onQueryTextChange(String s) {
              // Arama sırasında her metin değişikliğinde aramayı güncelle
              viewModel.ara(s);

              return true;
          }
        });



        return binding.getRoot();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        viewModel = new ViewModelProvider(this).get(AnasayfaViewModel.class);
    }


    @Override
    public void onResume() {
        super.onResume();


    }
}