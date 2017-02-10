/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controlador;

import java.util.List;

import modelo.Maestrico;

public interface MaestricoListService {

	/** get Todo list **/
	List<Maestrico> getMaestricoList();
	
	/** get Maestrico by id **/
	Maestrico getMaestrico(Integer id);
	
	/** save Todo **/
	Maestrico saveMaestrico(Maestrico maestrico);
	
	/** update Todo **/
	Maestrico updateMaestrico(Maestrico maestrico);
	
	/** delete Todo **/
	void deleteMaestrico(Maestrico maestrico);
	
}
