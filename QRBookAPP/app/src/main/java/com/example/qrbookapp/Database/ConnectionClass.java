package com.example.qrbookapp.Database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionClass {
    public static Connection con;

    public void setConnection() {
        try {
            System.setProperty("javax.net.ssl.trustStore", "path_to_truststore_file");
            System.setProperty("javax.net.ssl.trustStorePassword", "user");
            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);
            String ip = "35.195.6.185:3306";//Ip de la base de datos
            String connURL = "jdbc:mysql://" + ip + "/QrBook";

            //Registramos la clase
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Creamos la conexión
            con = DriverManager.getConnection(connURL, "user", "user");

            Log.e("ASK", "Connection Called");
        } catch (Exception e) {
            Log.e("ASK", "EXCEPTION" + e.getMessage());
        }
    }

    public void closeConnection() {
        try {
            con.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
