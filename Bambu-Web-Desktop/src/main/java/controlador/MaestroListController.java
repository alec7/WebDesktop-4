/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controlador;

import java.util.ArrayList;
import java.util.List;

import controlador.MaestroListServiceChapter6Impl;
import modelo.Priority;
import modelo.Servicio;

import modelo.Cod_Des;
import modelo.Maestro;
import modelo.Maestro_servicio;
import modeloDAO.MaestroDAO;
import controlador.MaestroListService;

import org.postgresql.translation.messages_bg;
import org.zkoss.lang.Strings;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Radiogroup;
import org.zkoss.zul.Textbox;

public class MaestroListController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	MaestroDAO mdao = new MaestroDAO();
	
	List<Servicio> listaTodosServicios = new ArrayList<Servicio>();
	List<Cod_Des> listaServiciosAsociados = new ArrayList<Cod_Des>();
	List<Servicio> listaTodosServiciosFiltrado = new ArrayList<Servicio>();
	
	
	
//	Execution execution = Executions.getCurrent();
//	String tabla = execution.getParameter("master");
	Session miSession = Sessions.getCurrent();
	String tabla =  miSession.getAttribute("master").toString();
	 //String tabla_maestro_servicio =  miSession.getAttribute("master_servicio").toString();
	
	
	
	//wire components
	@Wire
	Textbox maestroSubject;
	@Wire
	Label titulo;
	@Wire
	Button addMaestro;
	@Wire
	Listbox maestroListbox;
	
	@Wire
	Component selectedMaestroBlock;
	@Wire
	Checkbox selectedMaestroCheck;
	@Wire
	Textbox selectedMaestroSubject;
	@Wire
	Radiogroup selectedMaestroPriority;
	@Wire
	Datebox selectedMaestroDate;
	@Wire
	Textbox selectedMaestroDescription;
	@Wire
	Button updateSelectedMaestro;
	
	@Wire
	Button agregar;
	
	@Wire
	Button quitar;
	@Wire
	Listbox listaServicios;
	
	@Wire
	Listbox listaServiciosSeleccionados;
	
	
	//services
	MaestroListService maestroListService = new MaestroListServiceChapter6Impl();
	
	//data for the view
	ListModelList<Maestro> maestroListModel;
	ListModelList<Cod_Des> servicioListModel;
	//ListModelList<Cod_Des> lista ;
	
	Maestro selectedMaestro;
	Servicio selectedServicio;
	
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//get data from service and wrap it to list-model for the view
		List<Maestro> maestroList = maestroListService.getMaestroList();
		maestroList = mdao.listarMaestro(tabla); //carga la lista de maestros
		maestroListModel = new ListModelList<Maestro>(maestroList);
		maestroListbox.setModel(maestroListModel);
		titulo.setValue(titulo());
		
 
		

	}
	
	//when user clicks on the button or enters on the textbox
	@Listen("onClick = #addMaestro; onOK = #maestroSubject")
	public void doMaestroAdd(){
		//get user input from view
		String descripcion = maestroSubject.getValue();
		if(Strings.isBlank(descripcion)){
			Clients.showNotification("Escriba la Descrición",maestroSubject);
		}else{
			//save data
			String codigo = mdao.TotalRegistros(tabla);
			selectedMaestro = maestroListService.saveMaestro(new Maestro(descripcion, codigo));
			//Messagebox.show(selectedTodo.get);
			
			//Messagebox.show(selectedTodo.getCodigo() +" "+ selectedTodo.getDescripcion()+" "+ selectedTodo.getStatus());
			
			
			//update the model of listbox
			maestroListModel.add(selectedMaestro);
			//set the new selection
			maestroListModel.addToSelection(selectedMaestro);
			
			
			//Guardando en la Base de Datos
			
			
			mdao.agregarMaestro(tabla,codigo,descripcion);
		
			
			//refresh detail view
			refreshDetailView();
			
			//reset value for fast typing.
			maestroSubject.setValue("");
			
		}
	}
	
	
	//when user clicks the delete button of each todo on the list
	@Listen("onMaestroDelete = #maestroListbox")
	public void doMaestroDelete(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		
		Maestro maestro = (Maestro)litem.getValue();
		
		//delete data
		maestroListService.deleteMaestro(maestro);
		mdao.eliminarMaestro(tabla,maestro.getCodigo());
		
		//update the model of listbox
		maestroListModel.remove(maestro);
		
		if(maestro.equals(selectedMaestro)){
			//refresh selected todo view
			selectedMaestro = null;
			refreshDetailView();
		}
	}

	//cuando el usuario selecciona un Maestro del listbox
	@Listen("onSelect = #maestroListbox")
	
	public void doMaestroSelect() {
		if(maestroListModel.isSelectionEmpty()){
			//just in case for the no selection
			selectedMaestro = null;
		}else{
			
			//Una vez que seleccione un maestro, cargo los servicios de la BD
			listaTodosServicios= mdao.listarServicios();
			
			//Agarro el maestro seleccionado
			selectedMaestro = maestroListModel.getSelection().iterator().next();
			
			
			
			
			//Cargo los Servicios asociados
			listaServiciosAsociados= mdao.CargarServiciosAsociados(selectedMaestro.getCodigo(),tabla+"_servicio" );
			
			
		//Filtro los servicios. (listaTodosServicios INTERSECTADO listaServiciosAsociados)
			
			Filtrar(listaTodosServicios,listaServiciosAsociados);
			
			
			
			//Muestro todos los servicios
			listaServicios.setModel(new ListModelList<Servicio>(listaTodosServicios));//carga los servicios en la vista
			
			
			
			
			//Muestro los servicios asociados
			listaServiciosSeleccionados.setModel(new ListModelList<Cod_Des>(listaServiciosAsociados));
			
			
			
			
			
			
			
			
		}
		refreshDetailView();
	}
	
	private void Filtrar(List<Servicio> lts, List<Cod_Des> lsa) {
		
		//List<Servicio> listaFiltrada = new ArrayList<Servicio>();
		
		
		for (int i = 0; i < lts.size(); i++) {
			
			for (int k = 0; k < lsa.size(); k++) 
			{
				if(lts.get(i).getCodigo().equals(lsa.get(k).getCodigo()))
				{
					listaTodosServicios.remove(i);
					
					
				}
				
			}
			
		
			
		}
		
		
	}

	private void refreshDetailView() {
		//refresh the detail view of selected todo
		if(selectedMaestro==null){
			//clean
			selectedMaestroBlock.setVisible(false);
			//selectedTodoCheck.setChecked(false);
			selectedMaestroSubject.setValue(null);
			//selectedTodoDate.setValue(null);
			//electedTodoDescription.setValue(null);
			updateSelectedMaestro.setDisabled(true);
			
			//priorityListModel.clearSelection();
		}else{
			selectedMaestroBlock.setVisible(true);
			//selectedTodoCheck.setChecked(selectedTodo.isComplete());
			selectedMaestroSubject.setValue(selectedMaestro.getDescripcion());
			//selectedTodoDate.setValue(selectedTodo.getDate());
			//selectedTodoDescription.setValue(selectedTodo.getDescripcion());
			updateSelectedMaestro.setDisabled(false);
			
			//priorityListModel.addToSelection(selectedTodo.getPriority());
		}
	} 
	
	//when user clicks the update button
	@Listen("onClick = #updateSelectedMaestro")
	public void doUpdateClick(){
		if(Strings.isBlank(selectedMaestroSubject.getValue())){
			Clients.showNotification("Escriba la Modificación",selectedMaestroSubject);
			return;
		}
		
		//selectedTodo.setComplete(selectedTodoCheck.isChecked());
		selectedMaestro.setDescripcion(selectedMaestroSubject.getValue());
		//selectedTodo.setDate(selectedTodoDate.getValue());
		//selectedTodo.setDescription(selectedTodoDescription.getValue());
		//selectedTodo.setPriority(priorityListModel.getSelection().iterator().next());
		
		//save data and get updated Todo object
		selectedMaestro = maestroListService.updateMaestro(selectedMaestro);
		
		
		//mdao.modificarMaestro(selectedMaestro.getCodigo(), selectedMaestro.getDescripcion());
		mdao.modificarMaestro(tabla,selectedMaestro.getCodigo(), selectedMaestro.getDescripcion());
		
		//replace original Todo object in listmodel with updated one
		maestroListModel.set(maestroListModel.indexOf(selectedMaestro), selectedMaestro);
		
		
		//Guarda en la BAse de datos la informacion de los servicios asociados a un maestro (TABLA MAESTRO_SERVICIO)
		
		
		mdao.ActualizarServiciosDeMaestro(selectedMaestro.getCodigo(),tabla+"_servicio",listaServiciosAsociados);
		
		
		//show message for user
		Clients.showNotification("Cambios Guardados");
	} 
	
	
	
	@Listen("onClick = #agregar")
	public void doAgregar()
	{
		Servicio serv = null;
		serv = listaServicios.getSelectedItem().getValue();
	
		
		Cod_Des cd = new Cod_Des(serv.getCodigo(), serv.getDescripcion());
		
		
		
		listaServiciosAsociados.add(listaServiciosAsociados.size(),cd );

		servicioListModel = new ListModelList<Cod_Des>(listaServiciosAsociados);
				
		listaServiciosSeleccionados.setModel(servicioListModel);
		
		
		listaTodosServicios.remove(listaServicios.getSelectedIndex());
		
		 
		
		listaServicios.setModel(new ListModelList<Servicio>(listaTodosServicios));
		
		

		
		}
		
		
	
	@Listen("onClick = #quitar")
	public void doQuitar()
	{
		Cod_Des cd = null;
		cd = listaServiciosSeleccionados.getSelectedItem().getValue();
		
		Servicio ser = new Servicio(cd.getCodigo(), cd.getDescripcion(), null, null, "Activo", null, null);
		
	
		
		listaTodosServicios.add(listaTodosServicios.size(), ser);

						
		listaServicios.setModel(new ListModelList<Servicio>(listaTodosServicios));
		
		
		listaServiciosAsociados.remove(listaServiciosSeleccionados.getSelectedIndex());
		
		 
		
		listaServiciosSeleccionados.setModel(new ListModelList<Cod_Des>(listaServiciosAsociados));
		
		}
		

		
	
	


		
		
	
		
		
		//Messagebox.show("hola");
		
		
	
		
	
	
	
	
	
	
	
	//when user clicks the update button
	@Listen("onClick = #reloadSelectedMaestro")
	public void doReloadClick(){
		refreshDetailView();
	} 
	
	public String titulo(){		
		String t= null;
		t = "Registrar ";
		switch (tabla){
		case "tb_habito": 
			t=t+"Hábito";
			return t;
		case "tb_antecedente": 
			t=t+"Antecedente";
			return t;
		case "tb_indicador": 
			t=t+"Indicador del Servicio";
			return t;
		case "tb_avance": 
			t=t+"Avance";
			return t;
		case "tb_ocupacion": 
			t=t+"Ocupación";
			return t;
		case "tb_equipo": 
			t=t+"Equipo";
			return t;
		
		}
		return "";
	}
}
