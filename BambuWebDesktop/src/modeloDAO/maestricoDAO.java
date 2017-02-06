package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import bean.Conexion;
import modelo.Maestrico;
import modelo.Usuario;

public class maestricoDAO extends ConexionDAO{
	
	public maestricoDAO() {
		super();
	}
	
	public void registrarMaestrico(Maestrico maestrico, String tabla ){
		String tiraSQL= "INSERT INTO "+tabla+" (codigo,descripcion) "
				+ "VALUES ('"+maestrico.getCodigo()+"'"+",'"+ maestrico.getDescripcion()+"')";
		Conexion.ejecutar(tiraSQL);
	}   
	    
	public ArrayList<Maestrico> listarMaestricos(String tabla) {
		String tiraSQL = "SELECT * FROM "+tabla+" ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<Maestrico> arr_maestricos = new ArrayList<Maestrico>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_maestricos.add(new Maestrico(resultSet.getString("codigo"), resultSet.getString("descripcion")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_maestricos;
	}
	
	
	public String TotalRegistros(String tabla ){
		String tiraSQL = "SELECT * FROM "+tabla+"";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		int numero=1;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					numero++;
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			
		} 
		return String.valueOf(numero);
	}
	
		
}   
	

	
	
	
	
