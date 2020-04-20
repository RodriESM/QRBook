package com.example.qrbookapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;
import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.TextView;
import android.widget.Toast;
import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;

import java.io.IOException;


public class main_escaner extends AppCompatActivity {

    private final int requestCodeCameraPermission=10001;
    private CameraSource cameraSource;
    private BarcodeDetector detector;
    private SurfaceView cameraSurfaceView;
    private TextView textScanResult;
    private String lastUrl="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_escaner);
        cameraSurfaceView=findViewById(R.id.cameraSurfaceView);
        textScanResult=findViewById(R.id.textScanResult);

        if(ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA)!=PackageManager.PERMISSION_GRANTED
        ){
            askForCameraPermission();
        }else{
            setupConstrols();
        }

    }

    private void setupConstrols(){
        detector= new BarcodeDetector.Builder(this).build();
        cameraSource=new CameraSource.Builder(this,detector).setAutoFocusEnabled(true).build();
        cameraSurfaceView.getHolder().addCallback(surfaceCallBack);
        detector.setProcessor(processor);
    }

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
        if (requestCode==requestCodeCameraPermission && grantResults!=null){
            if (grantResults[0]==PackageManager.PERMISSION_GRANTED){
                setupConstrols();
            }else {
                Toast.makeText(getApplicationContext(),"Permisos denegados",Toast.LENGTH_SHORT).show();
            }
        }
    }

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


    private Detector.Processor processor= new Detector.Processor<Barcode>() {
        @Override
        public void release() {

        }

        @Override
        public void receiveDetections(Detector.Detections<Barcode> detections) {

            if(detections !=null && detections.getDetectedItems().size()!=0){
               SparseArray<Barcode> qrCodes=detections.getDetectedItems();
               Barcode code=qrCodes.valueAt(0);
               if((code.displayValue.contains("http")||code.displayValue.contains("https")) && !code.displayValue.contentEquals(lastUrl)){
                   lastUrl=code.displayValue;
                   Uri uri=Uri.parse(code.displayValue);
                   Intent i=new Intent(Intent.ACTION_VIEW,uri);
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
}
