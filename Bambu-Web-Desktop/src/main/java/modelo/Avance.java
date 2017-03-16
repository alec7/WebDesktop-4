package modelo;

import java.util.Date;

public class Avance {
	
	private String codigo;
	private String descripcion;
	private String status;
	private String unidad_medida;
	private Date  fecha;
	public Avance() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Avance(String codigo, String descripcion, String status, String unidad_medida) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.unidad_medida = unidad_medida;
		
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
	public String getUnidad_medida() {
		return unidad_medida;
	}
	public void setUnidad_medida(String unidad_medida) {
		this.unidad_medida = unidad_medida;
	}
	public Date getFecha() {
		return fecha;
	}
	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	

}
