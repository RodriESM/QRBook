package com.example.qrbookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
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
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;




public class main_escaner extends AppCompatActivity {

    //Forzamos a que nos solicite el permiso de la camara
    private final int requestCodeCameraPermission=10001;
    //Creamos el objeto de creación de la cámara
    private CameraSource cameraSource;
    //Creamos el objeto que detecta el código de barras
    private BarcodeDetector detector;
    //Creamos la zona de detección del código
    private SurfaceView cameraSurfaceView;
    //TextView donde mostramos la URL o Texto
    private TextView textScanResult;
    //Url para comprobar que no es la misma url que la anterior y no abrir demasiadas veces la actividad.
    private String lastUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_escaner);
        cameraSurfaceView=findViewById(R.id.cameraSurfaceView);
        textScanResult=findViewById(R.id.textScanResult);

        //Solicitud de permisos
        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED
        ){
            askForCameraPermission();
        }else{
            setupConstrols();
        }

    }

    //Iniciamos todos los controles de la cámara
    private void setupConstrols(){
        detector= new BarcodeDetector.Builder(this).build();
        cameraSource=new CameraSource.Builder(this,detector).setAutoFocusEnabled(true).build();
        cameraSurfaceView.getHolder().addCallback(surfaceCallBack);
        detector.setProcessor(processor);
    }

    //Solicitamos los permisos de la cámara
    private void askForCameraPermission(){
        String[] permisos={Manifest.permission.CAMERA};
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
        if (requestCode==requestCodeCameraPermission && grantResults!=null){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                setupConstrols();
            }else { //En el caso de que los hayamos denegado, no nos dejará acceder mostrando un mensaje.
                Toast.makeText(getApplicationContext(),"Permisos denegados",Toast.LENGTH_SHORT).show();
            }
        }
    }

    //Muestra al usuario información sobre cambios en la deteccion de cambios de la cámara.
    private SurfaceHolder.Callback surfaceCallBack=new SurfaceHolder.Callback() {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            try{
                cameraSource.start(holder);
            }catch (Exception e){
                Toast.makeText(getApplicationContext(),"Algo ha ido mal",Toast.LENGTH_SHORT).show();
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
    private Detector.Processor processor= new Detector.Processor<Barcode>() {
        @Override
        public void release() {

        }

        @Override
        //Método al que s ele pasa una clase Detector con una colección de items detectados.
        public void receiveDetections(Detector.Detections<Barcode> detections) {


            if(detections !=null && detections.getDetectedItems().size()!=0){
               SparseArray<Barcode> qrCodes=detections.getDetectedItems();
               Barcode code=qrCodes.valueAt(0);
               if((code.displayValue.contains("http")||code.displayValue.contains("https")) && !code.displayValue.contentEquals(lastUrl)){
                   lastUrl=code.displayValue;
                  /*
                   Si queremos abrirla en el navegador por defecto usariamos este código o Script
                   Uri uri=Uri.parse(code.displayValue);
                   Intent i=new Intent(Intent.ACTION_VIEW,uri);*/
                  //Al querer abrirlo en otra actividad, pasamos los datos a la otra actividad de esta forma
                  Intent i=new Intent(main_escaner.this,vista_escaner.class);
                  i.putExtra("url",code.displayValue);
                   startActivity(i);
               }else{
                   textScanResult.setText(code.displayValue);
               }
        }else{
                lastUrl="";
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
