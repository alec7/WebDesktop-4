package modelo;

public class Objetivo {
	
	private String codigo,descripcion,tipo_objetivo,codigo_organizacion,status;

	public Objetivo(String codigo, String descripcion, String tipo_objetivo, String codigo_organizacion,
			String status) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo_objetivo = tipo_objetivo;
		this.codigo_organizacion = codigo_organizacion;
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

	public String getTipo_objetivo() {
		return tipo_objetivo;
	}

	public void setTipo_objetivo(String tipo_objetivo) {
		this.tipo_objetivo = tipo_objetivo;
	}

	public String getCodigo_organizacion() {
		return codigo_organizacion;
	}

	public void setCodigo_organizacion(String codigo_organizacion) {
		this.codigo_organizacion = codigo_organizacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
