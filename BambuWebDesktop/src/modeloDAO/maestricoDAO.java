package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Usuario;

public class maestricoDAO extends ConexionDAO{
	
	public maestricoDAO() {
		super();
	}
	
	public void registrarMaestrico(Maestrico maestrico, String tabla ){
		String tiraSQL= "INSERT INTO "+tabla+" (codigo,descripcion) "
				+ "VALUES ('"+maestrico.getCodigo()+"'"+",'"+ maestrico.getDescripcion()+"')";
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
	


}
