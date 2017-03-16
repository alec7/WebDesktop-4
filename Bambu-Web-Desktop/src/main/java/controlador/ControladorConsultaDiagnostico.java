package controlador;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import modelo.Antecedente;
import modelo.AntecedenteCliente;
import modelo.Cliente;
import modelo.Cod_Des;
import modelo.Habito;
import modelo.HabitoCliente;
import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Rol;
import modelo.Servicio;
import modeloDAO.AntecedenteClienteDAO;
import modeloDAO.AntecedenteDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.HabitoClienteDAO;
import modeloDAO.HabitoDAO;
import modeloDAO.IndicadorDAO;
import modeloDAO.NecesidadDAO;
import modeloDAO.ServicioDAO;
import modeloDAO.ServicioRecomendadoDAO;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.ListModelMap;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;

public class ControladorConsultaDiagnostico extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Wire
	Tabpanel tabHabitos;
	@Wire
	Tabpanel tabAntecedentes;
	@Wire
	Tabpanel tabIndicadores;
	@Wire
	Textbox cedula;
	@Wire
	Textbox apellido;
	@Wire
	Textbox nombre;
	@Wire
	Listbox necesidades;
	@Wire
	Listbox listaHabitos;
	@Wire
	Listbox listaAntencedentes;
	@Wire
	Listbox listaRecomendados;
	
	
	/*** gerardo **/
		ListModelList<Habito> habitosListModel;
		String nombreCliente;
		
		
		Habito habito;
		HabitoCliente habitocliente;
		HabitoClienteDAO habClienteDAO = new HabitoClienteDAO();
		
		HabitoDAO habDao = new HabitoDAO();
	
		List<Habito> habitosIntactos = habDao.listaHabito();
		
		List<Habito> habitosAll = habDao.listaHabito();
		
		AntecedenteDAO antecedenteDAO = new AntecedenteDAO();
		List<Antecedente> antecedentesAll =  antecedenteDAO.listaAntecedentes();
		ListModelList<Antecedente> antecedenteListModel;
		Antecedente antecedente;
		
		AntecedenteClienteDAO antClienteDao = new AntecedenteClienteDAO();
		AntecedenteCliente antecedentecliente;
		
		AntecedenteDAO antDao  = new AntecedenteDAO();
		AntecedenteCliente antCliente = new AntecedenteCliente();
		
		List<Antecedente> antecedentesIntactos = antDao.listaAntecedentes();
		
		Map<String, String> servPorHabParaUnCliente = new TreeMap<>();
		
		ListModelList<Map<String, String>> recomendadosListModel;
		
		AntecedenteClienteDAO antecedenteClienteDao = new AntecedenteClienteDAO(); 
		
		ServicioDAO servicioDao = new ServicioDAO();
		
		ServicioRecomendadoDAO servicioRecomendadoDao = new ServicioRecomendadoDAO();
		
	/* fin gerardo */
	
	
	
	Maestrico maestrico = new Maestrico();
	HabitoCliente habC = new HabitoCliente();
	
	ClienteDAO cdao = new ClienteDAO();
	NecesidadDAO nDao = new NecesidadDAO();
	HabitoCliente habCliente = new HabitoCliente();
	

	
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("../ayudas/consultaDiagnostico.html");
			
	}
	 final ArrayList<HabitoCliente> habitosClientes = new ArrayList<HabitoCliente>();
	 final ArrayList<AntecedenteCliente> antecedentesClientes = new ArrayList<AntecedenteCliente>();
	Cliente c;
	Cod_Des cd;
	ListModelList<Cod_Des> necesidadListModel;
	List<Cod_Des> listNecesidad = new ArrayList<Cod_Des>();
	ListModelList<Maestrico> descripcionNecesidad;
	
	String cedulaCliente = null;
	

	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		cargarHabitos(habitosAll);
		cargarAntecedentes(antecedentesAll);
		
		
	}
		
	
	
	public void cargarHabitos(List<Habito> arrHabitos){
		

		habitosListModel = new ListModelList<Habito>(arrHabitos);
		listaHabitos.setModel(habitosListModel);
	}
	public void cargarAntecedentes(List<Antecedente> arrAntecedentes){
		

		antecedenteListModel = new ListModelList<Antecedente>(arrAntecedentes);
		listaAntencedentes.setModel(antecedenteListModel);
	}
	public void cargarserviciosRecPorHabito(){

		List<Servicio> serviciosPorHabitosAll = habClienteDAO.serviciosPorHabitosParaUnCliente(cedulaCliente);
		listaRecomendados.setModel(new ListModelList<Servicio>(serviciosPorHabitosAll));
		
	}
	public void cargarServiciosRecAntecedentes(){
		List<Servicio> servicosPorAntecedentesAll = antecedenteClienteDao.serviciosPorAntecedentesParaUnCliente(cedulaCliente);
		listaRecomendados.setModel(new ListModelList<Servicio>(servicosPorAntecedentesAll));
	}
	public void cargarServiciosRecomendados(){
		List<Servicio> recomendaciones = interseccionSericiosRecomendados();
		listaRecomendados.setModel(new ListModelList<Servicio>(recomendaciones));
	}
	@Listen("onClick = #buscar")
	public void buscar()
	{
		cedulaCliente = cedula.getText();
		c = cdao.buscarCliente(cedula.getText());
		if(c!=null){
			nombre.setText(c.getNombre());
			apellido.setText(c.getApellido());
			
			listNecesidad = nDao.listarNecesidad(c.getCedula());
			nombreNecesidad();
			necesidadListModel = new ListModelList<Cod_Des>(listNecesidad);
			
			necesidades.setModel(necesidadListModel);
			
			
			
			ArrayList<HabitoCliente> habxcliente =   habDao.buscarHabitosXCliente(cedula.getText());
		
			   // LISTA DE HABITOS CON MATCH DE STATUS DE HABITOS POR CLIENTE
			for (int i = 0; i < habitosAll.size(); i++) {
				
				habito = habitosAll.get(i);
				
				for (int j = 0; j < habxcliente.size(); j++) {
					habitocliente = habxcliente.get(j);
					
					if (habito.getCodigo().equals(habitocliente.getCodigo_habito())) {
						habito.setStatus(habitocliente.getStatus());
					}
				}
			}
			   
			
				
			//LISTA DE ANTECEDENTES CON MATCH DE STATUS DE ANTECEDETES POR CLIENTE 
			ArrayList<AntecedenteCliente> antClientes = antClienteDao.buscarAntecedenteXCliente(cedulaCliente);
			System.out.println("antClientes" + antClientes.size());
			for (int i = 0; i < antecedentesAll.size(); i++) {
				
				antecedente = antecedentesAll.get(i);
				
				for (int j = 0; j < antClientes.size(); j++) {
					antecedentecliente = antClientes.get(j);
					
					if (antecedente.getCodigo().equals(antecedentecliente.getCodigo_antecedente())) {
						antecedente.setStatus(antecedentecliente.getStatus());
					}
				}
			}
			
			cargarHabitos(habitosAll);
			cargarAntecedentes(antecedentesAll);
			//cargarserviciosRec();
			//cargarServiciosRecAntecedentes();
			cargarServiciosRecomendados();
		}else{
			Messagebox.show("Cliente no Registrado ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		
			cargarHabitos(habitosIntactos);
			cargarAntecedentes(antecedentesIntactos);
		}
		
		
		
	}
	public List<Servicio> interseccionSericiosRecomendados(){
		List<Servicio> serviciosPorHabitosAll = habClienteDAO.serviciosPorHabitosParaUnCliente(cedulaCliente);
		System.out.println("****serviciosPorHabitosAll*****" + serviciosPorHabitosAll.size());
		for (int i = 0; i < serviciosPorHabitosAll.size(); i++) {
			System.out.println(serviciosPorHabitosAll.get(i).getCodigo());
		}
		List<Servicio> servicosPorAntecedentesAll = antecedenteClienteDao.serviciosPorAntecedentesParaUnCliente(cedulaCliente);
		System.out.println("****servicosPorAntecedentesAll*****" + servicosPorAntecedentesAll.size());
		for (int i = 0; i < servicosPorAntecedentesAll.size(); i++) {
			
			System.out.println(servicosPorAntecedentesAll.get(i).getCodigo());
		}
		List<Servicio> interseccionDeServicios = new ArrayList<>();
		
		if (serviciosPorHabitosAll.size() == 0  && servicosPorAntecedentesAll.size() == 0) {
			List<Servicio> servicioAll = servicioDao.listaServicios();
			for (int i = 0; i < servicioAll.size(); i++) {
				interseccionDeServicios.add(new Servicio(servicioAll.get(i).getCodigo(), servicioAll.get(i).getTitulo()));
			}
		}
		
		if (serviciosPorHabitosAll.size() > 0  && servicosPorAntecedentesAll.size() > 0) {

			for (int i = 0; i < serviciosPorHabitosAll.size(); i++) 
			{
				for (int j = 0; j < servicosPorAntecedentesAll.size(); j++) 
				{
					if (serviciosPorHabitosAll.get(i).getCodigo().equals(servicosPorAntecedentesAll.get(j).getCodigo())) 
					{
						interseccionDeServicios.add(new Servicio(serviciosPorHabitosAll.get(i).getCodigo(), serviciosPorHabitosAll.get(i).getTitulo()));
					}
				}
			}
		}
		
		if (serviciosPorHabitosAll.size() > 0  && servicosPorAntecedentesAll.size() == 0) {
			for (int i = 0; i < serviciosPorHabitosAll.size(); i++) 
			{
				interseccionDeServicios.add(new Servicio(serviciosPorHabitosAll.get(i).getCodigo(), serviciosPorHabitosAll.get(i).getTitulo()));
			}
		}
		
		if (serviciosPorHabitosAll.size() == 0  && servicosPorAntecedentesAll.size() > 0) 
		{
			for (int j = 0; j < servicosPorAntecedentesAll.size(); j++) 
			{
				interseccionDeServicios.add(new Servicio(servicosPorAntecedentesAll.get(j).getCodigo(), servicosPorAntecedentesAll.get(j).getTitulo()));
			}
		}
		
		/*
		for (int i = 0; i < interseccionDeServicios.size(); i++) {
			if (servicioRecomendadoDao.existe(interseccionDeServicios.get(i).getCodigo(), cedulaCliente) && servicioRecomendadoDao.estatus(interseccionDeServicios.get(i).getCodigo(), cedulaCliente)==false ) {
				// actualizar
				servicioRecomendadoDao.actualizarServicio(interseccionDeServicios.get(i).getCodigo(), cedulaCliente,  true );
			}
			if (servicioRecomendadoDao.existe(interseccionDeServicios.get(i).getCodigo(), cedulaCliente) && servicioRecomendadoDao.estatus(interseccionDeServicios.get(i).getCodigo(), cedulaCliente)==true ) {
				servicioRecomendadoDao.actualizarServicio(interseccionDeServicios.get(i).getCodigo(), cedulaCliente,  false );
			}
			
				
				
			if (servicioRecomendadoDao.existe(interseccionDeServicios.get(i).getCodigo(), cedulaCliente)==false ) {
				String codigoTabla = servicioRecomendadoDao.TotalRegistros("tb_servicio_recomendado");
				servicioRecomendadoDao.insertarServicio(codigoTabla, interseccionDeServicios.get(i).getCodigo(), cedulaCliente,  true);
			}
			
			else{
				
				
				
				servicioRecomendadoDao.insertarServicio(codigoTabla, interseccionDeServicios.get(i).getCodigo(), cedulaCliente,  true);
			}
		}
		
		*/
		
		for (int i = 0; i < interseccionDeServicios.size(); i++) {
			String codigoTabla = servicioRecomendadoDao.TotalRegistros("tb_servicio_recomendado");
			servicioRecomendadoDao.insertarServicio(codigoTabla, interseccionDeServicios.get(i).getCodigo(), cedulaCliente,  true);
		}
		
	
		return interseccionDeServicios;
	}
	@Listen("onCheck = #listaHabitos")
	public void chequearOpcion(ForwardEvent evt)
	{
		Checkbox cbox = (Checkbox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)cbox.getParent().getParent();
		
		
		boolean checked = true;
		int posicion ;
		checked = cbox.isChecked();
		posicion = litem.getIndex();

		
		habitosAll.get(posicion).setStatus(checked);
		
		
		
	}
	@Listen("onCheck = #listaAntencedentes")
	public void chequearOpcionAntecedentes(ForwardEvent evt)
	{
		Checkbox cbox = (Checkbox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)cbox.getParent().getParent();
		//Messagebox.show(String.valueOf(litem.getIndex()));
		
		boolean checked = true;
		int posicion ;
		checked = cbox.isChecked();
		posicion = litem.getIndex();

		
		antecedentesAll.get(posicion).setStatus(checked);
		
		
		
	}
	


	public void nombreNecesidad(){
		
		for(int i=0;i<listNecesidad.size();i++){
			String descripcion;
			descripcion= nDao.buscarDescpNecesidad(listNecesidad.get(i).getCodigo());
			listNecesidad.get(i).setCodigo(descripcion);
		}
		
	}

	

	@Listen("onClick = #guardar")
	public void guardar()
	{
		
		// SECCION DE HABITOS
		ArrayList<HabitoCliente> habxcliente =   habDao.buscarHabitosXCliente(cedula.getText());
		
		for (int i = 0; i < habitosAll.size(); i++) 
		{
			boolean a = false;
			
			if (habxcliente.size() > 0) {
				for (int j = 0; j < habxcliente.size(); j++) 
				{
					if (habitosAll.get(i).getCodigo().equals(habxcliente.get(j).getCodigo_habito())) {
						
						//si lo consigue actualizar estatus
						Habito hab = habitosAll.get(i);
						boolean nuevoStatus = hab.getStatus();
						String codigoHabitoEncontrado = hab.getCodigo();
						
						habClienteDAO.ActualizarHabitoCliente(codigoHabitoEncontrado, cedulaCliente, nuevoStatus);
						
						a = true;
					}
					
					if( a == false){
						//si no lo consigue y estatus es true en el primer arreglo entonces debe insertar este nuevo habito al cliente
						if (habitosAll.get(i).getStatus() == true) {
							
							String codigo = habClienteDAO.TotalRegistros("tb_habito_cliente");
							String codigoHabito =habitosAll.get(i).getCodigo();
							
							if(habClienteDAO.encontrarOpcion(codigoHabito, cedulaCliente) == false ){
							habClienteDAO.insertarHabitoCliente(codigo, cedulaCliente, codigoHabito, true);
							}
						}
					}
						
					
				}
			}
			if (habxcliente.size() == 0) {
				if (habitosAll.get(i).getStatus() == true) {
					
					String codigo = habClienteDAO.TotalRegistros("tb_habito_cliente");
					String codigoHabito =habitosAll.get(i).getCodigo();
					
					if(habClienteDAO.encontrarOpcion(codigoHabito, cedulaCliente) == false ){
					habClienteDAO.insertarHabitoCliente(codigo, cedulaCliente, codigoHabito, true);
					}
				}
			}
			
		}
		
		// SECCION DE ANTECEDENTES 
		
		ArrayList<AntecedenteCliente> antecedentesDeCliente =  antClienteDao.buscarAntecedenteXCliente(cedulaCliente);
		
		for (int i = 0; i < antecedentesAll.size(); i++) 
		{
			boolean a = false;
			for (int j = 0; j < antecedentesDeCliente.size(); j++) 
			{
				if (antecedentesAll.get(i).getCodigo().equals(antecedentesDeCliente.get(j).getCodigo_antecedente())) {
					
					//si lo consigue actualizar estatus
					Antecedente antecedente = antecedentesAll.get(i);
					boolean nuevoStatus = antecedente.getStatus();
					String codigoHabitoEncontrado = antecedente.getCodigo();
					
					antClienteDao.ActualizarAntecedenteCliente(codigoHabitoEncontrado, cedulaCliente, nuevoStatus);
					
					a = true;
				}
				
				if( a == false){
					//si no lo consigue y estatus es true en el primer arreglo entonces debe insertar este nuevo habito al cliente
					if (antecedentesAll.get(i).getStatus() == true) {
						
						String codigo = antClienteDao.TotalRegistros("tb_antecedente_cliente");
						String codigoAntecedente =antecedentesAll.get(i).getCodigo();
						
						if(antClienteDao.encontrarAntecedenteCliente(codigoAntecedente, cedulaCliente) == false ){
							antClienteDao.insertarAntecedenteCliente(codigo, cedulaCliente, codigoAntecedente, true);
						}
					}
				}
					
				
			}
		}
		Messagebox.show("La consulta diagnostico se ha generado exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		
	}
	
	
	

}
