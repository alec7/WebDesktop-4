package modelo;

public class Paquete {
	private String codigo,descripcion,tipo_paquete,status;

	public Paquete(String codigo, String descripcion, String tipo_paquete, String status) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo_paquete = tipo_paquete;
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

	public String getTipo_paquete() {
		return tipo_paquete;
	}

	public void setTipo_paquete(String tipo_paquete) {
		this.tipo_paquete = tipo_paquete;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
