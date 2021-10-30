package com.proyectomintic.stockerinv;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class ElementosActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_elementos);


        ImageButton botonregresar = findViewById(R.id.imageButton);
        botonregresar.setOnClickListener(v -> {
            Intent intent = new Intent(this, MainActivity.class);
            startActivity(intent);
        });

    }

    @Override
    protected void onStop() {
        super.onStop();
        Toast.makeText(this, "Gracias por Elegir °StockerInv°", Toast.LENGTH_LONG).show();

    }
}