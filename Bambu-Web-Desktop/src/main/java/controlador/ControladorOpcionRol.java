package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;

import modelo.Opcion;
import modelo.Opcion_rol;
import modelo.Rol;
import modeloDAO.OpcionDAO;
import modeloDAO.Opcion_RolDAO;
import modeloDAO.RolDAO;

public class ControladorOpcionRol extends SelectorComposer<Component> { 
	

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	Combobox comboRol;
	@Wire
	Listbox listaOpciones;
	
	@Wire
	Checkbox miperfil;
	@Wire
	Checkbox editarperfil;
	@Wire
	Checkbox preferencias;
	@Wire
	Checkbox clientes;
	@Wire
	Checkbox fichadelcliente;
	@Wire
	Checkbox consultadiagnostico;
	@Wire
	Checkbox serviciorecomendado;
	@Wire
	Checkbox solicitarservicio;
	@Wire
	Checkbox a; 
	@Wire  
	Button btnguardar;
	
	
	RolDAO roldao = new RolDAO();
	ListModelList<Rol> rolListModel;

	OpcionDAO opciondao = new OpcionDAO();
	ListModelList<Opcion_rol> opcionListModel;
	
	List<Rol> roles = roldao.obtenerRoles();
	
	Opcion_RolDAO opcionRolDAO = new Opcion_RolDAO();
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		
		llenarComoboRoles();
		

	}
	
	public void llenarComoboRoles()
	{
		
		rolListModel = new ListModelList<Rol>(roles);
		comboRol.setModel(rolListModel);
	}

	
	public void LLenarListaOpciones()
	{
		int indiceSeleccionado = comboRol.getSelectedItem().getIndex();
		Rol rolSeleccionado = roles.get(indiceSeleccionado);
		String codigoRolSeleccionado = rolSeleccionado.getCodigo();
		
		List<Opcion_rol> opcionesPorRol = opcionRolDAO.ObtenerOpcionesPorRol(codigoRolSeleccionado);
		opcionListModel = new ListModelList<Opcion_rol>(opcionesPorRol);
		listaOpciones.setModel(opcionListModel);
		
	}
	@Listen("onSelect = combobox#comboRol")
	public void actualizarListadoPorRolSeleccionado(){
		
		LLenarListaOpciones();
		
		
		
		/*	int indiceSeleccionado = comboRol.getSelectedItem().getIndex();
		Rol rolSeleccionado = roles.get(indiceSeleccionado);
		String codigoRolSeleccionado = rolSeleccionado.getCodigo();
		List<Opcion_rol> opcionesPorRol = opcionRolDAO.ObtenerOpcionesPorRol(codigoRolSeleccionado);
		
		ListModelList<Opcion_rol> opcionRolListModel = new ListModelList<Opcion_rol>(opcionesPorRol);
		listaOpciones.setModel(opcionListModel);
		*/	
	}

	/*@Listen("onClick = button#btnguardar")*/
	public void opcionesPorRolSeleccionado(){
		
		int indiceSeleccionado = comboRol.getSelectedItem().getIndex();
		Rol rolSeleccionado = roles.get(indiceSeleccionado);
		String nombre = rolSeleccionado.getDescripcion();
		String codigoRolSeleccionado = rolSeleccionado.getCodigo();
		String status = rolSeleccionado.getStatus();
		/*
		System.out.println("el rol selecionado es id : " + codigoRolSeleccionado);
	
		   listaOpciones.getItemAtIndex(listaOpciones.getSelectedIndex());
		System.out.println("el rol selecionado es id : " + codigo);
		System.out.println("y nombre :" + nombre);
		System.out.println("estatus del check  :" + status);
		
	    ArrayList<Opcion_rol> opcionesPorRol = opcionRolDAO.ObtenerOpcionesPorRol(codigo);
	   System.out.println(opcionesPorRol.size());
		Checkbox checki = (Checkbox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)checki.getParent().getParent();
		
		System.out.println(litem.getValue().toString()) ;
		
	    
	    for (int i=0; i < opcionesPorRol.size(); i++){
	    	
	    	   Opcion_rol opcionrol = opcionesPorRol.get(i);
	    	   int codigoOpcion = opcionrol.getFk_id_opcion();
	    	   String estatus = opcionrol.getStatus();
	    	   
	    	 
	    	   Opcion op = opciondao.ObtenerOpcion(codigoOpcion);
	    	   System.out.println(op.getTexto());
	    	
	    	   
	    }*/
		
	
		List<Opcion> opciones = opciondao.ObtenerTodos();
		
		List<String> idOpciones = new ArrayList<String>();
		List<Boolean> statuschecked = new ArrayList<Boolean>();
		
		for (int i = 0; i < opciones.size(); i++) {
				Opcion opcion = opciones.get(i);
				String id = opcion.getCodigo();
				idOpciones.add(id);
			
				
		}
		statuschecked.add(miperfil.isChecked());
		statuschecked.add(editarperfil.isChecked());
		statuschecked.add(clientes.isChecked());
		statuschecked.add(fichadelcliente.isChecked());
		statuschecked.add(consultadiagnostico.isChecked());
		statuschecked.add(serviciorecomendado.isChecked());
		statuschecked.add(solicitarservicio.isChecked());
		statuschecked.add(a.isChecked());
		statuschecked.add(preferencias.isChecked());
		
	    String codigoTabla = ""; 
		//guardarOpcionesRol(codigoTabla, codigoRolSeleccionado, idOpciones, statuschecked );
		
		
		
	}

	private void guardarOpcionesRol(String codigotabla, List<String> idOpciones, List<Boolean> statuschecked, String codigoRolSeleccionado) {
		
		for (int i = 0; i < idOpciones.size() ; i++) {
			opcionRolDAO.insertarOpcionesRol(codigotabla,idOpciones.get(i), statuschecked.get(i),  codigoRolSeleccionado);
		}
		
	}
}



