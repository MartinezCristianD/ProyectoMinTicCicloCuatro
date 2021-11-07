package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityElementosBinding;
import com.proyectomintic.stockerinv.databinding.ActivityMainBinding;

public class ElementosActivity extends AppCompatActivity {
    private ActivityElementosBinding binding;
    AutoCompleteTextView listaCategorias;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityElementosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        listaCategorias = ((AutoCompleteTextView) binding.textViewCategoriaElegida.getEditText());
         if(listaCategorias != null){
             listaCategorias.setAdapter(new ArrayAdapter<String>(this, android.R.layout.select_dialog_item,getResources().getStringArray(R.array.categorias)));
         }


        TextView mostrarArticulosSeekBar = findViewById(R.id.textViewContador);
        SeekBar barraAgregarArticulos;


        Button btnCrearElemento = findViewById(R.id.btnCrearElementos);
        btnCrearElemento.setOnClickListener(v -> {
            Intent intent = new Intent(this, InventarioActivity.class);
            startActivity(intent);
        });

        // SeekBar
        barraAgregarArticulos = findViewById(R.id.seekBar);
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
                        mostrarArticulosSeekBar.setText(progress + " " + textoProgressBar);
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




