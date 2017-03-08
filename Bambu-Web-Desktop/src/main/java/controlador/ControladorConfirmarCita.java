package controlador;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Cliente;
import modeloDAO.ClienteDAO;

public class ControladorConfirmarCita extends GenericForwardComposer<Window>{

	/**
	 * 
	 */
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	
	ClienteDAO clienteDao = new ClienteDAO();
	Cliente cliente = new Cliente();
	
	Session miSession = Sessions.getCurrent();
	Session miSession2 = Sessions.getCurrent();
	
	private static final long serialVersionUID = 1L;

	public void onCreate$confirmacion()  
	{
		if(miSession2.getAttribute("rol").toString().equals("00004")){
			
		String correo = miSession.getAttribute("correo").toString();
		cliente = clienteDao.obtenerCliente(correo);
		cedula.setText(cliente.getCedula());
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		cedula.setDisabled(true);
		nombre.setDisabled(true);
		apellido.setDisabled(true);
		}
		else
		{
			cedula.setDisabled(false);
			nombre.setDisabled(false);
			apellido.setDisabled(false);
		}
		
	}
	
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
