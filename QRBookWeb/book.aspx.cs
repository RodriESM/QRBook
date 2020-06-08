using System;
using System.Collections.Generic;
using System.Linq;
using System.Web;
using System.Web.UI;
using System.Web.UI.WebControls;
using MySql.Data.MySqlClient;
using QRBookWeb.assets.cs;
using QRBookWeb.assets.sql;

namespace QRBookWeb {
    public partial class book : System.Web.UI.Page {

        Conexion cs = new Conexion();
        Herramientas hr = new Herramientas();

        protected void Page_Load(object sender, EventArgs e) {

            string qstr = Request.QueryString["ISBN"];

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

            if (!admin) {
                txttitulo.Visible = false;
                lbltitulo.Visible = false;
                trBtn.Visible = false;
                trDir.Visible = false;
                if (!datosLibro(qstr)) {
                    Response.Redirect("/buscarLib.aspx?Desde=Book");
                }
            } else {
                if (confirmar.Value.Equals("delete")) {
                    borrarLibro(qstr);
                } else {
                    if (Session["mensaje"] != null) {
                        hr.MsgBox(Session["mensaje"].ToString(), this.Page, this);
                        Session["mensaje"] = null;
                    }

                    TextBox[] txt = { txtautor, txteditorial, txtgenero, txtidioma, txtyear, txtsinopsis };
                    foreach (TextBox t in txt) {
                        t.CssClass = "txtedit";
                        t.ReadOnly = false;
                    }
                    if (String.IsNullOrEmpty(qstr)) {
                        titulo.Text = "Añadir un libro";
                        //btnSave.InnerHtml = btnSave.InnerHtml.Replace("Guardar", "Añadir");
                        txtisbn.CssClass = "txtedit";
                        txtisbn.ReadOnly = false;
                        btnDel.Visible = false;
                        btnSave.InnerHtml = "<i class=\"fas fa-plus blanco\"></i>\n Añadir";
                        divSave.Style.Add("width", "78%");
                    } else if (!datosLibro(qstr, admin)) {
                        Response.Redirect("/book.aspx");
                    }
                }

                
            }


        }

        private bool datosLibro(string qstr, bool admin = false) {
            MySqlConnection DBCon = cs.CONECTAR();
            string sel = "SELECT * FROM LIBRO where ISBN = '" + qstr + "'";
            MySqlCommand cmd = new MySqlCommand(sel, DBCon);
            MySqlDataReader result = cmd.ExecuteReader();

            bool encontrado = false;
            while (result.Read()) {
                encontrado = true;
                if (!IsPostBack) {
                    rellenarDatos(result, admin);
                }
            }

            result.Close();
            cs.CERRAR();
            return encontrado;
        }

        private void rellenarDatos(MySqlDataReader result, bool admin) {

            imglib.ImageUrl = Convert.ToString(result["PORTADA"]);
            titulo.Text = Convert.ToString(result["TITULO"]);
            txtautor.Text = Convert.ToString(result["AUTOR"]);
            txteditorial.Text = Convert.ToString(result["EDITORIAL"]);
            txtidioma.Text = Convert.ToString(result["IDIOMA"]);
            txtgenero.Text = Convert.ToString(result["GENERO"]);
            txtyear.Text = Convert.ToString(result["YEAR"]);
            txtisbn.Text = Convert.ToString(result["ISBN"]);
            txtsinopsis.Text = Convert.ToString(result["SINOPSIS"]);

            if (admin) {
                txttitulo.Text = Convert.ToString(result["TITULO"]);
                txtportada.Text = Convert.ToString(result["PORTADA"]);
                txtpdf.Text = Convert.ToString(result["PDF"]);
            }

        }

        protected void btnSalir_Click(object sender, EventArgs e) {
            Session.Clear();
            Response.Redirect(Request.RawUrl, true);
        }

        private void borrarLibro(string isbn) {
            confirmar.Value = "";

            MySqlConnection DBCon = cs.CONECTAR();
            string sel = "DELETE FROM LIBRO where ISBN = '" + isbn + "'";
            MySqlCommand cmd = new MySqlCommand(sel, DBCon);
            int result = cmd.ExecuteNonQuery();
            cs.CERRAR();

            if (result > 0) {
                Session["mensaje"] = "Libro eliminado. (ISBN-" + isbn + ")";
                Response.Redirect("/book.aspx");
            } else {
                Session["mensaje"] = "Fallo al eliminar el libro.";
                Response.Redirect(Request.RawUrl, true);
            }
        }

        protected void btnSave_ServerClick(object sender, EventArgs e) {
            //System.Diagnostics.Debug.WriteLine("todo bien");

            string isbn = Request.QueryString["ISBN"];

            MySqlConnection DBCon = cs.CONECTAR();
            string sql = "update LIBRO set " +
                         "AUTOR = '" + txtautor.Text + "', " +
                         "TITULO = '" + txttitulo.Text + "', " +
                         "EDITORIAL = '" + txteditorial.Text + "', " +
                         "SINOPSIS = '" + txtsinopsis.Text + "', " +
                         "YEAR = '" + txtyear.Text + "', " +
                         "IDIOMA = '" + txtidioma.Text + "', " +
                         "GENERO = '" + txtgenero.Text + "', " +
                         "PORTADA = '" + txtportada.Text + "', " +
                         "PDF = '" + txtpdf.Text + "' " +
                         "where ISBN = '" + isbn + "'";
            MySqlCommand cmd = new MySqlCommand(sql, DBCon);
            int result = cmd.ExecuteNonQuery();

            if (result == 0) {
                sql = "insert into LIBRO (ISBN, AUTOR, TITULO, EDITORIAL, SINOPSIS, YEAR, IDIOMA, GENERO, PORTADA, PDF) " +
                    "VALUES ('" + txtisbn.Text + "', " +
                    "'" + txtautor.Text + "', " +
                    "'" + txttitulo.Text + "', " +
                    "'" + txteditorial.Text + "', " +
                    "'" + txtsinopsis.Text + "', " +
                    "'" + txtyear.Text + "', " +
                    "'" + txtidioma.Text + "', " +
                    "'" + txtgenero.Text + "', " +
                    "'" + txtportada.Text + "', " +
                    "'" + txtpdf.Text + "')";
                cmd = new MySqlCommand(sql, DBCon);
                result = cmd.ExecuteNonQuery();
                cs.CERRAR();

                if (result > 0) {
                    Session["mensaje"] = "Libro añadido. (ISBN-" + txtisbn.Text + ")";
                    Response.Redirect("/book.aspx?ISBN=" + txtisbn.Text);
                } else {
                    Session["mensaje"] = "Fallo al guardar el libro.";
                    Response.Redirect(Request.RawUrl, true);
                }
            } else {
                cs.CERRAR();
                Session["mensaje"] = "Libro guardado. (ISBN-" + isbn + ")";
                Response.Redirect(Request.RawUrl, true);
            }

        }
    }
}