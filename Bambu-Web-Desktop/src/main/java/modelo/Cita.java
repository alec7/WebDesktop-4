package modelo;

public class Cita 
{
	
	String codigo,codigo_detalle_solicitud, codigo_cubiculo, status, codigo_motivo_cancelacion, fecha, codigo_esteticista, codigo_servicio, codigo_bloque;



	public String getCodigo_detalle_solicitud() {
		return codigo_detalle_solicitud;
	}

	public void setCodigo_detalle_solicitud(String codigo_detalle_solicitud) {
		this.codigo_detalle_solicitud = codigo_detalle_solicitud;
	}

	public Cita(String codigo, String codigo_detalle_solicitud, String codigo_cubiculo, String status,
			String codigo_motivo_cancelacion, String fecha, String codigo_esteticista, String codigo_servicio,
			String codigo_bloque) {
		super();
		this.codigo = codigo;
		this.codigo_detalle_solicitud = codigo_detalle_solicitud;
		this.codigo_cubiculo = codigo_cubiculo;
		this.status = status;
		this.codigo_motivo_cancelacion = codigo_motivo_cancelacion;
		this.fecha = fecha;
		this.codigo_esteticista = codigo_esteticista;
		this.codigo_servicio = codigo_servicio;
		this.codigo_bloque = codigo_bloque;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_cubiculo() {
		return codigo_cubiculo;
	}

	public void setCodigo_cubiculo(String codigo_cubiculo) {
		this.codigo_cubiculo = codigo_cubiculo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodigo_motivo_cancelacion() {
		return codigo_motivo_cancelacion;
	}

	public void setCodigo_motivo_cancelacion(String codigo_motivo_cancelacion) {
		this.codigo_motivo_cancelacion = codigo_motivo_cancelacion;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigo_esteticista() {
		return codigo_esteticista;
	}

	public void setCodigo_esteticista(String codigo_esteticista) {
		this.codigo_esteticista = codigo_esteticista;
	}

	public String getCodigo_servicio() {
		return codigo_servicio;
	}

	public void setCodigo_servicio(String codigo_servicio) {
		this.codigo_servicio = codigo_servicio;
	}

	public String getCodigo_bloque() {
		return codigo_bloque;
	}

	public void setCodigo_bloque(String codigo_bloque) {
		this.codigo_bloque = codigo_bloque;
	}
	
	
	
	
	

}
