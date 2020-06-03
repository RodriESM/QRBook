<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="registro.aspx.cs" Inherits="QRBookWeb.registro" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml" lang="es">

<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Registro</title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
    <script type="text/javascript">

    </script>
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
                        <a href="buscarLib.aspx" id="busqLib" class="btn-header" runat="server">Buscar Libros</a>
                        <a href="index.aspx#Inicio" id="enlace-inicio" class="btn-header">Inicio</a>
                        <a href="index.aspx#Informacion" id="enlace-info" class="btn-header">Información</a>
                        <a href="index.aspx#Desarrollo" id="enlace-app" class="btn-header">Desarrollo</a>
                        <a href="index.aspx#Equipo" id="enlace-equipo" class="btn-header">Conocenos</a>
                         <!-- <div class="dropdown" id="dropdown">
                        <input type="image"  src="img/user-img.png" height="60px"  width="50px" id="user"  onclick="location.href = 'user.aspx';" /> 
                          <div class="dropdown-content">
                            <a href="user.aspx">Perfil</a>
                            <a onclick="salir()">Salir</a>
                          </div>
                        </div> -->
                        <input id="registro" class="btn-registro" type="button" onclick="location.href='registro.aspx';" value="Registro" />
                        <input id="inicio" class="btn-inicio" type="button" onclick="location.href = 'login.aspx';" value="Inicio de sesion" style="margin-right: 15px" />
                       <!--<a href="#" id="enlace-contacto" class="btn-header">Inicio</a>--> 
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>

