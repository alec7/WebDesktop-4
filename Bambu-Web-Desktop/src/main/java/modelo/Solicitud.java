package modelo;

public class Solicitud {
	private String codigo,codigo_cliente,status,fecha;

	public Solicitud(String codigo, String codigo_cliente, String status, String fecha) {
		super();
		this.codigo = codigo;
		this.codigo_cliente = codigo_cliente;
		this.status = status;
		this.fecha = fecha;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_cliente() {
		return codigo_cliente;
	}

	public void setCodigo_cliente(String codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getFecha() {
		return fecha;
	}

	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	
	
	

}
