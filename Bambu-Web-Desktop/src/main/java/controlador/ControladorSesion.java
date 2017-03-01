package controlador;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Textbox;

import modelo.Usuario;
import modeloDAO.UsuarioDAO;



public class ControladorSesion extends SelectorComposer<Component>{
	

	
/**
	 * 
	 */

private static final long serialVersionUID = 1L;
private UsuarioDAO dao = new UsuarioDAO ();

	
    @Wire
	private Textbox correo;
    @Wire
	private Textbox contraseņa;
    
    ControladorDatos datos = new ControladorDatos();
	
	@Listen("onClick =#iniciar")
	public void autenticar()
	{
		
	
	String correoUsuario = correo.getValue();
	String contraseņaUsuario = contraseņa.getValue();
	Usuario usuario = dao.buscarUsuario(correoUsuario, contraseņaUsuario);
	String nombreUsuario = dao.obtenerNombre(correoUsuario);
		if(usuario!=null)
		{
		 Session miSession = Sessions.getCurrent();
		 Session miSession1 = Sessions.getCurrent();
		 Session miSession2 = Sessions.getCurrent();
		 miSession.setAttribute("usuario", nombreUsuario);
		 miSession1.setAttribute("correo", correoUsuario);
		 miSession2.setAttribute("rol", usuario.getRol());
		   datos.asignarDatos(correoUsuario, contraseņaUsuario,usuario.getRol(), usuario.getStatus());
			Executions.sendRedirect("index.zul");
		}
		else{
			Messagebox.show("Usuario no encontrado");
		}
	}
	
	
	

}
