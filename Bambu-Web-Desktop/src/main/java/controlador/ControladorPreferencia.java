package controlador;

import java.io.FileOutputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Noticia;
import modelo.Paquete;
import modelo.Preferencia;
import modelo.Servicio;
import modeloDAO.MaestricoDAO;
import modeloDAO.PreferenciaDAO;
import modeloDAO.ServicioDAO;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

public class ControladorPreferencia extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox tipo_preferencia;
	@Wire
	private Listbox listaPreferencia;
	@Wire
	private Textbox descripcion;
	@Wire
	private Listbox listaTodosServicios;
	@Wire
	private Listbox listaServiciosSeleccionados;
	@Wire
	private Image imagen;

	@Wire
	private Media media;

	@Wire
	private Button uploadImagen;

	PreferenciaDAO preferenciaDao = new PreferenciaDAO();
	MaestricoDAO dao = new MaestricoDAO();
	Preferencia preferencia = new Preferencia();
	ListModelList<Maestrico> tipoPreferencia;
	List<Preferencia> listPreferencia =  preferenciaDao.listaPreferencia();
	ListModelList<Preferencia> preferenciaListModel;
	ServicioDAO servicioDao = new ServicioDAO();
	//ARREGLO QUE TIENE TODOS LOS SERVICIOS DE LA BD
	List<Servicio> todosServicios =new ArrayList<Servicio>();
	//ARREGLO QUE TIENE TODOS LOS SERVICIOS QUE SELECCIONA EL USUARIO (ARREGLO LADO DERECHO)
	List<Servicio> ServiciosSeleccionados = new ArrayList<Servicio>();
	//ARREGLO MODELO PARA CARGAR LA VISTA
	ListModelList<Servicio> servicioSeleccionadoModel;
	//ARREGLO MODELO DE TODOS LOS SERVICIOS DE LA BD
	ListModelList<Servicio> todosServiciosModel;
	

	List<Maestrico> tipo_preferencia1 = dao.listarMaestrico("tb_tipo_preferencia");
	
	@Listen("onCreate = #preferencia")
	public void cargarOrg(CreateEvent event){
		
    		
    		cargarComboBox();
	    }
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarPreferencia.html");
	}
	
	@Listen("onCreate = #listaPreferencia")
	public void preferencia(CreateEvent event)
    {
		
		this.cargarTabla();
		cargarTodosServicios();
    }
	
	public void cargarTabla(){
		
		nombreTipoPreferencia();
		listaPreferencia.setModel(new ListModelList<Preferencia>(listPreferencia));
	
	}
	
	
