package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Cod_Des;


public class NecesidadDAO extends ConexionDAO{
	
	public List<Cod_Des> listarNecesidad(String cedula){
		String tiraSQL = "SELECT * FROM tb_necesidad_cliente where codigo_cliente = '"+cedula+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Cod_Des> arr_necesidad = new ArrayList<Cod_Des>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_necesidad.add(new Cod_Des(resultSet.getString("codigo_necesidad"),resultSet.getString("codigo_cliente")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_necesidad;
	}
	
	public String buscarDescpNecesidad(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_necesidad where codigo = '"+codigo+"'";
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
	

