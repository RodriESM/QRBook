<%@ Page Language="C#" AutoEventWireup="true" CodeBehind="index.aspx.cs" Inherits="QRBookWeb.index" %>

<!DOCTYPE html>

<html xmlns="http://www.w3.org/1999/xhtml">
<head runat="server">
    <meta http-equiv="Content-Type" content="text/html" charset="utf-8" />
    <meta lang="es" />
    <meta http-equiv="X-UA-Compatible" />
    <title>QRBook Inicio</title>
    <link rel="stylesheet" type="text/css" href="style.css" />
    <link href="/your-path-to-fontawesome/css/fontawesome.css" rel="stylesheet">
    <link href="/your-path-to-fontawesome/css/brands.css" rel="stylesheet">
    <link href="/your-path-to-fontawesome/css/solid.css" rel="stylesheet">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.3.1/css/all.css" integrity="sha384-mzrmE5qonljUremFsqc01SB46JvROS7bZs3IO2EmfFsd15uHvIt+Y8vEf7N7fWAU"
        crossorigin="anonymous">
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
            <nav id="nav">
                <div class="contenedor-nav ">
                    <div class="logo">
                        <img alt="logo" src="img/Logo.png" width="100px"/>
                    </div>
                    <div class="enlaces" id="enlaces">
                        <a href="#" id="enlace-inicio" class="btn-header">Inicio</a>
                        <a href="#" id="enlace-info" class="btn-header">Inicio</a>
                        <a href="#" id="enlace-equipo" class="btn-header">Inicio</a>
                        <a href="#" id="enlace-app" class="btn-header">Inicio</a>
                        <a href="#" id="enlace-contacto" class="btn-header">Inicio</a>
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

            <!--SECCION DE PERFILES-->
           <section class="team contenedor" id="equipo">
            <h3>Nuestro equipo</h3>
            <p class="after">Nuestro equipo</p>
            <div class="card">
                <div class="content-card">
                    <div class="people">
                        <img src="img/rodri.jpeg" alt="Rodrigo">
                    </div>
                    <div class="texto-team">
                        <h4>Rodrigo</h4>
                        <p>Lorem ipsum dolor sit.</p>
                    </div>
                </div>
                <div class="content-card">
                    <div class="people">
                        <img src="img/ivan.jpg" alt="Iván">
                    </div>
                    <div class="texto-team">
                        <h4>Iván</h4>
                        <p>Lorem ipsum dolor sit.</p>
                    </div>
                </div>
                <div class="content-card">
                    <div class="people">
                        <img src="img/quique.jpg" alt="Enrique">
                    </div>
                    <div class="texto-team">
                        <h4>Enrique</h4>
                        <p>Lorem ipsum dolor sit.</p>
                    </div>
                </div>
            </div>
        </section>

        <!--SECCION DE FUNCIONES-->
        <section class="about" id="servicio">
            <div class="contenedor">
                <h3>Nuestra aplicación</h3>
                <p class="after">Una forma nueva de ver los libros</p>
                <div class="servicios">
                    <div class="caja-servicios">
                        <img src="img/iconbook.png" alt="">
                        <h4>Creativos y asombrosos</h4>
                        <p>Lorem ipsum dolor sit amet consectetur.</p>
                    </div>
                    <div class="caja-servicios">
                        <img src="img/responsive.png" alt="">
                        <h4>Creativos y asombrosos</h4>
                        <p>Lorem ipsum dolor sit amet consectetur.</p>
                    </div>
                    <div class="caja-servicios">
                        <img src="img/efectos.png" alt="">
                        <h4>Creativos y asombrosos</h4>
                        <p>Lorem ipsum dolor sit amet consectetur.</p>
                    </div>
                </div>
            </div>
        </section>

            <!--SECCION DE DESCRIPCION DE LIBROS-->
        <section class="work contenedor" id="trabajo">
            <h3>Nuestro trabajo</h3>
            <p class="after">Hacemos de algo simple algo extraordinario</p>
            <div class="botones-work">
                <ul>
                    <li class="filter" data-nombre='marketing'>Marketing</li>
                </ul>
            </div>
            <div class="galeria-work">
                
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing1.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Marketing</h4>
                    </div>
                </div>
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing2.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Marketing</h4>
                    </div>
                </div>
                <div class="cont-work marketing">
                    <div class="img-work">
                        <img src="img/marketing3.jpeg" alt="">
                    </div>
                    <div class="textos-work">
                        <h4>Marketing</h4>
                    </div>
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
                <i class="fab fa-youtube"></i>
                <i class="fab fa-facebook-square"></i>
                <i class="fab fa-github"></i>
            </div>
            <p>La pasión e innovación es lo que nos distingue del resto</p>
        </div>
        </footer>
        <script src="main.js"></script>
        <script src="jquery.js"></script>
        <script src="filtro.js"></script>
    </form>
</body>
</html>
