package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Notificacion;
import modelo.Promocion;

public class PromocionDAO extends ConexionDAO {
	
	public void registrarPromocion(Promocion p){
		
		String tiraSQL= "INSERT INTO  tb_promocion (codigo,descripcion,status,codigo_paquete,fecha_inicio,fecha_fin,eslogan) "
				+ "VALUES ('"+p.getCodigo()+"'"+",'"+p.getDescripcion()+"','"+p.getStatus()+"','"+ p.getCodigo_paquete()+"','"+ p.getFecha_inicio()+"','"+p.getFecha_fin()+"','"+p.getEslogan()+"')";
		Conexion.ejecutar(tiraSQL);
		
	} 
	
	public List<Promocion> listarPromocion() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_promocion where status = 'Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Promocion> arr_promo = new ArrayList<Promocion>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_promo.add(new Promocion(resultSet.getString("codigo"),  resultSet.getString("descripcion"), resultSet.getString("codigo_paquete"), resultSet.getString("status"), resultSet.getString("fecha_inicio"), resultSet.getString("fecha_fin"), resultSet.getString("eslogan"))); 
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_promo;
	}
	
	public String buscarDescpPaquete(String codigoPaquete) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where codigo = '"+codigoPaquete+"'";
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
		String tiraSQL = "SELECT * FROM tb_promocion ";
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
	public void modificarStatus(String codigo) {
		String tiraSQL = "UPDATE tb_promocion SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
		Conexion.ejecutar(tiraSQL);
		
		
	}
}
