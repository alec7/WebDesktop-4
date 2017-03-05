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
//	@Wire
//	private Chosenbox chosen;
	@Wire
	private Listbox listaLunes;
	@Wire
	private Listbox listaMartes;
	@Wire
	private Listbox listaMiercoles;
	@Wire
	private Listbox listaJueves;
	@Wire
	private Listbox listaViernes;
	@Wire
	private Listbox listaSabado;
	@Wire
	private Listbox listaDomingo;
	@Wire
	private Listbox listaLunesSeleccionados;
	@Wire
	private Listbox listaMartesSeleccionados;
	@Wire
	private Listbox listaMiercolesSeleccionados;
	@Wire
	private Listbox listaJuevesSeleccionados;
	@Wire
	private Listbox listaViernesSeleccionados;
	@Wire
	private Listbox listaSabadoSeleccionados;
	@Wire
	private Listbox listaDomingoSeleccionados;
	
	BloqueDAO bloqueDao = new BloqueDAO(); 
	EsteticistaDAO estDao = new EsteticistaDAO();
	List<Bloque> listaBloquesLunesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesMartesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesMiercolesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesJuevesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesViernesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesSabadoSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesDomingoSeleccionados = new ArrayList<Bloque>();
	List<Maestrico> diasLab = new ArrayList<Maestrico>();
	ListModelList<Bloque> bloquesModel;
	ListModelList<Bloque> bloquesSeleccionadoModel;
	List<Bloque> listaBloquesLunes = bloqueDao.listarBloques();
	List<Bloque> listaBloquesMartes = bloqueDao.listarBloques();
	List<Bloque> listaBloquesMiercoles = bloqueDao.listarBloques();
	List<Bloque> listaBloquesJueves = bloqueDao.listarBloques();
	List<Bloque> listaBloquesViernes = bloqueDao.listarBloques();
	List<Bloque> listaBloquesSabado = bloqueDao.listarBloques();
	List<Bloque> listaBloquesDomingo = bloqueDao.listarBloques();
	Esteticista est=null;
	MaestricoDAO mdao = new MaestricoDAO();

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
		
		bloquesModel = new ListModelList<Bloque>(listaBloquesLunes);
		listaLunes.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesMartes);
		listaMartes.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesMiercoles);
		listaMiercoles.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesJueves);
		listaJueves.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesViernes);
		listaViernes.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesSabado);
		listaSabado.setModel(bloquesModel);
		bloquesModel = new ListModelList<Bloque>(listaBloquesDomingo);
		listaDomingo.setModel(bloquesModel);
	}
	
	@Listen("onClick = #agregarLunes")
	public void doAgregar()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaLunes.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaLunes.getSelectedItem().getValue();
					listaBloquesLunesSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesLunesSeleccionados);
					listaLunesSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesLunes.remove(listaLunes.getSelectedIndex());
					listaLunes.setModel(new ListModelList<Bloque>(listaBloquesLunes));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarMartes")
	public void doAgregarMartes()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaMartes.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaMartes.getSelectedItem().getValue();
					listaBloquesMartesSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesMartesSeleccionados);
					listaMartesSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesMartes.remove(listaMartes.getSelectedIndex());
					listaMartes.setModel(new ListModelList<Bloque>(listaBloquesMartes));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarMiercoles")
	public void doAgregarMiercoles()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaMiercoles.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaMiercoles.getSelectedItem().getValue();
					listaBloquesMiercolesSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesMiercolesSeleccionados);
					listaMiercolesSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesMiercoles.remove(listaMiercoles.getSelectedIndex());
					listaMiercoles.setModel(new ListModelList<Bloque>(listaBloquesMiercoles));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarJueves")
	public void doAgregarJueves()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaJueves.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaJueves.getSelectedItem().getValue();
					listaBloquesJuevesSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesJuevesSeleccionados);
					listaJuevesSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesJueves.remove(listaJueves.getSelectedIndex());
					listaJueves.setModel(new ListModelList<Bloque>(listaBloquesJueves));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarViernes")
	public void doAgregarViernes()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaViernes.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaViernes.getSelectedItem().getValue();
					listaBloquesViernesSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesViernesSeleccionados);
					listaViernesSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesViernes.remove(listaViernes.getSelectedIndex());
					listaViernes.setModel(new ListModelList<Bloque>(listaBloquesViernes));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarSabado")
	public void doAgregarSabado()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaSabado.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaSabado.getSelectedItem().getValue();
					listaBloquesSabadoSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesSabadoSeleccionados);
					listaSabadoSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesSabado.remove(listaSabado.getSelectedIndex());
					listaSabado.setModel(new ListModelList<Bloque>(listaBloquesSabado));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	@Listen("onClick = #agregarDomingo")
	public void doAgregarDomingo()
	{
		if(est==null){
			Messagebox.show("Debe buscar un esteticista");
		}
		else{
			if(listaDomingo.getSelectedIndex() == -1)
			{
				Messagebox.show("Debe serleccionar un bloque");
			}
			else{
					Bloque b = null;
					b = listaDomingo.getSelectedItem().getValue();
					listaBloquesDomingoSeleccionados.add(b);
					bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesDomingoSeleccionados);
					listaDomingoSeleccionados.setModel(bloquesSeleccionadoModel);
					listaBloquesDomingo.remove(listaDomingo.getSelectedIndex());
					listaDomingo.setModel(new ListModelList<Bloque>(listaBloquesDomingo));
					//listaBloques.remove(bloquesSeleccionadoModel.get)
					//listaTodosServicios.remove(listaServicios.getSelectedIndex());
			}
		}
	}
	
	@Listen("onClick = #quitarLunes")
	public void doQuitarLunes()
	{
		
		if(listaLunesSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaLunesSeleccionados.getSelectedItem().getValue();
		listaBloquesLunes.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesLunes);
		listaLunes.setModel(bloquesModel);
		listaBloquesLunesSeleccionados.remove(listaLunesSeleccionados.getSelectedIndex());
		listaLunesSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesLunesSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarMartes")
	public void doQuitarMartes()
	{
		
		if(listaLunesSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaMartesSeleccionados.getSelectedItem().getValue();
		listaBloquesMartes.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesMartes);
		listaMartes.setModel(bloquesModel);
		listaBloquesMartesSeleccionados.remove(listaMartesSeleccionados.getSelectedIndex());
		listaMartesSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesMartesSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarMiercoles")
	public void doQuitarMiercoles()
	{
		
		if(listaMiercolesSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaMiercolesSeleccionados.getSelectedItem().getValue();
		listaBloquesMiercoles.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesMiercoles);
		listaMiercoles.setModel(bloquesModel);
		listaBloquesMiercolesSeleccionados.remove(listaMiercolesSeleccionados.getSelectedIndex());
		listaMiercolesSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesMiercolesSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarJueves")
	public void doQuitarJueves()
	{
		
		if(listaJuevesSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaJuevesSeleccionados.getSelectedItem().getValue();
		listaBloquesJueves.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesJueves);
		listaJueves.setModel(bloquesModel);
		listaBloquesJuevesSeleccionados.remove(listaJuevesSeleccionados.getSelectedIndex());
		listaJuevesSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesJuevesSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarViernes")
	public void doQuitarViernes()
	{
		
		if(listaViernesSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaViernesSeleccionados.getSelectedItem().getValue();
		listaBloquesViernes.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesViernes);
		listaViernes.setModel(bloquesModel);
		listaBloquesViernesSeleccionados.remove(listaViernesSeleccionados.getSelectedIndex());
		listaViernesSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesViernesSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarMiSabado")
	public void doQuitarSabado()
	{
		
		if(listaSabadoSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaSabadoSeleccionados.getSelectedItem().getValue();
		listaBloquesSabado.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesSabado);
		listaSabado.setModel(bloquesModel);
		listaBloquesSabadoSeleccionados.remove(listaSabadoSeleccionados.getSelectedIndex());
		listaSabadoSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesSabadoSeleccionados));
		
		}
	}
	@Listen("onClick = #quitarDomingo")
	public void doQuitarDomingo()
	{
		
		if(listaDomingoSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un bloque");
		}
		else
		{
		Bloque b = null;
		b = listaDomingoSeleccionados.getSelectedItem().getValue();
		listaBloquesDomingo.add(b);
						
		bloquesModel = new ListModelList<Bloque>(listaBloquesDomingo);
		listaDomingo.setModel(bloquesModel);
		listaBloquesDomingoSeleccionados.remove(listaDomingoSeleccionados.getSelectedIndex());
		listaDomingoSeleccionados.setModel(new ListModelList<Bloque>(listaBloquesDomingoSeleccionados));
		
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
