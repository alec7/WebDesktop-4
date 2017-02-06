package controlador;

import javax.websocket.Decoder.Text;

import org.zkoss.zhtml.A;
import org.zkoss.zhtml.B;
import org.zkoss.zhtml.Li;
import org.zkoss.zhtml.Ul;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.MouseEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Maestrico;
import modeloDAO.maestricoDAO;

public class ControladorMaestrico  extends SelectorComposer<Component>{
	
	A cubiculo;
	A equipo;
	//B guardar;
	
	


	@Wire
	private Button guardar;
	
	@Wire
	private Textbox txtDescripcion;
	

	
	 
	
	
	Li m;
	
	 //String tabla= "equipo";
	 
	
	Execution execution = Executions.getCurrent();
	String tabla = execution.getParameter("myId");

	
	@Listen("onClick = #guardar")
	public void mostrarMensaje(){
		//Messagebox.show(txtdes);
		maestricoDAO mdao = new maestricoDAO();
		Maestrico maestrico = new Maestrico("0002", txtDescripcion.getValue());
		mdao.registrarMaestrico(maestrico, tabla);
		
	}
	
	

	
	

}
