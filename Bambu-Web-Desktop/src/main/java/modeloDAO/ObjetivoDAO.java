package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Objetivo;

public class ObjetivoDAO extends ConexionDAO {
	
	public void agregarObjetivo(Objetivo o) {
		
		String tiraSQL= "INSERT INTO tb_objetivo (codigo,descripcion,tipo_objetivo,codigo_organizacion,status) "
				+ "VALUES ('"+o.getCodigo()+"'"+",'"+ o.getDescripcion()+"','"+ o.getTipo_objetivo()+"','"+ o.getCodigo_organizacion()+"','"+ o.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_objetivo ";
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
	
	public List<Objetivo> listaObjetivo() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_objetivo WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Objetivo> arr_objetivo = new ArrayList<Objetivo>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_objetivo.add(new Objetivo(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_objetivo"), resultSet.getString("codigo_organizacion"), resultSet.getString("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_objetivo;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_objetivo SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}

}
