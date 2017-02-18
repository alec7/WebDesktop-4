package modeloDAO;

import org.zkoss.zul.Messagebox;

import bean.Conexion;
import modelo.PerfilUsuario;

public class EsteticistaDAO extends ConexionDAO {
	
public void agregarPerfilEsteticista(PerfilUsuario pf) {
		
		String tiraSQL= "INSERT INTO tb_esteticista (cedula,nombre,apellido,direccion,telefono,sexo,estado_civil,correo,codigo_estado,status) "
				+ "VALUES ('"+pf.getCedula()+"'"+",'"+ pf.getNombre()+"','"+ pf.getApellido()+"','"+ pf.getDireccion()+"','"+ pf.getTelefono()+"','"+ pf.getSexo()+"','"+ pf.getEstado_civil()+"','"+ pf.getCorreo()+"','"+ pf.getEstado()+"','"+ pf.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}

}
