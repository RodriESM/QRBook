using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb.ControlesUsuario {
    public partial class controlUsuario : System.Web.UI.UserControl {

        private string correo;
        HiddenField qstr;

        protected void Page_Load(object sender, EventArgs e) {
            //nomusu.Text = "xD";
        }

        public void fillFields (string u, string em, string an, HiddenField qs) {
            correo = em;
            email.Text = em;
            usu.Text = u;
            nomusu.Text = an;
            qstr = qs;
            btnmodif.Attributes.Add("onClick", "javascript: irUsu('" + em + "');");
            

        }

        
        //<input id="btnmodif" type="button" class="btnitem" runat="server" value="Modificar" />
        //<asp:Button id="Button1" class="btnitemdel" runat="server" Text="Eliminar"/>
    }
}