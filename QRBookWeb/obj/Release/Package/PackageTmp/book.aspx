<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="book.aspx.cs" Inherits="QRBookWeb.book" EnableEventValidation="false" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
<link rel="stylesheet" href="assets/css/objsearch.css">
<link rel="stylesheet" href="assets/css/infoStyle.css">
</head>

<body class="hidden">
    <form id="form1" runat="server">
    <!--PRECARGA-->
    <div class="centrado" id="onload">
            <div class="loadingio-spinner-blocks-a73ijrg8fkr">
                <div class="ldio-hetvfw5zgq">
                    <div style='left: 38px; top: 38px; animation-delay: 0s'></div>
                    <div style='left: 80px; top: 38px; animation-delay: 0.125s'></div>
                    <div style='left: 122px; top: 38px; animation-delay: 0.25s'></div>
                    <div style='left: 38px; top: 80px; animation-delay: 0.875s'></div>
                    <div style='left: 122px; top: 80px; animation-delay: 0.375s'></div>
                    <div style='left: 38px; top: 122px; animation-delay: 0.75s'></div>
                    <div style='left: 80px; top: 122px; animation-delay: 0.625s'></div>
                    <div style='left: 122px; top: 122px; animation-delay: 0.5s'></div>
                </div>
            </div>
        </div>


<!--CABECERA-->
        <header style="padding-bottom: 40px">
            <a name="Inicio"/>
            <nav id="nav">
                <div class="contenedor-nav ">
                    <div class="logo">
                        <button type="button" style="background: none; border: none; cursor: pointer" onclick="location.href = 'index.aspx';">
                            <img alt="QrBook" src="img/Logo.png" style="width: 85px"/>
                        </button>
                    </div>
                    <div class="enlaces" id="enlaces">
                        <a href="buscarUsu.aspx" id="busqUsu" class="btn-header" runat="server">Buscar Usuarios</a>
                        <a href="buscarLib.aspx" id="busqLib" class="btn-header" runat="server">Buscar Libros</a>
                        <a href="#Equipo" id="enlace-equipo" class="btn-header">Conócenos</a>
                        <div runat="server" class="dropdown" id="dropdown">
                            <a href="user.aspx" style="border: 2px solid rgba(255,255,255, 0.5); border-radius: 100%">
                                <img id="user" alt="Perfil" src="img/user-img.png" style="width: 50px; height: 60px"/>
                            </a>
                            <div class="dropdown-content">
                                <a href="user.aspx">Perfil</a>
                                <asp:LinkButton id="btnSalir" class="btn-salir" runat="server" Text="Salir" OnClick="btnSalir_Click"/>
                            </div>
                        </div>
                        <input runat="server" id="registro" class="btn-registro" type="button" onclick="location.href = 'registro.aspx';" value="Registro" />
                        <input runat="server" id="inicio" class="btn-inicio" type="button" onclick="location.href = 'login.aspx';" value="Inicio de sesión" style="margin-right: 15px" />
                       
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>

<div class="formularioBus">
    <asp:TextBox ID="titulo" runat="server" Text="Titulo libro" class="txttitle" ReadOnly="true" TextMode="MultiLine" Rows="2"/>
     <div class="contenedor">
         
         <asp:ScriptManager runat="server" />
         <asp:UpdatePanel runat="server">
             <ContentTemplate>

         <asp:HiddenField runat="server" id="confirmar"/>

         <table style="width: 100%; table-layout: fixed">
             <tr>
                 <td style="width: 3%" rowspan="9"></td>
                 <td style="width: 22%; padding-right: 2%" rowspan="8">
                    <asp:Image ID="imglib" runat="server" ImageUrl="../img/book-img.png" Width="100%" class="imginfo"/>
                 </td>
                 <td style="width: 100px">
                    <label runat="server" id="lbltitulo" class="lblinfo">Titulo:</label>
                 </td>
                 <td style="width: 65%">
                    <asp:TextBox ID="txttitulo" runat="server" class="txtedit" MaxLength="120"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">Autor:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtautor" runat="server" class="txtinfo" ReadOnly="true" MaxLength="150"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">Editorial:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txteditorial" runat="server" class="txtinfo" ReadOnly="true" MaxLength="30"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">Genero:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtgenero" runat="server" class="txtinfo" ReadOnly="true" MaxLength="45"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">Idioma:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtidioma" runat="server" class="txtinfo" ReadOnly="true" MaxLength="20"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">Año:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtyear" runat="server" class="txtinfo" ReadOnly="true" MaxLength="12"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td>
                    <label class="lblinfo">ISBN:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtisbn" runat="server" class="txtinfo" ReadOnly="true" MaxLength="13"></asp:TextBox>
                 </td>
             </tr>
             <tr>
                 <td style="vertical-align: text-top; padding-top: 18px">
                    <label class="lblinfo">Sinopsis:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="txtsinopsis" runat="server" class="txtinfo" ReadOnly="true" TextMode="MultiLine" Rows="4" MaxLength="500"></asp:TextBox>
                 </td>
             </tr>
             <tr runat="server" id="trDir">
                 <td colspan="3">
                    <div style="width: 100%">
                        <label class="lblinfo" style="width: 100px">Url portada del libro:</label>
                        <asp:TextBox ID="txtportada" runat="server" class="txtedit" MaxLength="1000" onchange="cambiarImg()"></asp:TextBox>
                    </div>
                     <br />
                    <div style="width: 100%">
                        <label class="lblinfo" style="width: 100px">Url PDF libro virtual:</label>
                        <asp:TextBox ID="txtpdf" runat="server" class="txtedit" MaxLength="1000"></asp:TextBox>
                    </div>
                 </td>
             </tr>
             <tr runat="server" id="trBtn">
                 <td colspan="4" style="padding-top: 20px">
                     <div runat="server" id="divSave" class="divbtn" style="margin-left: 2%">
                        <button type="button" id="btnSave" class="btnInfo btnSave" runat="server" style="margin-left: 23%" onserverclick="btnSave_ServerClick">
                            <i class="fas fa-book blanco"></i>
                            Guardar
                        </button>
                    </div>
                     <div runat="server" class="divbtn">
                        <button type="submit" id="btnDel" class="btnInfo btnDelete" runat="server" style="margin-right: 25%" onclick="Confirm()">
                            <i class="fas fa-trash-alt blanco"></i>
                            Eliminar
                        </button>
                     </div>
                 </td>
             </tr>
         </table>
             
             </ContentTemplate>
         </asp:UpdatePanel>

     </div>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
        <script src="assets/js/user.js"></script>
        <script type="text/javascript">
            function Confirm() {
                var conf = document.getElementById("<%=confirmar.ClientID%>");
                if (confirm("¿Deseas eliminar este libro?")) {
                    conf.value = "delete";
                    __doPostBack();
                } else {
                    //conf.value = "cancel";
                }
                //console.log(conf.value);
            }

            function cambiarImg() {
                var txt = document.getElementById("<%=txtportada.ClientID%>");
                var img = document.getElementById("<%=imglib.ClientID%>");

                var str = txt.value;
                if (!str || 0 === str.length) {
                    img.src = "../img/book-img.png";
                } else {
                    img.src = txt.value;
                }
            }

        </script>

    </div>
    </form>
</body>

</html>
