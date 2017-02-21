package modeloDAO;



import java.sql.ResultSet;
import java.sql.SQLException;


import bean.Conexion;
import modelo.Esteticista;
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
}
