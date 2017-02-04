<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  
   <link href="../../assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="../../assets/font-awesome/css/font-awesome.css" rel="stylesheet">
    <link href="../../assets/css/plugins/iCheck/custom.css" rel="stylesheet">
    <link href="../../assets/css/plugins/steps/jquery.steps.css" rel="stylesheet">
    <link href="../../assets/css/animate.css" rel="stylesheet">
    <link href="../../assets/css/style.css" rel="stylesheet">
</head>
<body>
<div id="wrapper">
<%@ include file="../../dashboard.jsp"%>
<div id="page-wrapper" class="gray-bg"  >
<%@ include file="../../cabecera.jsp"%>
<div>
<div class="wrapper wrapper-content animated fadeInRight">
        <div class="row">
                <div class="col-lg-12">
                    <div class="ibox">
                        <div class="ibox-title">
                            <h5>Perfil del Cliente</h5>
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
                            <h2>
                             
                            </h2>
                            

                            <form id="form" action="#" class="form-horizontal">
                                <h1>Cliente</h1>
                                <fieldset>
                                    <h2>Información del Cliente</h2>
                                    <div class="row">
                                        <div class="col-lg-8">
                                            
                                        <div class="form-group"><label class="col-lg-2 control-label">Cédula</label>
                                      <div class="col-sm-5"> <div class="input-group"><input type="text" class="form-control col-lg-2"> <span class="input-group-btn"> <button type="button" class="btn btn-primary">Buscar
                                        </button> </span></div>
                                </div>   
                                      
                                </div>
                           
                                              
                                        
                                           <div class="form-group"><label class="col-lg-2 control-label">Nombre</label>
                                           <div class="col-lg-10"><input type="text" placeholder="" class="form-control required"> 
                                           </div>
                                           </div>
                                      
                                      
                                
                               
                                <div class="form-group"><label class="col-lg-2 control-label">Apellido</label>

                                    <div class="col-lg-10"><input type="text" placeholder="" class="form-control required"></div>
                                </div>
                                
                                                            
                                 <div class="form-group"><label class="col-lg-2 control-label">Direccion</label>

                                    <div class="col-lg-10"><input type="text" placeholder="" class="form-control required"></div>
                                </div>
                                
                                <div class="form-group"><label class="col-lg-2 control-label">Correo</label>

                                    <div class="col-lg-10"><input type="e-mail" placeholder="" class="form-control required"></div>
                                </div>
                                
                                 <div class="form-group"><label class="col-lg-2 control-label">Telefono</label>

                                    <div class="col-lg-10"><input type="text" placeholder="" class="form-control required"></div>
                                </div>
                                
                                
                                 <div class="form-group"><label class="col-lg-2 control-label">Referencia</label>

                                    <div class="col-sm-5"><select class="form-control m-b" name="account">
                                         <option></option>
                                        <option>Cirugía</option>
                                        <option>Spinetti Laser</option>
                                        <option>No Aplica</option>
                                    </select>
                                </div>
                                </div>
                                
                                
                                        </div>
                                       
                                    </div>

                                </fieldset>
                                <h1>Preferencias</h1>
                                <fieldset>
                                    <h2></h2>
                                    <div class="row">
                                          <form class="form-horizontal">
                            
                            
                            
                     
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
                                </fieldset>

                                
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
 
            <script src="../../assets/js/jquery-3.1.1.min.js"></script>
    <script src="../../assets/js/bootstrap.min.js"></script>
    <script src="../../assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="../../assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="../../assets/js/inspinia.js"></script>
    <script src="../../assets/js/plugins/pace/pace.min.js"></script>

    <!-- Steps -->
    <script src="../../assets/js/plugins/steps/jquery.steps.min.js"></script>

    <!-- Jquery Validate -->
    <script src="../../assets/js/plugins/validate/jquery.validate.min.js"></script>


    <script>
        $(document).ready(function(){
            $("#wizard").steps();
            $("#form").steps({
                bodyTag: "fieldset",
                onStepChanging: function (event, currentIndex, newIndex)
                {
                    // Always allow going backward even if the current step contains invalid fields!
                    if (currentIndex > newIndex)
                    {
                        return true;
                    }

                    // Forbid suppressing "Warning" step if the user is to young
                    if (newIndex === 3 && Number($("#age").val()) < 18)
                    {
                        return false;
                    }

                    var form = $(this);

                    // Clean up if user went backward before
                    if (currentIndex < newIndex)
                    {
                        // To remove error styles
                        $(".body:eq(" + newIndex + ") label.error", form).remove();
                        $(".body:eq(" + newIndex + ") .error", form).removeClass("error");
                    }

                    // Disable validation on fields that are disabled or hidden.
                    form.validate().settings.ignore = ":disabled,:hidden";

                    // Start validation; Prevent going forward if false
                    return form.valid();
                },
                onStepChanged: function (event, currentIndex, priorIndex)
                {
                    // Suppress (skip) "Warning" step if the user is old enough.
                    if (currentIndex === 2 && Number($("#age").val()) >= 18)
                    {
                        $(this).steps("next");
                    }

                    // Suppress (skip) "Warning" step if the user is old enough and wants to the previous step.
                    if (currentIndex === 2 && priorIndex === 3)
                    {
                        $(this).steps("previous");
                    }
                },
                onFinishing: function (event, currentIndex)
                {
                    var form = $(this);

                    // Disable validation on fields that are disabled.
                    // At this point it's recommended to do an overall check (mean ignoring only disabled fields)
                    form.validate().settings.ignore = ":disabled";

                    // Start validation; Prevent form submission if false
                    return form.valid();
                },
                onFinished: function (event, currentIndex)
                {
                    var form = $(this);

                    // Submit form input
                    form.submit();
                }
            }).validate({
                        errorPlacement: function (error, element)
                        {
                            element.before(error);
                        },
                        rules: {
                            confirm: {
                                equalTo: "#password"
                            }
                        }
                    });
       });
    </script>
</html>