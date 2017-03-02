package modelo;

public class Comentario {

	private String tipo_comentario;
	private String codigo;
	private String descripcion;
	private String codigo_usuario;
	private String status;
	private String codigo_usuario_web;
	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Comentario(String tipo_comentario, String codigo, String descripcion, String codigo_usuario, String status,
			String codigo_usuario_web) {
		super();
		this.tipo_comentario = tipo_comentario;
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.codigo_usuario = codigo_usuario;
		this.status = status;
		this.codigo_usuario_web = codigo_usuario_web;
	}
	public String getTipo_comentario() {
		return tipo_comentario;
	}
	public void setTipo_comentario(String tipo_comentario) {
		this.tipo_comentario = tipo_comentario;
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
	public String getCodigo_usuario() {
		return codigo_usuario;
	}
	public void setCodigo_usuario(String codigo_usuario) {
		this.codigo_usuario = codigo_usuario;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getCodigo_usuario_web() {
		return codigo_usuario_web;
	}
	public void setCodigo_usuario_web(String codigo_usuario_web) {
		this.codigo_usuario_web = codigo_usuario_web;
	}
	
	
	
}
