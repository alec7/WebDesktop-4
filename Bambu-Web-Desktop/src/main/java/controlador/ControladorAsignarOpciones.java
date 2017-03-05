package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listcell;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;

import modelo.Opcion;
import modelo.Opcion_rol;
import modelo.Rol;
import modeloDAO.OpcionDAO;
import modeloDAO.Opcion_RolDAO;
import modeloDAO.RolDAO;


public class ControladorAsignarOpciones extends SelectorComposer<Component>{	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	Combobox comboRol;
	@Wire
	Listbox listaOpciones;
	
	@Wire
	Button cancelar;
	@Wire  
	Button btnguardar;
	
	
	RolDAO roldao = new RolDAO();
	ListModelList<Rol> rolListModel;

	OpcionDAO opciondao = new OpcionDAO();
	ListModelList<Opcion_rol> opcionListModel;
	
	ListModelList<Opcion> arrayOpcionListModel;
	ListModelList<Opcion> opcionesTodasListModel;
	
	List<Rol> roles = roldao.obtenerRoles();
	
	Opcion_RolDAO opcionRolDAO = new Opcion_RolDAO();
	ArrayList<Opcion> opciones = opciondao.ObtenerTodos();
	
	ArrayList<Opcion> arrOpcionesModificadas ;
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		llenarComoboRoles();
		
