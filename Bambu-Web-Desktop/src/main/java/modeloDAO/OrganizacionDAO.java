package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Organizacion;
import modelo.Usuario;


public class OrganizacionDAO extends ConexionDAO {
	
	public void actualizarOrganizacion(Organizacion o) {
		String tiraSQL = "UPDATE tb_organizacion SET rif = '"+o.getRif()+"', nombre='"+o.getNombre()+"', tipo_organizacion='"+o.getTipo_organizacion()+"', correo='"+o.getCorreo()+"', direccion='"+o.getDireccion()+"', telefono='"+o.getTelefono()+"', mision='"+o.getMision()+"', vision='"+o.getVision()+"', imagen='"+o.getImagen()+"' WHERE status = 'Activo'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
	public void agregarOganizacion(Organizacion o) {
		
		String tiraSQL= "INSERT INTO tb_organizacion (rif,nombre,tipo_organizacion,correo,direccion,telefono,mision,vision,status) "
				+ "VALUES ('"+o.getRif()+"'"+",'"+ o.getNombre()+"','"+ o.getTipo_organizacion()+"','"+ o.getCorreo()+"','"+ o.getDireccion()+"','"+ o.getTelefono()+"','"+ o.getMision()+"','"+ o.getVision()+"','"+ o.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public List<Maestrico> tipo_organizacion() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_organizacion";
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
	
	public Organizacion buscarRegistro() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_organizacion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		Organizacion organizacion = null;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					organizacion = new Organizacion(resultSet.getString("rif"), resultSet.getString("nombre"), resultSet.getString("tipo_organizacion"), resultSet.getString("correo"), resultSet.getString("direccion"), resultSet.getString("telefono"),resultSet.getString("mision"), resultSet.getString("vision"), resultSet.getString("status"),resultSet.getString("imagen"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return organizacion;
	}
	
	public String buscarTipoOrganizacion(String descripcion) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_organizacion where descripcion = '"+descripcion+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String codigo="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					codigo= resultSet.getString("codigo");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;
	}
	public String buscarTipoOrganizacionString(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_organizacion where codigo = '"+codigo+"'";
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
}
