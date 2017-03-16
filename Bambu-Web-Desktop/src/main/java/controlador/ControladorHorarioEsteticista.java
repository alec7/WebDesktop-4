package controlador;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;

import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;

import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;

import modelo.Bloque;

import modelo.Esteticista;
import modelo.Horario;
import modelo.Horario_Esteticista;
import modelo.Maestrico;

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
	private Textbox apellido;
	@Wire
	private Button buscar;
	@Wire
	private Div dias_laborables;
	@Wire
	private Tabs tabs;
	@Wire
	private Tabpanels tabpanels;
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
	
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/horarioEsteticista.html");
	}
	@Listen("onClick = #buscar")
	public void buscarEsteticista(){
		est= estDao.buscarEsteticista(cedula.getValue());
		if(est==null){
			Messagebox.show("Esteticista no registrado", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			listaBloquesLunesSeleccionados.clear();
			listaBloquesMartesSeleccionados.clear();
			listaBloquesMiercolesSeleccionados.clear();
			listaBloquesJuevesSeleccionados.clear();
			listaBloquesViernesSeleccionados.clear();
			listaBloquesSabadoSeleccionados.clear();
			listaBloquesDomingoSeleccionados.clear();
			nombre.setText(est.getNombre());
			apellido.setText(est.getApellido());
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
			listarBloquesSeleccionados();
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
	public void listarBloquesSeleccionados(){
		DateFormat format = new SimpleDateFormat( "h:mm a" );
		for(int i=0;i<listaBloquesLunesSeleccionados.size();i++){
			String str = format.format( listaBloquesLunesSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesLunesSeleccionados.get(i).getHora_fin() );
			listaBloquesLunesSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesMartesSeleccionados.size();i++){
			String str = format.format( listaBloquesMartesSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesMartesSeleccionados.get(i).getHora_fin() );
			listaBloquesMartesSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesMiercolesSeleccionados.size();i++){
			String str = format.format( listaBloquesMiercolesSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesMiercolesSeleccionados.get(i).getHora_fin() );
			listaBloquesMiercolesSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesJuevesSeleccionados.size();i++){
			String str = format.format( listaBloquesJuevesSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesJuevesSeleccionados.get(i).getHora_fin() );
			listaBloquesJuevesSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesViernesSeleccionados.size();i++){
			String str = format.format( listaBloquesViernesSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesViernesSeleccionados.get(i).getHora_fin() );
			listaBloquesViernesSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesSabadoSeleccionados.size();i++){
			String str = format.format( listaBloquesSabadoSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesSabadoSeleccionados.get(i).getHora_fin() );
			listaBloquesSabadoSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesDomingoSeleccionados.size();i++){
			String str = format.format( listaBloquesDomingoSeleccionados.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesDomingoSeleccionados.get(i).getHora_fin() );
			listaBloquesDomingoSeleccionados.get(i).setDescripcion(str+"-"+str1);
		}
	}
	public void listarBloques(){
		DateFormat format = new SimpleDateFormat( "h:mm a" );
		for(int i=0;i<listaBloquesLunes.size();i++){
			String str = format.format( listaBloquesLunes.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesLunes.get(i).getHora_fin() );
			listaBloquesLunes.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesMartes.size();i++){
			String str = format.format( listaBloquesMartes.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesMartes.get(i).getHora_fin() );
			listaBloquesMartes.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesMiercoles.size();i++){
			String str = format.format( listaBloquesMiercoles.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesMiercoles.get(i).getHora_fin() );
			listaBloquesMiercoles.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesJueves.size();i++){
			String str = format.format( listaBloquesJueves.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesJueves.get(i).getHora_fin() );
			listaBloquesJueves.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesViernes.size();i++){
			String str = format.format( listaBloquesViernes.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesViernes.get(i).getHora_fin() );
			listaBloquesViernes.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesSabado.size();i++){
			String str = format.format( listaBloquesSabado.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesSabado.get(i).getHora_fin() );
			listaBloquesSabado.get(i).setDescripcion(str+"-"+str1);
		}
		for(int i=0;i<listaBloquesDomingo.size();i++){
			String str = format.format( listaBloquesDomingo.get(i).getHora_inicio() );
			String str1 = format.format( listaBloquesDomingo.get(i).getHora_fin() );
			listaBloquesDomingo.get(i).setDescripcion(str+"-"+str1);
		}

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
		
		if(listaMartesSeleccionados.getSelectedIndex() == -1)
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
			Messagebox.show("Debe seleccionar un blque ", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
			Messagebox.show("Debe seleccionar un blque ", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
			Messagebox.show("Debe seleccionar un blque ", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
			Messagebox.show("Debe seleccionar un blque ", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
			Messagebox.show("Debe seleccionar un blque ", "Información", Messagebox.OK, Messagebox.INFORMATION);
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
		//SI hay al menos un registro en la lista de servicios asociados
		if(listaBloquesLunesSeleccionados.size()>0){
			codigoDia =TabDias.get(0).getId();
			//recorro la lista de bloques asociados
			for(int i=0; i<listaBloquesLunesSeleccionados.size();i++){
				//Busco si uno de los bloques que estan seleccionados ya existen
				if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo())==false){
					//si existe no hago nada, debo ver si esta en la BD pero no en la lista
					String codigo = hDao.TotalRegistros();
					Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo(), "Activo");
					//Registro en la tb_horario
					hDao.registrarHorario(nuevoHorario);
					String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
					String codigoAgenda = hDao.codigoAgenda(codigoDia);
					//regitro en la tb_horario_esteticista
					Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
					hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
					//Messagebox.show("Agregue un nuevo registro");
				}
				else{
					//Si existe en la BD pero Inactivo y lo quiero volver a agregar
					Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesLunesSeleccionados.get(i).getCodigo());
					hDao.activarrHorarioEsteticista(he.getCodigo_horario());
					hDao.activarHorario(he.getCodigo_horario());
				}
				
			}
			
		}
		//Busco los bloques que estan en la bd pero no en la lista de seleccionados
		for(int i=0; i<horariosPorEsteticistas.size();i++){
			//Si no he seleccionado nada o quite los que tenia asociados
			if(listaBloquesLunesSeleccionados.size()>0){
				for(int j=0; j<listaBloquesLunesSeleccionados.size();j++){
					//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
					//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
					//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
					if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesLunesSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
						//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
						break;
					}
					else{
						//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
						hDao.actualizarHorario(horariosPorEsteticistas.get(i));
						hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
						//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
					}
				}
			}
			else{
				
				for(int j=0; j<horariosEst.size();j++){
					hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
					hDao.actualizarHorario(horariosPorEsteticistas.get(i));
					
				}
			}
			
			
		}
		//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesMartesSeleccionados.size()>0){
					codigoDia =TabDias.get(1).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesMartesSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesMartesSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesMartesSeleccionados.size()>0){
						for(int j=0; j<listaBloquesMartesSeleccionados.size();j++){
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesMartesSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
				}
				
				
				//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesMiercolesSeleccionados.size()>0){
					codigoDia =TabDias.get(2).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesMiercolesSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesMiercolesSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesMiercolesSeleccionados.size()>0){
						for(int j=0; j<listaBloquesMiercolesSeleccionados.size();j++){
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesMiercolesSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
				}
				
				//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesJuevesSeleccionados.size()>0){
					codigoDia =TabDias.get(3).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesJuevesSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesJuevesSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesJuevesSeleccionados.size()>0){
						for(int j=0; j<listaBloquesJuevesSeleccionados.size();j++){
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesJuevesSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
				}
				
				//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesViernesSeleccionados.size()>0){
					codigoDia =TabDias.get(4).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesViernesSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesViernesSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesViernesSeleccionados.size()>0){
						for(int j=0; j<listaBloquesViernesSeleccionados.size();j++){
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesViernesSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
				}
				
				//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesSabadoSeleccionados.size()>0){
					codigoDia =TabDias.get(5).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesSabadoSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesSabadoSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesSabadoSeleccionados.size()>0){
						for(int j=0; j<listaBloquesSabadoSeleccionados.size();j++){
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesSabadoSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
				}
				
				//SI hay al menos un registro en la lista de servicios asociados
				if(listaBloquesDomingoSeleccionados.size()>0){
					codigoDia =TabDias.get(6).getId();
					//recorro la lista de bloques asociados
					for(int i=0; i<listaBloquesDomingoSeleccionados.size();i++){
						//Busco si uno de los bloques que estan seleccionados ya existen
						if(hDao.buscarHorarioExistete(cedula.getText(), codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo())==false){
							//si existe no hago nada, debo ver si esta en la BD pero no en la lista
							String codigo = hDao.TotalRegistros();
							Horario nuevoHorario = new Horario(codigo, codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo(), "Activo");
							//Registro en la tb_horario
							hDao.registrarHorario(nuevoHorario);
							String codigoHorarioEst = hDao.TotalRegistrosHorarioEsteticista();
							String codigoAgenda = hDao.codigoAgenda(codigoDia);
							//regitro en la tb_horario_esteticista
							Horario_Esteticista nuevoHorarioEsteti = new Horario_Esteticista(codigoHorarioEst, nuevoHorario.getCodigo(), cedula.getText(), "Activo", codigoAgenda);
							hDao.registrarHorarioEsteticista(nuevoHorarioEsteti);
							//Messagebox.show("Agregue un nuevo registro");
						}
						else{
							//Si existe en la BD pero Inactivo y lo quiero volver a agregar
							Horario_Esteticista he = hDao.buscarHorarioEst(cedula.getText(), codigoDia, listaBloquesDomingoSeleccionados.get(i).getCodigo());
							hDao.activarrHorarioEsteticista(he.getCodigo_horario());
							hDao.activarHorario(he.getCodigo_horario());
						}
						
					}
					
				}
				//Busco los bloques que estan en la bd pero no en la lista de seleccionados
				for(int i=0; i<horariosPorEsteticistas.size();i++){
					//Si no he seleccionado nada o quite los que tenia asociados
					if(listaBloquesDomingoSeleccionados.size()>0){
						for(int j=0; j<listaBloquesDomingoSeleccionados.size();j++){
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							//DEBO CAMBIAR EL CODIGO DEL DIA PARA CADA DIA!!!!!!!!!!!!!!!!!
							if(horariosPorEsteticistas.get(i).getCodigo_bloque().equals(listaBloquesDomingoSeleccionados.get(j).getCodigo())&&horariosPorEsteticistas.get(i).getCodigo_dia_laborable().equals(codigoDia)){
								//Messagebox.show("es igual no hago nada"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
								break;
							}
							else{
								//Horario h = hDao.buscarHorario(horariosPorEsteticistas.get(i).getCodigo());
								hDao.actualizarHorario(horariosPorEsteticistas.get(i));
								hDao.actualizarHorarioEsteticista(horariosPorEsteticistas.get(i).getCodigo());
								//Messagebox.show("no es igual lo actualizo"+horariosPorEsteticistas.get(i).getCodigo()+"iteracion "+String.valueOf(i));
							}
						}
					}
					else{
						
						for(int j=0; j<horariosEst.size();j++){
							hDao.actualizarHorarioEsteticista(horariosEst.get(j).getCodigo_horario());
							hDao.actualizarHorario(horariosPorEsteticistas.get(i));
							
						}
					}
					
					
				}	
		Messagebox.show("Horario Registrado Satisfactoriamente ", "Información", Messagebox.OK, Messagebox.INFORMATION);
	}
	
	
	
	
}
