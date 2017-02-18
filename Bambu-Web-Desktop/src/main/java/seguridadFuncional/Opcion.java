package seguridadFuncional;

public class Opcion {
	
	String id_opcion;
	String id_padre;
	String vinculo;
	String texto;
	String estatus;
	String icono;
	
	

	public Opcion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	public Opcion(String id_opcion, String id_padre, String vinculo, String texto, String estatus, String icono) {
		super();
		this.id_opcion = id_opcion;
		this.id_padre = id_padre;
		this.vinculo = vinculo;
		this.texto = texto;
		this.estatus = estatus;
		this.icono = icono;
	}
	
	
	public String getIcono() {
		return icono;
	}

	public void setIcono(String icono) {
		this.icono = icono;
	}
	
	public String getId_opcion() {
		return id_opcion;
	}

	public void setId_opcion(String id_opcion) {
		this.id_opcion = id_opcion;
	}

	public String getId_padre() {
		return id_padre;
	}

	public void setId_padre(String id_padre) {
		this.id_padre = id_padre;
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
	
	

}
