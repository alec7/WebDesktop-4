package modelo;

import java.io.ByteArrayOutputStream;

public class Slider {
	
	private String codigo;
	private String titulo;
	private String leer_mas;
	private String status;
	private String subtitulo;
	private String imagen;
	public Slider() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Slider(String codigo, String titulo, String leer_mas, String status, String subtitulo, String imagen) {
		super();
		this.codigo = codigo;
		this.titulo = titulo;
		this.leer_mas = leer_mas;
		this.status = status;
		this.subtitulo = subtitulo;
		this.imagen = imagen;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public String getLeer_mas() {
		return leer_mas;
	}
	public void setLeer_mas(String leer_mas) {
		this.leer_mas = leer_mas;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getSubtitulo() {
		return subtitulo;
	}
	public void setSubtitulo(String subtitulo) {
		this.subtitulo = subtitulo;
	}
	public String getImagen() {
		return imagen;
	}
	public static Slider clone(Slider slider) {
		try {
			return (Slider) slider.clone();
		} catch (CloneNotSupportedException e) {
			// not possible
		}
		return null;
	}
	
	
	

}
