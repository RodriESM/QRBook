package com.example.qrbookapp.Class;

public class AccesoFichero {

    public boolean archivoExisteEntreFicheros(String[] archivos, String nombreArchivoBuscar) {
        boolean encontrado = false;
        for (String archivo : archivos) {
            if (nombreArchivoBuscar.equals(archivo)) {
                encontrado = true;
                break;
            }
        }

        return encontrado;
    }

}
