package modelo;

public class DisponibilidadEsteticista

{
	
	String fecha, codigo_esteticista, status;

	public DisponibilidadEsteticista(String fecha, String codigo_esteticista, String status) {
		super();
		this.fecha = fecha;
		this.codigo_esteticista = codigo_esteticista;
		this.status = status;
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
