/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package modelo;

import java.io.Serializable;




/**
 * Todo entity 
 */
public class Maestro_servicio 
{
	
	
	  String codigo_maestro;
	   String codigo_servicio;
	   String codigo;
	   String status;
	   
	   
	   
	   
	   
	public Maestro_servicio(String codigo_maestro, String codigo_servicio, String codigo, String status) {
		super();
		this.codigo_maestro = codigo_maestro;
		this.codigo_servicio = codigo_servicio;
		this.codigo = codigo;
		this.status = status;
	}






	public String getCodigo_maestro() {
		return codigo_maestro;
	}



	public void setCodigo_maestro(String codigo_maestro) {
		this.codigo_maestro = codigo_maestro;
	}



	public String getCodigo_servicio() {
		return codigo_servicio;
	}



	public void setCodigo_servicio(String codigo_servicio) {
		this.codigo_servicio = codigo_servicio;
	}



	public String getCodigo() {
		return codigo;
	}



	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}



	public String getStatus() {
		return status;
	}



	public void setStatus(String status) {
		this.status = status;
	}

	
	
	
	

	

	
	   
	   
	   
	   
	   
	   
	   
}
