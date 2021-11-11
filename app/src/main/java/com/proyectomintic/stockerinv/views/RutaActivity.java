package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityRutaBinding;
import com.proyectomintic.stockerinv.views.utils.Dialogos;

public class RutaActivity extends AppCompatActivity {

    private ActivityRutaBinding binding;
    String eleccionOrigen, eleccionDestino;
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
                Dialogos.mensajePersonalizadoDialogo(this, getString(R.string.titulo_campo_vacio), getString(R.string.mensaje_campo_vacio));
            } else {

                // Ir a ElementosActivity
                Intent i = new Intent(v.getContext(), InventarioActivity.class);

                //pasar datos a la activity
                i.putExtras(eleccionRuta);

                // Iniciar la actividad
                startActivity(i);
            }
        });

    }
}
