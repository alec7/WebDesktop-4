package controlador;

import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Cliente;
import modeloDAO.ClienteDAO;

public class ControladorRegistrarSesion extends  GenericForwardComposer<Window>{

	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	

	ClienteDAO clienteDao = new ClienteDAO();
	Cliente cliente = new Cliente();
	
	
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
		
		
	}
}
