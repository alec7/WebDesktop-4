<!--
*
*  INSPINIA - Responsive Admin Theme
*  version 2.7
*
-->

<!DOCTYPE html>
<html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">

    <title>INSPINIA | Dashboard</title>

    <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    <link href="assets/font-awesome/css/font-awesome.css" rel="stylesheet">

    <!-- Toastr style -->
    <link href="assets/css/plugins/toastr/toastr.min.css" rel="stylesheet">

    <!-- Gritter -->
    <link href="assets/js/plugins/gritter/jquery.gritter.css" rel="stylesheet">

    <link href="assets/css/animate.css" rel="stylesheet">
    <link href="assets/css/style.css" rel="stylesheet">

</head>

<body>
   
        <nav class="navbar-default navbar-static-side" role="navigation">
            <div class="sidebar-collapse">
                <ul class="nav metismenu" id="side-menu">
                    <li class="nav-header">
                        <div class="dropdown profile-element"> <span>
                            <img alt="image" class="img-circle" src="assets/img/da1.png" />
                             </span>
                            <a data-toggle="dropdown" class="dropdown-toggle" href="#">
                            <span class="clear"> <span class="block m-t-xs"> <strong class="font-bold">Bienvenido</strong>
                             </span> <span class="text-muted text-xs block">En linea <b class="caret"></b></span> </span> </a>
                            <ul class="dropdown-menu animated fadeInRight m-t-xs">
                                <li><a href="profile.html">Perfil</a></li>
                                <li><a href="contacts.html">Contáctanos</a></li>
                                <li><a href="mailbox.html">Buzón de Mensajes</a></li>
                                <li class="divider"></li>
                                <li><a href="login.jsp">Cerrar Sesión</a></li>
                            </ul>
                        </div>
                        <div class="logo-element">
                            IN+
                        </div>
                    </li>
                    
                    
                     <li class="active">
                        <a href="index.html"><i class="fa fa-address-book-o"></i> <span class="nav-label">Clientes</span> <span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level">
                            <li class="active"><a  onclick="abrir();">Cliente</a></li>
                            <li><a href="dashboard_3.html">Preferencia</a></li>
                             <li><a href="dashboard_3.html">Solicitud de servicio</a></li>
                            
                        </ul>
                    </li>
                   
                    <li>
                        <a href="#"><i class="fa fa-calendar"></i> <span class="nav-label">Agenda</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="graph_flot.html">Ver Agenda</a></li>
                            <li><a href="graph_morris.html">Solicitar Cita</a></li>
                            
                           
                        </ul>
                    </li>
                    <li>
                        <a href="mailbox.html"><i class="fa fa-bell"></i> <span class="nav-label">Notificación</span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="mailbox.html">Eventualidad</a></li>
                            <li><a href="mail_detail.html">Noticia</a></li>
                        </ul>
                    </li>
                    
                     <li>
                        <a href="mailbox.html"><i class="fa fa-cogs"></i> <span class="nav-label">Configuraciones </span><span class="fa arrow"></span></a>
                        <ul class="nav nav-second-level collapse">
                            <li><a href="mail_detail.html">Servicio</a></li>
                            <li><a href="mail_detail.html">Paquete</a></li>
                            <li><a href="mail_detail.html">Promoción</a></li>
                            <li><a href="mail_detail.html">Preferencia</a></li>
                            <li><a href="mail_detail.html">Esteticista</a></li>
                            <li><a href="mail_detail.html">Cubiculo</a></li>
                            <li><a href="mail_detail.html">Recurso</a></li>
                            <li><a href="mail_detail.html">Equipo</a></li>
                            <li><a href="mail_detail.html">Tipo de Servicio</a></li>
                            <li><a href="mail_detail.html">Horario del Esteticista</a></li>
                           
                            
                            
                        </ul>
                    </li>
                    
                   
                 
                </ul>

            </div>
        </nav>

        <div id="resultreturn" style="display: none"></div>
        
        
        <script type="text/javascript">
       
        $(document).ready(function(){
            $("#loader").click(function(){
                        // Load the page into the div
                $("#resultreturn").load("vista/cliente/registrarCliente");
            });
        });
        
        
        </script>
  

    <!-- Mainly scripts -->
    <script src="assets/js/jquery-3.1.1.min.js"></script>
    <script src="assets/js/bootstrap.min.js"></script>
    <script src="assets/js/plugins/metisMenu/jquery.metisMenu.js"></script>
    <script src="assets/js/plugins/slimscroll/jquery.slimscroll.min.js"></script>

    <!-- Flot -->
    <script src="assets/js/plugins/flot/jquery.flot.js"></script>
    <script src="assets/js/plugins/flot/jquery.flot.tooltip.min.js"></script>
    <script src="assets/js/plugins/flot/jquery.flot.spline.js"></script>
    <script src="assets/js/plugins/flot/jquery.flot.resize.js"></script>
    <script src="assets/js/plugins/flot/jquery.flot.pie.js"></script>

    <!-- Peity -->
    <script src="assets/js/plugins/peity/jquery.peity.min.js"></script>
    <script src="assets/js/demo/peity-demo.js"></script>

    <!-- Custom and plugin javascript -->
    <script src="assets/js/inspinia.js"></script>
    <script src="assets/js/plugins/pace/pace.min.js"></script>

    <!-- jQuery UI -->
    <script src="assets/js/plugins/jquery-ui/jquery-ui.min.js"></script>

    <!-- GITTER -->
    <script src="assets/js/plugins/gritter/jquery.gritter.min.js"></script>

    <!-- Sparkline -->
    <script src="assets/js/plugins/sparkline/jquery.sparkline.min.js"></script>

    <!-- Sparkline demo data  -->
    <script src="assets/js/demo/sparkline-demo.js"></script>

    <!-- ChartJS-->
    <script src="assetsjs/plugins/chartJs/Chart.min.js"></script>

    <!-- Toastr -->
    <script src="assets/js/plugins/toastr/toastr.min.js"></script>
    

    
