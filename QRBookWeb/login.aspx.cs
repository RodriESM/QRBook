using QRBookWeb.assets.cs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb
{
    public partial class login : System.Web.UI.Page {

        Herramientas hr = new Herramientas();
        protected void Page_Load(object sender, EventArgs e)
        {
            if (Request.QueryString["Desde"] == "Registro") {
                hr.MsgBox("Te has registrado!!!!!!!", this.Page, this);
            }
        }
    }
}