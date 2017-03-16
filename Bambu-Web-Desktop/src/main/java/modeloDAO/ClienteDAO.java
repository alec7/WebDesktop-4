package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;

import modelo.Acuerdo;
import modelo.Cliente;
import modelo.Maestrico;
import modelo.Noticia;
import modelo.Objetivo;
import bean.Conexion;

public class ClienteDAO extends ConexionDAO {
	
	public void agregarClientePotencial(Cliente c) {
		
		String tiraSQL= "INSERT INTO tb_cliente (cedula,nombre,apellido,sexo,telefono,correo,ciudad,tipo_cliente,codigo_referencia,codigo_organizacion,status) "
				+ "VALUES ('"+c.getCedula()+"'"+",'"+c.getNombre()+"','"+ c.getApellido()+"','"+ c.getSexo()+"','"+ c.getTelefeno()+"','"+ c.getCorreo()+"','"+ c.getCuidad()+"','"+ c.getTipo_cliente()+"','"+ c.getCodigo_referecnia()+"','"+ c.getCodigo_organizacion()+"','"+ c.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	public void modificarCliente(String cedula, String estado_civil, String direccion,String codigo_ocupacion, String fecha_nac,String rifOrg,String codigo_organizacion) {
		String tiraSQL = "UPDATE tb_cliente SET estado_civil = '"+estado_civil+"', direccion='"+direccion+"', tipo_cliente='00002', codigo_ocupacion='"+codigo_ocupacion+"', fecha_nacimiento='"+fecha_nac+"', codigo_organizacion='"+rifOrg+"', codigo_acuerdo='"+codigo_organizacion+"' WHERE cedula = '"+cedula+"'";
		Conexion.ejecutar(tiraSQL);
		Messagebox.show(tiraSQL);
			
	}
	public void modificarClientePerfil( String estado_civil, String direccion,String codigo_ocupacion,String cedula,String telefono) {
		String tiraSQL = "UPDATE tb_cliente SET estado_civil = '"+estado_civil+"', direccion='"+direccion+"', codigo_ocupacion='"+codigo_ocupacion+"', telefono='"+telefono+"' WHERE cedula = '"+cedula+"'";
		Conexion.ejecutar(tiraSQL);
			
	}

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
	
	public List<Maestrico> ocupacion() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_ocupacion  WHERE status='Activo' ORDER BY codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Maestrico> arr_maestricos = new ArrayList<Maestrico>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					i++;
					arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_maestricos;
	}
	public String codigoOcupacion(String descrip) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_ocupacion  WHERE descripcion='"+descrip+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String codigo="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					codigo = resultSet.getString("codigo");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;
	}
	
	public ClienteDAO(){
		super();
	}
	
	public Cliente obtenerCliente(String correo)
	{
		Cliente cliente = null;
		String tiraSQL = "select cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo, ciudad, tipo_cliente, codigo_acuerdo, codigo_referencia, codigo_organizacion, status, codigo_ocupacion, fecha_nacimiento  from tb_cliente a where a.correo='"+correo+"'";
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
	
	public List<Cliente> listaCliente() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_cliente WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cliente> arr_clientes = new ArrayList<Cliente>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_clientes.add(new Cliente(resultSet.getString("cedula"), resultSet.getString("nombre"), resultSet.getString("apellido"),resultSet.getString("sexo"), resultSet.getString("estado_civil"), resultSet.getString("telefono"), resultSet.getString("direccion"), resultSet.getString("correo"), resultSet.getString("ciudad"), resultSet.getString("tipo_cliente"), resultSet.getString("codigo_acuerdo"), resultSet.getString("codigo_referencia"), resultSet.getString("codigo_organizacion"), resultSet.getString("status"), resultSet.getString("codigo_ocupacion"), resultSet.getString("fecha_nacimiento")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_clientes;
	}
	
	

	public Cliente BuscarClienteXCedula(String cedulaCliente)
	{
		Cliente cliente = null;
		String tiraSQL = "select cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo, ciudad, tipo_cliente, codigo_acuerdo, codigo_referencia, codigo_organizacion, status, codigo_ocupacion, fecha_nacimiento  from tb_cliente a where a.cedula='"+cedulaCliente+"'";
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
	
	public Cliente BuscarClienteXCorreo(String correoCliente)
	{
		Cliente cliente = null;
		String tiraSQL = "select cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo, ciudad, tipo_cliente,codigo_acuerdo,codigo_referencia,codigo_organizacion,status,codigo_ocupacion,fecha_nacimiento from tb_cliente a where a.correo='"+correoCliente+"'";
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
				cliente = new Cliente(cedula, nombre, apellido, sexo, estado_civil, telefono, direccion, correo1, ciudad, tipo_cliente,codigo_acuerdo,codigo_referencia,codigo_organizacion,status,codigo_ocupacion,fecha_nacimiento);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return cliente;

	}

	public String buscarReferenciaString(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_referencia where codigo = '"+codigo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String descripcion="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					descripcion= resultSet.getString("descripcion");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}
	
	public String buscarEmpresaString(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_acuerdo where codigo = '"+codigo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String descripcion="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					descripcion= resultSet.getString("nombre_empresa");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}
	public String buscarCodigoEmpresa(String nombre_empresa) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_acuerdo where nombre_empresa = '"+nombre_empresa+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String descripcion="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					descripcion= resultSet.getString("codigo");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}
	public String buscarCodigoOcupacion(String nombre_ocupacion) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_ocupacion where descripcion = '"+nombre_ocupacion+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String descripcion="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					descripcion= resultSet.getString("codigo");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return descripcion;
	}
	
	}
