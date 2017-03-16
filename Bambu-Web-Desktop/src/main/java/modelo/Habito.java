package modelo;

public class Habito {

	String codigo;
	String descripcion;
	boolean status;
	
	
	public Habito() {
		super();
		// TODO Auto-generated constructor stub
	}


	public Habito(String codigo, String descripcion, boolean status) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
	}


	public String getCodigo() {
		return codigo;
	}


	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}


	public String getDescripcion() {
		return descripcion;
	}


	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}


	public boolean getStatus() {
		return status;
	}


	public void setStatus(boolean status) {
		this.status = status;
	}

	
	
}
