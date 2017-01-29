<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
   <%@ include file="../estilos/css.jsp"%>
</head>
<body>
<div id="wrapper">
<%@ include file="../../dashboard.jsp"%>


 
<div id="page-wrapper" class="gray-bg"  >
<%@ include file="../../cabecera.jsp"%>
<div class="col-md-12" >
<div class="wrapper wrapper-content animated fadeInRight">
<div class="row" >
            
                <div class="col-lg-9" >
                    <div class="ibox float-e-margins">
                        <div class="ibox-title">
                            <h5></h5>
                            <div class="ibox-tools">
                                <a class="collapse-link">
                                    <i class="fa fa-chevron-up"></i>
                                </a>
                                <a class="dropdown-toggle" data-toggle="dropdown" href="#">
                                    <i class="fa fa-wrench"></i>
                                </a>
                                <ul class="dropdown-menu dropdown-user">
                                    <li><a href="#">Config option 1</a>
                                    </li>
                                    <li><a href="#">Config option 2</a>
                                    </li>
                                </ul>
                                <a class="close-link">
                                    <i class="fa fa-times"></i>
                                </a>
                            </div>
                        </div>
                        <div class="ibox-content">
                            <form class="form-horizontal">
                            <div class="form-group"><label class="col-lg-2 control-label">Cedula</label>
                            <div class="col-lg-10"><input type="text" placeholder="cedula" class="form-control required"> 
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">Nombre</label>

                                    <div class="col-lg-10"><input type="text" placeholder="Nombre" class="form-control required"> 
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">Apellido:</label>

                                    <div class="col-lg-10"><input type="text" placeholder="Apellido" class="form-control required"></div>
                                </div>
                                
                                 <div class="form-group"><label class="col-lg-2 control-label">Direccion:</label>

                                    <div class="col-lg-10"><input type="text" placeholder="Direccion" class="form-control required"></div>
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">Correo:</label>

                                    <div class="col-lg-10"><input type="e-mail" placeholder="Correo" class="form-control required"></div>
                                </div>
                                
                                 <div class="form-group"><label class="col-lg-2 control-label">Telefono:</label>

                                    <div class="col-lg-10"><input type="text" placeholder="Telefono" class="form-control required"></div>
                                </div>
                                
                                <div class="form-group">
                                    <div class="col-lg-offset-2 col-lg-5">
                                        <button class="btn btn-primary block full-width m-b" type="submit" onclick="_gaq.push(['_trackEvent', 'example, 'try', 'Success']);">Guardar</button>
                                    </div>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            </div>
            </div>
            </div>
            </div>
</body>
 <%@ include file="../estilos/javascript.jsp"%>
</html>