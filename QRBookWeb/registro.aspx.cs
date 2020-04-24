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
            MsgBox("Hola, estas conectado", this.Page, this);
        }

        public void MsgBox(String ex, Page pg, Object obj)
        {
            string s = "<SCRIPT language='javascript'>alert('" + ex.Replace("\r\n", "\\n").Replace("'", "") + "'); </SCRIPT>";
            Type cstype = obj.GetType();
            ClientScriptManager cs = pg.ClientScript;
            cs.RegisterClientScriptBlock(cstype, s, s.ToString());
        }

        protected void Unnamed2_Click(object sender, EventArgs e)
        {
            MsgBox("Hola, has cerrado la conexion", this.Page, this);
            cs.CERRAR();


        }
    }
}