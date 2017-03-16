package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Antecedente;
import modelo.ServicioRecomendado;

public class ServicioRecomendadoDAO extends ConexionDAO{

	
	public List<ServicioRecomendado> listaSericiosRecomendados() {
		//int i=0;
		String tiraSQL = "SELECT * FROM tb_servicio_recomendado WHERE status = 'false' ORDER BY codigo";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		ArrayList<ServicioRecomendado> arr_servicio_rec = new ArrayList<ServicioRecomendado>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
				
					arr_servicio_rec.add(new ServicioRecomendado(resultSet.getString("codigo_cliente"), resultSet.getString("codigo_servicio"), resultSet.getBoolean("status")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_servicio_rec;
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



	

	public boolean existe(String codigo, String cedula) {
		
		
		boolean encontro=true;
		String tiraSQL = "SELECT * FROM tb_servicio_recomendado "
						+" WHERE codigo_cliente='"+codigo+"' AND codigo_servicio='"+cedula+"' LIMIT 1 ";
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

	public void actualizarServicio(String codigo, String cedulaCliente, boolean status) {
		String tiraSQL ="UPDATE tb_servicio_recomendado SET status = '"+status+"'  WHERE codigo_servico ='"+codigo+"' AND codigo_cliente='"+cedulaCliente+"' AND status='"+status+"' ";
		  Conexion.ejecutar(tiraSQL);
		
	}

	public boolean estatus(String codigoServicio, String cedulaCliente) {
		boolean status=true;
		String tiraSQL = "SELECT status FROM tb_servicio_recomendado "
						+" WHERE codigo_cliente='"+codigoServicio+"' AND codigo_servicio='"+cedulaCliente+"' LIMIT 1 ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		
		
		try {
			
			if(resultSet!=null){
					while(resultSet.next()){
						status =resultSet.getBoolean("status");
						
					}	
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return status;
	}


	public void insertarServicio(String codigoTabla, String codigoServicio, String cedulaCliente, boolean b) {
		String tiraSQL ="INSERT INTO tb_servicio_recomendado( codigo, codigo_servicio, codigo_cliente, status) VALUES ('"+codigoTabla+"','"+codigoServicio+"','"+cedulaCliente+"','"+b+"')";
		  Conexion.ejecutar(tiraSQL);
		
	}

}
