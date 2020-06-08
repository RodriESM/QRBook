<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="user.aspx.cs" Inherits="QRBookWeb.user" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
<link rel="stylesheet" href="assets/css/user.css">
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
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>

<div class="formularioBus">
    <h1 runat="server" id="titulo" class="tituloBus" style="white-space: nowrap; overflow-x: hidden">Titulo</h1>
     <div class="contenedor" style="height: 420px">
         
         <asp:ScriptManager runat="server" />

         <table style="height: 100%; table-layout: fixed">
             <tr>
             <td style="width: 60%">

         <div class="boxPassLeft boxPassRight boxPassTop boxPassBottom" style="display: inline-block; padding-top: 10px; border-radius: 5px 5px; height: 100%">
         <asp:UpdatePanel runat="server">
             <ContentTemplate>
                 
         <asp:HiddenField runat="server" id="confirmar"/>

         <table style="width: 100%; table-layout: fixed">
             <tr>
                 <td style="width: 100px; height: 34px">
                    <label class="lblinfousu">Correo:</label>
                 </td>
                 <td style="width: 100%; padding-right: 2%">
                     <asp:TextBox ID="email" runat="server" class="txtinfo" style="font-size: 15px" disabled="disabled"/>
                 </td>
                 <td style="width: 10px" rowspan="10"></td>
                 <td style="width: 120px">
                    <label class="lblinfousu" style="font-size: 15px; font-weight: bold">Fecha de nacimiento:</label>
                 </td>
                 <td style="width: 100%">
                     <asp:TextBox ID="birth" runat="server" class="txteditusu" type="date" style="font-size: 15px"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu">Usuario:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="usu" runat="server" class="txtinfo" style="font-size: 15px" disabled="disabled"></asp:TextBox>
                 </td>
                 <td>
                    <label class="lblinfousu">Telefono:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="tlf" runat="server" class="txteditusu" type="number" max="999999999" min="1"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu">Nombre:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="nom" runat="server" class="txteditusu" MaxLength="30"/>
                 </td>
                 <td>
                    <label class="lblinfousu">Provincia:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="prov" runat="server" class="txteditusu" MaxLength="30"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu">1er Apellido:</label>
                 </td>
                 <td>
                    <asp:TextBox ID="ape1" runat="server" class="txteditusu" MaxLength="30"/>
                 </td>
                 <td>
                    <label class="lblinfousu">Ciudad:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="city" runat="server" class="txteditusu" MaxLength="50"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu">2º Apellido:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="ape2" runat="server" class="txteditusu" MaxLength="30"/>
                 </td>
                 <td>
                    <label class="lblinfousu">Dirección:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="dir" runat="server" class="txteditusu" MaxLength="50"/>
                 </td>
             </tr>
             <tr>
                 <td class="boxPassTop" style="height: 34px; border-radius: 5px 0">
                    <label class="lblinfousu" style="font-size: 15px; font-weight: bold">Antigua contraseña:</label>
                 </td>
                 <td class="boxPassTop boxPassRight" style="border-radius: 0 5px">
                     <asp:TextBox ID="txtLastPassword" runat="server" class="txteditusu" TextMode="Password" MaxLength="30"/>
                 </td>
                 <td>
                    <label class="lblinfousu">Código postal:</label>
                 </td>
                 <td>
                     <asp:TextBox ID="cp" runat="server" class="txteditusu" type="number" max="99999" min ="1000"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu" style="font-size: 15px; font-weight: bold">Nueva contraseña:</label>
                 </td>
                 <td class="boxPassRight">
                     <asp:TextBox ID="txtNewPassword" runat="server" class="txteditusu" TextMode="Password" MaxLength="30"/>
                 </td>
                 <td rowspan="2">
                    <label runat="server" id="lblAdmin" class="lblinfousu">¿Administrador?:</label>
                 </td>
                 <td rowspan="2">
                     <asp:CheckBox runat="server" ID="chkAdmin" style="margin-left: 20px"/>
                 </td>
             </tr>
             <tr>
                 <td style="height: 34px">
                    <label class="lblinfousu" style="font-size: 15px; font-weight: bold">Repetir contraseña:</label>
                 </td>
                 <td class="boxPassRight">
                     <asp:TextBox ID="txtRNewPassword" runat="server" class="txteditusu" TextMode="Password" MaxLength="30"/>
                 </td>
             </tr>
             <tr>
                 <td class="boxPassBottom boxPassRight" style="height: 34px; text-align: center; border-radius: 5px 0" colspan="2">
                    <asp:Button runat="server" style="font-weight: bold; margin-right: 20%" id="btnguardarpass" class="btnPass" type="button" OnClick="btnguardarpass_Click" Text="  Guardar Contraseña  "/>
                    <button id="show_password" class="btnPass" type="button" onmousedown="mostrarPassword()">
                        <i class="fa fa-eye-slash icon" style="color: #555"></i>
                    </button>
                 </td>
             </tr>
             <tr>
                 <td runat="server" id="tdSave" colspan="2" style="text-align: center; height: 34px">
                    <button type="button" id="btnguardar" class="btnInfo btnSave" runat="server" style="font-size: 18px; padding: 5px 5px; height: 30px; max-width: 150px; width: 50%; min-width: 80px" onserverclick="btnguardar_Click">
                        Guardar
                    </button>
                 </td>
                 <td runat="server" id="tdDel" colspan="2" style="text-align: center">
                    <button type="button" id="btneliminar" class="btnInfo btnDelete" runat="server" style="font-size: 18px; padding: 5px 5px; height: 30px; max-width: 150px; width: 50%; min-width: 80px" onclick="Confirm()">
                        Eliminar
                    </button>
                 </td>
             </tr>
         </table>
             
             </ContentTemplate>
         </asp:UpdatePanel>
         </div>






         </td>
         <td style="width: 39.5%">
         
         <div class="boxPassRight boxPassTop boxPassBottom" style="display: inline-block; border-radius: 5px 5px; margin: 0 0; height: 100%; width: 100%">
         <asp:UpdatePanel runat="server">
             <ContentTemplate>

             <asp:HiddenField runat="server" id="qstr"/>
                  
            <div class="libros" style="width: 100%">
                <div class="row">
                  <div class="col">
                     <hr>
                  </div>
               </div>
                <div class="col-img">
                   <center>
                      <img width="60px" src="img/library.png"/>
                   </center>
                </div>
                <div class="col">
                   <center>
                      <h4 style="margin-bottom: 5px">Libros</h4>
                   </center>
                </div>
                
                <div runat="server" id="locodiv" style="margin: 0; width: 100%; height: 295px; overflow-y: scroll">

                </div>

            </div>


             </ContentTemplate>
         </asp:UpdatePanel>
         </div>
        </td>
        </tr>
        </table>

     </div>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
        <script src="assets/js/user.js"></script>
        <script type="text/javascript">
            function Confirm() {
                var conf = document.getElementById("<%=confirmar.ClientID%>");
                if (confirm("¿Deseas eliminar este usuario?")) {
                    conf.value = "delete";
                    __doPostBack();
                }
            }
        </script>

    </div>
    </form>
</body>

</html>
