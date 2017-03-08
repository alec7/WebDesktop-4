package modeloDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Formatter;
import java.util.List;

import bean.Conexion;
import modelo.Noticia;
import modelo.Servicio;


public class ServicioDAO extends ConexionDAO{
	
public void agregarServicio(Servicio servicio) {
		
		String tiraSQL= "INSERT INTO tb_servicio (codigo,descripcion,tipo_servicio,codigo_organizacion,imagen,status,precio,titulo,duracion) "
				+ "VALUES ('"+servicio.getCodigo()+"'"+",'"+servicio.getDescripcion()+"','"+servicio.getTipo_servicio()+"','"+servicio.getCodigo_organizacion()+"','"+servicio.getImagen()+"','"+servicio.getStatus()+"','"+servicio.getPrecio()+"','"+servicio.getTitulo()+"','"+servicio.getDuracion()+"')";
		Conexion.ejecutar(tiraSQL);
	}


public String TotalRegistros(){
	String tiraSQL = "SELECT * FROM tb_servicio ";
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


public List<Servicio> listaServicios() { //para listar en el grid la informacion
	String tiraSQL = "SELECT * FROM tb_servicio WHERE status='Activo'";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Servicio> arr_Servicios = new ArrayList<Servicio>();
	try {
		if(resultSet!=null){
			while(resultSet.next()){
				arr_Servicios.add(new Servicio(resultSet.getString("codigo"), resultSet.getString("descripcion"), resultSet.getString("tipo_servicio"), resultSet.getString("codigo_organizacion"), resultSet.getString("imagen"), resultSet.getString("status"),resultSet.getDouble("precio"),resultSet.getString("titulo"),resultSet.getInt("duracion")));
			}
		}
	} catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	return arr_Servicios;
}


public void eliminarServicio(String codigo){
	
	String tiraSQL= "UPDATE tb_servicio SET  status= 'Inactivo' WHERE codigo='"+codigo+"' ";
	Conexion.ejecutar(tiraSQL);

}



}
