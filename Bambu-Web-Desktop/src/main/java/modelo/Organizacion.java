package modelo;

import java.io.FileInputStream;

public class Organizacion {
	private String rif,nombre, tipo_organizacion, correo, direccion, telefono,mision, vision,status,imagen;
	//private FileInputStream logo;

	public Organizacion(String rif, String nombre, String tipo_organizacion, String correo, String direccion,
			String telefono, String mision, String vision, String status,String imagen) {
		super();
		this.rif = rif;
		this.nombre = nombre;
		this.tipo_organizacion = tipo_organizacion;
		this.correo = correo;
		this.direccion = direccion;
		this.telefono = telefono;
		this.mision = mision;
		this.vision = vision;
		this.status = status;
		this.imagen=imagen;
		
	}
	
	

	public String getImagen() {
		return imagen;
	}



	public void setImagen(String imagen) {
		this.imagen = imagen;
	}



	public String getRif() {
		return rif;
	}

	public void setRif(String rif) {
		this.rif = rif;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getTipo_organizacion() {
		return tipo_organizacion;
	}

	public void setTipo_organizacion(String tipo_organizacion) {
		this.tipo_organizacion = tipo_organizacion;
	}

	public String getCorreo() {
		return correo;
	}

	public void setCorreo(String correo) {
		this.correo = correo;
	}

	public String getDireccion() {
		return direccion;
	}

	public void setDireccion(String direccion) {
		this.direccion = direccion;
	}

	public String getTelefono() {
		return telefono;
	}

	public void setTelefono(String telefono) {
		this.telefono = telefono;
	}

	public String getMision() {
		return mision;
	}

	public void setMision(String mision) {
		this.mision = mision;
	}

	public String getVision() {
		return vision;
	}

	public void setVision(String vision) {
		this.vision = vision;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
