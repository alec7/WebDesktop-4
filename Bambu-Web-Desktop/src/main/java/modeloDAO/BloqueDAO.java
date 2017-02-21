package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Bloque;


public class BloqueDAO extends ConexionDAO {
	
	public List<Bloque> listarBloques() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_bloque where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Bloque> arr_bloque = new ArrayList<Bloque>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloque.add(new Bloque(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getTimestamp("hora_inicio"), resultSet.getTimestamp("hora_fin")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_bloque;
	}
	
	
	
	

}
