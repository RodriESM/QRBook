<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="user.aspx.cs" Inherits="QRBookWeb.user" %>

<!DOCTYPE html>


<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
<title>Perfil</title>
<meta name="viewport" content="width=device-width, user-scalable=yes, initial-scale=1.0, maximum-scale=3.0, minimum-scale=1.0">
<link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.6.3/css/all.css" >
<link rel="stylesheet" href="assets/css/estilos.css">
<link rel="stylesheet" type="text/css" href="assets/css/user.css">
    <script type="text/javascript">
        function salir() {
            window.localStorage.setItem('user', '0');
            window.localStorage.clear();
            location.replace("index.aspx");
        }

    </script>
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
                         <div class="dropdown" id="dropdown">
                        <input type="image"  src="img/user-img.png" height="60px"  width="50px" id="user"  onclick="location.href = 'user.aspx';" /> 
                          <div class="dropdown-content">
                            <a href="#">Perfil</a>
                            <a onclick="salir()">Salir</a>
                          </div>
                        </div>
                        <input id="registro" class="btn-registro" type="button" onclick="location.href='registro.aspx';" value="Registro" />
                        <input id="inicio" class="btn-inicio" type="button" onclick="location.href='login.aspx';" value="Inicio de sesion" style="margin-right: 15px" />
                       <!--<a href="#" id="enlace-contacto" class="btn-header">Inicio</a>--> 
                    </div>
                    <div class="icono" id="open">
                        <span>&#9776;</span>
                    </div>
                </div>
            </nav>
        </header>
        <button onclick="topFunction()" class="btn-top" id="btn-top" title="Subir">Subir</button>

         <!--Sección del USUARIO-->
         <section>
               <div class="row-img">
                  <div class="col-img">
                        <img width="100px" src="img/user-img.png"/>
                  </div>
               </div>
               <div class="row-nombre">
                  <div class="col-nombre">
                        <h3>Nombre</h3>
                        <p>Lore ipsum...</p>
                  </div>
               </div>

               <!--Ficha-->
                  <div class="usuario">

                           <div class="col">
                              <hr>
                           </div>
                        <!--Nobre y apellidos-->
                           <div class="col-md-6">
                                 <label class="label"><p>Nombre*</p><input class="input" type="TextBox" name="Full-name"></label><br>
                                 <label class="label"><p>Apellido1</p><input class="input" type="TextBox" name="Full-name"></label><br>
                                 <label class="label"><p>Apellido2</p><input class="input" type="TextBox" name="Full-name"></label><br>
                                 <!-- <asp:TextBox CssClass="form-control" ID="TextBox3" runat="server" placeholder="Full Name"></asp:TextBox> -->
                           </div>

                           <!--Fecha-->
                            <div class="col-md-7">
                              <div class="fecha">
                                 <label class="label"><p>Cumpleaños</p><input type="Date" name="Fecha"></label><br>
                                 <!--<asp:TextBox CssClass="form-control" ID="TextBox4" runat="server" placeholder="Password" TextMode="Date"></asp:TextBox>
                              </div>-->
                           </div>
                              <div class="tlf">
                                 <!--<asp:TextBox CssClass="form-control" ID="TextBox5" runat="server" placeholder="Contact No" TextMode="Number"></asp:TextBox>-->
                                 <label class="label"><p>Teléfono</p><input type="tlf" name="Telefono"></label><br>
                              </div>
                               <div class="email">
                                 <!--<asp:TextBox CssClass="form-control" ID="TextBox6" runat="server" placeholder="Email ID" TextMode="Email"></asp:TextBox>-->
                                 <label class="label"><p>Email</p><input type="Email" name="Email"></label><br>
                              </div>
                           </div>
       					

                        <!--Datos de ciudad-->
                           <div class="col-md-6">
                              <div class="form-group">
                                 <label class="label"><p>Provincia</p><input type="TextBox" name="Provincia"></label><br>
                                 <!--<asp:TextBox class="form-control" ID="TextBox7" runat="server" placeholder="City"></asp:TextBox>-->
                                 <label class="label"><p>Ciudad</p><input type="City" name="Ciudad"></label><br>
                                 <!-- <asp:TextBox class="form-control" ID="TextBox8" runat="server" placeholder="Pincode" TextMode="Number"></asp:TextBox>-->
                                 <label class="label"><p>Código postal</p><input type="CP" name="Pincode"></label><br>
                                 <!-- <asp:TextBox class="form-control" ID="TextBox9" runat="server" placeholder="Pincode" TextMode="Number"></asp:TextBox>-->
                                  <label class="label"><p>Dirección</p><input type="TextBox" name="Direccion"></label><br>
                              <!--<div class="form-group">
                                 <asp:TextBox CssClass="form-control" ID="TextBox10" runat="server" placeholder="Full Address" TextMode="MultiLine" Rows="2"></asp:TextBox>-->
                                                         <!--Datos titulo credenciales-->
                              </div>
                           </div>

                     <!--Contraseñas-->
                   <div class="col-md-7" id="boxpwd">
                      <div class="titulo-credenciales">
                         <h4 >Contraseña</h4>
                      </div><br>

                        <label class="labelpassword"><p>Antigua</p><input ID="txtRNewPassword" type="Password"></label><br>
                     
                        <!-- <asp:TextBox class="form-control" ID="TextBox11" runat="server" placeholder="Email ID" TextMode="Password" ReadOnly="True"></asp:TextBox> -->

                        <label class="labelpassword"><p>Nueva</p><input ID="txtLastPassword" type="Password"></label><br>
                        <label class="labelpassword"><p>Repetir nueva</p><input class="pwd" ID="txtNewPassword" type="password"></label><br>
                        <button style="float: right; margin-right:  15px;"  id="show_password" class="button-user" type="button" onmousedown="mostrarPassword()"> <i class="fa fa-eye-slash icon"></i></button>
                        <!-- <asp:TextBox class="form-control" ID="TextBox12" runat="server" placeholder="Email ID" TextMode="Password"></asp:TextBox> -->

                     </div>
						<div class="div-button-save">
                           <button class="button-save"><b>Guardar</b></button>
                        </div>
           		</div>

         <!--A PARTIR DE AQUI VAN LOS LIBROS-->

            
            <div class="libros">
                <div class="row">
                  <div class="col">
                     <hr>
                  </div>
               </div>
               <div class=""col-img>
               <center>
                  <img width="100px" src="img/library.png"/>
               </center>
            </div>
            <div class="col">
               <center>
                  <h4>Libros</h4>
               </center>
            </div>
              
               <div class="card">
                  <div class="card-body">
                     <div class="row">
                        <div class="col">
                           <!--<asp:GridView class="table table-striped table-bordered" ID="GridView2" runat="server"></asp:GridView>-->
                        </div>
                     </div>
                  </div>
               </div>
           </div>
         </section>

         <footer>
            
         </footer>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
        <script src="assets/js/user.js"></script>

      </form>
   </body>
</html>
