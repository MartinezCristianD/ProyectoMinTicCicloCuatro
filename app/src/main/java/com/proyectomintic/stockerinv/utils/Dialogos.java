package com.proyectomintic.stockerinv.utils;

import android.content.Context;

import androidx.appcompat.app.AlertDialog;

public class Dialogos {

    public static void mensajePersonalizadoDialogo(Context context, String titulo, String mensaje) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(titulo)
                .setMessage(mensaje);
        builder.create();
        builder.show();

    }

}
