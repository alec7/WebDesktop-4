package modelo;

public class ServicioRecomendado {
	String codigoCliente;
	String codigoServicio;
	boolean status;
	
	public ServicioRecomendado() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ServicioRecomendado(String codigoCliente, String codigoServicio, boolean status) {
		super();
		this.codigoCliente = codigoCliente;
		this.codigoServicio = codigoServicio;
		this.status = status;
	}

	public String getCodigoCliente() {
		return codigoCliente;
	}

	public void setCodigoCliente(String codigoCliente) {
		this.codigoCliente = codigoCliente;
	}

	public String getCodigoServicio() {
		return codigoServicio;
	}

	public void setCodigoServicio(String codigoServicio) {
		this.codigoServicio = codigoServicio;
	}

	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	
	
	
}
