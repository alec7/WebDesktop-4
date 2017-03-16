package controlador;

import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Include;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;

import modelo.Comentario;
import modelo.CorreoHTML;
import modelo.TipoComentario;
import modeloDAO.ComentarioDAO;
import modeloDAO.TipoComentarioDAO;

public class ControladorComentario extends SelectorComposer<Component> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox listaComentarios;
	@Wire
	private Listbox listaTipos;

	@Wire
	private Div bandejaEntrada;

	@Wire
	private Div detalleMensaje;

	@Wire
	private Textbox txtDetalleMensaje;
	@Wire
	private Textbox txtEmailUsuario;
	@Wire
	private Textbox txtRespuestaMensaje;
	@Wire
	private Button btnResponder;
	ComentarioDAO dao = new ComentarioDAO();
	TipoComentarioDAO daoTipo = new TipoComentarioDAO();
	Comentario comentario = new Comentario();

	@Listen("onCreate = #listaComentarios")
	public void Comentario(CreateEvent event) {
		// Messagebox.show(String.valueOf(dao.listaComentarios().size()));
		List<Comentario> comentarios = dao.listaComentarios();
		listaComentarios.setModel(new ListModelList<Comentario>(comentarios));
	}

	@Listen("onSelect = #listaTipos")
	public void SeleccionarTipo() {
		TipoComentario tipoComentario = listaTipos.getSelectedItem().getValue();
		List<Comentario> comentarios = dao.listaComentarios(tipoComentario
				.getCodigo());
		listaComentarios.setModel(new ListModelList<Comentario>(comentarios));
	}

	@Listen("onSelect = #listaComentarios")
	public void VerDetalle() {
		bandejaEntrada.setVisible(false);
		detalleMensaje.setVisible(true);
		this.comentario = listaComentarios.getSelectedItem().getValue();

		txtDetalleMensaje.setText(this.comentario.getDescripcion());
		txtEmailUsuario.setText(this.comentario.getCodigo_usuario());
		if (this.comentario.getCodigo_usuario_web() != null
				&& !this.comentario.getCodigo_usuario_web().isEmpty()) {
		
			txtRespuestaMensaje.setVisible(true);
			btnResponder.setVisible(true);
			
		}else{
			txtRespuestaMensaje.setVisible(false);
			btnResponder.setVisible(false);
		}
	}

	@Listen("onClick = #btnAtras ")
	public void btnAtras() {
		bandejaEntrada.setVisible(true);
		detalleMensaje.setVisible(false);
	}

	@Listen("onCreate = #listaTipos")
	public void Tipos(CreateEvent event) {
		// Messagebox.show(String.valueOf(dao.listaComentarios().size()));
		List<TipoComentario> tipos = daoTipo.listaTipoComentarios();
		listaTipos.setModel(new ListModelList<TipoComentario>(tipos));

	}

	@Listen("onClick = #btnResponder")
	public void ResponderMensaje() {
		CorreoHTML correo = new CorreoHTML();
		correo.enviarEmailRespuestaComentario(
				this.comentario.getCodigo_usuario(),
				this.comentario.getDescripcion(),
				this.txtRespuestaMensaje.getText());
		this.txtRespuestaMensaje.setText("");
		btnAtras();
	}

}
