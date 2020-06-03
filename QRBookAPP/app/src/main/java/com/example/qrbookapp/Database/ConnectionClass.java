package com.example.qrbookapp.Database;

import android.os.StrictMode;
import android.util.Log;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class ConnectionClass {
    private static final String USER ="user" ;
    private static final String PASSWORD ="user" ;

    public static Connection con;
    Properties properties =new Properties();

    public void setConnection() {
        try {

            // Setting the Username, Password
            properties.put("user", USER);
            properties.put("password", PASSWORD);

            // Setting the truststore
            properties.put("trustCertificateKeyStoreUrl","truststore");
            properties.put("trustCertificateKeyStorePassword", "mypassword");


            // Setting the keystore
            properties.put("clientCertificateKeyStoreUrl", "keystore");
            properties.put("clientCertificateKeyStorePassword", "mypassword");

            StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
            StrictMode.setThreadPolicy(policy);

            String ip = "35.195.6.185:3306";//Ip de la base de datos
            String connURL = "jdbc:mysql://" + ip + "/QrBook?autoReconnect=true&?verifyServerCertificate=true";

            //Registramos la clase
            Class.forName("com.mysql.jdbc.Driver").newInstance();

            //Creamos la conexión
            con = DriverManager.getConnection(connURL, USER, PASSWORD);
            con.setClientInfo(properties);

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
