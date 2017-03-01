package modelo;

import java.sql.Date;

public class Notificacion {
	
	private String codigo,descripcion,status,titulo,tipo_notificacion;
	Date fecha;
	public Notificacion(String codigo, String descripcion, String status, String titulo,String tipo_not,Date f) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.titulo = titulo;
		this.tipo_notificacion = tipo_not;
		this.fecha = f;
	}
	
	
	
	
	
	public Date getFecha() {
		return fecha;
	}





	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}





	public String getTipo_notificacion() {
		return tipo_notificacion;
	}



	public void setTipo_notificacion(String tipo_notificacion) {
		this.tipo_notificacion = tipo_notificacion;
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
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	
	

}
