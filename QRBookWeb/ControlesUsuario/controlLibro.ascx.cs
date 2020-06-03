using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;

namespace QRBookWeb.ControlesUsuario {
    public partial class controlLibro : System.Web.UI.UserControl {

        protected void Page_Load(object sender, EventArgs e) {

        }

        public void fillFields(string titulo, string autor, string editorial, string isbn, string genero, string portada, bool admin) {
            this.titulo.Text = titulo;
            this.autor.Text = autor;
            this.editorial.Text = editorial;
            this.isbn.Text = isbn;
            this.genero.Text = genero;
            imglib.ImageUrl = portada;
            if (admin) {
                btnmodif.Attributes.Add("onClick", "javascript: irLib('" + isbn + "');");
            } else {
                tdModif.Visible = false;
            }

        }

        protected void btnmodif_Click(object sender, EventArgs e) {
            Session["modificar"] = isbn.Text;
            Response.Redirect("/book.aspx?ISBN=" + isbn.Text);
        }

        /*
        <td class="tditem" style="width: 60px; border-style: none">
         <button id="btnpredel" class="btnitemdel" runat="server"><i class="fas fa-trash-alt blanco"></i></button>
        </td>
        */
    }
}