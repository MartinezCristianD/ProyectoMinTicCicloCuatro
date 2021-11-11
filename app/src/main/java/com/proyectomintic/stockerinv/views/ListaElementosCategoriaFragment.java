package com.proyectomintic.stockerinv.views;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proyectomintic.stockerinv.databinding.FragmentListaElementosCategoriaBinding;


public class ListaElementosCategoriaFragment extends BottomSheetDialogFragment {

    FragmentListaElementosCategoriaBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // evento click del boton continuar
        binding.btnContinuarFr.setOnClickListener(v -> {
            // esto es para cerrar el fragment
            dismiss();
        });

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        // selecciona el layout para mostrar en el fragment
        binding = FragmentListaElementosCategoriaBinding.inflate(inflater);
        return binding.getRoot();
    }
}
