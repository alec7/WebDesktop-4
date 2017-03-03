package controlador;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;

import modelo.Bloque;
import modelo.Cod_Des;
import modelo.Esteticista;
import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Servicio;
import modelo.Usuario;
import modeloDAO.BloqueDAO;
import modeloDAO.EsteticistaDAO;
import modeloDAO.MaestricoDAO;

public class ControladorHorarioEsteticista extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Button buscar;
	@Wire
	private Div dias_laborables;
	@Wire
	private Tabs tabs;
	@Wire
	private Tabpanels tabpanels;
	@Wire
	private Chosenbox chosen;
	@Wire
	private Listbox listaLunes;
	@Wire
	private Listbox listaLunesSeleccionados;
	
	
	BloqueDAO bloqueDao = new BloqueDAO(); 
	EsteticistaDAO estDao = new EsteticistaDAO();
	List<Bloque> listaBloquesSeleccionados = new ArrayList<Bloque>();
	List<Maestrico> diasLab = new ArrayList<Maestrico>();
	ListModelList<Bloque> bloquesModel;
	ListModelList<Bloque> bloquesSeleccionadoModel;
	List<Bloque> listaBloques = bloqueDao.listarBloques();
	Esteticista est;
	MaestricoDAO mdao = new MaestricoDAO();
	List l = new ArrayList();

	String horaInicio = new String();
	String horaFin = new String();
	
	@Listen("onClick = #buscar")
	public void buscarEsteticista(){
		est= estDao.buscarEsteticista(cedula.getValue());
		if(est==null){
			Messagebox.show("Esteticista no registrado", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			nombre.setText(est.getNombre()+" "+est.getApellido());
		}
		
	}
	
	@Listen("onCreate = #dias_laborables")
	public void usuarios(CreateEvent event)
    {
		diasLab = mdao.listarMaestrico("tb_dia_laborable");
		for(int i=0; i<diasLab.size();i++){
			Tab tab = new Tab();
			tab.setId(diasLab.get(i).getCodigo());
			tab.setLabel(diasLab.get(i).getDescripcion());
			tab.setParent(tabs);

		}
		listarBloques();
		
    }
	
	public void listarBloques(){
		
		bloquesModel = new ListModelList<Bloque>(listaBloques);
		listaLunes.setModel(bloquesModel);
	}
	@Listen("onClick = #agregarLunes")
	public void doAgregar()
	{
		
		if(listaLunes.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe Serleccionar un Bloque");
		}
		else{
			Bloque b = null;
			b = listaLunes.getSelectedItem().getValue();
			listaBloquesSeleccionados.add(b);
			bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesSeleccionados);
			listaLunesSeleccionados.setModel(bloquesSeleccionadoModel);
			
			//listaBloques.remove(bloquesSeleccionadoModel.get)
			//listaTodosServicios.remove(listaServicios.getSelectedIndex());
		}
	}
	
	
//	private ListModelList _model1;
//	private Set _selectedObjects1;
//	public ListModel getModel1 () {
//		if (_model1 == null) {		
//			for(int i=0; i<listaBloques.size();i++){
//				horaInicio = new SimpleDateFormat("HH:mm a").format(listaBloques.get(i).getHora_inicio());
//				horaFin = new SimpleDateFormat("HH:mm a").format(listaBloques.get(i).getHora_fin());
//				l.add(horaInicio+"-"+horaFin);
//				//l.add(descripcion.get(i).getDescripcion());
//			}
//			_model1 = new ListModelList(l);
//		}
//		return _model1;
//	}
//	public void setSelectedObjects1 (Set selectedObjects) {
//		_selectedObjects1 = selectedObjects;
//	}
//	public Set getSelectedObjects1 () {
//		if (_selectedObjects1 == null) {
//			_selectedObjects1 = new HashSet();
//		}
//		return _selectedObjects1;
//	}
//	@Command
//	public void showSelection1 () {
//		Clients.alert(getSelectedObjects1().toString());
//		 Set<String> set = new HashSet<String>();
//		 set= _selectedObjects1;
//		System.out.println(set);
//
//		
//		}
//	
//	private ListModelList _model2;
//	private Set _selectedObjects2;
//	public ListModel getModel2 () {
//		if (_model2 == null) {		
//			for(int i=0; i<listaBloques.size();i++){
//				horaInicio = new SimpleDateFormat("hh:mm a").format(listaBloques.get(i).getHora_inicio());
//				horaFin = new SimpleDateFormat("hh:mm a").format(listaBloques.get(i).getHora_fin());
//				l.add(horaInicio+"-"+horaFin);
//				//l.add(descripcion.get(i).getDescripcion());
//			}
//			_model2 = new ListModelList(l);
//		}
//		return _model2;
//	}
//	public void setSelectedObjects2 (Set selectedObjects) {
//		_selectedObjects2 = selectedObjects;
//	}
//	public Set getSelectedObjects2 () {
//		if (_selectedObjects2 == null) {
//			_selectedObjects2 = new HashSet();
//		}
//		return _selectedObjects2;
//	}
//	@Command
//	public void showSelection2 () {
//		Clients.alert(getSelectedObjects2().toString());
//		 Set<String> set = new HashSet<String>();
//		 set= _selectedObjects2;
//		System.out.println(set);
//
//		
//		}
	
	
}
