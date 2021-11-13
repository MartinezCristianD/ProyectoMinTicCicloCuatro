package com.proyectomintic.stockerinv.utils;

import android.content.pm.PackageManager;

import androidx.core.app.ActivityCompat;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;


public abstract class PermisosFragment extends BottomSheetDialogFragment {

    String[] listaPermisos = new String[]{};

    private Boolean tienePermisos() {
        boolean permisosAccedidos = true;

        for (String listaPermiso : listaPermisos) {

            permisosAccedidos = permisosAccedidos && ActivityCompat.checkSelfPermission(requireContext(), listaPermiso) == PackageManager.PERMISSION_GRANTED;
        }
        return permisosAccedidos;
    }


}
