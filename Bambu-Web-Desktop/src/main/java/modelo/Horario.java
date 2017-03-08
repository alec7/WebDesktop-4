package modelo;

public class Horario {

	private String codigo, codigo_dia_laborable,codigo_bloque,status;

	public Horario(String codigo, String codigo_dia_laborable, String codigo_bloque, String status) {
		super();
		this.codigo = codigo;
		this.codigo_dia_laborable = codigo_dia_laborable;
		this.codigo_bloque = codigo_bloque;
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_dia_laborable() {
		return codigo_dia_laborable;
	}

	public void setCodigo_dia_laborable(String codigo_dia_laborable) {
		this.codigo_dia_laborable = codigo_dia_laborable;
	}

	public String getCodigo_bloque() {
		return codigo_bloque;
	}

	public void setCodigo_bloque(String codigo_bloque) {
		this.codigo_bloque = codigo_bloque;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	

}
