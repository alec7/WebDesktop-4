package modelo;

public class AntecedenteCliente {
	
	
	private String codigo_antecedente;
	private String codigo_cliente;
	private String codigo;
	private Boolean status;
	public AntecedenteCliente() {
		super();
		// TODO Auto-generated constructor stub
	}
	public AntecedenteCliente(String codigo_antecedente, String codigo_cliente, String codigo, Boolean status) {
		super();
		this.codigo_antecedente = codigo_antecedente;
		this.codigo_cliente = codigo_cliente;
		this.codigo = codigo;
		this.status = status;
	}
	public String getCodigo_antecedente() {
		return codigo_antecedente;
	}
	public void setCodigo_antecedente(String codigo_antecedente) {
		this.codigo_antecedente = codigo_antecedente;
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
