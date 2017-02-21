package modelo;

public class Opcion_rol {
	
	
    String	id_opcion_rol;
    String fk_id_opcion;
    String fk_id_rol;
    char estatus;
    
    public Opcion_rol(String id_opcion_rol, String fk_id_opcion, String fk_id_rol, char estatus) {
		super();
		this.id_opcion_rol = id_opcion_rol;
		this.fk_id_opcion = fk_id_opcion;
		this.fk_id_rol = fk_id_rol;
		this.estatus = estatus;
	}
	
	
	public String getId_opcion_rol() {
		return id_opcion_rol;
	}


	public void setId_opcion_rol(String id_opcion_rol) {
		this.id_opcion_rol = id_opcion_rol;
	}


	public String getFk_id_opcion() {
		return fk_id_opcion;
	}


	public void setFk_id_opcion(String fk_id_opcion) {
		this.fk_id_opcion = fk_id_opcion;
	}


	public String getFk_id_rol() {
		return fk_id_rol;
	}


	public void setFk_id_rol(String fk_id_rol) {
		this.fk_id_rol = fk_id_rol;
	}


	public char getEstatus() {
		return estatus;
	}
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}
    
    
    
    

}
