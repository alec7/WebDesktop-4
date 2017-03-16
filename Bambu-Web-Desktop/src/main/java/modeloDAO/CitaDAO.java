package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Cita;

public class CitaDAO extends ConexionDAO{

	public Cita citaPorCliente(String codigo_detalle_solicitud) { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_cita WHERE status='Activo' and codigo_detalle_solicitud='"+codigo_detalle_solicitud+"'and status='Pendiente'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		Cita cita=null;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					cita= new Cita(resultSet.getString("codigo"), resultSet.getString("codigo_detalle_solicitud"), resultSet.getString("codigo_detalle_cubiculo"), resultSet.getString("status"), resultSet.getString("codigo_motivo_cancelacion"), resultSet.getString("fecha"), resultSet.getString("codigo_esteticista"), resultSet.getString("codigo_servicio"), resultSet.getString("codigo_bloque"));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return cita;
	}
}
