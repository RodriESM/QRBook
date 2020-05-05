package com.example.qrbookapp;

import java.io.Serializable;
import java.net.URL;

public class QR implements Serializable {

    private URL url;
    private String isbn;
    private String tipo;
    private String nombre;
    private String Descripcion;

    public QR(URL url, String isbn, String tipo, String nombre, String descripcion) {
        this.url = url;
        this.isbn = isbn;
        this.tipo = tipo;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    public QR(URL url, String isbn, String nombre, String descripcion) {
        this.url = url;
        this.isbn = isbn;
        this.nombre = nombre;
        Descripcion = descripcion;
    }


    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return Descripcion;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
