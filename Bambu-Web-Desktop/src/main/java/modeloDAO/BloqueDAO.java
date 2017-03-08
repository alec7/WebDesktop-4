package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Bloque;
import modelo.PerfilUsuario;


public class BloqueDAO extends ConexionDAO {
	
	public List<Bloque> listarBloques() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_bloque where status = 'Activo' order by codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Bloque> arr_bloque = new ArrayList<Bloque>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_bloque.add(new Bloque(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getTimestamp("hora_inicio"), resultSet.getTimestamp("hora_fin")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_bloque;
	}
	public Bloque buscarBloque(String codigo_bloque) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_bloque where status = 'Activo' and codigo='"+codigo_bloque+"' ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		Bloque bloque = null;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					bloque = new Bloque(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getTimestamp("hora_inicio"), resultSet.getTimestamp("hora_fin"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bloque;
	}
	
	public void agregarBloque(Bloque b) {
		
		String tiraSQL= "INSERT INTO tb_bloque (codigo,descripcion,hora_inicio,hora_fin,status)"
				+ "VALUES ('"+b.getCodigo()+"'"+",'"+ b.getDescripcion()+"','"+ b.getHora_inicio()+"','"+b.getHora_fin()+"','"+ b.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_bloque ";
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
