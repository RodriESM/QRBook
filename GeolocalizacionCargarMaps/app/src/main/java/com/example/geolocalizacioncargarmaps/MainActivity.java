package com.example.geolocalizacioncargarmaps;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.os.Looper;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static final String TAG="mi_localizacion";
    private static final long MIN_CAMBIO_DISTANCIA_PARA_UPDATE=10;//10 METROS
    private static final long MIN_CAMBIO_TIEMPO_PARA_UPDATE=1000*10;//10 SEC

    private TextView tvLatitud,tvLongitud,tvAltura,tvPrecision;

    private Location location;
    private LocationManager locationManager;

    private LocationListener locationListener = new LocationListener() {
        @Override
        public void onLocationChanged(Location location) {
            Log.i(TAG,"Latitud: " +location.getLatitude()+" --- Longitud: "+location.getLongitude());
            if(location!=null){
                tvLatitud.setText(String.valueOf(location.getLatitude()));
                tvLongitud.setText(String.valueOf(location.getLongitude()));
                tvAltura.setText(String.valueOf(location.getAltitude()));
                tvPrecision.setText(String.valueOf(location.getAccuracy()));
            }else {
                AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(MainActivity.this);
                alertDialogBuilder.setMessage("Buscando sensor ubicacion...Asegurese de que esta activado y reinicie la app");
                alertDialogBuilder.setCancelable(true);
                alertDialogBuilder.show();
            }

        }

        @Override
        public void onStatusChanged(String provider, int status, Bundle extras) {
            Log.i(TAG,"entra en onStatusChanged()");
        }

        @Override
        public void onProviderEnabled(String provider) {
            Log.i(TAG,"entra en onProviderEnabled()");
        }

        @Override
        public void onProviderDisabled(String provider) {
            Log.i(TAG,"entra en onProviderDisabled()");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tvLatitud=(TextView) findViewById(R.id.tvLatitud);
        tvLongitud=(TextView) findViewById(R.id.tvLongitud);
        tvAltura=(TextView) findViewById(R.id.tvAltura);
        tvPrecision=(TextView) findViewById(R.id.tvPrecision);

        ActivityCompat.requestPermissions(MainActivity.this,new String[]{Manifest.permission.ACCESS_FINE_LOCATION,Manifest.permission.ACCESS_COARSE_LOCATION},100);
        if((ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_FINE_LOCATION)!= PackageManager.PERMISSION_GRANTED)&&
                (ActivityCompat.checkSelfPermission(this,Manifest.permission.ACCESS_COARSE_LOCATION)!= PackageManager.PERMISSION_GRANTED)){
            AlertDialog.Builder alertDialogBuilder= new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("NECESITA INCORPORAR PERMISOS PARA ACCEDER A SENSORES DE UBICACION... VUELVA A ARRANCAR LA APLICION Y CONCEDA PERMISOS");
            alertDialogBuilder.setCancelable(true);
            alertDialogBuilder.show();

        }else{
            locationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

            locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,MIN_CAMBIO_TIEMPO_PARA_UPDATE,MIN_CAMBIO_DISTANCIA_PARA_UPDATE, locationListener, Looper.getMainLooper());
            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,MIN_CAMBIO_TIEMPO_PARA_UPDATE,MIN_CAMBIO_DISTANCIA_PARA_UPDATE, locationListener, Looper.getMainLooper());


        }
    }

    public void cargarMapa(View view) {
        Intent i = new Intent(this,Mapa.class);
        i.putExtra("latitud", Double.parseDouble(tvLatitud.getText().toString()));
        i.putExtra("longitud", Double.parseDouble(tvLongitud.getText().toString()));

        startActivity(i);

    }
}
