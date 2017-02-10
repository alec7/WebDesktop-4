/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controlador;

import java.util.List;

import controlador.MaestricoListServiceChapter6Impl;
import modelo.Priority;
import modelo.Maestrico;
import modeloDAO.maestricoDAO;
import controlador.MaestricoListService;
import org.zkoss.lang.Strings;
import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
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

public class MaestricoListController extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	maestricoDAO mdao = new maestricoDAO();
	Execution execution = Executions.getCurrent();
	String tabla = execution.getParameter("master");
	
	
	
	//wire components
	@Wire
	Textbox maestricoSubject;
	@Wire
	Label titulo;
	@Wire
	Button addMaestrico;
	@Wire
	Listbox maestricoListbox;
	
	@Wire
	Component selectedMaestricoBlock;
	@Wire
	Checkbox selectedMaestricoCheck;
	@Wire
	Textbox selectedMaestricoSubject;
	@Wire
	Radiogroup selectedMaestricoPriority;
	@Wire
	Datebox selectedMaestricoDate;
	@Wire
	Textbox selectedMaestricoDescription;
	@Wire
	Button updateSelectedMaestrico;
	
	//services
	MaestricoListService maestricoListService = new MaestricoListServiceChapter6Impl();
	
	//data for the view
	ListModelList<Maestrico> maestricoListModel;
	ListModelList<Priority> priorityListModel;
	Maestrico selectedMaestrico;
	
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		//get data from service and wrap it to list-model for the view
		List<Maestrico> maestricoList = maestricoListService.getMaestricoList();
		maestricoListModel = new ListModelList<Maestrico>(maestricoList);
		maestricoListbox.setModel(maestricoListModel);
		titulo.setValue(titulo());
		
		//priorityListModel = new ListModelList<Priority>(Priority.values());
		//selectedTodoPriority.setModel(priorityListModel);
	}
	
	//when user clicks on the button or enters on the textbox
	@Listen("onClick = #addMaestrico; onOK = #maestricoSubject")
	public void doMaestricoAdd(){
		//get user input from view
		String descripcion = maestricoSubject.getValue();
		Messagebox.show(descripcion);
		if(Strings.isBlank(descripcion)){
			Clients.showNotification("Nothing to do ?",maestricoSubject);
		}else{
			//save data
			String codigo = mdao.TotalRegistros(tabla);
			selectedMaestrico = maestricoListService.saveMaestrico(new Maestrico(descripcion, codigo));
			//Messagebox.show(selectedTodo.get);
			
			//Messagebox.show(selectedTodo.getCodigo() +" "+ selectedTodo.getDescripcion()+" "+ selectedTodo.getStatus());
			
			
			//update the model of listbox
			maestricoListModel.add(selectedMaestrico);
			//set the new selection
			maestricoListModel.addToSelection(selectedMaestrico);
			
			
			//Guardando en la Base de Datos
			
			
			mdao.agregarMaestrico(tabla,codigo,descripcion);
		
			
			//refresh detail view
			refreshDetailView();
			
			//reset value for fast typing.
			maestricoSubject.setValue("");
			
		}
	}
	
	/*//when user checks on the checkbox of each todo on the list
	@Listen("onTodoCheck = #todoListbox")
	public void doTodoCheck(ForwardEvent evt){
		//get data from event
		Checkbox cbox = (Checkbox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)cbox.getParent().getParent();
		
		boolean checked = cbox.isChecked();
		Todo todo = (Todo)litem.getValue();
		todo.setComplete(checked);
		
		//save data
		todo = todoListService.updateTodo(todo);
		if(todo.equals(selectedTodo)){
			selectedTodo = todo;
			//refresh detail view
			refreshDetailView();
		}
		//update listitem style
		((Listitem)cbox.getParent().getParent()).setSclass(checked?"complete-todo":"");
	}  */
	
	//when user clicks the delete button of each todo on the list
	@Listen("onMaestricoDelete = #maestricoListbox")
	public void doMaestricoDelete(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		
		Maestrico maestrico = (Maestrico)litem.getValue();
		
		//delete data
		maestricoListService.deleteMaestrico(maestrico);
		mdao.eliminarMaestrico(tabla,maestrico.getCodigo());
		
		//update the model of listbox
		maestricoListModel.remove(maestrico);
		
		if(maestrico.equals(selectedMaestrico)){
			//refresh selected todo view
			selectedMaestrico = null;
			refreshDetailView();
		}
	}

	//when user selects a todo of the listbox
	@Listen("onSelect = #maestricoListbox")
	public void doMaestricoSelect() {
		if(maestricoListModel.isSelectionEmpty()){
			//just in case for the no selection
			selectedMaestrico = null;
		}else{
			selectedMaestrico = maestricoListModel.getSelection().iterator().next();
		}
		refreshDetailView();
	}
	
	private void refreshDetailView() {
		//refresh the detail view of selected todo
		if(selectedMaestrico==null){
			//clean
			selectedMaestricoBlock.setVisible(false);
			//selectedTodoCheck.setChecked(false);
			selectedMaestricoSubject.setValue(null);
			//selectedTodoDate.setValue(null);
			//electedTodoDescription.setValue(null);
			updateSelectedMaestrico.setDisabled(true);
			
			//priorityListModel.clearSelection();
		}else{
			selectedMaestricoBlock.setVisible(true);
			//selectedTodoCheck.setChecked(selectedTodo.isComplete());
			selectedMaestricoSubject.setValue(selectedMaestrico.getDescripcion());
			//selectedTodoDate.setValue(selectedTodo.getDate());
			//selectedTodoDescription.setValue(selectedTodo.getDescripcion());
			updateSelectedMaestrico.setDisabled(false);
			
			//priorityListModel.addToSelection(selectedTodo.getPriority());
		}
	} 
	
	//when user clicks the update button
	@Listen("onClick = #updateSelectedMaestrico")
	public void doUpdateClick(){
		if(Strings.isBlank(selectedMaestricoSubject.getValue())){
			Clients.showNotification("Nothing to do ?",selectedMaestricoSubject);
			return;
		}
		
		//selectedTodo.setComplete(selectedTodoCheck.isChecked());
		selectedMaestrico.setDescripcion(selectedMaestricoSubject.getValue());
		//selectedTodo.setDate(selectedTodoDate.getValue());
		//selectedTodo.setDescription(selectedTodoDescription.getValue());
		//selectedTodo.setPriority(priorityListModel.getSelection().iterator().next());
		
		//save data and get updated Todo object
		selectedMaestrico = maestricoListService.updateMaestrico(selectedMaestrico);
		
		
		//mdao.modificarMaestrico(selectedMaestrico.getCodigo(), selectedMaestrico.getDescripcion());
		mdao.modificarMaestrico(tabla,selectedMaestrico.getCodigo(), selectedMaestrico.getDescripcion());
		
		//replace original Todo object in listmodel with updated one
		maestricoListModel.set(maestricoListModel.indexOf(selectedMaestrico), selectedMaestrico);
		
		//show message for user
		Clients.showNotification("maestrico saved");
	} 
	
	//when user clicks the update button
	@Listen("onClick = #reloadSelectedMaestrico")
	public void doReloadClick(){
		refreshDetailView();
	} 
	
	public String titulo(){
		String t= "Registrar ";
		switch (tabla){
		case "tb_pregunta": 
			return t=t+"Pregunta";
		case "tb_equipo": 
			t=t+"Equipo";
			return t;
		case "tb_dia_laborable": 
			t=t+"Dia Laborable";
			return t;
		case "tb_indicador": 
			t=t+"Indicador";
			return t;
		case "tb_ocupacion": 
			t=t+"Ocupación";
			return t;
		case "tb_rol": 
			t=t+"Rol";
			return t;
		case "tb_necesidad": 
			t=t+"Necesidad";
			return t;
		}
		return "";
	}
}
