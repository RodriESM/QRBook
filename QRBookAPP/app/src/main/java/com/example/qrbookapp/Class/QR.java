package com.example.qrbookapp.Class;

import java.io.Serializable;
import java.net.URL;

public class QR implements Serializable {

    private String url;
    private String isbn;
    private String tipo;
    private String nombre;
    private String Descripcion;
    private String pagina;

    public QR(String url, String isbn, String tipo, String nombre, String descripcion) {
        this.url = url;
        this.isbn = isbn;
        this.tipo = tipo;
        this.nombre = nombre;
        Descripcion = descripcion;
    }

    //He añadido la página para así poder ponerla en un texto y que el usuario pueda ver a qué hace referencia.
    public QR(String url, String isbn, String tipo, String nombre, String descripcion,String pagina) {
        this.url = url;
        this.isbn = isbn;
        this.nombre = nombre;
        this.tipo=tipo;
        this.pagina=pagina;
        Descripcion = descripcion;
    }


    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
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

    public String getPagina() {
        return pagina;
    }

    public void setDescripcion(String descripcion) {
        Descripcion = descripcion;
    }
}
