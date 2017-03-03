package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Usuario;

public class UsuarioDAO extends ConexionDAO{
	
	public UsuarioDAO() {
		super();
	}
	
	public void registrarUsuario(Usuario usuario){
		
		String tiraSQL= "INSERT INTO  tb_usuario (usuario,contrasenna,rol,status) "
				+ "VALUES ('"+usuario.getUsuario()+"'"+",'"+ usuario.getContrasenna()+"','"+ usuario.getRol()+"','"+ usuario.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
		
	}   
	
	public List<Usuario> listarUsuario() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_usuario where status='Activo' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Usuario> usuario = new ArrayList<Usuario>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					usuario.add(new Usuario(resultSet.getString("usuario"), resultSet.getString("contrasenna"),resultSet.getString("rol"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return usuario;
	}
	
	public void modificarStatus(String usuario) {
		String tiraSQL = "UPDATE tb_usuario SET status = 'Inactivo' WHERE usuario = '"+usuario+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	

	public Usuario buscarUsuario(String correo, String contrasenna){
		Usuario usuarioIng = null;
		String tiraSQL = "select * from tb_usuario where usuario='"+correo+"' and contrasenna='"+contrasenna+"'   ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if(resultSet.next()){
			    String usuario1 = resultSet.getString("usuario");
				String rol = resultSet.getString("rol");
				String estatus = resultSet.getString("status");
				usuarioIng = new Usuario(usuario1, contrasenna, rol, estatus);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioIng;
	}
	
	/*public Usuario obtenerNombre(String correo){
		Usuario usuarioIng = null;
		String tiraSQL = "select correo, nombre, rol, contrasenna, a.status from tb_cliente a, tb_usuario b where b.usuario ='"+correo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if(resultSet.next()){
			    String usuario1 = resultSet.getString("correo");
				String rol = resultSet.getString("rol");
				String contrasenna = resultSet.getString("contrasenna");
				String estatus = resultSet.getString("status");
				usuarioIng = new Usuario(usuario1, contrasenna, rol, estatus);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuarioIng;
	}*/
	
	public String obtenerNombre(String correo)
	{
		String nombre = null;
		String tiraSQL = "select nombre from tb_cliente a where a.correo ='"+correo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try{
			while(resultSet.next()){
				nombre = resultSet.getString("nombre");
			}
			resultSet.close();
		}catch (SQLException e) {
			e.printStackTrace();
		}	
		
		return nombre;
		
	}
 

	
	

}
