package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

import bean.Conexion;
import modelo.Cliente;

public class ClienteDAO extends ConexionDAO{
	
	
	public ClienteDAO(){
		super();
	}
	
	public Cliente obtenerCliente(String correo)
	{
		Cliente cliente = null;
		String tiraSQL = "select cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo, ciudad from tb_cliente a where a.correo='"+correo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
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
				String  ciudad = resultSet.getString("ciudad");
				cliente = new Cliente(cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo1, ciudad);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;

	}
	

	public Cliente BuscarClienteXCedula(String cedulaCliente)
	{
		Cliente cliente = null;
		String tiraSQL = "select cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo, ciudad from tb_cliente a where a.cedula='"+cedulaCliente+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
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
				String  ciudad = resultSet.getString("ciudad");
				cliente = new Cliente(cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo1, ciudad);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;

	}
	

}
