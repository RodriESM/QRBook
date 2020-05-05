package com.example.qrbookapp;

import java.io.Serializable;
import java.net.URL;

public class Libros implements Serializable {
    private int isbn;
    private String autor;
    private String titulo;
    private String editorial;
    private String sinopsis;
    private int year;
    private URL url;

    //TO_DO realizar una vaiable url que contenddrá la imagen del libro

    public Libros(int isbn, String autor, String titulo, String editorial, String sinopsis, int year) {
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.sinopsis = sinopsis;
        this.year = year;
    }

    public Libros(int isbn, String autor, String titulo, String editorial, String sinopsis, int year, URL url) {
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.sinopsis = sinopsis;
        this.year = year;
        this.url = url;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }

    public int getIsbn() {
        return isbn;
    }

    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
    }

    public String getSinopsis() {
        return sinopsis;
    }

    public void setSinopsis(String sinopsis) {
        this.sinopsis = sinopsis;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }
}
