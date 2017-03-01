package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Incidencia;
import modelo.Maestrico;

public class IncidenciaDAO extends ConexionDAO{
	
	public IncidenciaDAO() {
		// TODO Auto-generated constructor stub
	}
	
	public void registrarIncidencia(Incidencia i){
		
		String tiraSQL= "INSERT INTO  tb_incidencia (codigo,descripcion,status,tipo_incidencia) "
				+ "VALUES ('"+i.getCodigo()+"'"+",'"+ i.getDescripcion()+"','"+i.getStatus()+"','"+ i.getTipo_incidencia()+"')";
		Conexion.ejecutar(tiraSQL);
		
	} 
	
	public List<Incidencia> listarIncidencia() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_incidencia where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Incidencia> arr_incidencia = new ArrayList<Incidencia>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_incidencia.add(new Incidencia(resultSet.getString("codigo"),resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("tipo_incidencia")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_incidencia;
	}
	
	
	public List<Maestrico> descripciones() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_incidencia";
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
	
	public String buscarDescpIncidencia(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_incidencia where codigo = '"+codigo+"'";
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
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_incidencia ";
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
	public String buscarCodigoTipoInc(String descripIncid) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_incidencia where descripcion = '"+descripIncid+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		String codigo="";
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					codigo= resultSet.getString("codigo");
					//arr_maestricos.add(new Maestrico(i,resultSet.getString("codigo"), resultSet.getString("descripcion"),resultSet.getString("status") ));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return codigo;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_incidencia SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}

}
