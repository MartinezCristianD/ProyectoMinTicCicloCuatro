package com.proyectomintic.stockerinv.views;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.proyectomintic.stockerinv.R;

public class InventarioActivity extends AppCompatActivity {
    BottomNavigationView btnNavigation ;
    Button btnExportar;
    Button btnHome;
    Button btnAgregar;
    Button btnRuta;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_inventario);

        btnNavigation = findViewById(R.id.bottom_navigation);
        btnExportar = findViewById(R.id.page_to_export);
        btnHome = findViewById(R.id.page_to_home);
        btnAgregar = findViewById(R.id.page_to_add);
        btnRuta = findViewById(R.id.page_to_ruta);

       /* btnNavigation.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener()) {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.page_to_export:
                        Intent intent = new Intent(InventarioActivity.this, InventarioActivity.class);
                        startActivity(intent);
                        break;
                    case R.id.page_to_home:
                        Intent intent_a = new Intent(InventarioActivity.this, MainActivity.class);
                        startActivity(intent_a);
                        break;
                    case R.id.page_to_add:
                        Intent intent_b = new Intent(InventarioActivity.this, CategoriasActivity.class);
                        startActivity(intent_b);
                        break;
                    case R.id.page_to_ruta:
                        Intent intent_c = new Intent(InventarioActivity.this, RutaActivity.class);
                        startActivity(intent_c);
                        break;
                }
                return true;
            }
        }
*/

        }
    }

