package modeloDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Conexion;
import seguridadFuncional.Opcion;



public class OpcionDAO extends ConexionDAO{
	



	public OpcionDAO(){
		super();
	}
	
     public ArrayList<Opcion> buscarPadre(){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL = "select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol='00001' and a.codigo_padre = '0' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String status = resultSet.getString("status");
				String icono = resultSet.getString("icono");
				Opcion padre = new Opcion(id_opcion, id_padre, vinculo, texto, status, icono);
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return padres;
	}
	
	public ArrayList<Opcion> buscarHijos(String idrol, String idopcion){
		ArrayList<Opcion> hijos = new ArrayList<Opcion>();
		String tiraSQL ="select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol ='"+idrol+"' and a.codigo_padre='"+idopcion+"' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String estatus = resultSet.getString("status");
				String icono = resultSet.getString("icono");
				Opcion hijo = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono);
				hijos.add(hijo);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return hijos;
	}
	
	public ArrayList<Opcion> buscarNietos(String idrol, String idopcion){
		ArrayList<Opcion> nietos = new ArrayList<Opcion>();
		String tiraSQL ="select b.codigo_opcion, a.codigo_padre, a.vinculo, a.texto, a.status, a.icono from tb_opcion a, tb_opcion_rol b where a.codigo = b.codigo_opcion and b.codigo_rol ='"+idrol+"' and a.codigo_padre='"+idopcion+"' order by b.codigo_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo_opcion");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String estatus = resultSet.getString("status");
				String icono = resultSet.getString("icono");
				Opcion nieto = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono);
				nietos.add(nieto);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return nietos;
	}
	
	public ArrayList<Opcion> ObtenerTodos(){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL ="select * from tb_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				String fk_id_opcion = resultSet.getString("codigo");
				String id_padre = resultSet.getString("codigo_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String estatus = resultSet.getString("status");
				String icono = resultSet.getString("icono");
				Opcion padre = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus, icono);
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return padres;
	}
}
