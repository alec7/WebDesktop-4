package modelo;

public class Opcion {
	
	int id_opcion;
	int id_padre;
	String vinculo;
	String texto;
	char estatus;
	
	
	
	public Opcion(int id_opcion, int id_padre, String vinculo, String texto, char estatus) {
		super();
		this.id_opcion = id_opcion;
		this.id_padre = id_padre;
		this.vinculo = vinculo;
		this.texto = texto;
		this.estatus = estatus;
	}
	
	public int getId_opcion() {
		return id_opcion;
	}
	public void setId_opcion(int id_opcion) {
		this.id_opcion = id_opcion;
	}
	public int getId_padre() {
		return id_padre;
	}
	public void setId_padre(int id_padre) {
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
	public char getEstatus() {
		return estatus;
	}
	public void setEstatus(char estatus) {
		this.estatus = estatus;
	}
	
	

}
