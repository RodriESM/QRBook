using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;

namespace QRBookWeb.assets.cs
    {
    public class Conexion
        {

        MySqlConnection DBCon;
        DataSet datos = new DataSet();
        MySqlDataAdapter adapter = new MySqlDataAdapter();

        private void Conectar(object sender, EventArgs e)
            {
            DBCon=new MySqlConnection();
            DBCon.ConnectionString="Server=localhost; Database=TiendaLibros; Uid=root; Pwd=system ; CertificateFile = @C:/Path/To/client.pfx ; CACertificateFile = C:/Path/To/server-ca.pem; SslMode = MySqlSslMode.VerifyCA; ";
            DBCon.Open();
            }

        private void BtnObtenerLibros_Click(object sender, EventArgs e)
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

            }

        }
    }