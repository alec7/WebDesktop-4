package modelo;

import java.sql.Timestamp;

public class Servicio 
{
	
String codigo,descripcion, tipo_servicio, codigo_equipo, status, codigo_organizacion;
Timestamp duracion;

boolean complete;
public Servicio(String codigo, String descripcion, String tipo_servicio, String codigo_equipo, String status,
		String codigo_organizacion, Timestamp duracion) {
	super();
	this.codigo = codigo;
	this.descripcion = descripcion;
	this.tipo_servicio = tipo_servicio;
	this.codigo_equipo = codigo_equipo;
	this.status = status;
	this.codigo_organizacion = codigo_organizacion;
	this.duracion = duracion;
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
public String getTipo_servicio() {
	return tipo_servicio;
}
public void setTipo_servicio(String tipo_servicio) {
	this.tipo_servicio = tipo_servicio;
}
public String getCodigo_equipo() {
	return codigo_equipo;
}
public void setCodigo_equipo(String codigo_equipo) {
	this.codigo_equipo = codigo_equipo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
}
public String getCodigo_organizacion() {
	return codigo_organizacion;
}
public void setCodigo_organizacion(String codigo_organizacion) {
	this.codigo_organizacion = codigo_organizacion;
}
public Timestamp getDuracion() {
	return duracion;
}
public void setDuracion(Timestamp duracion) {
	this.duracion = duracion;
}
public boolean isComplete() {
	return complete;
}


public void setComplete(boolean complete) {
	this.complete = complete;
}








}
