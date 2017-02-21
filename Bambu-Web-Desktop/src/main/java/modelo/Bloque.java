package modelo;

import java.sql.Timestamp;

public class Bloque {
	String codigo,descripcion,status;
	Timestamp hora_inicio, hora_fin;
	
	
	public Bloque(String codigo, String descripcion, String status, Timestamp hora_inicio, Timestamp hora_fin) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
		this.hora_inicio = hora_inicio;
		this.hora_fin = hora_fin;
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


	public Timestamp getHora_inicio() {
		return hora_inicio;
	}


	public void setHora_inicio(Timestamp hora_inicio) {
		this.hora_inicio = hora_inicio;
	}


	public Timestamp getHora_fin() {
		return hora_fin;
	}


	public void setHora_fin(Timestamp hora_fin) {
		this.hora_fin = hora_fin;
	}
	
	
	
	

}


