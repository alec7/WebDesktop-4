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
import modelo.Horario;
import modelo.Horario_Esteticista;
import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Servicio;
import modelo.Usuario;
import modeloDAO.BloqueDAO;
import modeloDAO.EsteticistaDAO;
import modeloDAO.HorarioDAO;
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
	HorarioDAO hDao = new HorarioDAO();
	EsteticistaDAO estDao = new EsteticistaDAO();
	List<Bloque> listaBloquesLunesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesMartesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesMiercolesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesJuevesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesViernesSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesSabadoSeleccionados = new ArrayList<Bloque>();
	List<Bloque> listaBloquesDomingoSeleccionados = new ArrayList<Bloque>();
	List<Maestrico> diasLab = new ArrayList<Maestrico>();
	List<Horario_Esteticista> horariosEst = new ArrayList<Horario_Esteticista>();
	List<Horario> horarios = new ArrayList<Horario>();
	List<Horario> horariosPorEsteticistas = new ArrayList<Horario>();
	List<Tab> TabDias = new ArrayList<Tab>();
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
			horariosEst = hDao.listaHorariosEsteticista(est.getCedula());
			horarios = hDao.listaHorarios();
			TabDias = tabs.getChildren();
			for(int i=0; i<horariosEst.size();i++){
				for(int j=0;j<horarios.size();j++){
					if(horariosEst.get(i).getCodigo_horario().equals(horarios.get(j).getCodigo())){
						bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque());
						if(TabDias.size()>=1){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(0).getId())){
								listaBloquesLunesSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarLunes(listaBloquesLunes, listaBloquesLunesSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesLunesSeleccionados);
								listaLunesSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=2){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(1).getId())){
								listaBloquesMartesSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarMartes(listaBloquesMartes, listaBloquesMartesSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesMartesSeleccionados);
								listaMartesSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=3){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(2).getId())){
								listaBloquesMiercolesSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarMiercoles(listaBloquesMiercoles, listaBloquesMiercolesSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesMiercolesSeleccionados);
								listaMiercolesSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=4){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(3).getId())){
								listaBloquesJuevesSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarJueves(listaBloquesJueves, listaBloquesJuevesSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesJuevesSeleccionados);
								listaJuevesSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=5){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(4).getId())){
								listaBloquesViernesSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarViernes(listaBloquesViernes, listaBloquesViernesSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesViernesSeleccionados);
								listaViernesSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=6){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(5).getId())){
								listaBloquesSabadoSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarSabado(listaBloquesSabado, listaBloquesSabadoSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesSabadoSeleccionados);
								listaSabadoSeleccionados.setModel(bloquesSeleccionadoModel);
							}}
							if(TabDias.size()>=7){
							if(horarios.get(j).getCodigo_dia_laborable().equals(TabDias.get(6).getId())){
								listaBloquesDomingoSeleccionados.add(bloqueDao.buscarBloque(horarios.get(j).getCodigo_bloque()));
								FiltrarDomingo(listaBloquesDomingo, listaBloquesDomingoSeleccionados);
								bloquesSeleccionadoModel = new ListModelList<Bloque>(listaBloquesDomingoSeleccionados);
								listaDomingoSeleccionados.setModel(bloquesSeleccionadoModel);
							}
						}
					}
				}
			}
			listarBloques();
		}
		
	}
	
	private void FiltrarLunes(List<Bloque> todos, List<Bloque> est) {
		
		for (int i = 0; i < todos.size(); i++) {
			
			for (int k = 0; k < est.size(); k++) 
			{
				if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
				{
					listaBloquesLunes.remove(i);		
				}
			}	
		}
	}
private void FiltrarMartes(List<Bloque> todos, List<Bloque> est) {
		
		for (int i = 0; i < todos.size(); i++) {
			
			for (int k = 0; k < est.size(); k++) 
			{
				if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
				{
					listaBloquesMartes.remove(i);		
				}
			}	
		}
	}
