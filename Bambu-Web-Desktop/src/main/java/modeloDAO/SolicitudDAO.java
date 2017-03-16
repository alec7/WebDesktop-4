package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.DetalleSolicitud;
import modelo.Servicio;
import modelo.Solicitud;

public class SolicitudDAO extends ConexionDAO {
	public List<Solicitud> listaSolicitudPorCliente(String cedula) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_solicitud WHERE status='Activo' and codigo_cliente='"+cedula+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Solicitud> arr_Solicitud = new ArrayList<Solicitud>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_Solicitud.add(new Solicitud(resultSet.getString("codigo"), resultSet.getString("codigo_cliente"), resultSet.getString("status"), resultSet.getString("fecha")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_Solicitud;
	}
	public DetalleSolicitud detalleServiciosPorCliente(String codigo_solicitud) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_detalle_solicitud WHERE status='Activo' and codigo_solicitud='"+codigo_solicitud+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		DetalleSolicitud destallSolicitud=null ;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					destallSolicitud =new DetalleSolicitud(resultSet.getString("codigo"), resultSet.getString("codigo_paquete"), resultSet.getString("codigo_servicio"), resultSet.getString("codigo"), resultSet.getString("status"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return destallSolicitud;
	}

}
