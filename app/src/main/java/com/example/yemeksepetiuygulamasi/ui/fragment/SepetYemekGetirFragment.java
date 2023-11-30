package com.example.yemeksepetiuygulamasi.ui.fragment;

import android.os.Bundle;


import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.yemeksepetiuygulamasi.databinding.FragmentSepetYemekBinding;

import com.example.yemeksepetiuygulamasi.ui.adapter.SepetAdapter;
import com.example.yemeksepetiuygulamasi.ui.viewmodel.SepetYemekGetirViewModel;

import dagger.hilt.android.AndroidEntryPoint;

@AndroidEntryPoint
public class SepetYemekGetirFragment extends Fragment {
private FragmentSepetYemekBinding binding;
private SepetYemekGetirViewModel v;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSepetYemekBinding.inflate(inflater, container, false);


        binding.rv.setLayoutManager(new LinearLayoutManager(requireContext()));
        v.sepetYemekler.observe(getViewLifecycleOwner(),sepetListe -> {
            SepetAdapter yAdapter = new SepetAdapter(requireContext(),sepetListe,v);
            binding.textViewTutar.setText("Toplam "+v.sepetTutari());
            binding.rv.setAdapter(yAdapter);
       ;

        });






  

        return binding.getRoot();
    }
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        v = new ViewModelProvider(this).get(SepetYemekGetirViewModel.class);
    }
}