private void FiltrarMiercoles(List<Bloque> todos, List<Bloque> est) {
	
	for (int i = 0; i < todos.size(); i++) {
		
		for (int k = 0; k < est.size(); k++) 
		{
			if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
			{
				listaBloquesMiercoles.remove(i);		
			}
		}	
	}
}
private void FiltrarJueves(List<Bloque> todos, List<Bloque> est) {
	
	for (int i = 0; i < todos.size(); i++) {
		
		for (int k = 0; k < est.size(); k++) 
		{
			if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
			{
				listaBloquesJueves.remove(i);		
			}
		}	
	}
}
private void FiltrarViernes(List<Bloque> todos, List<Bloque> est) {
	
	for (int i = 0; i < todos.size(); i++) {
		
		for (int k = 0; k < est.size(); k++) 
		{
			if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
			{
				listaBloquesViernes.remove(i);		
			}
		}	
	}
}
private void FiltrarSabado(List<Bloque> todos, List<Bloque> est) {
	
	for (int i = 0; i < todos.size(); i++) {
		
		for (int k = 0; k < est.size(); k++) 
		{
			if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
			{
				listaBloquesSabado.remove(i);		
			}
		}	
	}
}
private void FiltrarDomingo(List<Bloque> todos, List<Bloque> est) {
	
	for (int i = 0; i < todos.size(); i++) {
		
		for (int k = 0; k < est.size(); k++) 
		{
			if(todos.get(i).getCodigo().equals(est.get(k).getCodigo()))
			{
				listaBloquesDomingo.remove(i);		
			}
		}	
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
	public void cargarBloquesEsteticista(){
		
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

//	if(listaBloquesLunesSeleccionados.size()>0){
//		codigoDia =TabDias.get(0).getId();
//		for(int i=0; i<listaBloquesLunesSeleccionados.size();i++){
//				String codigo = hDao.TotalRegistros();
//				Horario h = new Horario(codigo, codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo(), "Activo");
//				String codigoAgenda = hDao.codigoAgenda(codigoDia);
//				String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
//				if(listaBloquesLunesSeleccionados.size()>horariosEst.size()){
//						//hDao.buscarHorarioEst(horariosEst.get(j).getCodigo_horario(),horariosEst.get(j).getCodigo_esteticista(),horariosEst.get(j).getCodigo_agenda())
//							hDao.registrarHorario(h);
//							estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//						
//				}
//			}
//		}

	@Listen("onClick = #guardar")
	public void guardar(){
		TabDias = tabs.getChildren();
		String codigoDia= null;
		for(int i=0;i<horarios.size();i++){
			for(int j=0;j<horariosEst.size();j++){
				if(horarios.get(i).getCodigo().equals(horariosEst.get(j).getCodigo_horario())){
					horariosPorEsteticistas.add(hDao.horariosPorEstetictas(horarios.get(i)));
				}
			}
		}
		if(listaBloquesLunesSeleccionados.size()>0){
			codigoDia =TabDias.get(0).getId();
			for(int i=0; i<listaBloquesLunesSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		
		
		
	
		if(listaBloquesMartesSeleccionados.size()>0){
			codigoDia =TabDias.get(1).getId();
			for(int i=0; i<listaBloquesMartesSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		
		if(listaBloquesMiercolesSeleccionados.size()>0){
			codigoDia =TabDias.get(2).getId();
			for(int i=0; i<listaBloquesMiercolesSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}


		if(listaBloquesJuevesSeleccionados.size()>0){
			codigoDia =TabDias.get(3).getId();
			for(int i=0; i<listaBloquesJuevesSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		if(listaBloquesViernesSeleccionados.size()>0){
			codigoDia =TabDias.get(4).getId();
			for(int i=0; i<listaBloquesViernesSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		if(listaBloquesSabadoSeleccionados.size()>0){
			codigoDia =TabDias.get(5).getId();
			for(int i=0; i<listaBloquesSabadoSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		if(listaBloquesDomingoSeleccionados.size()>0){
			codigoDia =TabDias.get(6).getId();
			for(int i=0; i<listaBloquesDomingoSeleccionados.size();i++){
				String codigo = hDao.TotalRegistros();
				Horario ho = hDao.validarHorario(codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo());
				if(horariosPorEsteticistas.size()>0){
					for(int l=0;l<horariosPorEsteticistas.size();l++){
						Horario h = new Horario(codigo, codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo(), "Activo");
						if(horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
							if(!(h.getCodigo_bloque().equals(horar.getCodigo_bloque())& h.getCodigo_dia_laborable().equals(horar.getCodigo_dia_laborable()))){
								hDao.registrarHorario(h);
								estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
							}
							
						}
						else{
								Horario horar=hDao.buscarHorario(horariosEst.get(l).getCodigo_horario());
								hDao.actualizarHorario(horar);
								hDao.actualizarHorarioEsteticista(horariosEst.get(l));

						}
						
					}
				}
				else{
					Horario h = new Horario(codigo, codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo(), "Activo");
//					if(!horariosEst.get(l).getCodigo_horario().equals(ho.getCodigo())){
//						Messagebox.show("IF");
						String codigoAgenda = hDao.codigoAgenda(codigoDia);
						String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
						hDao.registrarHorario(h);
						estDao.agregarHoriarioEsteticista(h, cedula.getText(), codigoAgenda, codigoHorarioEst);
//					}
				}
				
			}
		}
		else{	
			for(int i=0; i<horariosPorEsteticistas.size();i++){
				Horario horar=hDao.buscarHorario(horariosEst.get(i).getCodigo_horario());
				hDao.actualizarHorario(horar);
				hDao.actualizarHorarioEsteticista(horariosEst.get(i));
			}
			
		}
		Messagebox.show("Horario Registrado Satisfactoriamente");
	}
	
	
	
	
}
