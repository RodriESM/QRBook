using MySql.Data.MySqlClient;
using QRBookWeb.assets.sql;
using QRBookWeb.ControlesUsuario;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb {
    public partial class buscarLib : System.Web.UI.Page {

        Conexion cs = new Conexion();

        protected void Page_Load(object sender, EventArgs e) {

            /*bool admin = false;
            if (Session["admin"] != null) {
                admin = Convert.ToBoolean(Session["admin"]);
            }

            if (!admin) {
                Response.Redirect("/index.aspx");
            } else {*/
            bool logged = false;
            bool admin = false;
            if (Session["correo"] != null) {
                if (!String.IsNullOrEmpty(Session["correo"].ToString())) {
                    logged = true;
                    admin = Convert.ToBoolean(Session["admin"]);
                    //System.Diagnostics.Debug.WriteLine(o["usuario"].ToString());
                    //System.Diagnostics.Debug.WriteLine(admin.ToString());
                }
            }

            if (logged) {
                registro.Visible = false;
                inicio.Visible = false;
                dropdown.Visible = true;
                if (!admin) {
                    busqUsu.Visible = false;
                }
            } else {
                registro.Visible = true;
                inicio.Visible = true;
                dropdown.Visible = false;
                busqUsu.Visible = false;
            }


            if (IsPostBack) {
                if (String.IsNullOrEmpty(qstr.Value)) {
                    buscarLibros();
                } else {
                    //Session["modificar"] = qstr.Value;
                    //Response.Redirect("/user.aspx?Email=" + qstr.Value);
                }
            } else {
                buscarLibros();
            }

            //}

            //System.Diagnostics.Debug.WriteLine(qstr.Value);

        }

        private void buscarLibros() {
            string txt = txtBus.Value;
            string cond = ddnBus.SelectedValue;

            string sql = "select ISBN, AUTOR, TITULO, EDITORIAL, GENERO, PORTADA from LIBRO";
            if (String.IsNullOrEmpty(txtBus.Value) || ddnBus.SelectedIndex == -1) {

            } else if (ddnBus.SelectedValue == "ISBN") {
                sql += " where " + cond + " like '" + txt + "%'";
            } else {
                sql += " where " + cond + " like '%" + txt + "%'";
            }

            locodiv.Controls.Clear();
            MySqlConnection DBCon = cs.CONECTAR();
            MySqlCommand cmd = new MySqlCommand(sql, DBCon);
            MySqlDataReader result = cmd.ExecuteReader();

            while (result.Read()) {
                fillUserControl(result);
            }


        }

        private void fillUserControl(MySqlDataReader result) {
            bool admin = false;
            if (Session["admin"] != null) {
                admin = Convert.ToBoolean(Session["admin"]);
            }

            controlLibro unLib = (controlLibro)Page.LoadControl("~/ControlesUsuario/controlLibro.ascx");
            string isbn = result["ISBN"].ToString();
            string autor = result["AUTOR"].ToString();
            string titulo = result["TITULO"].ToString();
            string editorial = result["EDITORIAL"].ToString();
            string genero = result["GENERO"].ToString();
            string portada = result["PORTADA"].ToString();
            unLib.fillFields(titulo, autor, editorial, isbn, genero, portada, admin);

            locodiv.Controls.Add(unLib);
        }

        protected void btnSalir_Click(object sender, EventArgs e) {
            Session.Clear();
            Response.Redirect(Request.RawUrl.Split('?')[0], true);
        }
    }
}