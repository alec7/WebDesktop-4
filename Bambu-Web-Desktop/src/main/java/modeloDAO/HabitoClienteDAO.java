package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import bean.Conexion;
import modelo.Esteticista;
import modelo.Servicio;

public class HabitoClienteDAO extends ConexionDAO {

	


	public void ActualizarHabitoCliente(String codigoHabitoEncontrado, String codigoCliente, Boolean status) {
		
		String tiraSQL ="UPDATE tb_habito_cliente SET status = '"+status+"'  WHERE codigo_habito ='"+codigoHabitoEncontrado+"' "
				+ "AND codigo_cliente = '"+codigoCliente+"'";
		  Conexion.ejecutar(tiraSQL);
		
	}
	

	public String TotalRegistros(String tabla ){
		String tiraSQL = "SELECT * FROM "+tabla;
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		int numero=1;
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					numero++;
				}
			}
		} catch (SQLException e) {
			e.getMessage();
			
		} 
		Formatter fmt = new Formatter();
		fmt.format("%05d", numero);
	
		return String.valueOf(fmt);
		
	}


	public void insertarHabitoCliente(String codigo, String cedulaCliente, String codigoHabito, boolean b) {
		
		String tiraSQL ="INSERT INTO tb_habito_cliente( codigo,  codigo_cliente, codigo_habito, status) VALUES ('"+codigo+"','"+cedulaCliente+"','"+codigoHabito+"','"+b+"')";
		  Conexion.ejecutar(tiraSQL);
		
		
	}


	public boolean encontrarOpcion(String codigoHabito,String  cedula) {

			boolean encontro=true;
			String tiraSQL = "SELECT * FROM tb_habito_cliente "
							+" WHERE codigo_habito='"+codigoHabito+"' AND codigo_cliente='"+cedula+"' LIMIT 1 ";
			ResultSet resultSet = Conexion.consultar(tiraSQL);
			
			
			try {
				if (resultSet.next()) {
					encontro = true;
					
				}else{
					encontro = false;
				}
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return encontro;
		}
	/*
	 * el proximo metodo devuele un MAP con clave el codigo del servicio y valor el nombre del ese servicio
	 *  que puede ser recomendado a el cliente  por los habitos que tiene registrados
	 */
	public ArrayList<Servicio> serviciosPorHabitosParaUnCliente(String cedulaCliente){
	 //	Map<String , String> serviciosRecomendadosPorHabito = new TreeMap<String, String>();
		ArrayList<Servicio> servicosRecomendadosPorHabito = new ArrayList<>();
		String tiraSQL = "SELECT DISTINCT tb_habito_servicio.codigo_servicio,tb_servicio.titulo "
				+ " FROM tb_habito_servicio, tb_servicio,(SELECT DISTINCT tb_habito.descripcion, select_general.codigo_cliente as cedula, tb_habito_cliente.codigo_habito"
				+ " FROM tb_habito,tb_habito_cliente, (SELECT tb_solicitud.codigo_cliente, "
				+ " tb_diagnostico.codigo_cita, tb_detalle_solicitud.codigo_solicitud, tb_cita.codigo "
				+ " FROM   tb_solicitud,tb_diagnostico,tb_detalle_solicitud,tb_cita "
				+ " WHERE tb_solicitud.codigo = tb_detalle_solicitud.codigo_solicitud "
				+ " AND    tb_detalle_solicitud.codigo = tb_cita.codigo_detalle_solicitud "
				+ " AND    tb_cita.codigo = tb_diagnostico.codigo_cita "
				+ " AND    tb_cita.codigo_servicio = '00001'  ) as select_general "
				+ " WHERE select_general.codigo_cliente = tb_habito_cliente.codigo_cliente "
				+ " AND   tb_habito_cliente.codigo_habito = tb_habito.codigo "
				+ " AND tb_habito_cliente.status = 'true' ) as select_habito1 "
				+ " WHERE select_habito1.codigo_habito = tb_habito_servicio.codigo_maestro "
				+ " AND   tb_habito_servicio.codigo_servicio = tb_servicio.codigo "
				+ " AND select_habito1.cedula = '"+cedulaCliente+"' "
				+ " order by 2,1 ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		try {
			while (resultSet.next()) {
				
				String codigo= resultSet.getString("codigo_servicio");
				String titulo = resultSet.getString("titulo");
			//	String status = resultSet.getString("status");
			//	serviciosRecomendadosPorHabito.put(codigo, titulo);
				
				servicosRecomendadosPorHabito.add(new Servicio(codigo, titulo));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicosRecomendadosPorHabito;
	}
}