		//LLenarListaOpcionesTodas();
		

	}
	
	public void llenarComoboRoles()
	{
		
		rolListModel = new ListModelList<Rol>(roles);
		comboRol.setModel(rolListModel);
	}
	
	public void LLenarListaOpcionesTodas()
	{
		
		opcionesTodasListModel = new ListModelList<Opcion>(opciones);
		listaOpciones.setModel(opcionesTodasListModel);
		
	
	}
	

	public void LLenarListaOpciones()
	{
		int indiceSeleccionado = comboRol.getSelectedItem().getIndex();
		Rol rolSeleccionado = roles.get(indiceSeleccionado);
		String codigoRolSeleccionado = rolSeleccionado.getCodigo();
		
		List<Opcion_rol> opcionesPorRolSeleccionado = opcionRolDAO.ObtenerOpcionesPorRol(codigoRolSeleccionado);
		
	
		
		for (int i = 0; i < opciones.size(); i++) {
			for (int j = 0; j < opcionesPorRolSeleccionado.size(); j++) {
				if (opcionesPorRolSeleccionado.size() > 0) {
						
					if (Integer.parseInt(opcionesPorRolSeleccionado.get(j).getCodigo_opcion()) == Integer.parseInt(opciones.get(i).getCodigo())) {
						System.out.println("pase por el if ... o sea encontre unos iguales");
						System.out.println("Estatus del encontrado en arrOpciones antes de la modificacion es : " + opciones.get(i).getStatus() );
						opciones.get(i).setStatus(opcionesPorRolSeleccionado.get(j).getStatus());
						System.out.println("Estatus del encontrado en arrOpciones despues de la modificacion es : " + opciones.get(i).getStatus() );
					}
				}else{
					opciones.get(i).setStatus(false);
				}
			}
		
		}
		
		arrOpcionesModificadas = opciones;
		arrayOpcionListModel = new ListModelList<Opcion>(opciones);
		listaOpciones.setModel(arrayOpcionListModel);
		
		opciones = new ArrayList<Opcion>();
		opciones = opciondao.ObtenerTodos();
	}
	@Listen("onSelect = combobox#comboRol")
	public void actualizarListadoPorRolSeleccionado(){
		
		LLenarListaOpciones();

	}
	
	@Listen("onCheck = #listaOpciones")
	public void chequearOpcion(ForwardEvent evt)
	{
		Checkbox cbox = (Checkbox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)cbox.getParent().getParent();
		
		boolean checked = true;
		int posicion ;
		checked = cbox.isChecked();
		posicion = litem.getIndex();
	/*	
		String codigoPadre = opciones.get(posicion).getCodigo_padre();
		
		if (!codigoPadre.equals("0"))
		{
			
			for (int i = 0; i < arrOpcionesModificadas.size(); i++) 
			{
				if (arrOpcionesModificadas.get(i).getCodigo().equals(codigoPadre)  )
				{
					arrOpcionesModificadas.get(i).setStatus(true);
				}
			}
		}
	*/
		
		arrOpcionesModificadas.get(posicion).setStatus(checked);
		/*
		arrayOpcionListModel = new ListModelList<Opcion>(arrOpcionesModificadas);
		listaOpciones.setModel(arrayOpcionListModel);
		*/
	}
	@Listen("onClick = button#cancelar")
	public void cancelar(){
		Executions.sendRedirect("http://localhost:8080/Bambu-Web-Desktop/WebContent/index.zul");
	}
	@Listen("onClick = button#btnguardar")
	public void opcionesPorRolSeleccionado(){
		
		int indiceSeleccionado = comboRol.getSelectedItem().getIndex();
		Rol rolSeleccionado = roles.get(indiceSeleccionado);
		
		String codigoRolSeleccionado = rolSeleccionado.getCodigo();
		
		String codigoOpcionEncontrada;
	
		
		List<Opcion_rol> opcionesPorRolSeleccionado = opcionRolDAO.ObtenerOpcionesPorRol(codigoRolSeleccionado);
		
		
		
		for (int i = 0; i < arrOpcionesModificadas.size(); i++) 
		{
			Boolean a = false;
			//SMessagebox.show(String.valueOf(opcionesPorRolSeleccionado.size()));
			if (opcionesPorRolSeleccionado.size() > 0) 
			{
				for (int j = 0; j < opcionesPorRolSeleccionado.size(); j++) 
				{
					//Messagebox.show( "voy a comparar : "+ arrOpcionesModificadas.get(i).getCodigo()+" con "+ opcionesPorRolSeleccionado.get(j).getCodigo_opcion());
					if (Integer.parseInt(arrOpcionesModificadas.get(i).getCodigo())   == Integer.parseInt(opcionesPorRolSeleccionado.get(j).getCodigo_opcion()))
					{   
						//Messagebox.show( arrOpcionesModificadas.get(i).getCodigo()+" y "+ opcionesPorRolSeleccionado.get(j).getCodigo_opcion()+ "son iguales");
						if (arrOpcionesModificadas.get(i).getStatus() != opcionesPorRolSeleccionado.get(j).getStatus()) 
						{
							//Messagebox.show("la opcion: "+ arrOpcionesModificadas.get(i).getCodigo()+"tiene estatus : "+arrOpcionesModificadas.get(i).getStatus()+ " en el arrmodificado"+ "y la opcion en oprol "+opcionesPorRolSeleccionado.get(j).getStatus() );
							Boolean nuevoStatus = arrOpcionesModificadas.get(i).getStatus();
							String codigoOpcionEncon = arrOpcionesModificadas.get(i).getCodigo();
							
							opcionRolDAO.ActualizarOpcionPorRol(codigoOpcionEncon, codigoRolSeleccionado, nuevoStatus);
							
							
						}
						a = true;
					}
					if ( a== false) 
					{ //aqui entra si la opcione no fue encontrada en el arreglo de opciones por rol seleccionado
						if (arrOpcionesModificadas.get(i).getStatus() == true) 
						{
							
							//Messagebox.show("NO ENCONTRO LA OPCION"+ arrOpcionesModificadas.get(i).getCodigo() +" EN OPCIONROL, PERO ESTA OPCION TIENE TRUE EN EL MODIFICADO");
							String codigoTabla = opcionRolDAO.TotalRegistros("tb_opcion_rol");
							String codigoOpcion = arrOpcionesModificadas.get(i).getCodigo();
							if(opcionRolDAO.encontrarOpcion(codigoOpcion) == false ){
								opcionRolDAO.insertarNuevaOpcionARol(codigoTabla, codigoOpcion, codigoRolSeleccionado, true);
							}
						}
					}
					
				}
			}
			if (opcionesPorRolSeleccionado.size() == 0)
			{
				
				
				if (arrOpcionesModificadas.get(i).getStatus() == true) 
				{
					
					//Messagebox.show("NO ENCONTRO LA OPCION"+ arrOpcionesModificadas.get(i).getCodigo() +" EN OPCIONROL, PERO ESTA OPCION TIENE TRUE EN EL MODIFICADO");
					String codigoTabla = opcionRolDAO.TotalRegistros("tb_opcion_rol");
					String codigoOpcion = arrOpcionesModificadas.get(i).getCodigo();
					if(opcionRolDAO.encontrarOpcion(codigoOpcion) == false ){
						opcionRolDAO.insertarNuevaOpcionARol(codigoTabla, codigoOpcion, codigoRolSeleccionado, true);
					}
				}
			}
		}
		
		
		/*
		for (int i = 0; i < arrOpcionesModificadas.size(); i++) 
		{
			if (opcionesPorRolSeleccionado.size() > 0 ) {
				for (int j = 0; j < opcionesPorRolSeleccionado.size(); j++) 
				{
					if(arrOpcionesModificadas.get(i).getCodigo().equals(opcionesPorRolSeleccionado.get(j).getCodigo_opcion()))
					{
						Boolean status=null;
						codigoOpcionEncontrada = opcionesPorRolSeleccionado.get(j).getCodigo();
								//clonOpciones.get(i).getCodigo();
						 status = arrOpcionesModificadas.get(i).getStatus();
						
//						Messagebox.show(String.valueOf(status));
//						Messagebox.show(String.valueOf(clonOpciones.get(i).getCodigo()));
						System.out.println("encontre la opcion"+codigoOpcionEncontrada + "con estatus: "+ status+ " y voy  a actualizar");
						opcionRolDAO.ActualizarOpcionPorRol(codigoOpcionEncontrada, codigoRolSeleccionado, status);
						System.out.println("actualicè en el for i : "+String.valueOf(i) );
						
						
					}else{
						if (arrOpcionesModificadas.get(i).getStatus().toString().equalsIgnoreCase("true")) {
							
							System.out.println("encontre la opcion : "+arrOpcionesModificadas.get(i).getCodigo()+ "con true debo voy a  insertar");
							 String codigoOpcion = arrOpcionesModificadas.get(i).getCodigo();
							 String codigoTabla = opcionRolDAO.TotalRegistros("tb_opcion_rol");
							 opcionRolDAO.insertarNuevaOpcionARol(codigoTabla, codigoOpcion, codigoRolSeleccionado, true);
							 
							 
//							opcionesPorRolSeleccionado = new ArrayList<Opcion_rol>();
//							opcionesPorRolSeleccionado = opcionRolDAO.ObtenerOpcionesPorRol(codigoRolSeleccionado);
						
							System.out.println("INSERTE en el for i : "+String.valueOf(i) );
							
						}
						//System.out.println("sali del if del insertar");
						
					}
					
				}
			}else{
				if (arrOpcionesModificadas.get(i).getStatus().toString().equalsIgnoreCase("true")) {
					
					System.out.println("ROL SIN OPCIONES encontre la opcion : "+arrOpcionesModificadas.get(i).getCodigo()+ "con true debo voy a  insertar");
					 String codigoOpcion = arrOpcionesModificadas.get(i).getCodigo();
					 String codigoTabla = opcionRolDAO.TotalRegistros("tb_opcion_rol");
					opcionRolDAO.insertarNuevaOpcionARol(codigoTabla, codigoOpcion, codigoRolSeleccionado, true);
					
				}
			}
				
		}
		
			arrOpcionesModificadas = new ArrayList<Opcion>();
		arrOpcionesModificadas = opciondao.ObtenerTodos();

		*/
	
		Messagebox.show("Opciones guardadas con exito");
		//Executions.sendRedirect("http://localhost:8080/Bambu-Web-Desktop/WebContent/index.zul");
		
		
	}
	
	
	private void guardarOpcionesRol(String codigoTabla, List<String> idOpciones, List<Boolean> statuschecked, String codigoRolSeleccionado) {
		
		for (int i = 0; i < idOpciones.size() ; i++) {
			opcionRolDAO.insertarOpcionesRol(codigoRolSeleccionado,idOpciones.get(i), statuschecked.get(i), codigoRolSeleccionado );
			 
		}
		
	}
}
