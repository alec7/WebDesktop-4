package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import org.zkoss.zul.Messagebox;

import bean.Conexion;
import modelo.Opcion_rol;

public class Opcion_RolDAO extends ConexionDAO {
	
	
	/*
	public ArrayList<Opcion_rol> ObtenerTodosl(){
		ArrayList<Opcion_rol> opciones_roles = new ArrayList<Opcion_rol>();
		String tiraSQL ="SELECT * FROM tb_opcion_rol WHERE status='"+codigo+"'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String cod = resultSet.getString("codigo");
				String codigo_opcion = resultSet.getString("codigo_opcion");
				String codigo_rol = resultSet.getString("codigo_rol");
				String status = resultSet.getString("status");
				 
				Opcion_rol opcion_rol = new Opcion_rol( cod,  codigo_opcion,  codigo_rol,  status);
				
				opciones_roles.add(opcion_rol);
				
		
				
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opciones_roles;
	}
*/
		
	public List<Opcion_rol> ObtenerOpcionesPorRol(String codigo){
		ArrayList<Opcion_rol> opciones_roles = new ArrayList<Opcion_rol>();
		String tiraSQL = "SELECT codigo, codigo_opcion, status, codigo_rol FROM tb_opcion_rol "
						+" WHERE codigo_rol='"+codigo+"' ORDER BY codigo ";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				
				
				String cod = resultSet.getString("codigo");
				String codigo_opcion = resultSet.getString("codigo_opcion");
				Boolean status = resultSet.getBoolean("status");
				String codigo_rol = resultSet.getString("codigo_rol");
				 
				Opcion_rol opcion_rol = new Opcion_rol( cod,  codigo_opcion, status,  codigo_rol);
				
				opciones_roles.add(opcion_rol);
				
		
				
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return opciones_roles;
	}
	
	

	public ArrayList<Opcion_rol> ObtenerOpcionesPorRolConNombreDeOpcion(String codigo){
			ArrayList<Opcion_rol> opciones_roles = new ArrayList<Opcion_rol>();
			String tiraSQL ="SELECT oprol.codigo , op.texto, oprol.status, oprol.codigo_rol FROM tb_opcion_rol oprol, tb_opcion op"
					+ " WHERE codigo_rol='"+codigo+"'  AND oprol.codigo_opcion=op.codigo"
					+ "      ORDER BY op.codigo ";
			ResultSet resultSet = Conexion.consultar(tiraSQL);
			try {
				while(resultSet.next()){
					
					
					String cod = resultSet.getString("codigo");
					String codigo_opcion = resultSet.getString("texto");
					Boolean status = resultSet.getBoolean("status");
					String codigo_rol = resultSet.getString("codigo_rol");
					 
					Opcion_rol opcion_rol = new Opcion_rol( cod,  codigo_opcion, status,  codigo_rol);
					
					opciones_roles.add(opcion_rol);
					
			
					
				}
				resultSet.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return opciones_roles;
		}
		
		public void insertarOpcionesRol(String codigotabla,  String codgioOpcion, Boolean status, String codigoRolSeleccionado) {
		
			String tiraSQL ="INSERT INTO tb_opcion_rol( codigo, codigo_opcion, status, codigo_rol) VALUES ('"+codigotabla+"','"+codgioOpcion+"','"+status+"','"+codigoRolSeleccionado+"')";
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



		public void ActualizarOpcionPorRol(String codigoOpcionEncontrada, String codigoRolSeleccionado, Boolean status) {
			
			String tiraSQL ="UPDATE tb_opcion_rol SET status = '"+status+"'  WHERE codigo_opcion ='"+codigoOpcionEncontrada+"' "
					+ "AND codigo_rol = '"+codigoRolSeleccionado+"'";
			  Conexion.ejecutar(tiraSQL);
			
		}



		public void insertarNuevaOpcionARol(String codigotabla, String codigoOpcion, String codigoRolSeleccionado, boolean b) {

			String tiraSQL ="INSERT INTO tb_opcion_rol( codigo, codigo_opcion, status, codigo_rol) VALUES ('"+codigotabla+"','"+codigoOpcion+"','"+b+"','"+codigoRolSeleccionado+"')";
			  Conexion.ejecutar(tiraSQL);
		}



		public Boolean encontrarOpcion(String codigoOpcion) {
			
			boolean encontro=true;
			String tiraSQL = "SELECT codigo, codigo_opcion, status, codigo_rol FROM tb_opcion_rol "
							+" WHERE codigo_opcion='"+codigoOpcion+"' LIMIT 1 ";
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



		public boolean encontrarOpcionConRolEspef(String codigoOpcion, String codigoRolSeleccionado) {
			boolean encontro=true;
			String tiraSQL = "SELECT codigo, codigo_opcion, status, codigo_rol FROM tb_opcion_rol "
							+" WHERE codigo_opcion='"+codigoOpcion+"' AND codigo_rol='"+codigoRolSeleccionado+"' LIMIT 1 ";
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
		
		
		

	}
