package com.example.qrbookapp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.qrbookapp.Class.AccesoFichero;
import com.example.qrbookapp.Class.Usuario;
import com.example.qrbookapp.Database.ConnectionClass;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class PerfilUsuario extends AppCompatActivity {

     EditText etUsuarioCam, etNombre, etApellido1, etApellido2, etContrasena, etRepiteContrasena, etAntiguaContrasena;
     ImageButton ibUsuario;
     Button btnRealizarCambios;
     ArrayList<String> contenidoFicheroRecordado = new ArrayList<>();
     AccesoFichero accesoFichero = new AccesoFichero();
     String correo;
     String contrasenaRecordada;
     String antiguoUsuario;
     Usuario usuario;
     final int imagen_request = 0;
     Bitmap bitmap;
     byte[] imagenByte;

    @SuppressLint("WrongThread")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil_usuario);

        etUsuarioCam = findViewById(R.id.etUsuarioCam);
        etNombre = findViewById(R.id.etNombre);
        etApellido1 = findViewById(R.id.etApellido1);
        etApellido2 = findViewById(R.id.etApellido2);
        etContrasena = findViewById(R.id.etContrasena);
        etRepiteContrasena = findViewById(R.id.etRepiteContrasena);
        etAntiguaContrasena = findViewById(R.id.etAntiguaContrasena);
        ibUsuario = findViewById(R.id.ibUsuario);
        btnRealizarCambios = findViewById(R.id.btnRealizarCambios);

        final String[] datos = fileList();
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
                contrasenaRecordada = contenidoFicheroRecordado.get(1);
            }
        }

        try {

            Connection connection = ConnectionClass.con;


            //A partir de un resulset obtenemos los datos de la consulta lanzada a la base de datos
            ResultSet rs = connection.createStatement().executeQuery("select * from USUARIO where correo like '" + correo + "'");

            //recorremos todos los usuarios que tenemos en la ase de datos y los introducimos en el array
            while (rs.next()) {
                usuario = new Usuario(correo, rs.getString(2), contrasenaRecordada, rs.getString(4), rs.getString(5), rs.getString(6), rs.getBytes(7));
            }

        } catch (Exception e) {
            e.getStackTrace();
        } finally {
            antiguoUsuario = usuario.getUsuario();
        }

        etUsuarioCam.setText(usuario.getUsuario());
        etNombre.setText(usuario.getNombre());
        etApellido1.setText(usuario.getApellido1());
        etApellido2.setText(usuario.getApellido2());
        //etContrasena.setText(usuario.getPassword());
        //etRepiteContrasena.setText(usuario.getPassword());

        if (usuario.getUrl() == null) {
            ibUsuario.setImageResource(R.drawable.perfil_user);

        } else {
            //TO_DO Mirar que la imagen no sea null porque si no salta error
            Bitmap bmp = BitmapFactory.decodeByteArray(usuario.getUrl(), 0, usuario.getUrl().length);
            ibUsuario.setImageBitmap(Bitmap.createScaledBitmap(bmp, 400, 390, false));

            //Ponemos la imagen.
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            bmp.compress(Bitmap.CompressFormat.JPEG, 50, baos);
            imagenByte = baos.toByteArray();
        }

        ibUsuario.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, imagen_request);
            }
        });

        btnRealizarCambios.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String usuario = etUsuarioCam.getText().toString();
                String nombre = etNombre.getText().toString();
                String apellido1 = etApellido1.getText().toString();
                String apellido2 = etApellido2.getText().toString();
                String contrasena = etContrasena.getText().toString();
                String repContrasena = etRepiteContrasena.getText().toString();

                if (etAntiguaContrasena.getText().toString().equals("") && contrasena.equals("") && repContrasena.equals("")) {
                    try {
                        Connection connection = ConnectionClass.con;
                        PreparedStatement ps;

                        if (antiguoUsuario.equals(usuario)) {
                            ps = connection.prepareStatement("update USUARIO set nombre=?,apellido1=?,apellido2=?, foto=? where correo=?");

                            ps.setString(1, nombre);
                            ps.setString(2, apellido1);
                            ps.setString(3, apellido2);
                            ps.setBytes(4, imagenByte);
                            ps.setString(5, correo);

                            ps.executeUpdate();
                            finish();

                        } else {
                            ResultSet rs = connection.createStatement().executeQuery("SELECT USUARIO FROM USUARIO WHERE USUARIO like '" + usuario + "'");
                            if (rs.next()) {
                                etUsuarioCam.setError("Nombre de usuario no disponible");

                            } else {
                                ps = connection.prepareStatement("update USUARIO set usuario=?,nombre=?,apellido1=?,apellido2=?, foto=? where correo=?");

                                ps.setString(1, usuario);
                                ps.setString(2, nombre);
                                ps.setString(3, apellido1);
                                ps.setString(4, apellido2);
                                ps.setBytes(5, imagenByte);
                                ps.setString(6, correo);

                                ps.executeUpdate();
                                finish();

                            }
                        }
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                } else if (!etAntiguaContrasena.getText().toString().equals(contrasenaRecordada) && !etAntiguaContrasena.getText().toString().equals("")) {
                    etContrasena.setError("La contraseña introducida no coincide con su antigua contraseña.");
                } else if (contrasena.length() < 8) {
                    etContrasena.setError("La contraseña debe tener mínimo 8 carácteres.");
                } else if (!contrasena.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[a-zA-Z\\d]{8,}$")) {
                    etContrasena.setError("La contraseña debe contener una letra mayúscula, minúscula y un número.");
                } else if (!contrasena.equals(repContrasena)) { //Pass: ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$
                    etRepiteContrasena.setError("Las nuevas contraseñas no coinciden. Intentelo de nuevo.");
                } else {
                    try {
                        Connection connection = ConnectionClass.con;
                        PreparedStatement ps;
                        if (antiguoUsuario.equals(usuario)) {
                            ps = connection.prepareStatement("update USUARIO set nombre=?,password=MD5(?),apellido1=?,apellido2=?, foto=? where correo=?");

                            ps.setString(1, nombre);
                            ps.setString(2, contrasena);
                            ps.setString(3, apellido1);
                            ps.setString(4, apellido2);
                            ps.setBytes(5, imagenByte);
                            ps.setString(6, correo);

                            ps.executeUpdate();

                            EscribirEnFichero(correo, contrasena);
                            finish();

                        } else {
                            ResultSet rs = connection.createStatement().executeQuery("SELECT USUARIO FROM USUARIO WHERE USUARIO like '" + usuario + "'");
                            if (rs.next()) {
                                etUsuarioCam.setError("Nombre de usuario no disponible");
                            } else {
                                ps = connection.prepareStatement("update USUARIO set usuario=?,password=MD5(?),nombre=?,apellido1=?,apellido2=?, foto=? where correo=?");

                                ps.setString(1, usuario);
                                ps.setString(2, contrasena);
                                ps.setString(3, nombre);
                                ps.setString(4, apellido1);
                                ps.setString(5, apellido2);
                                ps.setBytes(6, imagenByte);
                                ps.setString(7, correo);

                                ps.executeUpdate();

                                EscribirEnFichero(correo, contrasena);
                                finish();

                            }
                        }

                    } catch (SQLException | IOException e) {
                        e.printStackTrace();
                    }
                }

            }
        });

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK)) {
            super.finish();
        }
        return super.onKeyDown(keyCode, event);

    }

    public void EscribirEnFichero(String correo, String contrasena) throws IOException {
        OutputStreamWriter osw = new OutputStreamWriter(openFileOutput("user.txt", Activity.MODE_PRIVATE));
        osw.write(correo + "\n" + contrasena);
        osw.flush();
        osw.close();

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == imagen_request && resultCode == RESULT_OK && data != null) {
            Uri ruta = data.getData();
            try {
                bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), ruta);
                ibUsuario.setImageBitmap(bitmap);
                ByteArrayOutputStream baos = new ByteArrayOutputStream();
                bitmap.compress(Bitmap.CompressFormat.JPEG, 50, baos);
                imagenByte = baos.toByteArray();

            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }


}
