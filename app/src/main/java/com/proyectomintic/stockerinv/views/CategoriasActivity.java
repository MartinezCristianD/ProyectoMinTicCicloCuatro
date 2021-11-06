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
    RadioButton radioBotonCocina ;
    RadioButton radioBotonLimpieza;
    RadioButton radioBotonMuebles ;
    RadioButton radioBotonJardin ;
    RadioButton radioBotonElectrodomesticos;
    RadioButton radioBotonTecnologia ;
    RadioButton radioBotonDecoracion ;
    Button botonCrearCategoria;
    RadioGroup radioGroupoCategorias;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias);

        radioBotonCocina = findViewById(R.id.radioButtonCocina);
        radioBotonLimpieza = findViewById(R.id.radioButtonLimpieza);
        radioBotonMuebles = findViewById(R.id.radioButtonMuebles);
        radioBotonJardin = findViewById(R.id.radioButtonJardin);
        radioBotonElectrodomesticos = findViewById(R.id.radioButtonElectrodomesticos);
        radioBotonTecnologia = findViewById(R.id.radioButtonTecnologia);
        radioBotonDecoracion = findViewById(R.id.radioButtonDecoracion);

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

        if (radioBotonCocina.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_cocina);
        }
        if (radioBotonLimpieza.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_limpieza);
        }
        if (radioBotonMuebles.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_muebles);
        }
        if (radioBotonJardin.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_jardin);
        }
        if (radioBotonElectrodomesticos.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_electrodomesticos);
        }
        if (radioBotonTecnologia.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_tecnologia);
        }
        if (radioBotonDecoracion.isChecked()) {
            textoRadioBotonEleccion = getString(R.string.categoria_decoracion);
        }

    }
}