package com.proyectomintic.stockerinv.views.model;

import android.graphics.Bitmap;

public class Elemento {
    String nombre, categoria, cantidad;
    Bitmap imagen;

    public Elemento(String nombre, String categoria, String cantidad, Bitmap imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;

    }


}

