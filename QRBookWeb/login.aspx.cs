using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb
{
    public partial class login : System.Web.UI.Page {

        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString["Desde"] == "Registro") {
                hr.MsgBox("Te has registrado!!!!!!!", this.Page, this);
            }
        }

        protected void Login_Click(object sender, EventArgs e) {
            if (String.IsNullOrEmpty(user.Value) || String.IsNullOrEmpty(pass.Value)) {
                hr.MsgBox("Faltan campos por rellenar", this.Page, this);
            } else {
                //try {
                MySqlConnection DBCon = cs.CONECTAR();
                string sel = "select * from USUARIO where USUARIO = '" + user.Value + "' and PASSWORD = '" + pass.Value + "'";
                MySqlCommand cmd = new MySqlCommand(sel, DBCon);
                object result = cmd.ExecuteScalar();
                bool encontrado = false;
                //System.Diagnostics.Debug.WriteLine("======== " + result.ToString());

                if (result != null) {
                    encontrado = true;
                } else {
                    sel = "select * from USUARIO where CORREO = '" + user.Value + "' and PASSWORD = '" + pass.Value + "'";
                    cmd = new MySqlCommand(sel, DBCon);
                    result = cmd.ExecuteScalar();

                    if (result != null) {
                        encontrado = true; 
                    }
                }

                if (encontrado) {
                    //Con QueryStrings, puedes mandar informacion en la propia ruta, y recogerla en la nueva pagina
                    Response.Redirect("/index.aspx?Desde=Login");
                } else {
                    hr.MsgBox("El usuario o contraseña son incorrectos.", this.Page, this);
                }

                cs.CERRAR();
            }
        }
    }
}