using Newtonsoft.Json.Linq;
using QRBookWeb.assets.cs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb 
{

    public partial class index : System.Web.UI.Page {

        Herramientas hr = new Herramientas();

        protected void Page_Load(object sender, EventArgs e) {

            string qstr = Request.QueryString["Desde"];
            if (qstr == "Login") {
                hr.MsgBox("Sesión iniciada.", this.Page, this);
                //Response.Redirect("/index.aspx");
            } else if (qstr == "Salir") {
                Session.Clear();
                Response.Redirect("/index.aspx");
            } else {
                string userdel = Request.QueryString["UserDel"];
                if (!String.IsNullOrEmpty(userdel)) {
                    hr.MsgBox(userdel, this.Page, this);
                    //Response.Redirect("/index.aspx");
                }
            }

            //JObject o = (JObject)Session["user"];

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

            /*if (Session["mensaje"] != null) {
                hr.MsgBox(Session["mensaje"].ToString(), this.Page, this);
                Session["mensaje"] = null;
            }*/

        }

        protected void btnSalir_Click(object sender, EventArgs e) {
            Session.Clear();
            Response.Redirect(Request.RawUrl.Split('?')[0], true);
        }

        //<input runat = "server" id="busqUsu" class="btn-header" type="button" onclick="location.href = 'buscarUsu.aspx';" value="Buscar Usuarios" style="color: #ffd800"/>
    }
}