using MySql.Data.MySqlClient;
using System;
using System.Collections.Generic;
using System.Data;
using System.Linq;
using System.Web;

namespace QRBookWeb.assets.cs
    {
    public class Conexion
        {

        MySqlConnection DBCon;
        DataSet datos = new DataSet();
        MySqlDataAdapter adapter = new MySqlDataAdapter();

        public void CONECTAR()
            {
            DBCon=new MySqlConnection();
            DBCon.ConnectionString= "Server=35.195.6.185; Database=strong-minutia-271120:europe-west1:qr-book-r31; Uid=user; Pwd=QrBo0o0/< ; CertificateFile = @client.pfx ; CACertificateFile = server-ca.pem; SslMode = MySqlSslMode.VerifyCA";
            DBCon.Open();
            }

        public void CERRAR()
        {
            DBCon.Close();
        }
        /*
        public void BtnObtenerLibros_Click(object sender, EventArgs e)
            {
            MySqlCommand command;
            command=new MySqlCommand();

            command.Connection=DBCon;
            command=DBCon.CreateCommand();
            command.CommandText="select * from Libros ";
            adapter.SelectCommand=command;
            adapter.FillSchema(datos, SchemaType.Mapped, "Libros");
            adapter.Fill(datos, "Libros");
            DBCon.Close();

            DataTable tablagv1 = datos.Tables["Libros"];
            dgvMostrar.DataSource=tablagv1;

            }*/

        }
    }