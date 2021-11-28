package com.proyectomintic.stockerinv.views;

import static com.proyectomintic.stockerinv.views.CrearElementoFragment.CATEGORIA_ELEGIDA;
import static com.proyectomintic.stockerinv.views.InventarioActivity.LISTA_CATEGORIAS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proyectomintic.stockerinv.databinding.FragmentListaElementosCategoriaBinding;
import com.proyectomintic.stockerinv.model.Elemento;

import java.util.ArrayList;


public class ListaElementosCategoriaFragment extends BottomSheetDialogFragment {

    FragmentListaElementosCategoriaBinding binding;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (getArguments() != null) {

            ArrayList<Elemento> listaCategorias = getArguments().getParcelableArrayList(LISTA_CATEGORIAS);
            ArrayList<String> nombresElementos = new ArrayList<>();
            for (Elemento elemento : listaCategorias) {
                nombresElementos.add(elemento.nombre.concat(" cantidad: ").concat(elemento.cantidad));
            }

            binding.textViewCategoriaHogar.setText(getArguments().getString(CATEGORIA_ELEGIDA));
            binding.listView.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, nombresElementos));

        }

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
