<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="QRBookWeb.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html"; charset="utf-8" />
    <meta lang="es" />
    <meta http-equiv="X-UA-Compatible" />
    <title>QRBook Inicio</title>
    <link rel="stylesheet" type="text/css" href="assets/css/style.css" />
    <link href="/your-path-to-fontawesome/css/fontawesome.css" rel="stylesheet">
    <link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
    <link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
        crossorigin="anonymous">
        <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300&display=swap" rel="stylesheet">
    <script type="text/javascript">
        /*function salir() {
            window.localStorage.setItem('user', '0');
            window.localStorage.clear();
            location.reload();
        }*/
    </script>
</head>
<body class="hidden">
    <form id="form1" runat="server">
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
                        <a href="buscarUsu.aspx" id="busqUsu" class="btn-header" runat="server">Buscar Usuarios</a>
                        <a href="#Inicio" id="enlace-inicio" class="btn-header">Inicio</a>
                        <a href="#Informacion" id="enlace-info" class="btn-header">Información</a>
                        <a href="#Desarrollo" id="enlace-app" class="btn-header">Desarrollo</a>
                        <a href="#Equipo" id="enlace-equipo" class="btn-header">Conócenos</a>
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
            <div class="textos">
                <h1>QRBook</h1>
                <h2>Leer nunca fue tan divertido</h2>
            </div>
        </header>
        <main>

           

        <!--SECCION DE FUNCIONES-->
        <section class="about" id="servicio">
        	<a name="Informacion"/>
            <div class="contenedor">
                <h3>Nuestra aplicación</h3>
                <p class="after">Una nueva forma de ver los libros</p>
                <div class="servicios">
                    <div class="caja-servicios">
                        <img src="img/creativoasombroso.png" alt="">
                        <h4>Creativos y asombrosos</h4>
                        <p>Puedes ver con todo detalle el mundo que siempre habías imaginado.</p>
                    </div>
                    <div class="caja-servicios">
                        <img src="img/divertidoeducativo.png" alt="">
                        <h4>Divertidos y educativos</h4>
                        <p>Divertirse y aprender de una forma más entretenida, con QRBook es posible.</p>
                    </div>
                    <div class="caja-servicios">
                        <img src="img/clarosencillo.png" alt="">
                        <h4>Claro y sencillo</h4>
                        <p>Con solamente la cámara puedes disfrutar de todo.</p>
                    </div>
                </div>
            </div>
        </section>

            <!--SECCION DE DESCRIPCION DE LIBROS-->
        <section class="work contenedor" id="trabajo">
        	<a name="Desarrollo"/>
            <h3>Nuestro trabajo</h3>
            <p class="after" >Hacemos de algo simple algo extraordinario</p>
            <!--
            <div class="botones-work">
                <ul>
                    <input class="filter" type="button" onclick="location.href = 'index.html';" value="Inicio de sesión" style="margin-right: 15px" />
                </ul>
            </div>
            -->

            <div class="galeria-work">
                
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing1.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Prueba</h4>
                    </div>
                </div>
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing2.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Prueba</h4>
                    </div>
                </div>
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing3.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Prueba</h4>
                    </div>
                </div>
            </div>
        </section>

         <!--SECCION DE PERFILES-->
           <section class="team contenedor" id="equipo">
           	<a name="Equipo"/>
            <h3 class="after">Nuestro equipo</h3>
            <div class="card">
                <div class="content-card">
                    <div class="people">
                        <img src="img/rodri.jpeg" alt="Rodrigo">
                    </div>
                   
                </div>
                <div class="content-card">
                    <div class="people">
                        <img src="img/ivan.jpg" alt="Iván">
                    </div>
                    
                </div>
                <div class="content-card">
                    <div class="people">
                        <img src="img/quique.jpg" alt="Enrique">
                    </div>
                    
                </div>
            </div>
            <div class="descripciones">
            	<div class="texto-team">
                    <h4>Rodrigo</h4>
                    <p>Técnico Superior en Sistemas de Telecomunicaciones e Informáticos. Amante de la tecnología, la música y los viajes.</p>
                </div>
                <div class="texto-team">
                    <h4>Iván</h4>
                    <p>Estudiante de Grado Superior de Desarrollo de Multiplataforma. Fanático del cine antiguo y acompañante de la música.</p>
                </div>
                <div class="texto-team">
                    <h4>Enrique</h4>
                    <p>Técnico Superior de Desarrollo de Aplicaciones Web. Jugador de billar americano, cubos de rubik, pelo largo.</p>
                </div>
            </div>
             
        </section>

        </main>

        <!--FOOTER-->
        <footer>
            <div class="footer contenedor">
            <div class="marca-logo">
                <img src="img/logo.png" alt="logo-footer" width="100px">
            </div>
            <div class="iconos">
                <i class="fab fa-google-play"></i>
                <i class="fab fa-github"></i>
            </div>
            <p style="color: white">La pasión e innovación es lo que nos distingue del resto</p>
        </div>
        </footer>
        <script src="assets/js/main.js"></script>
        <script src="assets/js/jquery.js"></script>
        <script src="assets/js/filtro.js"></script>
        <script src="assets/js/user.js"></script>

    </form>
</body>
</html>
