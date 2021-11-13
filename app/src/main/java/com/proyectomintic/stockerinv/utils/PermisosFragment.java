package com.proyectomintic.stockerinv.utils;

import android.content.pm.PackageManager;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;

import java.util.ArrayList;
import java.util.function.BiConsumer;

public abstract class PermisosFragment extends BottomSheetDialogFragment {

    ArrayList<String> listaPermisos = new ArrayList<>();
    BiConsumer<ArrayList<String>, ArrayList<String>> onPermisionGranted = (n, m) -> {
    };

    private final ActivityResultLauncher lanzadorPermisos = registerForActivityResult(new ActivityResultContracts.RequestMultiplePermissions(), result -> {

        ArrayList<String> permisosOtorgados, permisosNoOtorgados;
        permisosOtorgados = new ArrayList<String>();
        permisosNoOtorgados = new ArrayList<String>();

        result.forEach((permiso, valor) -> {
            if (valor) {
                permisosOtorgados.add(permiso);
            } else {
                permisosNoOtorgados.add(permiso);
            }
        });

        onPermisionGranted.accept(permisosOtorgados, permisosNoOtorgados);
    });

    private Boolean tienePermisos() {
        boolean permisosAccedidos = true;

        for (String listaPermiso : listaPermisos) {
            permisosAccedidos &= ActivityCompat.checkSelfPermission(requireContext(), listaPermiso) == PackageManager.PERMISSION_GRANTED;
        }
        return permisosAccedidos;
    }

    public void verificarPermisos(ArrayList<String> listaPermisos, BiConsumer<ArrayList<String>, ArrayList<String>> onPermisionGranted) {
        this.listaPermisos = listaPermisos;
        this.onPermisionGranted = onPermisionGranted;

        if (!tienePermisos()) {
            String[] lista = new String[listaPermisos.size()];
            lista = listaPermisos.toArray(lista);

            lanzadorPermisos.launch(lista);
        } else {
            onPermisionGranted.accept(listaPermisos, new ArrayList<>());
        }

    }
}
