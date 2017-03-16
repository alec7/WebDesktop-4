package controlador;

import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Notificacion;
import modeloDAO.NotificacionDao;

public class ControladorNotificacion extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	
	@Wire
	private Textbox titulo;
	@Wire
	private Textbox contenido;
	@Wire
	private Listbox tipoNotificacion;
	@Wire
	private Listbox notificaciones;
	
	NotificacionDao nDao = new NotificacionDao();
	Notificacion n;
	ListModelList<Notificacion> notificacionListModel;
	List<Notificacion> listNotificacion = new ArrayList<Notificacion>();
	ListModelList<Maestrico> descripcionNotificacion;
	
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarNotificacion.html");
	}
	
	@Listen("onCreate = #notificaciones")
	public void notifi(CreateEvent event)
    {
		this.cargarTabla();
    }
	
	public void cargarTabla(){
//		String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//		Messagebox.show(codigoIncidencia);
		listNotificacion = nDao.listarNotificacion();
		//Messagebox.show(String.valueOf(listNotificacion.size()));
		nombreNotificaciones();
		notificacionListModel = new ListModelList<Notificacion>(listNotificacion);
		notificaciones.setModel(notificacionListModel);
		
	}
	public void nombreNotificaciones(){
		
		for(int i=0;i<listNotificacion.size();i++){
			String descripcion;
			descripcion= nDao.buscarDescpIncidencia(listNotificacion.get(i).getTipo_notificacion());
			
			listNotificacion.get(i).setTipo_notificacion(descripcion);
//			Messagebox.show(listNotificacion.get(i).getTipo_notificacion());
		}
		
	}
	@Listen("onCreate = #tipoNotificacion")
	public void roles(CreateEvent event)
    {	
		List<Maestrico> descripcion = nDao.descripciones();
		descripcionNotificacion = new ListModelList<Maestrico>(descripcion);
		tipoNotificacion.setModel(descripcionNotificacion);
    }
	
	@Listen("onClick = #guardar")
	public void guargar(){
		if(tipoNotificacion.getSelectedItem()!=null){
			String codigo = nDao.buscarCodigoTipoInc(tipoNotificacion.getSelectedItem().getLabel());
			Date hoy = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha= sdf.format(hoy);
			n = new Notificacion(nDao.TotalRegistros(), contenido.getText(), "Activo", titulo.getText(), codigo,java.sql.Date.valueOf(fecha));
			nDao.registrarIncidencia(n);
			 Messagebox.show("Notificación Registrada Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			 cargarTabla();
			 cancelar();
		}
		else{
			Messagebox.show("Debe Seleccionar un Tipo de Notificación ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	@Listen("onClick = #cancelar")
	public void cancelar(){
		tipoNotificacion.clearSelection();
		titulo.setText("");
		contenido.setText("");
		
	}
	
	@Listen("onNotificacionDelete = #notificaciones")
	public void onIncidenciaDelete$incidencias(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 n = (Notificacion)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	nDao.modificarStatus(n.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta Notificación?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
}
