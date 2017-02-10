package controlador;

import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zkmax.zul.Navitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class Prueba  extends SelectorComposer<Component>{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	//A a;
	
	Li m;
	
	A mensaje;
	A cubiculo;
	A preferencia;
	A indicador;
	A necesidad;
	A incidencia;
	A ocupacion;
	Navitem pregunta;
	A respuesta;
	A comentario;
	A paquete;
	A rol;
	
	/*@Listen("onClick=#a")
	public void onClick$a()
	{
		Executions.sendRedirect("login.jsp");
	}*/
	
	
	/*@Listen("onClick =#a")
	public void mostrarGridEstudiantes(MouseEvent event){
		Window grid = (Window)Executions.createComponents("vista/cliente/registrar.zul",null,null);
		grid.doModal();
	}*/
	
	
	@Listen("onClick =#mensaje")
	public void mostrarMensaje(MouseEvent event){
	alert("hola");
		//Messagebox.show("hola");
	}
	
	
	
	@Listen("onClick =#cubiculo")
	public void Cubiculo(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_cubiculo");
	}
	
	@Listen("onClick =#equipo")
	public void Equipo(MouseEvent event){
		
		 Executions.sendRedirect("maestrico.zul?master="+"tb_equipo");
	}
	
	@Listen("onClick =#preferencia")
	public void Preferencia(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_preferencia");
	}
	@Listen("onClick =#necesidad")

	public void Necesidad(MouseEvent event){
		Messagebox.show("Necesidad");
		 Executions.sendRedirect("maestrico.zul?master="+"tb_necesidad");
	}
	@Listen("onClick =#indicador")
	public void Indicador(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_indicador");
	}
	@Listen("onClick =#incidencia")
	public void Incidencia(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_incidencia");
	}
	@Listen("onClick =#ocupacion")
	public void Ocupacion(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_ocupacion");
	}
	@Listen("onClick =#pregunta")
	public void Pregunta(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_pregunta");
	}
	@Listen("onClick =#respuesta")
	public void Respuesta(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_respuesta");
		 
	}
	@Listen("onClick =#comentario")
	public void Comentario(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_comentario");
	}
	@Listen("onClick =#diaLaborable")
	public void DiaLaborable(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_dia_laborable");
	}
	@Listen("onClick =#paquete")
	public void Paquete(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_paquete");
	}
	@Listen("onClick =#rol")
	public void Rol(MouseEvent event){
		 Executions.sendRedirect("maestrico.zul?master="+"tb_rol");
	}
	

}
