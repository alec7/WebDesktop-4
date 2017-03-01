package modelo;

public class Preferencia {
	public Preferencia(String codigo, String descripcion, String status, String tipo_preferencia, String imagen) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.tipo_preferencia = tipo_preferencia;
		this.imagen = imagen;

	}
	private String codigo,descripcion,status, tipo_preferencia, imagen;
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
	public String getTipo_preferencia() {
		return tipo_preferencia;
	}
	public void setTipo_preferencia(String tipo_preferencia) {
		this.tipo_preferencia = tipo_preferencia;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
 }
