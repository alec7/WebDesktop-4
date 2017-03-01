package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;



import modelo.Maestrico;
import bean.Conexion;

public class HabitoDAO extends ConexionDAO{
	
	public ArrayList<Maestrico> listaHabito() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_habito where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<Maestrico> arr_habito = new ArrayList<Maestrico>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					i++;
					arr_habito.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_habito;
	}

}
