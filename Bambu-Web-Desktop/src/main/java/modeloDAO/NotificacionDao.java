package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Maestrico;
import modelo.Notificacion;

public class NotificacionDao extends ConexionDAO {
	
public void registrarIncidencia(Notificacion n){
		
		String tiraSQL= "INSERT INTO  tb_notificacion (codigo,descripcion,status,tipo_notificacion,titulo,fecha) "
				+ "VALUES ('"+n.getCodigo()+"'"+",'"+ n.getDescripcion()+"','"+n.getStatus()+"','"+ n.getTipo_notificacion()+"','"+ n.getTitulo()+"','"+ n.getFecha()+"')";
		Conexion.ejecutar(tiraSQL);
		
	} 

public List<Notificacion> listarNotificacion() { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_notificacion where status = 'Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Notificacion> arr_notif = new ArrayList<Notificacion>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_notif.add(new Notificacion(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("status"), resultSet.getString("titulo"),  resultSet.getString("tipo_notificacion"),resultSet.getDate("fecha")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_notif;
}

		public String buscarDescpIncidencia(String codigo) { //para listar en el grid la informacion
			String tiraSQL = "SELECT * FROM tb_tipo_notificacion where codigo = '"+codigo+"'";
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
		
		public List<Maestrico> descripciones() { //para listar en el grid la informacion
			int i=0;
			String tiraSQL = "SELECT * FROM tb_tipo_notificacion";
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
		
		public String buscarCodigoTipoInc(String descripIncid) { //para listar en el grid la informacion
			String tiraSQL = "SELECT * FROM tb_tipo_notificacion where descripcion = '"+descripIncid+"'";
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
			String tiraSQL = "SELECT * FROM tb_notificacion ";
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
			String tiraSQL = "UPDATE tb_notificacion SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
			Conexion.ejecutar(tiraSQL);
			
			
		}

}
