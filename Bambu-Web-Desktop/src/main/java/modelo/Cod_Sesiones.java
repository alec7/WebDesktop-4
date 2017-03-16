package modelo;

public class Cod_Sesiones {
	private String codigoServicio;
	private int sesiones;
	public Cod_Sesiones(String codigoServicio, int sesiones) {
		super();
		this.codigoServicio = codigoServicio;
		this.sesiones = sesiones;
	}
	public String getCodigoServicio() {
		return codigoServicio;
	}
	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	public int getSesiones() {
		return sesiones;
	}
	public void setSesiones(int sesiones) {
		this.sesiones = sesiones;
	}
	
}
