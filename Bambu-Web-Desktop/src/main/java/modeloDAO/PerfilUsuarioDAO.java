package modeloDAO;

import bean.Conexion;
import modelo.PerfilUsuario;

public class PerfilUsuarioDAO extends ConexionDAO {
	
	public void agregarPerfilUsuario(PerfilUsuario pf) {
		
		String tiraSQL= "INSERT INTO tb_perfil_usuario (cedula,nombre,apellido,direccion,telefono,sexo,estado_civil,correo,codigo_estado,status) "
				+ "VALUES ('"+pf.getCedula()+"'"+",'"+ pf.getNombre()+"','"+ pf.getApellido()+"','"+ pf.getDireccion()+"','"+ pf.getTelefono()+"','"+ pf.getSexo()+"','"+ pf.getEstado_civil()+"','"+ pf.getCorreo()+"','"+ pf.getEstado()+"','"+ pf.getStatus()+"')";
		Conexion.ejecutar(tiraSQL);
	}

}
