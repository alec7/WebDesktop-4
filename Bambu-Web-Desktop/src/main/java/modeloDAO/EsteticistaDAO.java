package modeloDAO;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.Conexion;
import modelo.Esteticista;
import modelo.Maestrico;
import modelo.PerfilUsuario;

public class EsteticistaDAO extends ConexionDAO {
	
public void agregarPerfilEsteticista(PerfilUsuario pf) {
		
		String tiraSQL= "INSERT INTO tb_esteticista (cedula,nombre,apellido,direccion,telefono,sexo,estado_civil,correo,codigo_estado,status) "
				+ "VALUES ('"+pf.getCedula()+"'"+",'"+ pf.getNombre()+"','"+ pf.getApellido()+"','"+ pf.getDireccion()+"','"+ pf.getTelefono()+"','"+ pf.getSexo()+"','"+ pf.getEstado_civil()+"','"+ pf.getCorreo()+"','"+ pf.getEstado()+"','"+ pf.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}


public Esteticista buscarEsteticista(String cedula) {
	String tiraSQL = "SELECT * FROM tb_esteticista WHERE cedula='"+cedula+"'  ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	Esteticista es;
	try {
		while (resultSet.next()) {
			es = new Esteticista(resultSet.getString("cedula"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("sexo"), resultSet.getString("estado_civil"), resultSet.getString("telefono"), resultSet.getString("direccion"), resultSet.getString("correo"), resultSet.getString("codigo_estado"), resultSet.getString("status"),resultSet.getString("codigo_organizacion"));
				return es;
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return null;
}
public List<Esteticista> buscarEsteticistaSexo(String sexo) {
	String tiraSQL = "SELECT * FROM tb_esteticista WHERE sexo='"+sexo+"'and  status='Activo' ";
	ResultSet resultSet = Conexion.consultar(tiraSQL);
	List<Esteticista> arr_esteticista = new ArrayList<Esteticista>();
	try {
		while (resultSet.next()) {
			arr_esteticista.add(new Esteticista(resultSet.getString("cedula"), resultSet.getString("nombre"), resultSet.getString("apellido"), resultSet.getString("sexo"), resultSet.getString("estado_civil"), resultSet.getString("telefono"), resultSet.getString("direccion"), resultSet.getString("correo"), resultSet.getString("codigo_estado"), resultSet.getString("status"),resultSet.getString("codigo_organizacion")));
		}
	} catch (SQLException e) {
		e.printStackTrace();
	}
	return arr_esteticista;
}

}
