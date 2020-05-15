package com.example.qrbookapp.Class;

import java.io.Serializable;
import java.net.URL;

public class Usuario implements Serializable {

    private String correo;
    private String usuario;
    private String password;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private URL url;

    public Usuario(String correo, String usuario, String password) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
    }

    public Usuario(String correo, String usuario, String password, String nombre, String apellido1, String apellido2, URL url) {
        this.correo = correo;
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.url = url;
    }

    public Usuario(String usuario, String password, String nombre, String apellido1, String apellido2) {
        this.usuario = usuario;
        this.password = password;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
    }

    public Usuario(String correo, URL url) {
        this.correo = correo;
        this.url = url;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public void setApellido1(String apellido1) {
        this.apellido1 = apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public void setApellido2(String apellido2) {
        this.apellido2 = apellido2;
    }

    public URL getUrl() {
        return url;
    }

    public void setUrl(URL url) {
        this.url = url;
    }
}
