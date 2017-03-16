package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.TipoComentario;
import modelo.TipoNoticia;
import modelo.TipoServicio;

public class TipoComentarioDAO extends ConexionDAO{
	


public List<TipoComentario> listaTipoComentarios()
{
	List<TipoComentario> tipos = new ArrayList<TipoComentario>();
	String tiraSQL = "SELECT * from tb_tipo_comentario where status='Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);	
	try {
		while (resultSet.next()) {
			
			String codigo = resultSet.getString("codigo");
			String descripcion = resultSet.getString("descripcion");
			String status =  resultSet.getString("status");
		 
		    TipoComentario tipo = new TipoComentario(codigo,descripcion, status);
			tipos.add(tipo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return tipos;



 }



}
