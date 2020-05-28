using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;
using QRBookWeb.ControlesUsuario;
using MySql.Data.MySqlClient;

namespace QRBookWeb {
    public partial class buscarUsu : System.Web.UI.Page {
        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();

        protected void Page_Init(object sender, EventArgs e) {
            //buscarUsuarios();
        }

        protected void Page_Load(object sender, EventArgs e) {
            //controlUsuario unUsu = (controlUsuario)Page.LoadControl("~/ControlesUsuario/controlUsuario.ascx");
            //locodiv.Controls.Add(unUsu);

            /*if (!IsPostBack) {
                buscarUsuarios();
            }*/
            bool admin = false;
            if (Session["admin"] != null) {
                admin = Convert.ToBoolean(Session["admin"]);
            }

            if (!admin) {
                Response.Redirect("/index.aspx");
            } else {

                if (IsPostBack) {
                    if (String.IsNullOrEmpty(qstr.Value)) {
                        buscarUsuarios();
                    } else {
                        Session["modificar"] = qstr.Value;
                        Response.Redirect("/user.aspx?Email=" + qstr.Value);
                    }
                } else {
                    buscarUsuarios();
                }

            }

            //System.Diagnostics.Debug.WriteLine(qstr.Value);

        }

        private void buscarUsuarios() {
            string txt = txtBus.Value;
            string cond = ddnBus.SelectedValue;

            string sql = "select USUARIO, CORREO, NOMBRE, APELLIDO1, APELLIDO2 from USUARIO";
            bool success = true;
            if (String.IsNullOrEmpty(txtBus.Value) || ddnBus.SelectedIndex == -1) {

            } else if (ddnBus.SelectedValue == "apenom") {
                sql += " where NOMBRE like '%" + txtBus.Value + "%' or APELLIDO1 like '%" + txtBus.Value + "%' or APELLIDO2 like '%" + txtBus.Value + "%'";
            } else if (ddnBus.SelectedValue == "BIRTH") {
                try {
                    DateTime fecha = DateTime.Parse(txtBus.Value);
                    sql += " where BIRTH >= #" + fecha.ToString("MM/dd/yyyy") + "# and BIRTH < #" + fecha.AddDays(1).ToString("MM/dd/yyyy") + "#";
                } catch (FormatException ex) {
                    success = false;
                }
            } else {
                sql += " where " + ddnBus.SelectedValue + " like '%" + txtBus.Value + "%'";
            }

            locodiv.Controls.Clear();
            if (!success) {
                System.Diagnostics.Debug.WriteLine("ERROR pls");
            } else {
                MySqlConnection DBCon = cs.CONECTAR();
                MySqlCommand cmd = new MySqlCommand(sql, DBCon);
                MySqlDataReader result = cmd.ExecuteReader();

                while (result.Read()) {
                    fillUserControl(result);
                }

            }

        }

        private void fillUserControl (MySqlDataReader result) {
            controlUsuario unUsu = (controlUsuario)Page.LoadControl("~/ControlesUsuario/controlUsuario.ascx");
            string apenom = result["NOMBRE"].ToString() + " " + result["APELLIDO1"].ToString() + " " + result["APELLIDO2"].ToString();
            unUsu.fillFields(result["USUARIO"].ToString(), result["CORREO"].ToString(), apenom, qstr);

            locodiv.Controls.Add(unUsu);
        }


        
        /*<asp:ListItem Value = "PROVINCIA" > Provincia </ asp:ListItem>
        <asp:ListItem Value = "CIUDAD" > Ciudad </ asp:ListItem>
        <asp:ListItem Value = "CODPOS" > Código postal</asp:ListItem>
        <asp:ListItem Value = "BIRTH" > Fecha de nacimiento</asp:ListItem>*/
    }
}