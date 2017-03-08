package modelo;

public class Horario_Esteticista {
	private String codigo,codigo_horario,codigo_esteticista,status,codigo_agenda;

	public Horario_Esteticista(String codigo, String codigo_horario, String codigo_esteticista, String status,
			String codigo_agenda) {
		super();
		this.codigo = codigo;
		this.codigo_horario = codigo_horario;
		this.codigo_esteticista = codigo_esteticista;
		this.status = status;
		this.codigo_agenda = codigo_agenda;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getCodigo_horario() {
		return codigo_horario;
	}

	public void setCodigo_horario(String codigo_horario) {
		this.codigo_horario = codigo_horario;
	}

	public String getCodigo_esteticista() {
		return codigo_esteticista;
	}

	public void setCodigo_esteticista(String codigo_esteticista) {
		this.codigo_esteticista = codigo_esteticista;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCodigo_agenda() {
		return codigo_agenda;
	}

	public void setCodigo_agenda(String codigo_agenda) {
		this.codigo_agenda = codigo_agenda;
	}
	
	
}
