<%@page import="javax.websocket.Session"%>
<%@page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="modelo.*,modeloDAO.*,java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>



    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="assets/css/animate.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">



</head>
<% HttpSession sesion = request.getSession();
            Usuario usuario = (Usuario)sesion.getAttribute("usuario");
	        if(usuario == null){%>
<body class="gray-bg">

    <div class="middle-box text-center loginscreen animated fadeInDown">
        <div>
            <div>

                <h1 class="logo-name">Spa</h1>

            </div>
            <h3>Bienvenido a Spinetti Spa Gym</h3>
            <!--<p>Perfectly designed and precisely prepared admin theme with over 50 pages with extra new web app views.
                Continually expanded and constantly improved Inspinia Admin Them (IN+)
            </p>-->
            <p>Iniciar Sesión.</p>
            <form action="ControladorSesion?operacion=entrar" method="post">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Usuario" name="usuario" id="usuario"  required="">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" placeholder="Contraseña" name="contra" id="contra" required="">
                </div>
                <button type="submit" class="btn btn-primary block full-width m-b">Iniciar sesión</button>

                <a href="#"><small>Olvidó su contraseña?</small></a>
                <p class="text-muted text-center"><small>¿No tiene una cuenta?</small></p>
                <a class="btn btn-sm btn-white btn-block" href="register.html">Crea una cuenta</a>
            </form>
            <p class="m-t"> <small>Bambú</small> </p>
        </div>
    </div>

    

</body>
	
	<%
	}
	%>

    <!-- Mainly scripts -->
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>

</body>
</html>