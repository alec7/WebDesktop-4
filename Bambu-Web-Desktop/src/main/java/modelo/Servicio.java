package modelo;



public class Servicio 
{
	

	String codigo;
	String  descripcion;
	String  tipo_servicio;
	String  codigo_organizacion;
	String  imagen;
	String  status;
	double  precio;  
	String  titulo;
	int  duracion;
	
	public Servicio() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Servicio(String codigo, String descripcion, String status ) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.status = status;
	}
	
	public Servicio(String codigo, String descripcion, String tipo_servicio, String codigo_organizacion, String imagen,
			String status, double precio, String titulo, int duracion) {
		super();
		this.codigo = codigo;
		this.descripcion = descripcion;
		this.tipo_servicio = tipo_servicio;
		this.codigo_organizacion = codigo_organizacion;
		this.imagen = imagen;
		this.status = status;
		this.precio = precio;
		this.titulo = titulo;
		this.duracion = duracion;
	}


	public Servicio(String codigo, String titulo) {
		this.codigo = codigo;
		this.titulo = titulo;
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
	public String getCodigo_organizacion() {
		return codigo_organizacion;
	}
	public void setCodigo_organizacion(String codigo_organizacion) {
		this.codigo_organizacion = codigo_organizacion;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getTitulo() {
		return titulo;
	}
	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}
	public int getDuracion() {
		return duracion;
	}
	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}






}