public void nombreTipoPreferencia(){
		
		for(int i=0;i<listPreferencia.size();i++){
			String descripcion;
			descripcion= preferenciaDao.buscarDescpTipoPreferencia(listPreferencia.get(i).getTipo_preferencia());
			listPreferencia.get(i).setTipo_preferencia(descripcion);
		}
		
	}
	public void cargarComboBox(){
	
		
		/*for(int i=0;i<tipo_noticia.getItemCount();i++){
			if(tipo_noticia.getItemAtIndex(i).getLabel().equalsIgnoreCase(tipoNot)){
				 tipo_noticia.setSelectedIndex(i);
				 
			}
		}*/
		tipo_preferencia.setModel(new ListModelList<Maestrico>(tipo_preferencia1));
		}
	
	
	@Listen("onClick = #agregar")
	public void doAgregar(){
		if(listaTodosServicios.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un servicio", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			Servicio s = null;
			//SACA EL SERVICIO QUE ESTA SELECCIONADO Y LO PASA AL LADO DERECHO
			s = listaTodosServicios.getSelectedItem().getValue();
			//LO AGREGA A LA LISTA DE LOS SELECCIONADOS
			ServiciosSeleccionados.add(s);
			//ACTUALIZA LA TABLA DE LA VISTA
			servicioSeleccionadoModel = new ListModelList<Servicio>(ServiciosSeleccionados);
			listaServiciosSeleccionados.setModel(servicioSeleccionadoModel);
			//LO REMUEVE DE LA LISTA DE LADO IZQUIERDO
			todosServicios.remove(listaTodosServicios.getSelectedIndex());
			//ACTUALIZA LA TABLA DE LA VISTA
			listaTodosServicios.setModel(new ListModelList<Servicio>(todosServicios));
		}
	}
	
	@Listen("onClick = #quitar")
	public void doQuitarMartes()
	{
		
		if(listaServiciosSeleccionados.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe seleccionar un servicio ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			Servicio s = null;
			s=listaServiciosSeleccionados.getSelectedItem().getValue();
			todosServicios.add(s);
			
			servicioSeleccionadoModel = new ListModelList<Servicio>(todosServicios);
			listaTodosServicios.setModel(servicioSeleccionadoModel);
			ServiciosSeleccionados.remove(listaServiciosSeleccionados.getSelectedIndex());
			listaServiciosSeleccionados.setModel(new ListModelList<Servicio>(ServiciosSeleccionados));
		}
	}
	//METODO QUE CARGA TODOS LOS SERVICIOS EN LA LISTA IZQUIERA
	public void cargarTodosServicios(){
		todosServicios = servicioDao.listaServicios();
		servicioSeleccionadoModel = new ListModelList<Servicio>(todosServicios);
		listaTodosServicios.setModel(servicioSeleccionadoModel);
	}
	
	@Listen("onCreate = #tipo_preferencia")
	public void tipoOrg(CreateEvent event)
    {	
		List<Maestrico> descripcion = preferenciaDao.tipo_preferencia();
		
		tipoPreferencia = new ListModelList<Maestrico>(descripcion);
		
		tipo_preferencia.setModel(tipoPreferencia);
		cargarComboBox();
    }
	
	@Listen("onUpload = #uploadImagen")
	public void onUpload$uploadImagen(UploadEvent e)
	{
		media = e.getMedia();
	    imagen.setContent((org.zkoss.image.Image) media);
	    uploadImagen.setVisible(false);
	}

	@Listen("onClick = #guardar")
	public void guargar(){
		String dir="";
		 if(media instanceof org.zkoss.image.Image) {
	             try {
	            	 String carpeta = "C:\\Users\\usuario\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
	            	 FileOutputStream fileOutputStream=new FileOutputStream(carpeta+"\\"+media.getName());
	                 dir="Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"+media.getName();
	            	 fileOutputStream.write(media.getByteData());
	            	 
	            	 if(tipo_preferencia.getSelectedItem()==null|| descripcion.getText().equals("")){
		         			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         		}else{
		         			preferencia = new Preferencia(preferenciaDao.TotalRegistros().toString(),descripcion.getText(),"Activo",tipo_preferencia1.get(tipo_preferencia.getSelectedIndex()).getCodigo(),dir);
		         			preferenciaDao.agregarPreferencia(preferencia);
		         			for(int i=0;i<ServiciosSeleccionados.size();i++){
								String codigoDetalle = preferenciaDao.TotalRegistrosPreferenciaServicio();
								preferenciaDao.agregarPreferenciaServicio(ServiciosSeleccionados.get(i).getCodigo(),preferencia.getCodigo(),"Activo",codigoDetalle);
							}
		         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         			limpiarCampos();
		         			cargarTabla();
		         		};
		         			 
		         		fileOutputStream.close();
		         		
	            	
	            	
	             }catch (Exception ex) {
	                 Logger.getLogger(ControladorPreferencia.class.getName()).log(Level.SEVERE, null, ex);
	             }
	           	

		}
		
	}
	
	@Listen("onPreferenciaDelete = #listaPreferencia")
	public void onPreferenciaDelete$listaPreferencia(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 preferencia = (Preferencia)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	preferenciaDao.modificarStatus(preferencia.getCodigo());
	            	//cargarTabla();
	            	cargarTabla();
	            						   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta preferencia?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	    
	  
	public void limpiarCampos()
	{
		
	    descripcion.setText("");
	    tipo_preferencia.clearSelection();
		imagen.setContent((org.zkoss.image.Image) null);
	}
	
	
	@Listen("onClick = #cancelar")
public void cancelar(){
	   descripcion.setText("");
		tipo_preferencia.clearSelection();

	}
	


}