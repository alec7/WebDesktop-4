package modelo;

import java.sql.Date;

public class Promocion {
	
	private String codigo,descripcion,codigo_paquete,status,fecha_inicio, fecha_fin,eslogan,imagen;
	public Promocion(String codigo, String descripcion, String codigo_paquete, String status, String fecha_inicio,
			String fecha_fin,String eslogan,String imagen) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigo_paquete = codigo_paquete;
		this.status = status;
		this.fecha_inicio = fecha_inicio;
		this.fecha_fin = fecha_fin;
		this.eslogan = eslogan;
		this.imagen = imagen;
	}
	
	
	public String getImagen() {
		return imagen;
	}


	public void setImagen(String imagen) {
		this.imagen = imagen;
	}


	public String getEslogan() {
		return eslogan;
	}


	public void setEslogan(String eslogan) {
		this.eslogan = eslogan;
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
	public String getCodigo_paquete() {
		return codigo_paquete;
	}
	public void setCodigo_paquete(String codigo_paquete) {
		this.codigo_paquete = codigo_paquete;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getFecha_inicio() {
		return fecha_inicio;
	}
	public void setFecha_inicio(String fecha_inicio) {
		this.fecha_inicio = fecha_inicio;
	}
	public String getFecha_fin() {
		return fecha_fin;
	}
	public void setFecha_fin(String fecha_fin) {
		this.fecha_fin = fecha_fin;
	}
	
	
	

}
