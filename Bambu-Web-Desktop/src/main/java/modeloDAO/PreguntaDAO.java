package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import modelo.Maestrico;
import modelo.Preferencia;
import modelo.Pregunta;
import bean.Conexion;

public class PreguntaDAO extends ConexionDAO {
	
	public void agregarPregunta(Pregunta p) {
		
		String tiraSQL= "INSERT INTO tb_pregunta (codigo,descripcion,status,tipo_pregunta) "
				+ "VALUES ('"+p.getCodigo()+"'"+",'"+ p.getDescripcion()+"','"+ p.getStatus()+"','"+ p.getTipo_pregunta()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_pregunta ";
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
	
	public List<Pregunta> listaPregunta() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_pregunta WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Pregunta> arr_pregunta = new ArrayList<Pregunta>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_pregunta.add(new Pregunta(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("tipo_pregunta")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_pregunta;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_pregunta SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
	
	public List<Maestrico> tipo_preferencia() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_pregunta";
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
	public String TotalRegistrosFormulario(){
		String tiraSQL = "SELECT * FROM tb_formulario ";
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
	
	public void agregarFormulario(String codigo_respuesta,String codigo_pregunta,String status, String codigo) {
		
		String tiraSQL= "INSERT INTO tb_formulario (codigo_respuesta,codigo_pregunta,status, codigo) "
				+ "VALUES ('"+codigo_respuesta+"'"+",'"+ codigo_pregunta+"','"+ status+"','"+ codigo+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String buscarDescpTipoPregunta(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_pregunta where codigo = '"+codigo+"'";
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
	
	public List<Maestrico> tipo_pregunta() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_pregunta";
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
}

