package modelo;

public class Incidencia {
	
	private String codigo,descripcion,status,tipo_incidencia;

	public Incidencia(String codigo, String descripcion, String status, String tipo_incidencia) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.tipo_incidencia = tipo_incidencia;
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

	public String getTipo_incidencia() {
		return tipo_incidencia;
	}

	public void setTipo_incidencia(String tipo_incidencia) {
		this.tipo_incidencia = tipo_incidencia;
	}
	
	

}
