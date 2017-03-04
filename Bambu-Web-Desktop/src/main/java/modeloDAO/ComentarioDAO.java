package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Comentario;


public class ComentarioDAO extends ConexionDAO{
	
	
	public List<Comentario> listaComentarios() { //para listar en el grid la informacion
		String tiraSQL = "SELECT * FROM tb_comentario WHERE status='Activo'";
		ResultSet resultSet = Conexion.consultar(tiraSQL);
		List<Comentario> arr_comentario = new ArrayList<Comentario>();
		try {
			if(resultSet!=null){
				while(resultSet.next()){
					arr_comentario.add(new Comentario(resultSet.getString("tipo_comentario"), resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("codigo_usuario"), resultSet.getString("status"), resultSet.getString("codigo_usuario_web")));
				}
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arr_comentario;
	}

}
