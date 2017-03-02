package controlador;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;

import modelo.Comentario;

import modeloDAO.ComentarioDAO;

public class ControladorComentario extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox listaComentarios;
	
	ComentarioDAO dao = new ComentarioDAO();
	Comentario comentario = new Comentario();
	
	
	
	@Listen("onCreate = #listaComentarios")
	public void Comentario(CreateEvent event)
    {
		//Messagebox.show(String.valueOf(dao.listaComentarios().size()));
		List<Comentario> comentarios = dao.listaComentarios();
	    listaComentarios.setModel(new ListModelList<Comentario>(comentarios));
    }
	
	
}
