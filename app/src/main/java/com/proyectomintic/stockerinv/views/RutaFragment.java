package com.proyectomintic.stockerinv.views;

import android.Manifest;
import android.annotation.SuppressLint;
import android.location.Location;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.UiSettings;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;
import com.proyectomintic.stockerinv.R;
import com.proyectomintic.stockerinv.databinding.FragmentRutaBinding;
import com.proyectomintic.stockerinv.utils.Dialogos;
import com.proyectomintic.stockerinv.utils.PermisosFragment;

import java.util.ArrayList;

public class RutaFragment extends PermisosFragment implements OnMapReadyCallback {

    private static final String MAP_VIEW_BUNDLE_KEY = "MapViewBundleKey";
    public static final String ORIGEN = "origen";
    public static final String DESTINO = "destino";
    public static final String FRAGMENT_RUTA_RESULT_KEY = "FRAGMENT_RUTA_RESULT_KEY";
    FragmentRutaBinding binding;
    String eleccionOrigen, eleccionDestino;
    private MapView mapView;
    private GoogleMap gmap;
    Double origenLat, origenLon, destinoLat, destinoLon;

    ArrayList<String> permisoRequerido = new ArrayList<String>() {
        {
            add(Manifest.permission.ACCESS_FINE_LOCATION);

        }
    };

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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

        //Latitudes y longitudesde las ciudades
        eleccionOrigen = binding.inputOrigenText.getText().toString();
        eleccionDestino = binding.inputDestinoText.getText().toString();

        //Evento click del boton continuar
        binding.buttonContinuar.setOnClickListener(v -> {

            // Obteniendo el string del EditText
            eleccionOrigen = binding.inputOrigenText.getText().toString();
            eleccionDestino = binding.inputDestinoText.getText().toString();


            if (TextUtils.isEmpty(eleccionOrigen) || TextUtils.isEmpty(eleccionDestino)) {

                // Creando dialogo de alerta
                Dialogos.mensajePersonalizadoDialogo(requireContext(), getString(R.string.titulo_campo_vacio), getString(R.string.mensaje_campo_vacio));

            } else {
                Bundle i = new Bundle();
                i.putString(ORIGEN, eleccionOrigen);
                i.putString(DESTINO, eleccionDestino);

                getParentFragmentManager().setFragmentResult(FRAGMENT_RUTA_RESULT_KEY, i);
                dismiss();

            }
        });

