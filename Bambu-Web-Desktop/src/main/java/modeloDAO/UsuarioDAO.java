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
	    

	
	

}
