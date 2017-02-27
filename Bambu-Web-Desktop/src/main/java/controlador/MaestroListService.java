/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controlador;

import java.util.List;

import modelo.Maestro;

public interface MaestroListService {

	/** get Todo list **/
	List<Maestro> getMaestroList();
	
	/** get Maestro by id **/
	Maestro getMaestro(Integer id);
	
	/** save Todo **/
	Maestro saveMaestro(Maestro maestro);
	
	/** update Todo **/
	Maestro updateMaestro(Maestro maestro);
	
	/** delete Todo **/
	void deleteMaestro(Maestro maestro);
	
}
