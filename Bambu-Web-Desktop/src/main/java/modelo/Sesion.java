package modelo;

public class Sesion {
	
	
	String descripcionServicio;
	boolean ejecucion;
	
	public Sesion() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Sesion(String descripcionServicio, boolean ejecucion) {
		super();
		this.descripcionServicio = descripcionServicio;
		this.ejecucion = ejecucion;
	}
	public String getDescripcionServicio() {
		return descripcionServicio;
	}
	public void setDescripcionServicio(String descripcionServicio) {
		this.descripcionServicio = descripcionServicio;
	}
	public boolean isEjecucion() {
		return ejecucion;
	}
	public void setEjecucion(boolean ejecucion) {
		this.ejecucion = ejecucion;
	}
	
	
	

}
