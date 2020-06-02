package com.example.qrbookapp;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.KeyEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Database.ConnectionClass;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class Escaner extends AppCompatActivity {

    //Forzamos a que nos solicite el permiso de la camara
    private final int requestCodeCameraPermission = 10001;
    //Creamos el objeto de creación de la cámara
    private CameraSource cameraSource;
    //Creamos el objeto que detecta el código de barras
    private BarcodeDetector detector;
    //Creamos la zona de detección del código
    private SurfaceView cameraSurfaceView;
    //TextView donde mostramos la URL o Texto
    private TextView textScanResult;
    //Url para comprobar que no es la misma url que la anterior y no abrir demasiadas veces la actividad.
    private String lastUrl = "";

    ArrayList<String> contenidoFicheroRecordado = new ArrayList<>();
    AccesoFichero accesoFichero = new AccesoFichero();
    String correo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_escaner);
        cameraSurfaceView = findViewById(R.id.cameraSurfaceView);
        textScanResult = findViewById(R.id.textScanResult);


        //Solicitud de permisos
        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED
        ) {
            askForCameraPermission();
        } else {
            setupConstrols();
        }

    }

    //Iniciamos todos los controles de la cámara
    private void setupConstrols() {
        detector = new BarcodeDetector.Builder(this).build();
        cameraSource = new CameraSource.Builder(this, detector).setAutoFocusEnabled(true).build();
        cameraSurfaceView.getHolder().addCallback(surfaceCallBack);
        detector.setProcessor(processor);
    }

    //Solicitamos los permisos de la cámara
    private void askForCameraPermission() {
        String[] permisos = {Manifest.permission.CAMERA};
        ActivityCompat.requestPermissions(
                this,
                permisos,
                requestCodeCameraPermission
        );
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {

        super.onRequestPermissionsResult(requestCode, permissions, grantResults);

        //En el caso de que ya esten concedidos los permisos, iniciamos los controles directamente
        if (requestCode == requestCodeCameraPermission && grantResults != null) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                setupConstrols();
            } else { //En el caso de que los hayamos denegado, no nos dejará acceder mostrando un mensaje.
                Toast.makeText(getApplicationContext(), "Permisos denegados", Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Muestra al usuario información sobre cambios en la deteccion de cambios de la cámara.
    private SurfaceHolder.Callback surfaceCallBack = new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try {
                cameraSource.start(holder);
            } catch (Exception e) {
                Toast.makeText(getApplicationContext(), "Algo ha ido mal", Toast.LENGTH_SHORT).show();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {


        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            cameraSource.stop();
        }
    };


    //Es un interfaz para detectar el postproceso a ejecutar tras detectar un cambio en el código de barras.
    //An instance of a processor is associated with the detector via the setProcessor(Detector.Processor) method.
    private Detector.Processor processor = new Detector.Processor<Barcode>() {
        @Override
        public void release() {

        }

        @Override
        //Método al que s ele pasa una clase Detector con una colección de items detectados.
        public void receiveDetections(Detector.Detections<Barcode> detections) {

            Bundle bundle = getIntent().getExtras();
            assert bundle != null;
            String isbn = bundle.getString("escanerisbn");

            Connection connection = ConnectionClass.con;

            final String[]  datos= fileList();
            final String nombreFicheroRecordatorio = "user.txt";


            if (accesoFichero.archivoExisteEntreFicheros(datos, nombreFicheroRecordatorio)) {

                try {
                    InputStreamReader isr = new InputStreamReader(openFileInput(nombreFicheroRecordatorio));
                    BufferedReader br = new BufferedReader(isr);
                    String linea = br.readLine();

                    //Introducimos los datos en un array recorriendo cada linea del fichero, en la primera linea tendrá el usuario y en la segunda la contraseña
                    while (linea != null) {
                        contenidoFicheroRecordado.add(linea);
                        linea = br.readLine();
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    correo = contenidoFicheroRecordado.get(0);
                }
            }


            if (detections != null && detections.getDetectedItems().size() != 0) {
                SparseArray<Barcode> qrCodes = detections.getDetectedItems();
                Barcode code = qrCodes.valueAt(0);
                if ((code.displayValue.contains("http") || code.displayValue.contains("https")) && !code.displayValue.contentEquals(lastUrl)) {
                    lastUrl = code.displayValue;
                    ResultSet rs;
                    ResultSet rsgeneral;

                    try {
                        //Revisamos todos los qr de la base de datos.
                        rsgeneral = connection.createStatement().executeQuery("select * from QR where URL like'" + code.displayValue + "' and ISBN like '" + isbn + "'");

                        //Si encuentra el QR, lo muestra.
                        if (rsgeneral.next()) {

                            Intent i = new Intent(Escaner.this, EscanerVista.class);
                            i.putExtra("url", code.displayValue);
                            startActivity(i);

                        } else {

                            try {
                                //Miramos que el qr no esté registrado para no crear duplicados en nuestro contenedor de qr
                                rs = connection.createStatement().executeQuery("SELECT * FROM USUARIOQR where URL like '" + code.displayValue + "' and CORREO like '" + correo + "' and ISBN like '" + isbn + "'");

                                //Recorremos todos lo libros que tenemos en la base de datos y los introducimos en el array
                                if (rs.next()) {
                                    Intent i = new Intent(Escaner.this, EscanerVista.class);
                                    i.putExtra("url", code.displayValue);
                                    startActivity(i);

                                } else {
//
//                                   /*
//                                    Si queremos abrirla en el navegador por defecto usariamos este código o Script
//                                    Uri uri=Uri.parse(code.displayValue);
//                                    Intent i=new Intent(Intent.ACTION_VIEW,uri);*/
//                                    //Al querer abrirlo en otra actividad, pasamos los datos a la otra actividad de esta forma
//
//
//                                    PreparedStatement ps = connection.prepareStatement("INSERT INTO USUARIOQR(CORREO,URL,ISBN) values(?,?,?)");
//                                    ps.setString(1, correo);
//                                    ps.setString(2, code.displayValue);
//                                    ps.setString(3,isbn);
//                                    ps.executeUpdate();

                                    Intent i = new Intent(Escaner.this, AnadirQrUsuario.class);
                                    i.putExtra("url", code.displayValue);
                                    i.putExtra("correo", correo);
                                    i.putExtra("isbn", isbn);
                                    startActivity(i);

                                }

                            } catch (SQLException e) {
                                e.printStackTrace();
                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
//Revisar si nos hiciera falta...
                }
            } else {
                lastUrl = "";
                textScanResult.setText("");
            }

        }

    };

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);
    }
}
