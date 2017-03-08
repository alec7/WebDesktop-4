package modelo;

public class HabitoCliente {
	
	
	   String codigo_habito;
	   String codigo_cliente;
	   String codigo;
	   Boolean status;
	   
	   
	   public HabitoCliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public HabitoCliente(String codigo_habito, String codigo_cliente, String codigo, Boolean status) {
		super();
		this.codigo_habito = codigo_habito;
		this.codigo_cliente = codigo_cliente;
		this.codigo = codigo;
		this.status = status;
	}
	public String getCodigo_habito() {
		return codigo_habito;
	}
	public void setCodigo_habito(String codigo_habito) {
		this.codigo_habito = codigo_habito;
	}
	public String getCodigo_cliente() {
		return codigo_cliente;
	}
	public void setCodigo_cliente(String codigo_cliente) {
		this.codigo_cliente = codigo_cliente;
	}
	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public Boolean getStatus() {
		return status;
	}
	public void setStatus(Boolean status) {
		this.status = status;
	}
	   
	   

}
