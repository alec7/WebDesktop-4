package modelo;

public class Pregunta {
	
	private String codigo, descripcion, status, tipo_pregunta;

	public Pregunta() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Pregunta(String codigo, String descripcion, String status,
			String tipo_pregunta) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.tipo_pregunta = tipo_pregunta;
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

	public String getTipo_pregunta() {
		return tipo_pregunta;
	}

	public void setTipo_pregunta(String tipo_pregunta) {
		this.tipo_pregunta = tipo_pregunta;
	}
	

}