        // Evento click del boton Verificar
        binding.btnVerificarRuta.setOnClickListener(v -> {
            gmap.clear();

            eleccionOrigen = binding.inputOrigenText.getText().toString();
            eleccionDestino = binding.inputDestinoText.getText().toString();

            switch (eleccionOrigen) {
                case "Arauca":
                    origenLat = 7.083;
                    origenLon = -70.757;
                    break;
                case "Armenia":
                    origenLat = 40.069099;
                    origenLon = -75.68111;
                    break;
                case "Barranquilla":
                    origenLat = 10.96854;
                    origenLon = -74.78132;
                    break;
                case "Bogotá":
                    origenLat = 4.60971;
                    origenLon = -74.08175;
                    break;
                case "Bucaramanga":
                    origenLat = 7.12539;
                    origenLon = -73.1198;
                    break;
                case "Cali":
                    origenLat = 3.43722;
                    origenLon = -76.5225;
                    break;
                case "Cartagena":
                    origenLat = 10.39972;
                    origenLon = -75.51444;
                    break;
                case "Florencia":
                    origenLat = 1.61389;
                    origenLon = -75.6128;
                    break;
                case "Ibague":
                    origenLat = 4.43889;
                    origenLon = -75.23222;
                    break;
                case "Inírida":
                    origenLat = 3.867;
                    origenLon = -67.917;
                    break;
                case "Leticia":
                    origenLat = -4.21528;
                    origenLon = -69.94056;
                    break;
                case "Manizales":
                    origenLat = 5.06889;
                    origenLon = -75.51738;
                    break;
                case "Medellin":
                    origenLat = 6.25184;
                    origenLon = -75.56359;
                    break;
                case "Mitu":
                    origenLat = 1.25509;
                    origenLon = -70.235;
                    break;
                case "Mocoa":
                    origenLat = 1.15284;
                    origenLon = -76.65208;
                    break;
                case "Montería":
                    origenLat = 8.74798;
                    origenLon = -75.88143;
                    break;
                case "Neiva":
                    origenLat = 2.9273;
                    origenLon = -75.28189;
                    break;
                case "Pereira":
                    origenLat = 4.81333;
                    origenLon = -75.69611;
                    break;
                case "Popayán":
                    origenLat = 2.43823;
                    origenLon = -76.61316;
                    break;
                case "Puerto Carreño":
                    origenLat = 6.18493;
                    origenLon = -67.4894;
                    break;
                case "Quibdó":
                    origenLat = 5.683;
                    origenLon = -76.65;
                    break;
                case "Riohacha":
                    origenLat = 11.54444;
                    origenLon = -72.90722;
                    break;
                case "San Andrés":
                    origenLat = 12.58317;
                    origenLon = -81.70636;
                    break;
                case "Cúcuta":
                    origenLat = 7.89391;
                    origenLon = -72.50782;
                    break;
                case "Guaviare":
                    origenLat = 2.567;
                    origenLon = -72.633;
                    break;
                case "Pasto":
                    origenLat = 1.2;
                    origenLon = -77.267;
                    break;
                case "Santa Marta":
                    origenLat = 11.24079;
                    origenLon = -74.19904;
                    break;
                case "Sincelejo":
                    origenLat = 9.30472;
                    origenLon = -75.39778;
                    break;
                case "Tunja":
                    origenLat = 5.53528;
                    origenLon = -73.36778;
                    break;
                case "Valledupar":
                    origenLat = 10.46314;
                    origenLon = -73.25322;
                    break;
                case "Villavicencio":
                    origenLat = 4.15;
                    origenLon = -73.633;
                    break;
                case "Yopal":
                    origenLat = 5.33775;
                    origenLon = -72.39586;
                    break;
                default:
                    origenLat = 4.81333;
                    origenLon = -75.69611;
                    break;
            }

            switch (eleccionDestino) {
                case "Arauca":
                    destinoLat = 7.083;
                    destinoLon = -70.757;
                    break;
                case "Armenia":
                    destinoLat = 40.069099;
                    destinoLon = -75.68111;
                    break;
                case "Barranquilla":
                    destinoLat = 10.96854;
                    destinoLon = -74.78132;
                    break;
                case "Bogotá":
                    destinoLat = 4.60971;
                    destinoLon = -74.08175;
                    break;
                case "Bucaramanga":
                    destinoLat = 7.12539;
                    destinoLon = -73.1198;
                    break;
                case "Cali":
                    destinoLat = 3.43722;
                    destinoLon = -76.5225;
                    break;
                case "Cartagena":
                    destinoLat = 10.39972;
                    destinoLon = -75.51444;
                    break;
                case "Florencia":
                    destinoLat = 1.61389;
                    destinoLon = -75.6128;
                    break;
                case "Ibague":
                    destinoLat = 4.43889;
                    destinoLon = -75.23222;
                    break;
                case "Inírida":
                    destinoLat = 3.867;
                    destinoLon = -67.917;
                    break;
                case "Leticia":
                    destinoLat = -4.21528;
                    destinoLon = -69.94056;
                    break;
                case "Manizales":
                    destinoLat = 5.06889;
                    destinoLon = -75.51738;
                    break;
                case "Medellin":
                    destinoLat = 6.25184;
                    destinoLon = -75.56359;
                    break;
                case "Mitu":
                    destinoLat = 1.25509;
                    destinoLon = -70.235;
                    break;
                case "Mocoa":
                    destinoLat = 1.15284;
                    destinoLon = -76.65208;
                    break;
                case "Montería":
                    destinoLat = 8.74798;
                    destinoLon = -75.88143;
                    break;
                case "Neiva":
                    destinoLat = 2.9273;
                    destinoLon = -75.28189;
                    break;
                case "Pereira":
                    destinoLat = 4.81333;
                    destinoLon = -75.69611;
                    break;
                case "Popayán":
                    destinoLat = 2.43823;
                    destinoLon = -76.61316;
                    break;
                case "Puerto Carreño":
                    destinoLat = 6.18493;
                    destinoLon = -67.4894;
                    break;
                case "Quibdó":
                    destinoLat = 5.683;
                    destinoLon = -76.65;
                    break;
                case "Riohacha":
                    destinoLat = 11.54444;
                    destinoLon = -72.90722;
                    break;
                case "San Andrés":
                    destinoLat = 12.58317;
                    destinoLon = -81.70636;
                    break;
                case "Cúcuta":
                    destinoLat = 7.89391;
                    destinoLon = -72.50782;
                    break;
                case "Guaviare":
                    destinoLat = 2.567;
                    destinoLon = -72.633;
                    break;
                case "Pasto":
                    destinoLat = 1.2;
                    destinoLon = -77.267;
                    break;
                case "Santa Marta":
                    destinoLat = 11.24079;
                    destinoLon = -74.19904;
                    break;
                case "Sincelejo":
                    destinoLat = 9.30472;
                    destinoLon = -75.39778;
                    break;
                case "Tunja":
                    destinoLat = 5.53528;
                    destinoLon = -73.36778;
                    break;
                case "Valledupar":
                    destinoLat = 10.46314;
                    destinoLon = -73.25322;
                    break;
                case "Villavicencio":
                    destinoLat = 4.15;
                    destinoLon = -73.633;
                    break;
                case "Yopal":
                    destinoLat = 5.33775;
                    destinoLon = -72.39586;
                    break;
                default:
                    destinoLat = 4.83852;
                    destinoLon = -75.6702;
                    break;
            }

            // Buscando la distancia  entre dos puntos

            // Asignando el origen
            Location origen = new Location("");
            origen.setLatitude(origenLat);
            origen.setLongitude(origenLon);

            //Asignando el destino
            Location destino = new Location("");
            destino.setLatitude(destinoLat);
            destino.setLongitude(destinoLon);

            // esta distancia hay que dividirla entre 1000 para que de KM
            int distancia = (int) origen.distanceTo(destino) / 1000;

            //Mostrando la distancia en el textView
            binding.textViewDistancia.setText(distancia + "Km");

            // Linea marcador de ruta
            LatLng origenM = new LatLng(origenLat, origenLon);
            gmap.addMarker(new MarkerOptions().position(origenM).title("Marker in Origen"));

            LatLng destinoM = new LatLng(destinoLat, destinoLon);
            gmap.addMarker(new MarkerOptions().position(destinoM).title("Marker in Destino"));

            Polyline polyline1 = gmap.addPolyline(new PolylineOptions()
                    .clickable(true)
                    .color(R.color.primary)
                    .add(
                            new LatLng(origenLat, origenLon),
                            new LatLng(destinoLat, destinoLon)));
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
        gmap.setMinZoomPreference(4);
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


    }

}