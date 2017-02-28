package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zul.Listbox;

import bean.Conexion;
import modelo.Cod_Des;
import modelo.Maestro;
import modelo.Maestro_servicio;
import modelo.Servicio;
import modelo.Usuario;

public class MaestroDAO extends ConexionDAO{
	
	
	public MaestroDAO() {
		super();
	}


public List<Maestro> listarMaestro(String tabla) { //para listar en el grid la informacion
	int i=0;
	String tiraSQL = "SELECT * FROM "+tabla+" where status = 'Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Maestro> arr_maestros = new ArrayList<Maestro>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				i++;
				arr_maestros.add(new Maestro(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		Messagebox.show(tiraSQL);
		e.printStackTrace();
	}
	//Messagebox.show(tiraSQL);
	return arr_maestros;
}



//String codigo, String descripcion
public void modificarMaestro(String tabla,String codigo, String descripcion) {
	String tiraSQL = "UPDATE "+tabla+" SET descripcion = '"+ descripcion + "' WHERE codigo = '"+codigo+"'";
	Conexion.ejecutar(tiraSQL);
	
	
}

public void eliminarMaestro(String tabla, String codigo){
	
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

	public void agregarMaestro(String tabla,String codigo, String descripcion) {
		
		String tiraSQL= "INSERT INTO "+tabla+ " (codigo,descripcion,status) "
				+ "VALUES ('"+codigo+"'"+",'"+ descripcion+"','Activo')";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
	
	
	
	
	public List<Cod_Des> CargarServiciosAsociados( String codigo, String tabla_maestro_servicio) { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "select tb_servicio.descripcion, tb_servicio.codigo from tb_servicio, "+tabla_maestro_servicio+" where codigo_maestro = '"+codigo+"'  and codigo_servicio = tb_servicio.codigo and "+tabla_maestro_servicio+".status = 'Activo'" ;
		Maestro_servicio mase;
		String cod;
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
	
		List<Cod_Des> arr_cod_des = new ArrayList<Cod_Des>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					
					
					cod=TotalRegistros(tabla_maestro_servicio);
					//Messagebox.show("codigo de la tabla tb_pref_serv "+cod);
					//Messagebox.show("Codigo del maestro " +resultSet.getString("codigo_maestro"));
					//Messagebox.show("codigo del servicio "+resultSet.getString("codigo"));
					
					arr_cod_des.add(new Cod_Des(resultSet.getString("codigo"),resultSet.getString("descripcion")));
					//Messagebox.show(String.valueOf(arr_mase.size()));
				}
			}
		} catch (SQLException e) {
		//	Messagebox.show(tiraSQL);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Messagebox.show(tiraSQL);
	
		return arr_cod_des;
	}
	
	public List<Cod_Des> CargarHistoricoDeServiciosAsociados( String codigo, String tabla_maestro_servicio) { // para traer todos los servicios que estan en la base de datos sin importar su estatus
		int i=0;
		String tiraSQL = "select tb_servicio.descripcion, tb_servicio.codigo from tb_servicio, "+tabla_maestro_servicio+" where codigo_maestro = '"+codigo+"'  and codigo_servicio = tb_servicio.codigo" ;
		Maestro_servicio mase;
		String cod;
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
	
		List<Cod_Des> arr_cod_des2 = new ArrayList<Cod_Des>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					
					
					cod=TotalRegistros(tabla_maestro_servicio);
					//Messagebox.show("codigo de la tabla tb_pref_serv "+cod);
					//Messagebox.show("Codigo del maestro " +resultSet.getString("codigo_maestro"));
					//Messagebox.show("codigo del servicio "+resultSet.getString("codigo"));
					
					arr_cod_des2.add(new Cod_Des(resultSet.getString("codigo"),resultSet.getString("descripcion")));
					//Messagebox.show(String.valueOf(arr_mase.size()));
				}
			}
		} catch (SQLException e) {
		//	Messagebox.show(tiraSQL);
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Messagebox.show(tiraSQL);
	
		return arr_cod_des2;
	}
	



	
	
	
	
	
	
	
	
	

public List<Servicio> listarServicios() { //para listar todos los servicios
	String tiraSQL = "SELECT * FROM tb_servicio where status = 'Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Servicio> arr_servicios = new ArrayList<Servicio>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				
				arr_servicios.add(new Servicio(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_servicio"), resultSet.getString("status"), resultSet.getString("codigo_organizacion"), resultSet.getTimestamp("duracion")));
			
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_servicios;
}


public void ActualizarServiciosDeMaestro(String codigo_maestro,String tabla_Maestro_servicio,List<Cod_Des> modificada) 
{
	
	
	List<Cod_Des> actual = new ArrayList<Cod_Des>();
	
	actual=CargarHistoricoDeServiciosAsociados(codigo_maestro,tabla_Maestro_servicio );
	
	
	for (int i = 0; i < actual.size(); i++) {
		Boolean a=false; 
		//Boolean b= false;
		//Boolean c= false;
		
		for (int k = 0; k < modificada.size(); k++) 
		{
			if(actual.get(i).getCodigo().equals(modificada.get(k).getCodigo())) //si esta en ACTUAL y en MODIFICADA 
			{
			
				//NO HAGO NADA
				
				String tiraSQL = "UPDATE "+tabla_Maestro_servicio+" SET status = 'Activo'  WHERE codigo_servicio = '"+actual.get(i).getCodigo()+"' AND codigo_maestro = '"+codigo_maestro+"'";
				Conexion.ejecutar(tiraSQL);
				Messagebox.show(tiraSQL);
				
				//String tiraSQL = "UPDATE "+tabla_Maestro_servicio+" SET descripcion = '"+ descripcion + "' WHERE codigo = '"+codigo+"'";
				//Conexion.ejecutar(tiraSQL);

				
				a=true;
				//break;
				
				
				
			}
			
		}
		
		if(a == false) //  SI ESTA EN ACTUAL Y NO EN MODIFICADA
		{
			
			//Elimino (Cambio status a Inactivo)
			
			String tiraSQL = "UPDATE "+tabla_Maestro_servicio+" SET status = 'Inactivo'  WHERE codigo_servicio = '"+actual.get(i).getCodigo()+"' AND codigo_maestro = '"+codigo_maestro+"'";
			Conexion.ejecutar(tiraSQL);
			Messagebox.show(tiraSQL);

			

			
		}
	

			
		
		} //-----------------INVERSA----------------------------------------
		
	for (int m = 0; m < modificada.size(); m++) {
		//Boolean a=false; 
		Boolean b= false;
		//Boolean c= false;
		
		for (int n = 0; n < actual.size(); n++) 
		{
			if(modificada.get(m).getCodigo().equals(actual.get(n).getCodigo())) //si existe
			{
				b=true;
				//break;
				
			}
			
		}
		
		if(b == false) // Si no existe 
		{
			//Messagebox()
			
			//Inserto
			
			String codigo = TotalRegistros(tabla_Maestro_servicio);
					
			
			String tiraSQL= "INSERT INTO "+tabla_Maestro_servicio+ " (codigo_maestro,codigo_servicio,codigo,status) "
					+ "VALUES ('"+codigo_maestro+"'"+",'"+ modificada.get(m).getCodigo()+"','"+codigo+"','Activo')";
			Conexion.ejecutar(tiraSQL);
			
			Messagebox.show(tiraSQL);
			
			
		}
		
		
		
	
		
	}
	
	
	
	
	
	
	

	
		
	} 
	
	
	

	
}





	

	
	
	
	
