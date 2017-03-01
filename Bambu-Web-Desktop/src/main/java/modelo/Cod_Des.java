package modelo;

public class Cod_Des {

	String texto;
	String codigo;
	Boolean status;
	
	public Cod_Des() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Cod_Des(String texto, String codigo, Boolean status) {
		super();
		this.texto = texto;
		this.codigo = codigo;
		this.status = status;
	}

	public String getDescripcion() {
		return texto;
	}

	public void setDescripcion(String descripcion) {
		this.texto = descripcion;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}
	
	
}
