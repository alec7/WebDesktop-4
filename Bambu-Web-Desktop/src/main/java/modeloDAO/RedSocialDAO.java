package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Objetivo;
import modelo.PerfilUsuario;
import modelo.RedSocial;

public class RedSocialDAO extends ConexionDAO {
	
	public List<Maestrico> tipoRedeSociales() { //para listar en el grid la informacion
		int i=0;
		String tiraSQL = "SELECT * FROM tb_tipo_red_social";
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
	
	public void agregarRedSocial(RedSocial r) {
		
		String tiraSQL= "INSERT INTO tb_red_social (codigo,url,tipo_red_social,codigo_organizacion,status) "
				+ "VALUES ('"+r.getCodigo()+"'"+",'"+ r.getUrl()+"','"+ r.getTipo_red_social()+"','"+ r.getCodigo_organizacion()+"','"+ r.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}
	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_red_social ";
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
	
	public String buscarTipoRedSocial(String descripcion) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_red_social where descripcion = '"+descripcion+"'";
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
	public String buscarTipoRedSocialString(String codigo) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_tipo_red_social where codigo = '"+codigo+"'";
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
	
	public List<RedSocial> listaRedesSociales() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_red_social WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<RedSocial> arr_red = new ArrayList<RedSocial>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_red.add(new RedSocial(resultSet.getString("codigo"), resultSet.getString("url"), resultSet.getString("tipo_red_social"), resultSet.getString("codigo_organizacion"), resultSet.getString("status"))); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_red;
	}
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_red_social SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}


}
