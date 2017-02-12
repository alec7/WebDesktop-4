package controlador;

import org.zkoss.zhtml.A;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
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
	
	Session miSession = Sessions.getCurrent();

//	@Listen("onClick =#mensaje")
//	public void mostrarMensaje(MouseEvent event){
//	alert("hola");
//		//Messagebox.show("hola");
//	}
	
	
	
	@Listen("onClick =#dia_laborable")
	public void diaLabolable(MouseEvent event){
		miSession.setAttribute("master","tb_dia_laborable");
		 Executions.sendRedirect("maestrico.zul");
	}
	
	@Listen("onClick =#estado")
	public void Estado(MouseEvent event){
		miSession.setAttribute("master","tb_estado");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#orientacion")
	public void orientacion(MouseEvent event){
		miSession.setAttribute("master","tb_orientacion");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#pregunta")
	public void pregunta(MouseEvent event){
		miSession.setAttribute("master","tb_pregunta");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#rol")
	public void rol(MouseEvent event){
		miSession.setAttribute("master","tb_rol");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_acuerdo")
	public void tipoAcuerdo(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_acuerdo");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_cliente")
	public void tipo_cliente(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_cliente");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_comentario")
	public void tipo_Comentario(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_comentario");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_incidencia")
	public void tipo_incidencia(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_incidencia");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_noticia")
	public void tipo_noticia(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_noticia");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_notificacion")
	public void tipo_notificacion(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_notificacion");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_organizacion")
	public void tipo_organizacion(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_organizacion");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_pregunta")
	public void tipo_pregunta(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_pregunta");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_red_social")
	public void tipo_red_social(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_red_social");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_servicio")
	public void tipo_servicio(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_servicio");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#turno")
	public void turno(MouseEvent event){
		miSession.setAttribute("master","tb_turno");
		 Executions.sendRedirect("maestrico.zul");
	}
	@Listen("onClick =#tipo_campaña")
	public void tipo_Campaña(MouseEvent event){
		miSession.setAttribute("master","tb_tipo_campaña");
		 Executions.sendRedirect("maestrico.zul");
	}
	

}
