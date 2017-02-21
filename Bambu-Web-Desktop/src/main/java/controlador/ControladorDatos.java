package controlador;

import java.io.Serializable;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;

import modelo.Usuario;

public class ControladorDatos implements Serializable {
	
	private static final long serialVersionUID = 1L;

	public ControladorDatos(){}
	
	public Usuario obtenerDatos(){
		Session session = Sessions.getCurrent();
		Usuario datos = (Usuario)session.getAttribute("UsuarioCesion");
		
		return datos;
	}
	
	public void asignarDatos(String usuario, String contrasenna,
			String rol, String status)
	{
		Session session= Sessions.getCurrent();
		Usuario datos = new Usuario(usuario,contrasenna,rol, status);
		session.setAttribute("UsuarioCesion",datos);
	
				
	}
	
	public void removerDatos()
	{
		Session session=Sessions.getCurrent();
		session.removeAttribute("UsuarioCesion");
	
	}
	
}