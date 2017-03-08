package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Conexion;
import modelo.Opcion;
import modelo.Sesion;

public class SesionDAO extends ConexionDAO{
	
	
	public ArrayList<Sesion> ObtenerSesion(String cedula){
		ArrayList<Sesion> servicios = new ArrayList<Sesion>();
		String tiraSQL = "select s.descripcion,  ds.ejecucion_servicio from tb_servicio s, tb_cliente b, tb_solicitud c, tb_detalle_solicitud d, tb_cita e, tb_detalle_sesion ds "
				+ "where s.codigo = e.codigo_servicio and b.cedula='"+cedula+"' and  c.codigo = d.codigo_solicitud  and  e.codigo_detalle_solicitud = d.codigo and e.codigo = ds.codigo_cita";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String descripcion = resultSet.getString("descripcion");
				Boolean ejecucion = resultSet.getBoolean("ejecucion_servicio");
				Sesion sesion = new Sesion(descripcion, ejecucion);
				servicios.add(sesion);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicios;
	}

}
