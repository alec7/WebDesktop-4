package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Paquete;

public class PaqueteDAO extends ConexionDAO {
	
	public List<Paquete> listarPaquetes() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Paquete> arr_paquete = new ArrayList<Paquete>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_paquete.add(new Paquete(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_paquete"), resultSet.getString("status")));							 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_paquete;
	}
	
	public String buscarCodigoPaquete(String descrip) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where descripcion = '"+descrip+"'";
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

}
