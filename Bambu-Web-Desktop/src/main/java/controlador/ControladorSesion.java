package controlador;

import org.apache.commons.mail.HtmlEmail;
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

import modeloDAO.EmailHTMLDao;
import modeloDAO.SesionDAO;

import org.zkoss.zul.Textbox;

import modelo.PasswordGenerator;
import modelo.Usuario;
import modeloDAO.UsuarioDAO;



public class ControladorSesion extends SelectorComposer<Component>{
	

	
/**
	 * 
	 */

private static final long serialVersionUID = 1L;
private UsuarioDAO dao = new UsuarioDAO ();
private SesionDAO sdao = new SesionDAO ();
EmailHTMLDao email = new EmailHTMLDao();
	
    @Wire
	private Textbox correo;
    @Wire
	private Textbox contraseña;
    
    ControladorDatos datos = new ControladorDatos();
    @Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/iniciarSesion.html");
	}
	@Listen("onClick =#iniciar")
	public void autenticar()
	{
		
	
	String correoUsuario = correo.getValue();
	String contraseñaUsuario = contraseña.getValue();
	Usuario usuario = dao.buscarUsuario(correoUsuario, contraseñaUsuario);
	String nombreUsuario = dao.obtenerNombre(correoUsuario);
		if(usuario!=null)
		{
		 Session miSession = Sessions.getCurrent();
		 Session miSession1 = Sessions.getCurrent();
		 Session miSession2 = Sessions.getCurrent();
		 miSession.setAttribute("usuario", nombreUsuario);
		 miSession1.setAttribute("correo", correoUsuario);
		 miSession2.setAttribute("rol", usuario.getRol());
		   datos.asignarDatos(correoUsuario, contraseñaUsuario,usuario.getRol(), usuario.getStatus());
			Executions.sendRedirect("index.zul");
		}
		else{
			 Messagebox.show("Usuario no encontrado", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	
	@Listen("onClick =#recuperarContrasenna")
	public void redireccionar()	{
		Executions.sendRedirect("recuperarContrasenna.zul");
	}
	
	@Listen("onClick =#recuperarContrasennanueva")
	public void RecuperarContrasenna()
	{
		
		String correoasociado = correo.getText();
		if(correoasociado=="")
		{	
			Messagebox.show("Debe ingresar su usuario", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else if(correoasociado==sdao.buscarCorreo(correoasociado)){
				Messagebox.show("Usuario no encontrado");
				
				System.out.println(correoasociado);
				}else{

					String nuevacon = PasswordGenerator.getPassword(
									PasswordGenerator.MINUSCULAS+
									PasswordGenerator.MAYUSCULAS,10);
		
					email.recuperarContrasenna("richard0227@gmail.com", "Recuperación de Contraseña", correoasociado, nuevacon);
					sdao.recuperarContrasennaUsuario(nuevacon, correoasociado);
				Messagebox.show("Una nueva contraseña se ha enviado a su correo", "Información", Messagebox.OK, Messagebox.INFORMATION);				
				//correo.setText("");
				
				}

		
		}
	
	

}
