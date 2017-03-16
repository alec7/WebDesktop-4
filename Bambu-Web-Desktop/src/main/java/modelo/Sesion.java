package modelo;

public class Sesion {
	
	private String codigo;
	private String descripcionServicio;
	private boolean ejecucion;
	private String codigoServicio;
	
	public Sesion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Sesion(String codigo, String descripcionServicio, boolean ejecucion, String codigoServicio) {
		super();
		this.codigo = codigo;
		this.descripcionServicio = descripcionServicio;
		this.ejecucion = ejecucion;
		this.codigoServicio = codigoServicio;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
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

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}
	

}
