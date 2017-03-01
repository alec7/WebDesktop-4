package modelo;

import java.util.Date;

public class Acuerdo {

	private String codigo, nombre_empresa, tipo_acuerdo, codigo_organizacion, status, observacion;
    Date fecha;
    
    public Acuerdo(String codigo, String nombre_empresa, String tipo_acuerdo, String codigo_organizacion,String status, Date fecha, String observacion) {
		super();
		this.codigo = codigo;
		this.nombre_empresa = nombre_empresa;
		this.tipo_acuerdo = tipo_acuerdo;
		this.codigo_organizacion = codigo_organizacion;
		this.status = status;
		this.fecha = fecha;
		this.observacion = observacion;
		
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getNombre_empresa() {
		return nombre_empresa;
	}

	public void setNombre_empresa(String nombre_empresa) {
		this.nombre_empresa = nombre_empresa;
	}

	public String getTipo_acuerdo() {
		return tipo_acuerdo;
	}

	public void setTipo_acuerdo(String tipo_acuerdo) {
		this.tipo_acuerdo = tipo_acuerdo;
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

	public String getObservacion() {
		return observacion;
	}

	public void setObservacion(String observacion) {
		this.observacion = observacion;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
    
    
}


