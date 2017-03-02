package modelo;

public class Rol {
	
	
	String descripcion;
	String  codigo;
	String status;
	
	public Rol() {
	
	}

	public Rol( String descripcion, String codigo, String status) {
		super();
		this.descripcion = descripcion;
		this.codigo = codigo;
		this.status = status;
	}

	

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
	
	
	
	
	
	

}
