package modelo;

public class Opcion_rol {
	
	
    int	id_opcion_rol;
    int fk_id_opcion;
    int fk_id_rol;
    char estatus;
    
    public Opcion_rol(int id_opcion_rol, int fk_id_opcion, int fk_id_rol, char estatus) {
		super();
		this.id_opcion_rol = id_opcion_rol;
		this.fk_id_opcion = fk_id_opcion;
		this.fk_id_rol = fk_id_rol;
		this.estatus = estatus;
	}
	
	public int getId_opcion_rol() {
		return id_opcion_rol;
	}
	public void setId_opcion_rol(int id_opcion_rol) {
		this.id_opcion_rol = id_opcion_rol;
	}
	public int getFk_id_opcion() {
		return fk_id_opcion;
	}
	public void setFk_id_opcion(int fk_id_opcion) {
		this.fk_id_opcion = fk_id_opcion;
	}
	public int getFk_id_rol() {
		return fk_id_rol;
	}
	public void setFk_id_rol(int fk_id_rol) {
		this.fk_id_rol = fk_id_rol;
	}
	public char getEstatus() {
		return estatus;
	}
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}
    
    
    
    

}
