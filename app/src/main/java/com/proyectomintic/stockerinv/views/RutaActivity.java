package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.databinding.ActivityRutaBinding;

public class RutaActivity extends AppCompatActivity {

    private ActivityRutaBinding binding;
    String eleccionOrigen;
    String eleccionDestino;
    Bundle eleccionRuta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRutaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obteniendo el string del EditText
        eleccionRuta = new Bundle();

        eleccionOrigen = binding.inputOrigenText.getText().toString();
        eleccionDestino = binding.inputDestinoText.getText().toString();
        eleccionRuta.putString("origen", eleccionOrigen);
        eleccionRuta.putString("destino", eleccionDestino);

        //Evento click del boton continuar
        binding.buttonContinuar.setOnClickListener(v -> {
            // Ir a ElementosActivity
            Intent i = new Intent(v.getContext(), ElementosActivity.class);
            //pasar datos a la activity
            i.putExtras(eleccionRuta);
            // Iniciar la actividad
            startActivity(i);

        });

    }
}