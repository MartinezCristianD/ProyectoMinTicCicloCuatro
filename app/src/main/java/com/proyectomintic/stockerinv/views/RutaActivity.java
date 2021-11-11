package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityRutaBinding;

public class RutaActivity extends AppCompatActivity {

    private ActivityRutaBinding binding;
    String eleccionOrigen;
    String eleccionDestino;
    Bundle eleccionRuta;

    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityRutaBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

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
                AlertaCamposVacios();

            } else {
                // Ir a ElementosActivity
                Intent i = new Intent(v.getContext(), ElementosActivity.class);
                //pasar datos a la activity
                i.putExtras(eleccionRuta);
                // Iniciar la actividad
                startActivity(i);
            }
        });

    }

    public void AlertaCamposVacios() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.titulo_campo_vacio)
                .setMessage(R.string.mensaje_campo_vacio);
        builder.create();
        builder.show();

    }
}
