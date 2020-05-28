using System;
using System.Collections.Generic;
using System.Linq;
using System.Text.RegularExpressions;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using Microsoft.SqlServer.Server;
using MySql.Data.MySqlClient;
using Newtonsoft.Json;
using Newtonsoft.Json.Linq;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;

namespace QRBookWeb
{
    public partial class user : System.Web.UI.Page {

        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();

        protected void Page_Load(object sender, EventArgs e) {

            //JObject o = (JObject)Session["user"];
            //hr.MsgBox("Hola\nHola\nHola", this.Page, this);

            bool logged = false;
            bool admin = false;
            if (Session["correo"] != null) {
                if (!String.IsNullOrEmpty(Session["correo"].ToString())) {
                    logged = true;
                    admin = Convert.ToBoolean(Session["admin"]);
                }
            }

            if (!logged) {
                Response.Redirect("/index.aspx");
            } else {

                if (!admin) {
                    busqUsu.Visible = false;
                }

                if (!IsPostBack) {
                    //string correo = Session["correo"].ToString();
                    string correo = getEmail();
                    
                    MySqlConnection DBCon = cs.CONECTAR();
                    string sel = "SELECT U.CORREO, U.USUARIO, U.NOMBRE, U.APELLIDO1, U.APELLIDO2, U.FOTO, " +
                                    "D.BIRTH, D.PHONE, D.PROVINCIA, D.CIUDAD, D.DIRECCION, D.CODPOS " +
                                    "FROM QrBook.USUARIO U LEFT JOIN QrBook.DATOSUSUARIO D " +
                                    "ON U.CORREO = D.CORREO where U.CORREO = '" + correo + "'";
                    MySqlCommand cmd = new MySqlCommand(sel, DBCon);
                    MySqlDataReader result = cmd.ExecuteReader();

                    while (result.Read()) {
                        rellenarDatos(result);
                    }

                    result.Close();
                    cs.CERRAR();

                }

                if (Session["mensaje"] != null) {
                    System.Diagnostics.Debug.WriteLine(Session["mensaje"].ToString());
                    hr.MsgBox(Session["mensaje"].ToString(), this.Page, this);
                    Session["mensaje"] = null;
                }
            }

        }

        private string getEmail() {
            bool current = true;
            string modif = "";
            string correo = "";
            bool admin = Convert.ToBoolean(Session["admin"]);
            if (admin && Session["modificar"] != null) {
                modif = Session["modificar"].ToString();
                if (Request.QueryString["Email"] == modif) {
                    current = false;
                }
            }
            if (current) {
                correo = Session["correo"].ToString();
            } else {
                correo = modif;
            }

            return correo;
        }

        private void rellenarDatos(MySqlDataReader result) {
            //Convert.ToString(result["CORREO"])
            nom.Text = Convert.ToString(result["NOMBRE"]);
            ape1.Text = Convert.ToString(result["APELLIDO1"]);
            ape2.Text = Convert.ToString(result["APELLIDO2"]);
            usutitle.InnerText = Convert.ToString(result["USUARIO"]);
            nomtitle.InnerText = Convert.ToString(result["NOMBRE"] + " " + result["APELLIDO1"] + " " + result["APELLIDO2"]);
            email.Text = Convert.ToString(result["CORREO"]);
            tlf.Text = Convert.ToString(result["PHONE"]);
            prov.Text = Convert.ToString(result["PROVINCIA"]);
            city.Text = Convert.ToString(result["CIUDAD"]);
            dir.Text = Convert.ToString(result["DIRECCION"]);
            cp.Text = Convert.ToString(result["CODPOS"]);
            try {
                birth.Text = Convert.ToDateTime(result["BIRTH"]).ToString("yyyy-MM-dd");
            } catch (Exception ex) { }

            //System.Diagnostics.Debug.WriteLine(Convert.ToString(result["BIRTH"]));
        }


        protected void btnSalir_Click(object sender, EventArgs e) {
            Session.Clear();
            Response.Redirect("/index.aspx");
        }

