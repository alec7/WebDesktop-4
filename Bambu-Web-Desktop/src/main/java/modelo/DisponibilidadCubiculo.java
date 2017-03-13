package modelo;

public class DisponibilidadCubiculo

{
	
	String fecha, codigo_cubiculo, status, codigo_bloque ;

	

	public DisponibilidadCubiculo(String fecha, String codigo_cubiculo, String status, String codigo_bloque) {
		super();
		this.fecha = fecha;
		this.codigo_cubiculo = codigo_cubiculo;
		this.status = status;
		this.codigo_bloque = codigo_bloque;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
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

	public String getCodigo_bloque() {
		return codigo_bloque;
	}

	public void setCodigo_bloque(String codigo_bloque) {
		this.codigo_bloque = codigo_bloque;
	}

	
	
	
	

}
