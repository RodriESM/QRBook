using QRBookWeb.assets.cs;
using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb
{
    public partial class registro : System.Web.UI.Page
    {
        Conexion cs = new Conexion();

        protected void Page_Load(object sender, EventArgs e)
        {
            
        }

        protected void Unnamed1_Click(object sender, EventArgs e)
        {
            cs.CONECTAR();
        }

        protected void Unnamed2_Click(object sender, EventArgs e)
        {
            cs.CERRAR();
        }
    }
}