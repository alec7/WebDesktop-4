package modelo;

public class DetalleSolicitud {
	private String codigo_solicitud,codigo_paquete,codigo_servicio,codigo,status;

	public DetalleSolicitud(String codigo_solicitud, String codigo_paquete, String codigo_servicio, String codigo,
			String status) {
		super();
		this.codigo_solicitud = codigo_solicitud;
		this.codigo_paquete = codigo_paquete;
		this.codigo_servicio = codigo_servicio;
		this.codigo = codigo;
		this.status = status;
	}

	public String getCodigo_solicitud() {
		return codigo_solicitud;
	}

	public void setCodigo_solicitud(String codigo_solicitud) {
		this.codigo_solicitud = codigo_solicitud;
	}

	public String getCodigo_paquete() {
		return codigo_paquete;
	}

	public void setCodigo_paquete(String codigo_paquete) {
		this.codigo_paquete = codigo_paquete;
	}

	public String getCodigo_servicio() {
		return codigo_servicio;
	}

	public void setCodigo_servicio(String codigo_servicio) {
		this.codigo_servicio = codigo_servicio;
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
	
	

}
