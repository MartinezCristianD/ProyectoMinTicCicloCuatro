package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.databinding.ActivityRutaBinding;

public class RutaActivity extends AppCompatActivity {

    private ActivityRutaBinding binding;
    String eleccionOrigen;
    String eleccionDestino;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRutaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Obteniendo el string del EditText
        eleccionOrigen = binding.InputOrigen.toString();
        eleccionDestino = binding.InputDestino.toString();


        //Evento click del boton continuar
        binding.buttonContinuar.setOnClickListener(v -> {
            // Ir a ElementosActivity
            Intent i = new Intent(v.getContext(), ElementosActivity.class);
            // Iniciar la actividad
            startActivity(i);


        });

/*

        Intent intent = new Intent(this, InventarioActivity.class);
        intent.putExtra("Eleccion_Origen",eleccionOrigen);
        intent.putExtra("Eleccion_Destino",eleccionDestino);
        // Iniciar la actividad

*/


    }
}