<script>
$(document).ready(function(){
    $("button").click(function(){
        $("p").hide(1000);
    });
});
</script>




<script type="text/javascript">
   
function abrir() {
	
    var myWindow = window.open("vista/cliente/registrar.zul", "_self");
  
	
	
		
    }
</script>

<script type="text/javascript">
   
function myFunction1() {
    var x = "vista/cliente/registrarCliente.jsp";
    if (x.style.display === 'none') {
        x.style.display = 'block';
    } else {
        x.style.display = 'none';
    }
}
</script>
    <script>
        $(document).ready(function() {
            setTimeout(function() {
                toastr.options = {
                    closeButton: true,
                    progressBar: true,
                    showMethod: 'slideDown',
                    timeOut: 4000
                };
                toastr.success('Bienvenido a Spinneti Spa Gym');

            }, 1300);


            var data1 = [
                [0,4],[1,8],[2,5],[3,10],[4,4],[5,16],[6,5],[7,11],[8,6],[9,11],[10,30],[11,10],[12,13],[13,4],[14,3],[15,3],[16,6]
            ];
            var data2 = [
                [0,1],[1,0],[2,2],[3,0],[4,1],[5,3],[6,1],[7,5],[8,2],[9,3],[10,2],[11,1],[12,0],[13,2],[14,8],[15,0],[16,0]
            ];
            $("#flot-dashboard-chart").length && $.plot($("#flot-dashboard-chart"), [
                data1, data2
            ],
                    {
                        series: {
                            lines: {
                                show: false,
                                fill: true
                            },
                            splines: {
                                show: true,
                                tension: 0.4,
                                lineWidth: 1,
                                fill: 0.4
                            },
                            points: {
                                radius: 0,
                                show: true
                            },
                            shadowSize: 2
                        },
                        grid: {
                            hoverable: true,
                            clickable: true,
                            tickColor: "#d5d5d5",
                            borderWidth: 1,
                            color: '#d5d5d5'
                        },
                        colors: ["#1ab394", "#1C84C6"],
                        xaxis:{
                        },
                        yaxis: {
                            ticks: 4
                        },
                        tooltip: false
                    }
            );

            var doughnutData = {
                labels: ["App","Software","Laptop" ],
                datasets: [{
                    data: [300,50,100],
                    backgroundColor: ["#a3e1d4","#dedede","#9CC3DA"]
                }]
            } ;


            var doughnutOptions = {
                responsive: false,
                legend: {
                    display: false
                }
            };


            var ctx4 = document.getElementById("doughnutChart").getContext("2d");
            new Chart(ctx4, {type: 'doughnut', data: doughnutData, options:doughnutOptions});

            var doughnutData = {
                labels: ["App","Software","Laptop" ],
                datasets: [{
                    data: [70,27,85],
                    backgroundColor: ["#a3e1d4","#dedede","#9CC3DA"]
                }]
            } ;


            var doughnutOptions = {
                responsive: false,
                legend: {
                    display: false
                }
            };


            var ctx4 = document.getElementById("doughnutChart2").getContext("2d");
            new Chart(ctx4, {type: 'doughnut', data: doughnutData, options:doughnutOptions});

        });
    </script>
</body>
</html>
