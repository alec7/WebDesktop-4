package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.TipoNoticia;
import modelo.TipoServicio;

public class TipoServicioDAO extends ConexionDAO{
	
public void agregarTipoServicio(TipoServicio tipo) {
		
		String tiraSQL= "INSERT INTO tb_tipo_servicio (codigo,descripcion,status,imagen) "
				+ "VALUES ('"+tipo.getCodigo()+"'"+",'"+tipo.getDescripcion()+"','"+tipo.getStatus()+"','"+tipo.getImagen()+"')";
		Conexion.ejecutar(tiraSQL);
	}


public void eliminarTipoServicio(String codigo){
	
	String tiraSQL= "UPDATE tb_tipo_servicio SET  status= 'Inactivo' WHERE codigo='"+codigo+"' ";
	Conexion.ejecutar(tiraSQL);

}


public List<TipoServicio> listaTipoServicios()
{
	List<TipoServicio> tipos = new ArrayList<TipoServicio>();
	String tiraSQL = "SELECT * from tb_tipo_servicio where status='Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);	
	try {
		while (resultSet.next()) {
			
			String codigo = resultSet.getString("codigo");
			String descripcion = resultSet.getString("descripcion");
			String status =  resultSet.getString("status");
		    String imagen = resultSet.getString("imagen");
		    TipoServicio tipo = new TipoServicio(codigo,descripcion, status,  imagen);
			tipos.add(tipo);
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return tipos;



 }



public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM  tb_tipo_servicio";
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
