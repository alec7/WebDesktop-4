package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import modelo.Maestrico;
import bean.Conexion;

public class AntecedenteDAO extends ConexionDAO{
	
	public ArrayList<Maestrico> listaAntecedente() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_antecedente where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<Maestrico> arr_antecedente = new ArrayList<Maestrico>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					i++;
					arr_antecedente.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_antecedente;
	}

}
