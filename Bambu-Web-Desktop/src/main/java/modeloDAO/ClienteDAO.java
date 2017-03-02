package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Cliente;
import modelo.Maestrico;
import bean.Conexion;

public class ClienteDAO extends ConexionDAO {
	
	
	public Cliente buscarCliente(String cedula) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_cliente where cedula='"+cedula+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		Cliente c=null;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					 c = new Cliente(resultSet.getString("cedula"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("sexo"), resultSet.getString("estado_civil"), resultSet.getString("telefono"), resultSet.getString("direccion"), resultSet.getString("correo"), resultSet.getString("ciudad"), resultSet.getString("tipo_cliente"), resultSet.getString("codigo_acuerdo"), resultSet.getString("codigo_referencia"), resultSet.getString("codigo_organizacion"), resultSet.getString("status"), resultSet.getString("codigo_ocupacion"), resultSet.getString("fecha_nacimiento"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return c;
	}
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
				String tipo_cliente = resultSet.getString("tipo_cliente");
				String codigo_acuerdo = resultSet.getString("codigo_acuerdo");
				String codigo_referencia = resultSet.getString("codigo_referencia");
				String codigo_organizacion = resultSet.getString("codigo_organizacion");
				String status = resultSet.getString("status");
				String codigo_ocupacion = resultSet.getString("codigo_ocupacion");
				String fecha_nacimiento = resultSet.getString("fecha_nacimiento");
				cliente = new Cliente(cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo1, ciudad,tipo_cliente,codigo_acuerdo,codigo_referencia,codigo_organizacion,status,codigo_ocupacion,fecha_nacimiento);
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
				String tipo_cliente = resultSet.getString("tipo_cliente");
				String codigo_acuerdo = resultSet.getString("codigo_acuerdo");
				String codigo_referencia = resultSet.getString("codigo_referencia");
				String codigo_organizacion = resultSet.getString("codigo_organizacion");
				String status = resultSet.getString("status");
				String codigo_ocupacion = resultSet.getString("codigo_ocupacion");
				String fecha_nacimiento = resultSet.getString("fecha_nacimiento");
				cliente = new Cliente(cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo1, ciudad,tipo_cliente,codigo_acuerdo,codigo_referencia,codigo_organizacion,status,codigo_ocupacion,fecha_nacimiento);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;

	}

}