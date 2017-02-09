package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Conexion;
import modelo.Usuario;

public class UsuarioDAO extends ConexionDAO{
	
	public UsuarioDAO() {
		super();
	}
	
	public void registrarUsuario(Usuario usuario){
		String tiraSQL = "INSERT INTO usuario"+
	           "(password,idrol, nombre,status)"+
			 				 "VALUES("+"','"+usuario.getClave()+
			 	           "',"+usuario.getId_rol()+",'"+usuario.getUsuario()+"','"+usuario.getEstatus()+"')";
	   Conexion.ejecutar(tiraSQL);
	}   
	    
	    
	/*public void modificarUsuario(Usuario usuario)
	  {
		String tiraSQL = "UPDATE usuario  SET password WHERE idusuario = '" +usuario.getPassword() + "'";
		Conexion.ejecutar(tiraSQL);
	  }
	
	public void eliminarUsuario(Usuario usuario)
	{
		String tiraSQL = "UPDATE usuario  SET status = 'I' WHERE idusuario = '" +usuario.getStatus()+"'";
		Conexion.ejecutar(tiraSQL);
	}
	
	public void reactivarUsuario(Usuario usuario)
	{
		String tiraSQL = "UPDATE usuario  SET status = 'A' WHERE idusuario = '" +usuario.getStatus()+"'";
		Conexion.ejecutar(tiraSQL);
	}*/
	
	public Usuario buscarUsuario(String usuarioIng, String clave){
		Usuario usuario = null;
		String tiraSQL = "select * from usuario where usuario='"+usuarioIng+"' and clave='"+clave+"'  ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			if(resultSet.next()){
				int id_usuario = resultSet.getInt("id_usuario");
				int id_rol = resultSet.getInt("id_rol");
				String estatus = resultSet.getString("estatus");
				usuario = new Usuario(id_usuario, id_rol, usuarioIng, clave, estatus.charAt(0));
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return usuario;
	}
	

}
