package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Slider;
import modelo.TipoNoticia;

public class TipoNoticiaDAO extends ConexionDAO{
	
public void agregarTipoNoticia(TipoNoticia tipo) {
		
		String tiraSQL= "INSERT INTO tb_tipo_noticia (codigo,descripcion,status,imagen) "
				+ "VALUES ('"+tipo.getCodigo()+"'"+",'"+tipo.getDescripcion()+"','"+tipo.getStatus()+"','"+tipo.getImagen()+"')";
		Conexion.ejecutar(tiraSQL);
	}


public void eliminarTipoNoticia(String codigo){
	
	String tiraSQL= "UPDATE tb_tipo_noticia SET  status= 'Inactivo' WHERE codigo='"+codigo+"' ";
	Conexion.ejecutar(tiraSQL);

}


public List<TipoNoticia> listaNoticia()
{
	List<TipoNoticia> tipos = new ArrayList<TipoNoticia>();
	String tiraSQL = "SELECT * from tb_tipo_noticia where status='Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);	
	try {
		while (resultSet.next()) {
			
			String codigo = resultSet.getString("codigo");
			String descripcion = resultSet.getString("descripcion");
			String status =  resultSet.getString("status");
		    String imagen = resultSet.getString("imagen");
		    TipoNoticia tipo = new TipoNoticia(codigo,descripcion, status,  imagen);
			tipos.add(tipo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return tipos;


 }



public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM  tb_tipo_noticia";
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

}
