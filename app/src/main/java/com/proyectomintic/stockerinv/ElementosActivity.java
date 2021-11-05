package com.proyectomintic.stockerinv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ElementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);

        TextView mostrarArticulosSeekBar = (TextView) findViewById(R.id.textViewContador);
        SeekBar barraAgregarArticulos;

        ImageButton botonRegresar = findViewById(R.id.botonAtras);
        botonRegresar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

        // SeekBar
        barraAgregarArticulos = (SeekBar) findViewById(R.id.seekBar);
        // Valor Inicial
        barraAgregarArticulos.setProgress(0);
        // Valor Final
        barraAgregarArticulos.setMax(20);
        barraAgregarArticulos.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {
                    //hace un llamado a la perilla cuando se arrastra
                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        String textoProgressBar = getString(R.string.texto_unidades);
                        mostrarArticulosSeekBar.setText(progress + textoProgressBar);
                    }

                    //hace un llamado  cuando se toca la perilla
                    public void onStartTrackingTouch(SeekBar seekBar) {
                    }

                    //hace un llamado  cuando se detiene la perilla
                    public void onStopTrackingTouch(SeekBar seekBar) {
                    }
                });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Gracias por Elegir °StockerInv°", Toast.LENGTH_LONG).show();

    }
}




