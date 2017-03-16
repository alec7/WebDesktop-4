package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;

import bean.Conexion;
import modelo.AntecedenteCliente;
import modelo.HabitoCliente;
import modelo.Servicio;

public class AntecedenteClienteDAO extends ConexionDAO {


	public void ActualizarAntecedenteCliente(String codigoAntecedenteEncontrado, String codigoCliente, Boolean status) {
		
		String tiraSQL ="UPDATE tb_antecedente_cliente SET status = '"+status+"'  WHERE codigo_antecedente ='"+codigoAntecedenteEncontrado+"' "
				+ "AND codigo_cliente = '"+codigoCliente+"'";
		  Conexion.ejecutar(tiraSQL);
		
	}
	

	/*
	 * el proximo metodo devuele un arrayList con  los servicios 
	 *  que puede ser recomendado a el cliente  por los antecedentes que tiene registrados
	 */
	public ArrayList<Servicio> serviciosPorAntecedentesParaUnCliente(String cedulaCliente){

		ArrayList<Servicio> servicosRecomendadosPorAntecedentes = new ArrayList<>();
		String tiraSQL = "SELECT DISTINCT tb_antecedente_servicio.codigo_servicio, tb_servicio.titulo  "
				+ " FROM tb_antecedente_servicio, tb_servicio,(SELECT DISTINCT tb_antecedente.descripcion, select_general1.codigo_cliente as cedula, tb_antecedente_cliente.codigo_antecedente"
				+ " FROM tb_antecedente,tb_antecedente_cliente, (SELECT tb_solicitud.codigo_cliente, tb_diagnostico.codigo_cita, "
				+ " tb_detalle_solicitud.codigo_solicitud, tb_cita.codigo  "
				+ " FROM   tb_solicitud,tb_diagnostico,tb_detalle_solicitud,tb_cita "
				+ " WHERE tb_solicitud.codigo = tb_detalle_solicitud.codigo_solicitud "
				+ " AND    tb_detalle_solicitud.codigo = tb_cita.codigo_detalle_solicitud "
				+ " AND    tb_cita.codigo = tb_diagnostico.codigo_cita "
				+ " AND tb_cita.codigo_servicio = '00001'  ) as select_general1 "
				+ " WHERE select_general1.codigo_cliente = tb_antecedente_cliente.codigo_cliente "
				+ " AND   tb_antecedente_cliente.codigo_antecedente = tb_antecedente.codigo "
				+ " AND tb_antecedente_cliente.status = 'true' ) as select_antecedente1 "
				+ " WHERE select_antecedente1.codigo_antecedente = tb_antecedente_servicio.codigo_maestro "
				+ " AND   tb_antecedente_servicio.codigo_servicio = tb_servicio.codigo "
				+ " AND select_antecedente1.cedula = '"+cedulaCliente+"' "
				+ " order by 1";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		
		try {
			while (resultSet.next()) {
				
				String codigo= resultSet.getString("codigo_servicio");
				String titulo = resultSet.getString("titulo");
				//String status = resultSet.getString("status");

				
				servicosRecomendadosPorAntecedentes.add(new Servicio(codigo, titulo));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return servicosRecomendadosPorAntecedentes;
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


	public void insertarAntecedenteCliente(String codigo, String cedulaCliente, String codigoAntecedente, boolean b) {
		
		String tiraSQL ="INSERT INTO tb_antecedente_cliente( codigo,  codigo_cliente, codigo_antecedente, status) VALUES ('"+codigo+"','"+cedulaCliente+"','"+codigoAntecedente+"','"+b+"')";
		  Conexion.ejecutar(tiraSQL);
		
		
	}


	public boolean encontrarAntecedenteCliente(String codigoAntecedente,String  cedula) {

			boolean encontro=true;
			String tiraSQL = "SELECT * FROM tb_antecedente_cliente "
							+" WHERE codigo_antecedente='"+codigoAntecedente+"' AND codigo_cliente='"+cedula+"' LIMIT 1 ";
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
	
	public ArrayList<AntecedenteCliente> buscarAntecedenteXCliente(String cedula){
		ArrayList<AntecedenteCliente> antecedenteCliente = new ArrayList<AntecedenteCliente>();
		String tiraSQL = "SELECT * from tb_antecedente_cliente WHERE codigo_cliente = '"+cedula+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String codigo_antecedente = resultSet.getString("codigo_antecedente");
				String codigo_cliente = resultSet.getString("codigo_cliente");
				String codigo = resultSet.getString("codigo");
				Boolean status = resultSet.getBoolean("status");
				
				AntecedenteCliente antClient = new AntecedenteCliente(codigo_antecedente, codigo_cliente, codigo, status);
				antecedenteCliente.add(antClient);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return antecedenteCliente;
				

	}
}
