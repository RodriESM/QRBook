using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Microsoft.Owin.Security.Google;

namespace QRBookWeb
{
    public partial class registro : System.Web.UI.Page
    {
        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();
        //Conexion a FireStore
        //FirestoreDb db = FirestoreDb.Create();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void Registro_Click(object sender, EventArgs e)
        {
            if (String.IsNullOrEmpty(usuario.Value) || String.IsNullOrEmpty(correo.Value) || String.IsNullOrEmpty(pass.Value))
            {
                hr.MsgBox("Faltan campos por rellenar", this.Page, this);
            }
            else
            {
                try
                {
                    MySqlConnection DBCon = cs.CONECTAR();
                    string ins = "insert into USUARIO (CORREO, USUARIO, PASSWORD) VALUES ('" + correo.Value + "', '" + usuario.Value + "', '" + pass.Value + "')";
                    MySqlCommand insert = new MySqlCommand(ins, DBCon);
                    insert.ExecuteNonQuery();
                    //hr.MsgBox("Te has registrado!!!!!!!", this.Page, this);

                    //Con QueryStrings, puedes mandar informacion en la propia ruta, y recogerla en la nueva pagina
                    Response.Redirect("/login.aspx?Desde=Registro");

                }
                catch (MySqlException ex)
                {
                    hr.MsgBox("El correo electronico o el nombre de usuario ya existen", this.Page, this);
                }
                finally
                {
                    cs.CERRAR();
                }
            }
        }

        protected void InicioSesion_Click(object sender, EventArgs e)
        {
        }
    }
}