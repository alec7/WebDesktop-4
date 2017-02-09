package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import bean.Conexion;
import modelo.Opcion;


public class OpcionDAO extends Conexion{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OpcionDAO(){
		super();
	}
	
	public ArrayList<Opcion> buscarPadre(int idrol){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL ="select b.fk_id_opcion, a.id_padre, a.vinculo, a.texto, a.estatus from opciones a, opcion_rol b where a.id_opcion = b.fk_id_opcion and b.fk_id_rol ='"+idrol+"' and a.id_padre = 0 order by b.fk_id_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				int id_opcion = resultSet.getInt("fk_id_opcion");
				int idpadre = resultSet.getInt("id_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String estatus = resultSet.getString("estatus");
				Opcion padre = new Opcion(id_opcion, idpadre, vinculo, texto, estatus.charAt(0));
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return padres;
	}
	
	public ArrayList<Opcion> buscarHijos(int idrol, int idopcion){
		ArrayList<Opcion> padres = new ArrayList<Opcion>();
		String tiraSQL ="select b.fk_id_opcion, a.id_padre, a.vinculo, a.texto, a.estatus from opciones a, opcion_rol b where a.id_opcion = b.fk_id_opcion and b.fk_id_rol ='"+idrol+"' and a.id_padre='"+idopcion+"' order by b.fk_id_opcion";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		try {
			while(resultSet.next()){
				int fk_id_opcion = resultSet.getInt("fk_id_opcion");
				int id_padre = resultSet.getInt("id_padre");
				String vinculo = resultSet.getString("vinculo");
				String texto = resultSet.getString("texto");
				String estatus = resultSet.getString("estatus");
				Opcion padre = new Opcion(fk_id_opcion, id_padre, vinculo, texto, estatus.charAt(0));
				padres.add(padre);
			}
			resultSet.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return padres;
	}
}
