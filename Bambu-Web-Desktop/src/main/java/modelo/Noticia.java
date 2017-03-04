package modelo;

import java.sql.Date;

public class Noticia {

   private	String codigo;
   private	String  status;
   private	String  codigo_sistema;
   private Date fecha;
   private	String  tipo_noticia;
   private	String  titulo;
   private	String  contenido;
   private	String  imagen;
public Noticia() {
	super();
	// TODO Auto-generated constructor stub
}
public Noticia(String codigo, String status, String codigo_sistema,Date fecha, String tipo_noticia, String titulo,
		String contenido, String imagen) {
	super();
	this.codigo = codigo;
	this.status = status;
	this.codigo_sistema = codigo_sistema;
	this.fecha = fecha;
	this.tipo_noticia = tipo_noticia;
	this.titulo = titulo;
	this.contenido = contenido;
	this.imagen = imagen;
}
public String getCodigo() {
	return codigo;
}
public void setCodigo(String codigo) {
	this.codigo = codigo;
}
public String getStatus() {
	return status;
}
public void setStatus(String status) {
	this.status = status;
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
public String getTitulo() {
	return titulo;
}
public void setTitulo(String titulo) {
	this.titulo = titulo;
}
public String getContenido() {
	return contenido;
}
public void setContenido(String contenido) {
	this.contenido = contenido;
}
public String getImagen() {
	return imagen;
}
public void setImagen(String imagen) {
	this.imagen = imagen;
}
public Date getFecha() {
	return fecha;
}
public void setFecha(Date fecha) {
	this.fecha = fecha;
}
	
	
	
	 }
