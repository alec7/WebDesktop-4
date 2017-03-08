package controlador;

import java.util.List;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Cliente;
import modelo.Noticia;
import modelo.Sesion;
import modeloDAO.ClienteDAO;
import modeloDAO.SesionDAO;

public class ControladorRegistrarSesion extends  GenericForwardComposer<Window>{

	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Listbox listaSesiones;
	

	ClienteDAO clienteDao = new ClienteDAO();
	Cliente cliente = new Cliente();
	
	SesionDAO sesionDao = new SesionDAO();
	Sesion sesion = new Sesion();
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void onClick$BuscarCliente()
	{
		
		cliente = clienteDao.BuscarClienteXCedula(cedula.getText());
		if(cliente!=null)
		{
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		}
		else{
			Messagebox.show("cliente no encontrado");
		}
		
		List<Sesion> sesiones = sesionDao.ObtenerSesion(cedula.getText());
		listaSesiones.setModel(new ListModelList<Sesion>(sesiones));
		
	}
	

	
	
	
	
	
}
