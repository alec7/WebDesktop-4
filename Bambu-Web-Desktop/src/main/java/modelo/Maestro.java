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
public class Maestro implements Serializable, Cloneable {
	private static final long serialVersionUID = 1L;
	
	
	  String codigo;
	   String descripcion;
	   String status;


	boolean complete;
	
	//String subject;
	/*
	Priority priority;
	Date date;
	String description;
	*/
	Integer id;
	
	

	public Maestro(int id,String codigo, String descripcion, String status) {
		
		this.id=id;
		this.codigo=codigo;
		this.descripcion=descripcion;
		this.status= status;
	}
	
	
	/*public  Todo(String descripcion) {
		
		this.descripcion=descripcion;
		//this.priority = Priority.LOW;
	} */

	public Maestro(String descripcion, String codigo) {
		this.descripcion = descripcion;
		this.codigo=codigo;
		
	}
	
	
	
	
	
	
	


	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}


	public Integer getId() {
		return id;
	}


	public void setId(Integer id) {
		this.id = id;
	}




	public boolean isComplete() {
		return complete;
	}


	public void setComplete(boolean complete) {
		this.complete = complete;
	}


	public String getCodigo() {
		return codigo;
	}
	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Maestro other = (Maestro) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

	public static Maestro clone(Maestro maestro) {
		try {
			return (Maestro) maestro.clone();
		} catch (CloneNotSupportedException e) {
			// not possible
		}
		return null;
	}

}
