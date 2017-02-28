package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
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
import modelo.Usuario;
import modeloDAO.IncidenciaDAO;

public class ControladorIncidencia extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox incidencia;
	@Wire
	private Listbox tipoIncidencia;
	@Wire
	private Listbox incidencias;

	
	IncidenciaDAO inDao = new IncidenciaDAO();
	Incidencia i;
	ListModelList<Incidencia> incidenciaListModel;
	List<Incidencia> listIncidencia = new ArrayList<Incidencia>();
	ListModelList<Maestrico> descripcionRoles;
	
	@Listen("onCreate = #incidencias")
	public void usuarios(CreateEvent event)
    {
		this.cargarTabla();
    }
	
	
	public void cargarTabla(){
//		String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//		Messagebox.show(codigoIncidencia);
		listIncidencia = inDao.listarIncidencia();
		nombreRoles();
		incidenciaListModel = new ListModelList<Incidencia>(listIncidencia);
		incidencias.setModel(incidenciaListModel);
		
	}
	
	public void nombreRoles(){
		
		for(int i=0;i<listIncidencia.size();i++){
			String descripcion;
			descripcion= inDao.buscarDescpIncidencia(listIncidencia.get(i).getTipo_incidencia());
			listIncidencia.get(i).setTipo_incidencia(descripcion);
		}
		
	}
	
	@Listen("onCreate = #tipoIncidencia")
	public void roles(CreateEvent event)
    {	
		List<Maestrico> descripcion = inDao.descripciones();
		descripcionRoles = new ListModelList<Maestrico>(descripcion);
		tipoIncidencia.setModel(descripcionRoles);
    }
	

	@Listen("onClick = #guardar")
	public void guargar(){
		if(tipoIncidencia.getSelectedItem()!=null){
			String codigo = inDao.buscarCodigoTipoInc(tipoIncidencia.getSelectedItem().getLabel());
			i = new Incidencia(inDao.TotalRegistros(), incidencia.getText(), "Activo", codigo);
			inDao.registrarIncidencia(i);
			 Messagebox.show("Incidencia Registrada Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			 cargarTabla();
			 cancelar();
		}
		else{
			Messagebox.show("Debe Seleccionar un Tipo de Incidencia ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	@Listen("onClick = #cancelar")
	public void cancelar(){
		incidencia.setText("");
		tipoIncidencia.clearSelection();
	}
	@Listen("onIncidenciaDelete = #incidencias")
	public void onIncidenciaDelete$incidencias(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 i = (Incidencia)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	inDao.modificarStatus(i.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta Incidencia?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
}
