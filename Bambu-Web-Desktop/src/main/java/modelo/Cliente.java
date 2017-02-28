package modelo;

public class Cliente {
	
	
	String cedula;
	String nombre;
	String apellido;
	String sexo;
	String  estado_civil;
	String  telefono;
	String  direccion;
	String  correo;
	String  ciudad;
	public Cliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Cliente(String cedula, String nombre, String apellido, String sexo, String estado_civil, String telefono,
			String direccion, String correo, String ciudad) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.estado_civil = estado_civil;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.ciudad = ciudad;
	}
	public String getCedula() {
		return cedula;
	}
	public void setCedula(String cedula) {
		this.cedula = cedula;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getApellido() {
		return apellido;
	}
	public void setApellido(String apellido) {
		this.apellido = apellido;
	}
	public String getSexo() {
		return sexo;
	}
	public void setSexo(String sexo) {
		this.sexo = sexo;
	}
	public String getEstado_civil() {
		return estado_civil;
	}
	public void setEstado_civil(String estado_civil) {
		this.estado_civil = estado_civil;
	}
	public String getTelefono() {
		return telefono;
	}
	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}
	public String getDireccion() {
		return direccion;
	}
	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}
	public String getCorreo() {
		return correo;
	}
	public void setCorreo(String correo) {
		this.correo = correo;
	}
	public String getCiudad() {
		return ciudad;
	}
	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}
	
	
	

}
