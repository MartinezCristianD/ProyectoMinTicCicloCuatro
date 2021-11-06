package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.proyectomintic.stockerinv.R;

public class CategoriasActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {
    String textoRadioBotonEleccion ;
    RadioButton radioBotonHogar ;
    RadioButton radioBotonOficina;
    RadioButton radioBotonMateriales ;
    RadioButton radioBotonDeportes ;
    Button botonCrearCategoria;
    RadioGroup radioGroupoCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        radioBotonHogar = findViewById(R.id.radioButtonHogar);
        radioBotonOficina = findViewById(R.id.radioButtonOficina);
        radioBotonMateriales = findViewById(R.id.radioButtonMateriales);
        radioBotonDeportes = findViewById(R.id.radioButtonDeportes);

        botonCrearCategoria = findViewById(R.id.botonCrearCategoria);
        radioGroupoCategorias = findViewById(R.id.radioGroupCategorias);

        radioGroupoCategorias.setOnCheckedChangeListener(this);

        botonCrearCategoria.setOnClickListener(v -> {
            Intent intent = new Intent(this, ElementosActivity.class);
            intent.putExtra("CATEGORIA_ELEGIDA", textoRadioBotonEleccion);
            startActivity(intent);
        });
    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Categoria creada exitosamente", Toast.LENGTH_LONG).show();

    }

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {

        if (radioBotonHogar.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_hogar);
        }
        if (radioBotonOficina.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_oficina);
        }
        if (radioBotonMateriales.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_materiales);
        }
        if (radioBotonDeportes.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_deportes);
        }

    }
}