package modelo;

public class Cliente {
	
	private String cedula, nombre,apellido,sexo,esta_civil,telefeno,direccion,correo,cuidad,tipo_cliente,codigo_acuerdo,codigo_referecnia,codigo_organizacion,status,codigo_ocupacion,fecha_nacimiento;

	public Cliente(String cedula, String nombre, String aprellido, String sexo,
			String esta_civil, String telefeno, String direccion,
			String correo, String cuidad, String tipo_cliente,
			String codigo_acuerdo, String codigo_referecnia,
			String codigo_organizacion, String status, String codigo_ocupacion,
			String fecha_nacimiento) {
		super();
		this.cedula = cedula;
		this.nombre = nombre;
		this.apellido = aprellido;
		this.sexo = sexo;
		this.esta_civil = esta_civil;
		this.telefeno = telefeno;
		this.direccion = direccion;
		this.correo = correo;
		this.cuidad = cuidad;
		this.tipo_cliente = tipo_cliente;
		this.codigo_acuerdo = codigo_acuerdo;
		this.codigo_referecnia = codigo_referecnia;
		this.codigo_organizacion = codigo_organizacion;
		this.status = status;
		this.codigo_ocupacion = codigo_ocupacion;
		this.fecha_nacimiento = fecha_nacimiento;
	}
	public Cliente(String cedula, String nombre, String aprellido, String sexo,String correo, String cuidad, String tipo_cliente, String codigo_referecnia,String codigo_organizacion, String status,String telefono) {
		super();
		// TODO Auto-generated constructor stub
		this.cedula=cedula;
		this.nombre=nombre;
		this.apellido=aprellido;this.tipo_cliente=tipo_cliente;
		this.sexo= sexo;
		this.correo=correo;
		this.cuidad=cuidad;
		this.codigo_referecnia=codigo_referecnia;
		this.codigo_organizacion= codigo_organizacion;
		this.status=status;
		this.telefeno=telefono;
		
	}
	public Cliente(){
		
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

	public void setApellido(String aprellido) {
		this.apellido = aprellido;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public String getEsta_civil() {
		return esta_civil;
	}

	public void setEsta_civil(String esta_civil) {
		this.esta_civil = esta_civil;
	}

	public String getTelefeno() {
		return telefeno;
	}

	public void setTelefeno(String telefeno) {
		this.telefeno = telefeno;
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

	public String getCuidad() {
		return cuidad;
	}

	public void setCuidad(String cuidad) {
		this.cuidad = cuidad;
	}

	public String getTipo_cliente() {
		return tipo_cliente;
	}

	public void setTipo_cliente(String tipo_cliente) {
		this.tipo_cliente = tipo_cliente;
	}

	public String getCodigo_acuerdo() {
		return codigo_acuerdo;
	}

	public void setCodigo_acuerdo(String codigo_acuerdo) {
		this.codigo_acuerdo = codigo_acuerdo;
	}

	public String getCodigo_referecnia() {
		return codigo_referecnia;
	}

	public void setCodigo_referecnia(String codigo_referecnia) {
		this.codigo_referecnia = codigo_referecnia;
	}

	public String getCodigo_organizacion() {
		return codigo_organizacion;
	}

	public void setCodigo_organizacion(String codigo_organizacion) {
		this.codigo_organizacion = codigo_organizacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodigo_ocupacion() {
		return codigo_ocupacion;
	}

	public void setCodigo_ocupacion(String codigo_ocupacion) {
		this.codigo_ocupacion = codigo_ocupacion;
	}

	public String getFecha_nacimiento() {
		return fecha_nacimiento;
	}

	public void setFecha_nacimiento(String fecha_nacimiento) {
		this.fecha_nacimiento = fecha_nacimiento;
	}
	
	

}
