using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb.ControlesUsuario {

    public partial class controlUsuLib : System.Web.UI.UserControl {

        protected void Page_Load(object sender, EventArgs e) {

        }

        public void fillFields(string titulo, string isbn, string portada, bool admin) {
            this.titulo.Text = titulo;
            imglib.ImageUrl = portada;
            if (!admin) {
                btnmodif.InnerHtml = "<i class=\"fas fa-book-reader fa-lg blanco\" style=\"margin: 7px 0\"></i>";
            }
            btnmodif.Attributes.Add("onClick", "javascript: irLib('" + isbn + "');");

        }
    }
}