<div style="margin-top: 5%">
    <form id="form1" runat="server" class="formulario">
    
    <h1>Registrate</h1>
    <div class="contenedor" onkeyup="btnregistro()">
     
         <div class="input-contenedor">
             <i class="fas fa-user icon"></i>
             <asp:TextBox class="txtbox" id="usuario" placeholder="Nombre de Usuario" runat="server" ValidationGroup="usuario" MaxLength="30" onkeypress="touchusu()"/>
         </div>
         <div class="validador-contenedor2" id="contusuario" runat="server">
             <asp:RequiredFieldValidator runat="server" class="validador" ID="requsuario" ControlToValidate="usuario"
                 ErrorMessage="Nombre de usuario obligatorio." ValidationGroup="usuario" Display="Dynamic" />
             <asp:CustomValidator runat="server" class="validador" ID="cususuario" ControlToValidate="usuario"
                 ErrorMessage="El nombre de usuario ya existe." ValidationGroup="usuario" Display="Dynamic" /> 
         </div>

         <div class="input-contenedor">
             <i class="fas fa-envelope icon"></i>
             <asp:TextBox class="txtbox" id="correo" placeholder="Correo Electronico" runat="server" ValidationGroup="correo" MaxLength="50" onkeypress="touchcor()"/>
         </div>
         <div class="validador-contenedor2" id="contcorreo" runat="server">
             <asp:RequiredFieldValidator runat="server" class="validador" ID="reqcorreo" ControlToValidate="correo"
                 ErrorMessage="Correo electrónico obligatorio." ValidationGroup="correo" Display="Dynamic" />
             <asp:RegularExpressionValidator runat="server" class="validador" ID="valcorreo" ControlToValidate="correo" 
                 ErrorMessage="Formato de correo inválido." EnableViewState="true"
                 ValidationExpression="^\w+@[a-zA-Z_]+?\.[a-zA-Z]{2,3}$" ValidationGroup="correo" Display="Dynamic"/>
             <asp:CustomValidator runat="server" class="validador" ID="cuscorreo" ControlToValidate="correo"
                 ErrorMessage="Ya existe un usuario con este correo electrónico." ValidationGroup="correo" Display="Dynamic" />
         </div>
         
         <div class="input-contenedor">
             <i class="fas fa-key icon"></i>
             <asp:TextBox class="txtbox" TextMode="Password" id="pass" placeholder="Contraseña" runat="server" ValidationGroup="pass" MaxLength="30" onkeypress="touchpass()"/>
         </div>
         <div class="validador-contenedor2">
             <asp:RequiredFieldValidator runat="server" class="validador" ID="reqpass" ControlToValidate="pass"
                 ErrorMessage="Contraseña obligatoria." ValidationGroup="pass" Display="Dynamic" />
             <asp:RegularExpressionValidator runat="server" class="validador" ID="valpass" ControlToValidate="pass" 
                 ErrorMessage="Minimo 8 caracteres, con mayúsculas, minúsculas y números."
                 ValidationExpression="^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$" ValidationGroup="pass" Display="Dynamic"/>

             <!-- Letras y numeros: ^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$ -->
             <!-- Mayus, minus y numeros: ^(?=.*[a-z])(?=.*[A-Z])(?=.*\d)[a-zA-Z\d]{8,}$ -->

         </div>


         <asp:Button id="btnregistro" class="button" runat="server" Text="Registrate" OnClick="Registro_Click"/>
         <!--<p>Al registrarte, aceptas nuestras Condiciones de uso y Política de privacidad.</p> -->
         <p>¿Ya tienes una cuenta? <asp:LinkButton runat="server" class="link"  href="login.aspx">Iniciar Sesión</asp:LinkButton></p>
         

     </div>

        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
        <script src="assets/js/user.js"></script>
        <script>
            function touchusu() {
                var req = document.getElementById("<%=requsuario.ClientID%>");

                if (req.innerHTML.includes("UNTOUCHED")) {
                    console.log("xD");
                    var cus = document.getElementById("<%=cususuario.ClientID%>");
                    req.innerHTML = req.innerHTML.split("UNTOUCHED")[0];
                    cus.innerHTML = cus.innerHTML.split("UNTOUCHED")[0];
                }
            }
            function touchcor() {
                var req = document.getElementById("<%=reqcorreo.ClientID%>");

                if (req.innerHTML.includes("UNTOUCHED")) {
                    console.log("xD");
                    var val = document.getElementById("<%=valcorreo.ClientID%>");
                    var cus = document.getElementById("<%=cuscorreo.ClientID%>");
                    req.innerHTML = req.innerHTML.split("UNTOUCHED")[0];
                    val.innerHTML = val.innerHTML.split("UNTOUCHED")[0];
                    cus.innerHTML = cus.innerHTML.split("UNTOUCHED")[0];
                }
            }
            function touchpass() {
                var req = document.getElementById("<%=reqpass.ClientID%>");

                if (req.innerHTML.includes("UNTOUCHED")) {
                    console.log("xD");
                    var val = document.getElementById("<%=valpass.ClientID%>");
                    req.innerHTML = req.innerHTML.split("UNTOUCHED")[0];
                    val.innerHTML = val.innerHTML.split("UNTOUCHED")[0];
                }
            }

            function btnregistro() {
                var btn = document.getElementById("<%=btnregistro.ClientID%>");

                if (Page_Validators != undefined && Page_Validators != null) {

                    var invalid = false;
                    var divs = [];
                    for (var i = 0; i < Page_Validators.length; i++) {
                        var validator = Page_Validators[i];
                        ValidatorEnable(Page_Validators[i]);

                        if (!validator.isvalid) {
                            invalid = true;
                            if (validator.innerHTML.includes("UNTOUCHED")) {
                                validator.isvalid = true;
                                validator.style = "display: none";
                                validator.parentElement.className = "validador-contenedor2";
                            } else {
                                validator.parentElement.className = "validador-contenedor";
                                divs.push(validator.parentElement);
                            }
                        } else {
                            if (!divs.includes(validator.parentElement)) {
                                validator.parentElement.className = "validador-contenedor2";
                            }
                        }
                    }
                    if (invalid) {
                        btn.disabled = true;
                        btn.className = "buttondis";
                    } else {
                        btn.disabled = false;
                        btn.className = "button";
                    }
                }
            }


            function btnregistro2() {
                var btn = document.getElementById("<%=btnregistro.ClientID%>");



                if (Page_Validators != undefined && Page_Validators != null) {

                    var usu = [];
                    var cor = [];
                    var pass = [];

                    var invalid = false;
                    for (var i = 0; i < Page_Validators.length; i++) {
                        var validator = Page_Validators[i];

                        if (validator.id.includes("usuario")) {
                            usu.push(validator);
                        } else if (validator.id.includes("correo")) {
                            cor.push(validator);
                        } else {
                            pass.push(validator);
                        }

                        ValidatorEnable(Page_Validators[i]);

                        if (!validator.isvalid) {
                            invalid = true;
                            if (validator.innerHTML.includes("UNTOUCHED")) {
                                validator.isvalid = true;
                                validator.style = "display: none";
                            }
                        }
                    }
                    if (invalid) {
                        btn.disabled = true;
                        btn.className = "buttondis";
                    } else {
                        btn.disabled = false;
                        btn.className = "button";
                    }
                }
            }
        </script>
    </form>
</div>
</body>

</html>
