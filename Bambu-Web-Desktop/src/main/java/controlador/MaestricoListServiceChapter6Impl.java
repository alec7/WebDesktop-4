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
import modelo.Maestrico;
import controlador.MaestricoListService;
import modeloDAO.MaestricoDAO;

public class MaestricoListServiceChapter6Impl implements MaestricoListService {
	
//	Execution execution = Executions.getCurrent();
//	String tabla = execution.getParameter("master");
	Session miSession = Sessions.getCurrent();
	String tabla =  miSession.getAttribute("master").toString();

	static int maestricoId = 0;
	static List<Maestrico> maestricoList = new ArrayList<Maestrico>();  
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
	
	public MaestricoListServiceChapter6Impl() {
			super();

			MaestricoDAO mdao = new MaestricoDAO();
			//List<Maestrico> todoList = new ArrayList<Maestrico>();
			maestricoList = mdao.listarMaestrico(tabla);


		}

	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized List<Maestrico>getMaestricoList() {
		List<Maestrico> list = new ArrayList<Maestrico>();
		for(Maestrico maestrico:maestricoList){
			list.add(Maestrico.clone(maestrico));
		}
		return list;
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestrico getMaestrico(Integer id){
		int size = maestricoList.size();
		for(int i=0;i<size;i++){
			Maestrico t = maestricoList.get(i);
			if(t.getId().equals(id)){
				return Maestrico.clone(t);
			}
		}
		return null;
	}

	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestrico saveMaestrico(Maestrico maestrico){
		maestrico = Maestrico.clone(maestrico);
		maestrico.setId(maestricoId++);
		maestricoList.add(maestrico);
		return maestrico;
	} 
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized Maestrico updateMaestrico(Maestrico maestrico){
		if(maestrico.getId()==null){
			throw new IllegalArgumentException("cann't save a null-id maestrico, save it first");
		}else{
			maestrico = Maestrico.clone(maestrico);
			int size = maestricoList.size();
			for(int i=0;i<size;i++){
				Maestrico t = maestricoList.get(i);
				if(t.getId().equals(maestrico.getId())){
					maestricoList.set(i, maestrico);
					return maestrico;
				}
			}
			throw new RuntimeException("maestrico not found "+maestrico.getId());
		}
	}
	
	/** synchronized is just because we use static userList in this demo to prevent concurrent access **/
	public synchronized void deleteMaestrico(Maestrico maestrico){
		if(maestrico.getId()!=null){
			int size = maestricoList.size();
			for(int i=0;i<size;i++){
				Maestrico t = maestricoList.get(i);
				if(t.getId().equals(maestrico.getId())){
					maestricoList.remove(i);
					return;
				}
			}
		}
	}    

}
