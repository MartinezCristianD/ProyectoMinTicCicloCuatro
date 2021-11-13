package com.proyectomintic.stockerinv.views;


import android.Manifest;
import android.annotation.SuppressLint;
import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.FragmentRutaBinding;
import com.proyectomintic.stockerinv.utils.Dialogos;
import com.proyectomintic.stockerinv.utils.PermisosFragment;

import java.util.ArrayList;


public class RutaFragment extends PermisosFragment implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";
    FragmentRutaBinding binding;
    String eleccionOrigen, eleccionDestino;
    Bundle eleccionRuta;
    private MapView mapView;
    private GoogleMap gmap;

    ArrayList<String> permisoRequerido = new ArrayList<String>() {
        {
            add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // como se busca la distancia  entre dos puntos
        Location pereira = new Location("");
        pereira.setLatitude(4.81321);
        pereira.setLongitude(-75.6946);
        Location medellin = new Location("");
        medellin.setLatitude(6.2518);
        medellin.setLongitude(-75.5636);
        float distancia = pereira.distanceTo(medellin) / 1000;// esta distancia hay que dividirla entre 1000 para que den kilometros

        binding.textViewDistancia.setText(String.valueOf(distancia));

        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAP_VIEW_BUNDLE_KEY);
        }
        // google maps
        mapView = binding.mapView;
        mapView.onCreate(mapViewBundle);
        mapView.getMapAsync(this);

        // Llenando la lista  para seleccionar  la ciudad de origen
        binding.inputOrigenText.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, getResources().getStringArray(R.array.ciudades)));
        binding.inputDestinoText.setAdapter(new ArrayAdapter<>(getContext(), android.R.layout.select_dialog_item, getResources().getStringArray(R.array.ciudades)));

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
                Dialogos.mensajePersonalizadoDialogo(requireContext(), getString(R.string.titulo_campo_vacio), getString(R.string.mensaje_campo_vacio));

            } else {
                // Pasar al InventarioActivity
                Intent i = new Intent(requireContext(), InventarioActivity.class);

                //pasar datos a la activity

                i.putExtra(ORIGEN, eleccionOrigen);
                i.putExtra(DESTINO, eleccionDestino);

                //para provar el envio de informacion

                Toast.makeText(requireContext(), eleccionDestino, Toast.LENGTH_SHORT).show();
                Toast.makeText(requireContext(), eleccionOrigen, Toast.LENGTH_SHORT).show();

                //Iniciar  InventariActividad
                startActivity(i);
            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = FragmentRutaBinding.inflate(inflater);

        return binding.getRoot();
        // Gets the MapView from the XML layout and creates it
    }

    @Override
    public void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);

        Bundle mapViewBundle = outState.getBundle(MAP_VIEW_BUNDLE_KEY);
        if (mapViewBundle == null) {
            mapViewBundle = new Bundle();
            outState.putBundle(MAP_VIEW_BUNDLE_KEY, mapViewBundle);
        }

        mapView.onSaveInstanceState(mapViewBundle);
    }

    @Override
    public void onResume() {
        super.onResume();
        mapView.onResume();
    }

    @Override
    public void onStart() {
        super.onStart();
        mapView.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
        mapView.onStop();
    }

    @Override
    public void onPause() {
        mapView.onPause();
        super.onPause();
    }

    @Override
    public void onDestroy() {
        mapView.onDestroy();
        super.onDestroy();
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        mapView.onLowMemory();
    }

    // Configuraion de google maps
    @SuppressLint("MissingPermission")
    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        gmap = googleMap;
        gmap.setMinZoomPreference(12);
        LatLng pereira = new LatLng(4.81321, -75.6946);
        gmap.moveCamera(CameraUpdateFactory.newLatLng(pereira));

        verificarPermisos(permisoRequerido, (otorgados, noOtorgados) -> {
            if (otorgados.contains(Manifest.permission.ACCESS_FINE_LOCATION)) {
                gmap.setMyLocationEnabled(true);
            }
        });

        UiSettings uiSettings = gmap.getUiSettings();

        uiSettings.setIndoorLevelPickerEnabled(true);
        uiSettings.setMyLocationButtonEnabled(true);
        uiSettings.setMapToolbarEnabled(true);
        uiSettings.setCompassEnabled(true);
        uiSettings.setZoomControlsEnabled(true);

        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(pereira);
        gmap.addMarker(markerOptions);

    }

}