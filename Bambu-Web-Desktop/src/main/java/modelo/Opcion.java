package modelo;

public class Opcion {
	
	String codigo;
	String codigo_padre;
	String vinculo;
	String texto;
	String estatus;
	String icono;
	String tabla;
	
	
	
	public Opcion() {
		super();
		// TODO Auto-generated constructor stub
	}



	public Opcion(String codigo, String codigo_padre, String vinculo, String texto, String estatus, String icono,
			String tabla) {
		super();
		this.codigo = codigo;
		this.codigo_padre = codigo_padre;
		this.vinculo = vinculo;
		this.texto = texto;
		this.estatus = estatus;
		this.icono = icono;
		this.tabla = tabla;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getCodigo_padre() {
		return codigo_padre;
	}



	public void setCodigo_padre(String codigo_padre) {
		this.codigo_padre = codigo_padre;
	}



	public String getVinculo() {
		return vinculo;
	}



	public void setVinculo(String vinculo) {
		this.vinculo = vinculo;
	}



	public String getTexto() {
		return texto;
	}



	public void setTexto(String texto) {
		this.texto = texto;
	}



	public String getEstatus() {
		return estatus;
	}



	public void setEstatus(String estatus) {
		this.estatus = estatus;
	}



	public String getIcono() {
		return icono;
	}



	public void setIcono(String icono) {
		this.icono = icono;
	}



	public String getTabla() {
		return tabla;
	}



	public void setTabla(String tabla) {
		this.tabla = tabla;
	}
	
	

	
	
	

}
