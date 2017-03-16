package modelo;

import java.util.Date;

public class DetalleAvance {

	private String codigo;
	private String codigo_detalle_sesion;
	private String codigo_avance;
	private int numero_sesion;
	private String valor;
	private Date  fecha;
	
	
	public DetalleAvance() {
		super();
		// TODO Auto-generated constructor stub
	}

public DetalleAvance(String codigo, String codigo_detalle_sesion, String codigo_avance, int numero_sesion,
			String valor, Date fecha) {
		super();
		this.codigo = codigo;
		this.codigo_detalle_sesion = codigo_detalle_sesion;
		this.codigo_avance = codigo_avance;
		this.numero_sesion = numero_sesion;
		this.valor = valor;
		this.fecha = fecha;
	}


public DetalleAvance( int numero_sesion , Date fecha, String valor) {
	super();
	this.numero_sesion = numero_sesion;
	this.fecha = fecha;
	this.valor = valor;
}




	public String getCodigo() {
		return codigo;
	}




	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}




	public String getCodigo_detalle_sesion() {
		return codigo_detalle_sesion;
	}




	public void setCodigo_detalle_sesion(String codigo_detalle_sesion) {
		this.codigo_detalle_sesion = codigo_detalle_sesion;
	}




	public String getCodigo_avance() {
		return codigo_avance;
	}




	public void setCodigo_avance(String codigo_avance) {
		this.codigo_avance = codigo_avance;
	}




	public int getNumero_sesion() {
		return numero_sesion;
	}




	public void setNumero_sesion(int numero_sesion) {
		this.numero_sesion = numero_sesion;
	}




	public String getValor() {
		return valor;
	}




	public void setValor(String valor) {
		this.valor = valor;
	}




	public Date getFecha() {
		return fecha;
	}




	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}
	
	
	
	
	
	
}
