package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.SeekBar;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.ActivityElementosBinding;

public class ElementosActivity extends AppCompatActivity {

    // Variables
    private ActivityElementosBinding binding;
    AutoCompleteTextView listaCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityElementosBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        // Llenando la lista  para seleccionar la categoria
        listaCategorias = ((AutoCompleteTextView) binding.textViewCategoriaElegida.getEditText());
         if(listaCategorias != null){
             listaCategorias.setAdapter(new ArrayAdapter<>(this, android.R.layout.select_dialog_item, getResources().getStringArray(R.array.categorias)));
         }

        // Evento click  para crear los Elementos
        binding.btnCrearElementos.setOnClickListener(v -> {
            Intent intent = new Intent(this, InventarioActivity.class);
            startActivity(intent);
        });

        // SeekBar Valor Inicial
        binding.seekBar.setProgress(0);

        // Valor Final
        binding.seekBar.setMax(20);

        // Evento estado de cambio de la barra inferior
        binding.seekBar.setOnSeekBarChangeListener(
                new SeekBar.OnSeekBarChangeListener() {

                    //hace un llamado a la perilla cuando se arrastra

                    @Override
                    public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                        String textoProgressBar = getString(R.string.texto_unidades);
                        binding.textViewContador.setText(progress + " " + textoProgressBar);
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




