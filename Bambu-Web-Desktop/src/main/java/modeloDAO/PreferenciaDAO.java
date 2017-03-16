package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Paquete;
import modelo.Preferencia;

public class PreferenciaDAO extends ConexionDAO {
	
	public void agregarPreferencia(Preferencia p) {
		
		String tiraSQL= "INSERT INTO tb_preferencia (codigo,descripcion,status,tipo_preferencia,imagen) "
				+ "VALUES ('"+p.getCodigo()+"'"+",'"+ p.getDescripcion()+"','"+ p.getStatus()+"','"+ p.getTipo_preferencia()+"','"+ p.getImagen()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_preferencia ";
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
	
	public List<Preferencia> listaPreferencia() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_preferencia WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Preferencia> arr_preferencia = new ArrayList<Preferencia>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_preferencia.add(new Preferencia(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("tipo_preferencia"), resultSet.getString("imagen")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_preferencia;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_preferencia SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
	
	public List<Maestrico> tipo_preferencia() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_preferencia";
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
	
		public String TotalRegistrosPreferenciaServicio(){
		String tiraSQL = "SELECT * FROM tb_preferencia_servicio ";
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
	
	public void agregarPreferenciaServicio(String codigo_servicio,String codigo_preferencia,String status,String codigo) {
		
		String tiraSQL= "INSERT INTO tb_preferencia_servicio (codigo_servicio,codigo_preferencia,status,codigo) "
				+ "VALUES ('"+codigo_servicio+"'"+",'"+ codigo_preferencia+"','"+ status+"','"+ codigo+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String buscarDescpTipoPreferencia(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_preferencia where codigo = '"+codigo+"'";
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
