<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="registro.aspx.cs" Inherits="QRBookWeb.registro" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="es">

<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title></title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
</head>

<body class="hidden">
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
        <header>
            <a name="Inicio"/>
            <nav id="nav">
                <div class="contenedor-nav ">
                    <div class="logo">
                        <img alt="logo" src="img/Logo.png" width="100px"/>
                    </div>
                    <div class="enlaces" id="enlaces">
                        <a href="index.aspx#Inicio" id="enlace-inicio" class="btn-header">Inicio</a>
                        <a href="index.aspx#Informacion" id="enlace-info" class="btn-header">Información</a>
                        <a href="index.aspx#Desarrollo" id="enlace-app" class="btn-header">Desarrollo</a>
                        <a href="index.aspx#Equipo" id="enlace-equipo" class="btn-header">Conocenos</a>
                        <input class="btn-registro" type="button" onclick="location.href='registro.aspx';" value="Registro" />
                        <input class="btn-inicio" type="button" onclick="location.href = 'login.aspx';" value="Inicio de sesion" style="margin-right: 15px" />
                       <!--<a href="#" id="enlace-contacto" class="btn-header">Inicio</a>--> 
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>

<div class="separacion">
    <form id="form1" runat="server" class="formulario">
    
    <h1>Registrate</h1>
     <div class="contenedor">
     
     <div class="input-contenedor">
         <i class="fas fa-user icon"></i>
         <input type="text" id="usuario" placeholder="Nombre de Usuario" runat="server">
         
         </div>
         
         <div class="input-contenedor">
         <i class="fas fa-envelope icon"></i>
         <input type="text" id="correo" placeholder="Correo Electronico" runat="server">
         
         </div>
         
         <div class="input-contenedor">
        <i class="fas fa-key icon"></i>
         <input type="password" id="pass" placeholder="Contraseña" runat="server">
         
         </div>
         <asp:Button class="button" runat="server" Text="Registrate" OnClick="Registro_Click"/>
         <p>Al registrarte, aceptas nuestras Condiciones de uso y Política de privacidad.</p>
         <p>¿Ya tienes una cuenta? <asp:LinkButton runat="server" class="link"  href="login.aspx" OnClick="InicioSesion_Click">Iniciar Sesión</asp:LinkButton></p>
     </div>

        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
    </form>
</div>
</body>

</html>
