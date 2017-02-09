package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Usuario;

public class maestricoDAO extends ConexionDAO{
	
	public maestricoDAO() {
		super();
	}
	
	public void registrarMaestrico(Maestrico maestrico, String tabla, String status ){
		String tiraSQL= "INSERT INTO "+tabla+" (codigo,descripcion,status) "
				+ "VALUES ('"+maestrico.getCodigo()+"'"+",'"+ maestrico.getDescripcion()+"','"+ status+"')";
		Conexion.ejecutar(tiraSQL);
	}   
	
public void actualizarStatus(String tabla,String cod){
		
		String tiraSQL= "UPDATE "+tabla+" SET  status= 'Inactivo' WHERE codigo='"+cod+"' ";
		Conexion.ejecutar(tiraSQL);
	
	}


public ArrayList<Maestrico> listarMaestricos(String tabla) {
	String tiraSQL = "SELECT * FROM "+tabla+" WHERE status='Activo'";
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
		Formatter fmt = new Formatter();
		fmt.format("%05d", numero);
		return String.valueOf(fmt);
	}
	
		
}   
	

	
	
	
	
