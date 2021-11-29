package com.proyectomintic.stockerinv.views;

import static com.proyectomintic.stockerinv.views.CrearElementoFragment.CATEGORIA_ELEGIDA;
import static com.proyectomintic.stockerinv.views.InventarioActivity.LISTA_CATEGORIAS;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proyectomintic.stockerinv.databinding.FragmentListaElementosCategoriaBinding;
import com.proyectomintic.stockerinv.model.AdaptadorPersonalizado;
import com.proyectomintic.stockerinv.model.Elemento;

import java.util.ArrayList;

public class ListaElementosCategoriaFragment extends BottomSheetDialogFragment {

    FragmentListaElementosCategoriaBinding binding;
    public static final String LISTA_ELEMENTOS_CATEGORIA_KEY = "LISTA_ELEMENTOS_CATEGORIA_KEY";
    AdaptadorPersonalizado adapter;
    ArrayList<Elemento> listaCategorias;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //traer los datos
        if (getArguments() != null) {

            listaCategorias = getArguments().getParcelableArrayList(LISTA_CATEGORIAS);

            binding.textViewCategoriaHogar.setText(getArguments().getString(CATEGORIA_ELEGIDA).concat("  Articulos:  ").concat(String.valueOf(listaCategorias.size())));
            adapter = new AdaptadorPersonalizado(listaCategorias, elemento -> {
                // borrar elemento
                listaCategorias.remove(elemento);
                adapter.notifyItemRemoved(listaCategorias.indexOf(elemento));
                binding.textViewCategoriaHogar.setText(getArguments().getString(CATEGORIA_ELEGIDA).concat("  Articulos:  ").concat(String.valueOf(listaCategorias.size())));

                return null;
            });
            binding.listView.setAdapter(adapter);
            //orientacion del layout
            binding.listView.setLayoutManager(new LinearLayoutManager(getContext(), LinearLayoutManager.VERTICAL, false));
            // esta linea grazantiza que la lista no se va modificar
            binding.listView.setHasFixedSize(true);
            // animaciones en la lista
            binding.listView.setItemAnimator(new DefaultItemAnimator());

        }

        // evento click del boton continuar
        binding.btnContinuarFr.setOnClickListener(v -> {

            Bundle i = new Bundle();
            i.putParcelableArrayList(LISTA_CATEGORIAS, listaCategorias);

            getParentFragmentManager().setFragmentResult(LISTA_ELEMENTOS_CATEGORIA_KEY, i);
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
