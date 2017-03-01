package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Cod_Des;
import modelo.Maestrico;
import modelo.Opcion;
import modelo.Rol;


public class RolDAO extends ConexionDAO{
	
	public String buscarRol(String descripRol) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_rol where descripcion = '"+descripRol+"'";
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
	
	public String buscarDescpRol(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_rol where codigo = '"+codigo+"'";
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
	
	public List<Maestrico> descripciones() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_rol";
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
	public ArrayList<Rol> obtenerRoles(){
		ArrayList<Rol> roles = new ArrayList<Rol>();
		String tiraSQL ="SELECT * FROM tb_rol";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				
				
				String descripcion = resultSet.getString("descripcion");  
				String codigo = resultSet.getString("codigo");
				String status = resultSet.getString("status");
				Rol rol = new Rol(descripcion, codigo, status);
				roles.add(rol);
				}
		
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return roles;	
	}
	
	

public List<Opcion> listarOpciones() { //para listar todos las opciones
	String tiraSQL = "SELECT * FROM tb_opcion where status = 'Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Opcion> arr_opciones = new ArrayList<Opcion>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				
				arr_opciones.add(new Opcion(resultSet.getString("codigo"), null, null, resultSet.getString("texto"), resultSet.getBoolean("status"),null,null));
			
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_opciones;
}


	
}
