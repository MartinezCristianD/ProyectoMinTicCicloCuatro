package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.FragmentRutaBinding;
import com.proyectomintic.stockerinv.views.utils.Dialogos;


public class RutaFragment extends BottomSheetDialogFragment {
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";
    FragmentRutaBinding binding;
    String eleccionOrigen, eleccionDestino;
    Bundle eleccionRuta;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        //Evento click del boton continuar
        binding.buttonContinuar.setOnClickListener(v -> {

            // Obteniendo el string del EditText
            eleccionRuta = new Bundle();

            eleccionOrigen = binding.inputOrigenText.getText().toString();
            eleccionDestino = binding.inputDestinoText.getText().toString();
            eleccionRuta.putString(ORIGEN, eleccionOrigen);
            eleccionRuta.putString(DESTINO, eleccionDestino);

            if (TextUtils.isEmpty(eleccionOrigen) || TextUtils.isEmpty(eleccionDestino)) {

                // Creando dialogo de alerta
                Dialogos.mensajePersonalizadoDialogo(requireContext(), getString(R.string.titulo_campo_vacio), getString(R.string.mensaje_campo_vacio));

            } else {
                // Pasar al InventarioActivity
                Intent i = new Intent(requireContext(), InventarioActivity.class);

                //pasar datos a la activity

                i.putExtra(ORIGEN, eleccionOrigen);
                i.putExtra(DESTINO, eleccionDestino);

                //para provar el envio de informacion

                Toast.makeText(requireContext(), eleccionDestino, Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), eleccionOrigen, Toast.LENGTH_SHORT).show();

                //Iniciar  InventariActividad
                startActivity(i);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRutaBinding.inflate(inflater);
        return binding.getRoot();
    }
}
