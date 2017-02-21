package modelo;

public class RedSocial {
	private String codigo,url,tipo_red_social,codigo_organizacion,status;

	public RedSocial(String codigo, String url, String tipo_red_social, String codigo_organizacion,
			String status) {
		super();
		this.codigo = codigo;
		this.url = url;
		this.tipo_red_social = tipo_red_social;
		this.codigo_organizacion = codigo_organizacion;
		this.status = status;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
	public String getTipo_red_social() {
		return tipo_red_social;
	}

	public void setTipo_red_social(String tipo_red_social) {
		this.tipo_red_social = tipo_red_social;
	}

	public String getCodigo_organizacion() {
		return codigo_organizacion;
	}

	public void setCodigo_organizacion(String codigo_organizacion) {
		this.codigo_organizacion = codigo_organizacion;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	
}
