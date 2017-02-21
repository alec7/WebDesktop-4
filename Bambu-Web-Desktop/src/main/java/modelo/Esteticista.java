package modelo;

public class Esteticista {
	String cedula,nombre,apellido,sexo,estado_civil,telefono,direccion,correo,codigo_estado,status,codigo_organizacion;

	

	public Esteticista(String cedula, String nombre, String apellido, String sexo, String estado_civil, String telefono,
			String direccion, String correo, String codigo_estado, String status, String codigo_organizacion) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = apellido;
		this.sexo = sexo;
		this.estado_civil = estado_civil;
		this.telefono = telefono;
		this.direccion = direccion;
		this.correo = correo;
		this.codigo_estado = codigo_estado;
		this.status = status;
		this.codigo_organizacion = codigo_organizacion;
	}
	
	

	public String getCodigo_organizacion() {
		return codigo_organizacion;
	}



	public void setCodigo_organizacion(String codigo_organizacion) {
		this.codigo_organizacion = codigo_organizacion;
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

	public String getCodigo_estado() {
		return codigo_estado;
	}

	public void setCodigo_estado(String codigo_estado) {
		this.codigo_estado = codigo_estado;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
