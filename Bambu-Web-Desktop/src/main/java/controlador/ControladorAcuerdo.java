package controlador;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.Maestrico;
import modelo.Acuerdo;
import modeloDAO.AcuerdoDAO;

import org.w3c.dom.ls.LSInput;
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

public class ControladorAcuerdo extends SelectorComposer<Component> {

private static final long serialVersionUID = 1L;
	
	
	@Wire
	private Textbox nombre_empresa;
	@Wire
	private Textbox observacion;
	@Wire
	private Listbox tipoAcuerdo;
	@Wire
	private Listbox acuerdos;
	
	AcuerdoDAO aDao = new AcuerdoDAO();
	Acuerdo a;
	ListModelList<Acuerdo> acuerdoListModel;
	List<Acuerdo> listAcuerdo = new ArrayList<Acuerdo>();
	ListModelList<Maestrico> descripcionAcuerdo;
	
	@Listen("onCreate = #acuerdos")
	public void acuerdo(CreateEvent event)
    {
		this.cargarTabla();
    }
	
	public void cargarTabla(){
//		String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//		Messagebox.show(codigoIncidencia);
		listAcuerdo = aDao.listarAcuerdo();
		
		//Messagebox.show(String.valueOf(listNotificacion.size()));
		nombreAcuerdos();
		acuerdoListModel = new ListModelList<Acuerdo>(listAcuerdo);
		acuerdos.setModel(acuerdoListModel);
		
	}
	public void nombreAcuerdos(){
		
		for(int i=0;i<listAcuerdo.size();i++){
			String descripcion;
			descripcion= aDao.buscarDescpAcuerdo(listAcuerdo.get(i).getTipo_acuerdo());
			
			listAcuerdo.get(i).setTipo_acuerdo(descripcion);
//			Messagebox.show(listNotificacion.get(i).getTipo_notificacion());
		}
		
	}
	@Listen("onCreate = #tipoAcuerdo")
	public void roles(CreateEvent event)
    {	
		List<Maestrico> descripcion = aDao.descripciones();
		descripcionAcuerdo = new ListModelList<Maestrico>(descripcion);
		tipoAcuerdo.setModel(descripcionAcuerdo);
    }
	
	@Listen("onClick = #guardar")
	public void guargar(){
		if(tipoAcuerdo.getSelectedItem()!=null){
			String codigo = aDao.buscarCodigoTipoAcuerdo(tipoAcuerdo.getSelectedItem().getLabel());
			Date hoy = new Date();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
			String fecha= sdf.format(hoy);
			a = new Acuerdo(aDao.TotalRegistros(), nombre_empresa.getText(), codigo, aDao.RifOrganizacion(),"Activo",java.sql.Date.valueOf(fecha),observacion.getText());
			aDao.registrarAcuerdo(a);
			
			 Messagebox.show("Acuerdo Registrado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			 cargarTabla();
			 cancelar();
		}
		else{
			Messagebox.show("Debe Seleccionar un Tipo de Acuerdo ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	@Listen("onClick = #cancelar")
	public void cancelar(){
		tipoAcuerdo.clearSelection();
		nombre_empresa.setText("");
		observacion.setText("");
		
	}
	
	@Listen("onAcuerdoDelete = #acuerdos")
	public void onAcuerdoDelete$acuerdos(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 a = (Acuerdo)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	aDao.modificarStatus(a.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar este Acuerdo?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
}
