package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zhtml.Messagebox;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Usuario;

public class MaestricoDAO extends ConexionDAO{
	
	
	public MaestricoDAO() {
		super();
	}


public List<Maestrico> listarMaestrico(String tabla) { //para listar en el grid la informacion
	int i=0;
	String tiraSQL = "SELECT * FROM "+tabla+" where status = 'Activo'";
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



//String codigo, String descripcion
public void modificarMaestrico(String tabla,String codigo, String descripcion) {
	String tiraSQL = "UPDATE "+tabla+" SET descripcion = '"+ descripcion + "' WHERE codigo = '"+codigo+"'";
	Conexion.ejecutar(tiraSQL);
	
	
}

public void eliminarMaestrico(String tabla, String codigo){
	
	String tiraSQL= "UPDATE "+tabla+" SET  status= 'Inactivo' WHERE codigo='"+codigo+"' ";
	Conexion.ejecutar(tiraSQL);

}

	
	public String TotalRegistros(String tabla ){
		String tiraSQL = "SELECT * FROM "+tabla;
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

	public void agregarMaestrico(String tabla,String codigo, String descripcion) {
		
		String tiraSQL= "INSERT INTO "+tabla+ " (codigo,descripcion,status) "
				+ "VALUES ('"+codigo+"'"+",'"+ descripcion+"','Activo')";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
		
}   
	

	
	
	
	
