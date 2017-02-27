/* 
	Description:
		ZK Essentials
	History:
		Created by dennis

Copyright (C) 2012 Potix Corporation. All Rights Reserved.
*/
package controlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Execution;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zul.ListModelList;

import modelo.Priority;
import modelo.Maestro;
import controlador.MaestroListService;
import modeloDAO.MaestroDAO;

public class MaestroListServiceChapter6Impl implements MaestroListService {
	
//	Execution execution = Executions.getCurrent();
//	String tabla = execution.getParameter("master");
	Session miSession = Sessions.getCurrent();
	String tabla =  miSession.getAttribute("master").toString();

	static int maestroId = 0;
	static List<Maestro> maestroList = new ArrayList<Maestro>();  
	//static{
		
		
		
		/*todoList.add(new Todo(todoId++,"1","Buy some milk"));
		todoList.add(new Todo(todoId++,"2","Dennis' birthday gift"));
		todoList.add(new Todo(todoId++,"3","Pay credit-card bill"));*/
	//}
	
	
	/*private static Date dayAfter(int d){
		Calendar c = Calendar.getInstance();
		c.add(Calendar.DATE, d);
		return c.getTime();
	} */
	
	public MaestroListServiceChapter6Impl() {
			super();

			MaestroDAO mdao = new MaestroDAO();
			//List<Maestro> todoList = new ArrayList<Maestro>();
			maestroList = mdao.listarMaestro(tabla);


		}

	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized List<Maestro>getMaestroList() {
		List<Maestro> list = new ArrayList<Maestro>();
		for(Maestro maestro:maestroList){
			list.add(Maestro.clone(maestro));
		}
		return list;
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestro getMaestro(Integer id){
		int size = maestroList.size();
		for(int i=0;i<size;i++){
			Maestro t = maestroList.get(i);
			if(t.getId().equals(id)){
				return Maestro.clone(t);
			}
		}
		return null;
	}

	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestro saveMaestro(Maestro maestro){
		maestro = Maestro.clone(maestro);
		maestro.setId(maestroId++);
		maestroList.add(maestro);
		return maestro;
	} 
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestro updateMaestro(Maestro maestro){
		if(maestro.getId()==null){
			throw new IllegalArgumentException("cann't save a null-id maestro, save it first");
		}else{
			maestro = Maestro.clone(maestro);
			int size = maestroList.size();
			for(int i=0;i<size;i++){
				Maestro t = maestroList.get(i);
				if(t.getId().equals(maestro.getId())){
					maestroList.set(i, maestro);
					return maestro;
				}
			}
			throw new RuntimeException("maestro not found "+maestro.getId());
		}
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized void deleteMaestro(Maestro maestro){
		if(maestro.getId()!=null){
			int size = maestroList.size();
			for(int i=0;i<size;i++){
				Maestro t = maestroList.get(i);
				if(t.getId().equals(maestro.getId())){
					maestroList.remove(i);
					return;
				}
			}
		}
	}    

}