        protected void btnguardar_Click(object sender, EventArgs e) {
            bool msg = true;
            try {
                if (Session["correo"] == null) {
                    throw new Exception();
                }
                //string correo = Session["correo"].ToString();
                string correo = getEmail();
                
                //System.Diagnostics.Debug.WriteLine(nom.Text);

                MySqlConnection DBCon = cs.CONECTAR();
                string sql = "update USUARIO set NOMBRE = '" + nom.Text + "', APELLIDO1 = '" + ape1.Text + "', APELLIDO2 = '" + ape2.Text + "' where CORREO = '" + correo + "'";
                MySqlCommand cmd = new MySqlCommand(sql, DBCon);
                cmd.ExecuteNonQuery();

                string fecha = "NULL";
                try {
                    DateTime.Parse(birth.Text);
                    fecha = "'" + birth.Text + "'";
                    //System.Diagnostics.Debug.WriteLine(birth.Text);
                } catch (FormatException ex) {
                    //System.Diagnostics.Debug.WriteLine(birth.Text);
                }

                sql = "update DATOSUSUARIO set BIRTH = " + fecha + ", PHONE = '" + tlf.Text +
                    "', PROVINCIA = '" + prov.Text + "', CIUDAD = '" + city.Text +
                    "', DIRECCION = '" + dir.Text + "', CODPOS = '" + cp.Text +
                    "' where CORREO = '" + correo + "'";
                cmd = new MySqlCommand(sql, DBCon);
                int i = cmd.ExecuteNonQuery();

                if (i == 0) {
                    sql = "insert ignore into DATOSUSUARIO values ('" + correo + "', " + fecha +
                        ", '" + tlf.Text + "', '" + prov.Text + "', '" + city.Text +
                        "', '" + dir.Text + "', '" + cp.Text + "')";
                    cmd = new MySqlCommand(sql, DBCon);
                    cmd.ExecuteNonQuery();
                }

                Session["mensaje"] = "Datos actualizados.";

            } catch (Exception ex) {
                msg = false;
                Session.Clear();
                Response.Redirect("/index.aspx");
            } finally {
                cs.CERRAR();
                if (msg) {
                    bool admin = Convert.ToBoolean(Session["admin"]);
                    if (admin) {
                        Response.Redirect(Request.Url.AbsoluteUri);
                    } else {
                        Response.Redirect("/user.aspx");
                    }
                }
            }

        }

        protected void btnguardarpass_Click(object sender, EventArgs e) {
            bool msg = true;
            try {
                if (Session["correo"] == null) {
                    throw new Exception();
                }
                //string correo = Session["correo"].ToString();
                string correo = getEmail();

                string regexpass = @"^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$";
                string mensaje = "";
                bool guardar = true;

                MySqlConnection DBCon = cs.CONECTAR();
                string sel = "SELECT PASSWORD FROM USUARIO where CORREO = '" + correo + "'";
                MySqlCommand cmd = new MySqlCommand(sel, DBCon);
                object result = cmd.ExecuteScalar();

                if (result.ToString() != txtLastPassword.Text) {
                    guardar = false;
                    mensaje += "Contraseña incorrecta.\n";
                }
                if (!Regex.IsMatch(txtNewPassword.Text, regexpass)) {
                    guardar = false;
                    mensaje += "Minimo 8 caracteres, con mayúsculas, minúsculas y números.\n";
                }
                if (txtNewPassword.Text != txtRNewPassword.Text) {
                    guardar = false;
                    mensaje += "Las contraseñas no coinciden.";
                }

                //System.Diagnostics.Debug.WriteLine(mensaje);

                if (guardar) {
                    string sql = "update USUARIO set PASSWORD = '" + txtNewPassword.Text + "' where CORREO = '" + correo + "'";
                    cmd = new MySqlCommand(sql, DBCon);
                    int i = cmd.ExecuteNonQuery();

                    if (i > 0) {
                        mensaje = "Contraseña actualizada.";
                    } else {
                        mensaje = "No se ha podido actualizar la contraseña.";
                    }
                }

                Session["mensaje"] = mensaje;
                //System.Diagnostics.Debug.WriteLine(Session["mensaje"].ToString());

            } catch (Exception ex) {
                msg = false;
                Session.Clear();
                //ViewState.Clear();
                Response.Redirect("/index.aspx");
            } finally {
                cs.CERRAR();
                if (msg) {
                    bool admin = Convert.ToBoolean(Session["admin"]);
                    if (admin) {
                        Response.Redirect(Request.Url.AbsoluteUri);
                    } else {
                        Response.Redirect("/user.aspx");
                    }
                }
            }

        }


    }
}