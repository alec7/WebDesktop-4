package modelo;

public class Opcion_rol {
	
	
    String	codigo;
    String codigo_opcion;
    String codigo_rol;
    Boolean status;
    
	public Opcion_rol() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Opcion_rol(String codigo, String codigo_opcion, Boolean status, String codigo_rol ) {
		super();
		this.codigo = codigo;
		this.codigo_opcion = codigo_opcion;
		this.codigo_rol = codigo_rol;
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_opcion() {
		return codigo_opcion;
	}

	public void setCodigo_opcion(String codigo_opcion) {
		this.codigo_opcion = codigo_opcion;
	}

	public String getCodigo_rol() {
		return codigo_rol;
	}

	public void setCodigo_rol(String codigo_rol) {
		this.codigo_rol = codigo_rol;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	
	
}
