package com.example.qrbookapp.Class;

import java.io.Serializable;
import java.net.URL;

public class Libro implements Serializable {
    private String isbn;
    private String autor;
    private String titulo;
    private String editorial;
    private String sinopsis;
    private String year;
    private String idioma;
    private String genero;
    private String portada;
    private String PDF;

    public Libro(String isbn, String autor, String titulo, String editorial, String sinopsis, String year, String idioma, String genero, String portada,String PDF) {
        this.isbn = isbn;
        this.autor = autor;
        this.titulo = titulo;
        this.editorial = editorial;
        this.sinopsis = sinopsis;
        this.year = year;
        this.idioma = idioma;
        this.genero = genero;
        this.portada = portada;
        this.PDF=PDF;
    }



    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
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

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public String getPortada() {
        return portada;
    }

    public void setPortada(String portada) {
        this.portada = portada;
    }

    public String getPDF() {
        return PDF;
    }

    public void setPDF(String PDF) {
        this.PDF = PDF;
    }
}
