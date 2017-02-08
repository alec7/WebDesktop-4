package controlador;

import java.util.ArrayList;

import org.zkoss.zhtml.Button;
import org.zkoss.zhtml.Li;
import org.zkoss.zul.Label;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modeloDAO.maestricoDAO;

public class ControladorMaestrico  extends GenericForwardComposer<Window>{//
	
	
	maestricoDAO mdao = new maestricoDAO();

	@Wire
	private Button guardar;
	
	@Wire
	private Textbox txtDescripcion;
	
	@Wire
	private Listbox listaMaestricos;
	
	@Wire
	private Label titulo;

	Li m;
	
	Execution execution = Executions.getCurrent();
	String tabla = execution.getParameter("master");
	

	
//	@Listen("onClick = #guardar")
//	public void mostrarMensaje(){
//		//Messagebox.show(txtdes);
//		Maestrico maestrico = new Maestrico("0005", txtDescripcion.getValue()); 
//		mdao.registrarMaestrico(maestrico, tabla);
//		this.cargarTabla();
//		
//	}
	public void onClick$guardar(){
		String codigo = mdao.TotalRegistros(tabla);
		
		Maestrico maestrico = new Maestrico(codigo, txtDescripcion.getValue()); 
		mdao.registrarMaestrico(maestrico, tabla,"Activo");
		this.cargarTabla();
		txtDescripcion.setValue("");
	}
	
	
	public void onSelect$listaMaestricos(){
		   // Executions.createComponents("vista/mensajes/mensaje.zul",null,null);
			EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
		        public void onEvent(ClickEvent event) throws Exception {
		            if(Messagebox.Button.YES.equals(event.getButton())) {
		            		Maestrico maestrico = listaMaestricos.getSelectedItem().getValue();
						   mdao.actualizarStatus(tabla, maestrico.getCodigo());
						   cargarTabla();
		            }
		        }
		    };
		    Messagebox.show("�Desea Eliminar El Registro?", "Mensaje de confirmaci�n", new Messagebox.Button[]{
		            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
		}
	
	private void cargarTabla(){
		ArrayList<Maestrico> arr_maestricos = new ArrayList<Maestrico>();
		arr_maestricos = mdao.listarMaestricos(tabla);
		listaMaestricos.setModel(new ListModelList<Maestrico>(arr_maestricos));
	}
	
	
	public void onCreate$maestrico(CreateEvent event)
    {
		this.cargarTabla();
    }
	
	public void onCreate$titulo(CreateEvent event){
	String t= "Registrar ";
	switch (tabla){
	case "tb_pregunta": 
		t=t+"Pregunta";
		titulo.setValue(t);
		return;
	case "tb_respuesta": 
		t=t+"Respuesta";
		titulo.setValue(t);
		return;
	case "tb_equipo": 
		t=t+"Equipo";
		titulo.setValue(t);
		return;
	case "tb_cubiculo": 
		t=t+"Cub�culo";
		titulo.setValue(t);
		return;
	case "tb_comentario": 
		t=t+"Comentario";
		titulo.setValue(t);
		return;
	case "tb_dia_laborable": 
		t=t+"Dia Laborable";
		titulo.setValue(t);
		return;
	case "tb_incidencia": 
		t=t+"Incidencia";
		titulo.setValue(t);
		return;
	case "tb_indicador": 
		t=t+"Indicador";
		titulo.setValue(t);
		return;
	case "tb_ocupacion": 
		t=t+"Ocupaci�n";
		titulo.setValue(t);
		return;
	case "tb_paquete": 
		t=t+"Paquete";
		titulo.setValue(t);
		return;
	case "tb_preferencia": 
		t=t+"Preferencia";
		titulo.setValue(t);
		return;
	case "tb_rol": 
		t=t+"Rol";
		titulo.setValue(t);
		return;
	}
	
	
	}

}
