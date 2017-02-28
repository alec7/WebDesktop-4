package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Noticia;

public class NoticiaDAO extends ConexionDAO {
	
	public void agregarNoticia(Noticia n) {
		
		String tiraSQL= "INSERT INTO tb_noticia (codigo,descripcion,status,imagen,codigo_sistema,fecha) "
				+ "VALUES ('"+n.getCodigo()+"'"+",'"+ n.getDescripcion()+"','"+ n.getStatus()+"','"+ n.getImagen()+"','"+ n.getCodigo_sistema()+"','"+ n.getFecha()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_noticia ";
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
	
	public List<Noticia> listaNoticia() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_noticia WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Noticia> arr_noticia = new ArrayList<Noticia>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_noticia.add(new Noticia(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("imagen"), resultSet.getString("codigo_sistema"), resultSet.getString("tipo_noticias"),resultSet.getDate("fecha")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_noticia;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_noticia SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
	
	
	public List<Maestrico> tipo_noticia() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_noticia";
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
