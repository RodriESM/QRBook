package com.example.qrbookapp.Class;

import android.app.Activity;

import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.util.ArrayList;

public class AccesoFichero {

    public boolean archivoExisteEntreFicheros(String [] archivos, String nombreArchivoBuscar){
        boolean encontrado=false;
        for(int i=0;i<archivos.length;i++){
            if(nombreArchivoBuscar.equals(archivos[i])){
                encontrado=true;
                break;
            }
        }

        return encontrado;
    }


}
