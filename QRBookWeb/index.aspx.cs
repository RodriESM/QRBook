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

        }




    }
}