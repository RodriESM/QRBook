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
            if (!admin) {
                btnmodif.InnerHtml = "<i class=\"fas fa-book-reader fa-lg blanco\" style=\"margin: 7px 0\"></i>";
            } 
            btnmodif.Attributes.Add("onClick", "javascript: irLib('" + isbn + "');");

        }


        /*
        <td class="tditem" style="width: 60px; border-style: none">
         <button id="btnpredel" class="btnitemdel" runat="server"><i class="fas fa-trash-alt blanco"></i></button>
        </td>
        */
    }
}