using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.Mvc;
using System.Web.UI;
using System.Web.UI.WebControls;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace QRBookWeb
{
    public partial class login : System.Web.UI.Page {

        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();

        protected void Page_Load(object sender, EventArgs e) {

            //JObject o = (JObject)Session["user"];

            bool logged = false;
            if (Session["correo"] != null) {
                if (!String.IsNullOrEmpty(Session["correo"].ToString())) {
                    logged = true;
                }
            }

            if (logged) {
                Response.Redirect("/index.aspx");
            } else {

                if (Request.QueryString["Desde"] == "Registro") {
                    hr.MsgBox("Te has registrado, ya puedes iniciar sesión.", this.Page, this);
                }

            }

            user.Focus();
        }

        protected void Login_Click(object sender, EventArgs e) {
            if (String.IsNullOrEmpty(user.Value) || String.IsNullOrEmpty(pass.Value)) {
                hr.MsgBox("Faltan campos por rellenar", this.Page, this);
            } else {
                //try {
                MySqlConnection DBCon = cs.CONECTAR();
                string sel = "select * from USUARIO where USUARIO = '" + user.Value + "' and PASSWORD = '" + pass.Value + "'";
                MySqlCommand cmd = new MySqlCommand(sel, DBCon);
                MySqlDataReader result = cmd.ExecuteReader();
                bool encontrado = false;
                JObject o = new JObject();

                while (result.Read()) {
                    encontrado = true;
                    toJObj(result, o);
                    //System.Diagnostics.Debug.WriteLine(o.ToString());
                }
                result.Close();

                if (!encontrado) {
                    sel = "select * from USUARIO where CORREO = '" + user.Value + "' and PASSWORD = '" + pass.Value + "'";
                    cmd = new MySqlCommand(sel, DBCon);
                    result = cmd.ExecuteReader();

                    while (result.Read()) {
                        encontrado = true;
                        toJObj(result, o);
                        //System.Diagnostics.Debug.WriteLine("XD");
                    }
                }

                if (encontrado) {
                    //Con QueryStrings, puedes mandar informacion en la propia ruta, y recogerla en la nueva pagina
                    
                    /*string json = o.ToString();
                    json = json.Replace("\n", String.Empty);
                    json = json.Replace("\r", String.Empty);
                    json = json.Replace("\t", String.Empty);*/

                    //ClientScriptManager cs = Page.ClientScript;
                    //cs.RegisterClientScriptBlock(this.GetType(), "script", "iniciado('" + json + "')", true);

                    //Session["user"] = o;
                    Session["usuario"] = o["usuario"].ToString();
                    Session["correo"] = o["correo"].ToString();
                    Session["admin"] = o["admin"].ToString();

                    Response.Redirect("/index.aspx?Desde=Login");
                } else {
                    hr.MsgBox("El usuario o contraseña son incorrectos.", this.Page, this);
                }

                result.Close();
                cs.CERRAR();
            }
        }

        private void toJObj(MySqlDataReader result, JObject o) {

            o.Add(new JProperty("correo", Convert.ToString(result["CORREO"])));
            o.Add(new JProperty("usuario", Convert.ToString(result["USUARIO"])));
            o.Add(new JProperty("password", Convert.ToString(result["PASSWORD"])));
            o.Add(new JProperty("nombre", Convert.ToString(result["NOMBRE"])));
            o.Add(new JProperty("apellido1", Convert.ToString(result["APELLIDO1"])));
            o.Add(new JProperty("apellido2", Convert.ToString(result["APELLIDO2"])));
            o.Add(new JProperty("foto", Convert.ToString(result["FOTO"])));
            o.Add(new JProperty("admin", Convert.ToString(result["ADMIN"])));

            //System.Diagnostics.Debug.WriteLine(o.ToString());

        }









        /*protected void Login_Click2(object sender, EventArgs e) {
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
                    // Get a ClientScriptManager reference from the Page class.
                    ClientScriptManager cs = Page.ClientScript;
                    cs.RegisterClientScriptBlock(this.GetType(), "script", "iniciado()", true);
                    //Response.Redirect("/index.aspx?Desde=Login");
                } else {
                    hr.MsgBox("El usuario o contraseña son incorrectos.", this.Page, this);
                }

                cs.CERRAR();
            }
        }*/




    }
}