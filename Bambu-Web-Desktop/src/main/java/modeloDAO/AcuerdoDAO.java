package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zhtml.Messagebox;

import modelo.Maestrico;
import modelo.Acuerdo;
import bean.Conexion;

public class AcuerdoDAO extends ConexionDAO{
	

	
public void registrarAcuerdo(Acuerdo a){
		
		String tiraSQL= "INSERT INTO  tb_acuerdo (codigo,nombre_empresa,tipo_acuerdo,codigo_organizacion,status,fecha, observacion) "
				+ "VALUES ('"+a.getCodigo()+"'"+",'"+ a.getNombre_empresa()+"','"+a.getTipo_acuerdo()+"','"+ a.getCodigo_organizacion()+"','"+ a.getStatus()+"','"+a.getFecha()+"', '"+a.getObservacion()+"')";
		Conexion.ejecutar(tiraSQL);
		
	} 

public List<Acuerdo> listarAcuerdo() { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_acuerdo where status = 'Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Acuerdo> arr_acuerdo = new ArrayList<Acuerdo>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_acuerdo.add(new Acuerdo(resultSet.getString("codigo"), resultSet.getString("nombre_empresa"), resultSet.getString("tipo_acuerdo"), resultSet.getString("codigo_organizacion"),  resultSet.getString("status"),resultSet.getDate("fecha"),resultSet.getString("observacion")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_acuerdo;
}

		public String buscarDescpAcuerdo(String codigo) { //para listar en el grid la informacion
			String tiraSQL = "SELECT * FROM tb_tipo_acuerdo where codigo = '"+codigo+"'";
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
			String tiraSQL = "SELECT * FROM tb_tipo_acuerdo";
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
		
		public String buscarCodigoTipoAcuerdo(String descripAcuerdo) { //para listar en el grid la informacion
			String tiraSQL = "SELECT * FROM tb_tipo_acuerdo where descripcion = '"+descripAcuerdo+"'";
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
			String tiraSQL = "SELECT * FROM tb_acuerdo ";
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
			String tiraSQL = "UPDATE tb_acuerdo SET status = 'Inactivo' WHERE codigo = '"+codigo+"'";
			Conexion.ejecutar(tiraSQL);
			
			
		}
		
		
		public String RifOrganizacion(){
			String tiraSQL = "SELECT rif FROM tb_organizacion";
			ResultSet resultSet = Conexion.consultar(tiraSQL);
			String rif= "";
			try {
				if(resultSet!=null){
					while(resultSet.next()){
						rif= resultSet.getString("rif");
					}
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				
			} 
			return rif;
		}


}
