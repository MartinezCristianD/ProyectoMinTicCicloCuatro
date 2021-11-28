package com.proyectomintic.stockerinv.model;

import android.graphics.Bitmap;
import android.os.Parcel;
import android.os.Parcelable;

public class Elemento implements Parcelable {
    public static final Creator<Elemento> CREATOR = new Creator<Elemento>() {
        @Override
        public Elemento createFromParcel(Parcel in) {
            return new Elemento(in);
        }

        @Override
        public Elemento[] newArray(int size) {
            return new Elemento[size];
        }
    };
    Bitmap imagen;

    public Elemento(String nombre, String categoria, String cantidad, Bitmap imagen) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.imagen = imagen;

    }

    public String nombre, categoria, cantidad;

    protected Elemento(Parcel in) {
        nombre = in.readString();
        categoria = in.readString();
        cantidad = in.readString();
        imagen = in.readParcelable(Bitmap.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(categoria);
        dest.writeString(cantidad);
        dest.writeParcelable(imagen, flags);
    }

    @Override
    public int describeContents() {
        return 0;
    }
}

