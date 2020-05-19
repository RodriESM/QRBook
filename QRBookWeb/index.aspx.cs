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

            if (Request.QueryString["Desde"] == "Login") {
                hr.MsgBox("Sesión iniciada.", this.Page, this);
            }

            //JObject o = (JObject)Session["user"];

            bool logged = false;
            if (Session["correo"] != null) {
                if (!String.IsNullOrEmpty(Session["correo"].ToString())) {
                    logged = true;
                    //System.Diagnostics.Debug.WriteLine(o["usuario"].ToString());
                    //System.Diagnostics.Debug.WriteLine("en el if");
                }
            }

            if (logged) {
                registro.Visible = false;
                inicio.Visible = false;
                dropdown.Visible = true;
            } else {
                registro.Visible = true;
                inicio.Visible = true;
                dropdown.Visible = false;
            }



        }

        protected void btnSalir_Click(object sender, EventArgs e) {
            Session.Clear();
            Response.Redirect(Request.RawUrl.Split('?')[0], true);
        }
    }
}