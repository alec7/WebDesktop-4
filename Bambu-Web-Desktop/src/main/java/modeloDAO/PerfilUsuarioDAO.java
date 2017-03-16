package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Conexion;
import modelo.Cliente;
import modelo.PerfilUsuario;

public class PerfilUsuarioDAO extends ConexionDAO {
	
	public void agregarPerfilUsuario(PerfilUsuario pf) {
		
		String tiraSQL= "INSERT INTO tb_perfil_usuario (cedula,nombre,apellido,direccion,telefono,sexo,estado_civil,correo,codigo_estado,status) "
				+ "VALUES ('"+pf.getCedula()+"'"+",'"+ pf.getNombre()+"','"+ pf.getApellido()+"','"+ pf.getDireccion()+"','"+ pf.getTelefono()+"','"+ pf.getSexo()+"','"+ pf.getEstado_civil()+"','"+ pf.getCorreo()+"','"+ pf.getEstado()+"','"+ pf.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}

	public PerfilUsuario buscarPerfilUsuario(String correo){
		String tiraSQL = "SELECT * FROM tb_perfil_usuario where status='Activo' and correo ='"+correo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		PerfilUsuario pf = null;
		try {
			if(resultSet.next()){
				String cedula = resultSet.getString("cedula");
				String nombre = resultSet.getString("nombre");
				String apellido = resultSet.getString("apellido");
				String sexo = resultSet.getString("sexo");
				String estado_civil = resultSet.getString("estado_civil");
				String  telefono = resultSet.getString("telefono");
				String  direccion = resultSet.getString("direccion");
				String  correo1 = resultSet.getString("correo");
				String  estado = resultSet.getString("codigo_estado");
				String status = resultSet.getString("status");
				
				pf= new PerfilUsuario(cedula, nombre, apellido, direccion, correo1, sexo, estado_civil, telefono, estado, status);
				
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return pf;

	}
}
