using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Google.Cloud.Firestore;
using Microsoft.Owin.Security.Google;

namespace QRBookWeb
{
    public partial class registro : System.Web.UI.Page
    {
        Conexion cs = new Conexion();
        //Conexion a FireStore
        //FirestoreDb db = FirestoreDb.Create();

        protected void Page_Load(object sender, EventArgs e)
        {
        }

        protected void Unnamed1_Click(object sender, EventArgs e) {

            if (String.IsNullOrEmpty(usuario.Value) || String.IsNullOrEmpty(correo.Value) || String.IsNullOrEmpty(pass.Value)) {
                MsgBox("Faltan campos por rellenar", this.Page, this);
            } else {
                try {
                    MySqlConnection DBCon = cs.CONECTAR();
                    string ins = "insert into USUARIO (CORREO, USUARIO, PASSWORD) VALUES ('" + correo.Value + "', '" + usuario.Value + "', '" + pass.Value + "')";
                    MySqlCommand insert = new MySqlCommand(ins, DBCon);
                    insert.ExecuteNonQuery();
                    MsgBox("Te has registrado!!!!!!!", this.Page, this);
                } catch (MySqlException ex) {
                    MsgBox("El correo electronico o el nombre de usuario ya existen", this.Page, this);
                } finally {
                    cs.CERRAR();
                }
            }

        }

        public void MsgBox(String ex, Page pg, Object obj)
        {
            string s = "<SCRIPT language='javascript'>alert('" + ex.Replace("\r\n", "\\n").Replace("'", "") + "'); </SCRIPT>";
            Type cstype = obj.GetType();
            ClientScriptManager cs = pg.ClientScript;
            cs.RegisterClientScriptBlock(cstype, s, s.ToString());
        }

        protected void Unnamed2_Click(object sender, EventArgs e)
        {
            MsgBox("Hola, has cerrado la conexion", this.Page, this);
            cs.CERRAR();


        }
    }
}