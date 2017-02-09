package controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import modelo.Usuario;
import modeloDAO.UsuarioDAO;

/**
 * Servlet implementation class ControladorSesion
 */
@WebServlet("/ControladorSesion")
public class ControladorSesion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
	private UsuarioDAO usuarioDAO;
	  
    public ControladorSesion() {
        super();
        usuarioDAO = new UsuarioDAO();
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		procesarPeticion(request, response);
	}
	
	public void procesarPeticion(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String operacion = request.getParameter("operacion");
		HttpSession sesion = request.getSession(true);
		if(operacion.equalsIgnoreCase("entrar")){
			String usuarioIng = request.getParameter("usuario");
			String clave = request.getParameter("contra");
			Usuario usuario = usuarioDAO.buscarUsuario(usuarioIng,clave);
			if(usuario != null){
				sesion.setAttribute("usuario", usuario);
				RequestDispatcher rd = request.getRequestDispatcher("inicio.jsp");
				rd.forward(request,response);
			}
			else{
				String mensaje="El usuario o Clave no es valido";
				response.sendRedirect("paginamensaje.jsp?mensaje="+mensaje);
			}
		}
		else if(operacion.equalsIgnoreCase("salir")){
			sesion.removeAttribute("usuario");
			sesion.invalidate();
			response.sendRedirect("index.jsp");
		}
	}


}
