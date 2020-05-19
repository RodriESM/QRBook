using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Microsoft.Owin.Security.Google;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;

namespace QRBookWeb {
    public partial class registro : System.Web.UI.Page {
        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();
        //Conexion a FireStore
        //FirestoreDb db = FirestoreDb.Create();

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

                if (IsPostBack) {
                    foreach (IValidator i in Validators) {
                        if (i.ErrorMessage.Contains("UNTOUCHED") && !(i == reqpass || i == valpass)) {
                            i.ErrorMessage = i.ErrorMessage.Replace("UNTOUCHED", "");
                        }
                    }
                    Page.Validate();
                } else {
                    foreach (IValidator i in Validators) {
                        if (!(i == cususuario || i == cuscorreo)) {
                            i.ErrorMessage += "UNTOUCHED";
                        }
                    }
                    usuario.Focus();
                }
                btnregistro.Enabled = false;
                btnregistro.CssClass = "buttondis";
                //usuario.BackColor = System.Drawing.Color.FromArgb(255,0,0);
                //btnregistro.Enabled = false;

            }


        }

        protected void Registro_Click(object sender, EventArgs e) {

            if (String.IsNullOrEmpty(usuario.Text) || String.IsNullOrEmpty(correo.Text) || String.IsNullOrEmpty(pass.Text)) {
                hr.MsgBox("Faltan campos por rellenar", this.Page, this);
                Page.Validate();
            } else {
                string encontrado = "";
                try {
                    MySqlConnection DBCon = cs.CONECTAR();

                    string sel = "select USUARIO from USUARIO where USUARIO = '" + usuario.Text + "'";
                    MySqlCommand cmd = new MySqlCommand(sel, DBCon);
                    object result = cmd.ExecuteScalar();
                    //System.Diagnostics.Debug.WriteLine("======== " + result.ToString());

                    if (result != null) {
                        encontrado += "usuario";
                    }

                    sel = "select CORREO from USUARIO where CORREO = '" + correo.Text + "'";
                    cmd = new MySqlCommand(sel, DBCon);
                    result = cmd.ExecuteScalar();

                    if (result != null) {
                        encontrado += "correo";
                    }


                    if (String.IsNullOrEmpty(encontrado)) {
                        string ins = "insert into USUARIO (CORREO, USUARIO, PASSWORD) VALUES ('" + correo.Text + "', '" + usuario.Text + "', '" + pass.Text + "')";
                        cmd = new MySqlCommand(ins, DBCon);
                        cmd.ExecuteNonQuery();
                        //hr.MsgBox("Te has registrado!!!!!!!", this.Page, this);

                        //Con QueryStrings, puedes mandar informacion en la propia ruta, y recogerla en la nueva pagina
                        Response.Redirect("/login.aspx?Desde=Registro");
                    } else {
                        if (encontrado.Contains("usuario")) {
                            cususuario.IsValid = false;
                            contusuario.Attributes["class"] = "validador-contenedor";
                        } else {
                            contusuario.Attributes["class"] = "validador-contenedor2";
                        }
                        if (encontrado.Contains("correo")) {
                            cuscorreo.IsValid = false;
                            contcorreo.Attributes["class"] = "validador-contenedor";
                        } else {
                            contcorreo.Attributes["class"] = "validador-contenedor2";
                        }
                    }

                } catch (MySqlException ex) {
                    hr.MsgBox("El correo electronico o el nombre de usuario ya existen", this.Page, this);
                } finally {
                    cs.CERRAR();
                }
            }
        }

    }
}