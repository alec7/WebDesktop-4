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

import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Window;

public class Prueba  extends SelectorComposer<Component>{
	
	//A a;
	
	Li m;
	
	A mensaje;
	
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

}
