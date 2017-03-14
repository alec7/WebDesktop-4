package modelo;

public class DisponibilidadEsteticista

{
	
	String fecha, codigo_esteticista, status, codigo_bloque;



	public DisponibilidadEsteticista(String fecha, String codigo_esteticista, String status, String codigo_bloque) {
		super();
		this.fecha = fecha;
		this.codigo_esteticista = codigo_esteticista;
		this.status = status;
		this.codigo_bloque = codigo_bloque;
	}

	public String getCodigo_esteticista() {
		return codigo_esteticista;
	}

	public void setCodigo_esteticista(String codigo_esteticista) {
		this.codigo_esteticista = codigo_esteticista;
	}

	public String getCodigo_bloque() {
		return codigo_bloque;
	}

	public void setCodigo_bloque(String codigo_bloque) {
		this.codigo_bloque = codigo_bloque;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}

	public String getCodigo_cubiculo() {
		return codigo_esteticista;
	}

	public void setCodigo_cubiculo(String codigo_cubiculo) {
		this.codigo_esteticista = codigo_cubiculo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	

}
