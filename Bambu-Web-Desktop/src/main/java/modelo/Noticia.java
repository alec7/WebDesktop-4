package modelo;

import java.sql.Date;

public class Noticia {

	private String codigo,descripcion,status, imagen, codigo_sistema,tipo_noticia;
	private	Date fecha;
	public Noticia(String codigo, String descripcion, String status, String imagen, String codigo_sistema,
			String tipo_noticia, Date fecha) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.imagen = imagen;
		this.codigo_sistema = codigo_sistema;
		this.tipo_noticia = tipo_noticia;
		this.fecha = fecha;
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
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getCodigo_sistema() {
		return codigo_sistema;
	}
	public void setCodigo_sistema(String codigo_sistema) {
		this.codigo_sistema = codigo_sistema;
	}
	public String getTipo_noticia() {
		return tipo_noticia;
	}
	public void setTipo_noticia(String tipo_noticia) {
		this.tipo_noticia = tipo_noticia;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	 }
