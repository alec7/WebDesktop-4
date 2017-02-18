package seguridadFuncional;

public class Rol {
	
	int id_rol;
	String descripcion;
	
	
	
	public Rol(int id_rol, String descripcion) {
		super();
		this.id_rol = id_rol;
		this.descripcion = descripcion;
	}
	
	public int getId_rol() {
		return id_rol;
	}
	public void setId_rol(int id_rol) {
		this.id_rol = id_rol;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	
	

}
