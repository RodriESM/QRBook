<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="buscarLib.aspx.cs" Inherits="QRBookWeb.buscarLib" EnableEventValidation="false" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
<link rel="stylesheet" href="assets/css/objsearch.css">
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
                        <img alt="logo" src="img/Logo.png" width="100px"/>
                    </div>
                    <div class="enlaces" id="enlaces">
                        <a href="buscarLib.aspx" id="busqLib" class="btn-header" runat="server">Buscar Libros</a>
                        <a href="buscarUsu.aspx" id="busqUsu" class="btn-header" runat="server">Buscar Usuarios</a>
                        <a href="index.aspx#Inicio" id="enlace-inicio" class="btn-header">Inicio</a>
                        <a href="index.aspx#Informacion" id="enlace-info" class="btn-header">Información</a>
                        <a href="index.aspx#Desarrollo" id="enlace-app" class="btn-header">Desarrollo</a>
                        <a href="index.aspx#Equipo" id="enlace-equipo" class="btn-header">Conocenos</a>
                        <div runat="server" class="dropdown" id="dropdown">
                            <input type="image"  src="img/user-img.png" height="60px"  width="50px" id="user"  onclick="location.href = 'user.aspx';" /> 
                            <div class="dropdown-content">
                                <a href="user.aspx">Perfil</a>
                                <!--<a href="#" onclick="salir()">Salir</a>-->
                                <asp:LinkButton id="btnSalir" class="btn-salir" runat="server" Text="Salir" OnClick="btnSalir_Click" />
                            </div>
                        </div>
                        <input runat="server" id="registro" class="btn-registro" type="button" onclick="location.href = 'registro.aspx';" value="Registro" />
                        <input runat="server" id="inicio" class="btn-inicio" type="button" onclick="location.href = 'login.aspx';" value="Inicio de sesión" style="margin-right: 15px" />
                       <!--<a href="#" id="enlace-contacto" class="btn-header">Inicio</a>--> 
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>

<div class="formularioBus">
    <h1 class="tituloBus">Buscar Libros</h1>
     <div class="contenedor">
         
         <asp:ScriptManager runat="server" />
         <asp:UpdatePanel runat="server">
             <ContentTemplate>

         <asp:HiddenField runat="server" id="qstr"/>

         <table style="width: 100%; table-layout: fixed">
             <tr>
                 <th style="width: 100%">

         <div class="input-contenedor">
             <!-- <i class="fas fa-search icon"></i> -->
             <input runat="server" type="text" id="txtBus" class="txtBus" placeholder="Buscar..." maxlength="50">
         </div>
                 </th>
                 <th style="width: 230px">
         <div class="input-contenedor">
             <asp:DropDownList runat="server" ID="ddnBus" CssClass="txtBus" style="min-width: 220px">
                 <asp:ListItem Value="TITULO" Selected="True">Título</asp:ListItem>
                 <asp:ListItem Value="AUTOR">Autor</asp:ListItem>
                 <asp:ListItem Value="EDITORIAL">Editorial</asp:ListItem>
                 <asp:ListItem Value="GENERO">Género</asp:ListItem>
                 <asp:ListItem Value="ISBN">ISBN</asp:ListItem>
             </asp:DropDownList>
         </div>
                 </th>
                 <th style="width: 80px">
                    <button type="submit" id="btnbus" class="btnbus" runat="server"><i class="fas fa-search blanco"></i></button>
                 </th>
             </tr>
             <tr>
                 <td colspan="3">
                     <div class="resultados" runat="server" id="locodiv">

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

        </script>

    </div>
    </form>
</body>

</html>
