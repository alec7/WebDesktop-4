package modelo;

public class TipoComentario {

	private String codigo;
	private String descripcion;
	private String status;

	public TipoComentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public TipoComentario(String codigo, String descripcion, String status) {
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

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

}
