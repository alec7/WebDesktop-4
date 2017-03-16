package controlador;

import java.io.FileOutputStream;

import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.Intbox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modelo.Servicio;
import modeloDAO.MaestricoDAO;
import modeloDAO.ServicioDAO;

public class ControladorRegistrarServicio extends SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	@Wire
	private Listbox tipo_servicio;
	@Wire
	private Textbox titulo;
	@Wire
	private Textbox descripcion;
	@Wire
	private Doublebox precio;
	@Wire
	private Intbox duracion;
	@Wire
	private Image imagen;
	@Wire
	private Media media;
	@Wire
	private Button uploadImagen;
	@Wire
	private Listbox listaServicios;
	
	
	
	ServicioDAO servDao = new  ServicioDAO();
	MaestricoDAO dao = new MaestricoDAO();
	Servicio servicio = new Servicio();
	
	List<Maestrico> tipoServicio = dao.listarMaestrico("tb_tipo_servicio");
	
	@Listen("onCreate = #registrarDatos")
	public void cargarOrg(CreateEvent event){
	tipo_servicio.setModel(new ListModelList<Maestrico>(tipoServicio));
	
	cargarTabla();
	}
	
	public void cargarTabla(){
		List<Servicio> servicios = servDao.listaServicios();
		listaServicios.setModel(new ListModelList<Servicio>(servicios));
	}
	
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarServicio.html");
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
	                
	            	 String carpeta = "C:\\Users\\Andres\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
	            	 FileOutputStream fileOutputStream=new FileOutputStream(carpeta+"\\"+media.getName());
	                 dir="Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"+media.getName();
	            	 fileOutputStream.write(media.getByteData());

		         		

//		             if(!tipo_servicio.getSelectedItem().getLabel().equals("")|| !titulo.getText().equals("")){
//		         			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
//		         		}else{
		         			//Date fecha = new Date(System.currentTimeMillis());
		         			servicio = new Servicio(servDao.TotalRegistros(),descripcion.getText(),tipoServicio.get(tipo_servicio.getSelectedIndex()).getCodigo(),"J298137738",dir,"Activo",precio.getValue(),titulo.getText(),duracion.getValue());
		         			servDao.agregarServicio(servicio);
		         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         			limpiarCampos();
		         			cargarTabla();
//		         		};
//		         			 
		         		fileOutputStream.close();
		         		
	            	
	            	
	             }catch (Exception ex) {
	                 Logger.getLogger(ControladorNoticia.class.getName()).log(Level.SEVERE, null, ex);
	             }
	             
		
	             	

		}
		
	}
	
	public void limpiarCampos()
	{
		titulo.setText("");
	    descripcion.setText("");
		precio.setText("");
		duracion.setText("");
		imagen.setContent((org.zkoss.image.Image) null);
	}
	
	
	
	@Listen("onDelete = #servicio")
	public void eliminarServicio(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		servicio = (Servicio)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	servDao.eliminarServicio(servicio.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta imagen?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
	
	

}
