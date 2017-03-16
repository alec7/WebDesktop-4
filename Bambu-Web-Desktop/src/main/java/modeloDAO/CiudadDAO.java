package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Ciudad;

public class CiudadDAO extends ConexionDAO {
	
	public List<Ciudad> ciudades() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_ciudad  WHERE status='Activo' ORDER BY codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Ciudad> arr_maestricos = new ArrayList<Ciudad>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_maestricos.add(new Ciudad(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("estado")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_maestricos;
	}

	public String codigoCiudad(String ciudad){
		String tiraSQL = "SELECT * FROM tb_ciudad WHERE descripcion='"+ciudad+"' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String codigo="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					codigo= resultSet.getString("codigo");
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		} 
		return codigo;
	}

}
