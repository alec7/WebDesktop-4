package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Objetivo;
import modelo.Paquete;
import modelo.Preferencia;

public class PaqueteDAO extends ConexionDAO {
	
	public List<Paquete> listarPaquetes() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where status = 'Activo' and tipo_paquete='Organizacion'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Paquete> arr_paquete = new ArrayList<Paquete>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_paquete.add(new Paquete(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_paquete"), resultSet.getString("status"), resultSet.getString("imagen"),resultSet.getDouble(("precio")))) ;						
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_paquete;
	}
	public List<Paquete> listarPaquetesDifusion() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where status = 'Activo' and tipo_paquete='Difusión'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Paquete> arr_paquete = new ArrayList<Paquete>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_paquete.add(new Paquete(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_paquete"), resultSet.getString("status"), resultSet.getString("imagen"),resultSet.getDouble(("precio")))) ;						
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_paquete;
	}
	
	public String buscarCodigoPaquete(String descrip) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_paquete where descripcion = '"+descrip+"'";
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

	
	public String TotalRegistros(){
		String tiraSQL = "SELECT * FROM tb_paquete ";
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
	public String TotalRegistrosDetallePaquete(){
		String tiraSQL = "SELECT * FROM tb_detalle_paquete ";
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
	
	
public void agregarPaquete(Paquete p) {
		
		String tiraSQL= "INSERT INTO tb_paquete (codigo,descripcion,status,tipo_paquete,imagen, precio) "
				+ "VALUES ('"+p.getCodigo()+"'"+",'"+ p.getDescripcion()+"','"+ p.getStatus()+"','"+ p.getTipo_paquete()+"','"+ p.getImagen()+"','"+ p.getPrecio()+"')";
		Conexion.ejecutar(tiraSQL);
	}
public void agregaralleDetPaquete(String codigo_servicio,String codigo_paquete,String status,String codigo,int cantidad_sesiones) {
	
	String tiraSQL= "INSERT INTO tb_detalle_paquete (codigo_servicio,codigo_paquete,status,codigo,cantidad_sesion) "
			+ "VALUES ('"+codigo_servicio+"'"+",'"+ codigo_paquete+"','"+ status+"','"+ codigo+"','"+ cantidad_sesiones+"')";
	Conexion.ejecutar(tiraSQL);
}
public void modificarStatus(String codigo) {
	String tiraSQL = "UPDATE tb_paquete SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
	Conexion.ejecutar(tiraSQL);
	
	
}

}
