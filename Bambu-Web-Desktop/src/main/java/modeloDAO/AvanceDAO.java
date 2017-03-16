package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import modelo.Avance;
import modelo.Maestrico;
import bean.Conexion;

public class AvanceDAO extends ConexionDAO{

	public void agregarAvance(Avance a) {
		
		String tiraSQL= "INSERT INTO tb_avance (codigo,descripcion,status,unidad_medida) "
				+ "VALUES ('"+a.getCodigo()+"'"+",'"+ a.getDescripcion()+"','"+ a.getStatus()+"','"+ a.getUnidad_medida()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_avance ";
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
	
	public List<Avance> listaAvance() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_avance WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Avance> arr_avance = new ArrayList<Avance>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_avance.add(new Avance(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("unidad_medida")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_avance;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_avance SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
	}
	
	
	
	public String TotalRegistrosAvanceServicio(){
		String tiraSQL = "SELECT * FROM tb_avance_servicio ";
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
	
	public void agregarAvanceServicio(String codigo_servicio,String codigo_maestro,String status,String codigo) {
		
		String tiraSQL= "INSERT INTO tb_avance_servicio (codigo_servicio,codigo_maestro,status,codigo) "
				+ "VALUES ('"+codigo_servicio+"'"+",'"+ codigo_maestro+"','"+ status+"','"+ codigo+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
}
