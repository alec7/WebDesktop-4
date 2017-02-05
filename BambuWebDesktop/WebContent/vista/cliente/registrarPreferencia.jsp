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
                            <h5>Preferencias</h5>
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
                            
                            
                            <div class="form-group"><label class="col-lg-2 control-label">Cédula</label>
                                      <div class="col-sm-5"> <div class="input-group"><input type="text" class="form-control col-lg-2"> <span class="input-group-btn"> <button type="button" class="btn btn-primary">Buscar
                                        </button> </span></div>
                                </div>   
                                      
                                </div>
                           
                            
                            <div class="form-group"><label class="col-lg-2 control-label">Nombre</label>
                            <div class="col-sm-5"><input type="text" placeholder="" class="form-control required"> 
                                    </div>
                                </div>
                                <div class="form-group"><label class="col-lg-2 control-label">¿Qué servicio realizas con frecuencia?</label>
                                      <div class="col-sm-5"><select class="form-control m-b" name="account">
                                         <option></option>
                                        <option>Tratamiento de sauna y vapor</option>
                                        <option>Tratamientos corporales</option>
                                    </select>
                                </div>   
                                      
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">¿Cuál es tu color favorito?</label>
                                      <div class="col-sm-5"><select class="form-control m-b" name="account">
                                         <option></option>
                                        <option>Verde</option>
                                        <option>Azul</option>
                                        <option>Rosado</option>
                                        <option>Morado</option>
                                        <option>Beige</option>
                                        <option>Anaranjado</option>
                                    </select>
                                </div>   
                                      
                                </div>
                                 <div class="form-group"><label class="col-lg-2 control-label">¿Cuál es tu música preferida?</label>
                                      <div class="col-sm-5"><select class="form-control m-b" name="account">
                                         <option></option>
                                        <option>Bachata</option>
                                        <option>Rock</option>
                                        <option>Romantica</option>
                                    </select>
                                </div>   
                                      
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">¿Cuál es tu aroma preferido?</label>
                                      <div class="col-sm-5"><select class="form-control m-b" name="account">
                                         <option></option>
                                        <option>Fresa</option>
                                        <option>Limon</option>
                                        <option></option>
                                    </select>
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