package controlador;

import java.io.FileOutputStream;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Textarea;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.event.UploadEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Button;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modelo.Noticia;
import modelo.Organizacion;
import modelo.Promocion;
import modelo.Slider;
import modelo.Usuario;
import modeloDAO.MaestricoDAO;
import modeloDAO.NoticiaDAO;

public class ControladorNoticia extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox codigo;
	@Wire
	private Textbox titulo;
	@Wire
	private Textbox contenido;
	@Wire
	private Listbox tipo_noticia;
	@Wire
	private Listbox listaNoticias;
	@Wire
	private Window registrarDatos;
	
	@Wire
	private Image imagen;
	
	@Wire
	private Media media;
	
	@Wire
	private Button uploadImagen;
	
	
	
	NoticiaDAO ndao = new NoticiaDAO();
	MaestricoDAO dao = new MaestricoDAO();
	Noticia n = new Noticia();
	Maestrico maestrico = new Maestrico();
	ListModelList<Maestrico> tipoNoticia;
	
	List<Maestrico> tipo_noticia1 = dao.listarMaestrico("tb_tipo_noticia") ;
	
	@Listen("onCreate = #registrarDatos")
	public void cargarOrg(CreateEvent event){
		
    		
    		cargarComboBox();
		
		
    }
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarNoticia.html");
	}
	
	@Listen("onCreate = #listaNoticias")
	public void noticia(CreateEvent event)
    {
		
		this.cargarTabla();
    }
	
	public void cargarTabla(){
		
		List<Noticia> noticias = ndao.listaNoticia();
		listaNoticias.setModel(new ListModelList<Noticia>(noticias));
	
	
	}
	
	public void cargarComboBox(){
	
		
		/*for(int i=0;i<tipo_noticia.getItemCount();i++){
			if(tipo_noticia.getItemAtIndex(i).getLabel().equalsIgnoreCase(tipoNot)){
				 tipo_noticia.setSelectedIndex(i);
				 
			}
		}*/
		tipo_noticia.setModel(new ListModelList<Maestrico>(tipo_noticia1));
		}
	

	
	@Listen("onCreate = #tipo_noticia")
	public void tipoOrg(CreateEvent event)
    {	
		List<Maestrico> descripcion = ndao.tipo_noticia();
		
		tipoNoticia = new ListModelList<Maestrico>(descripcion);
		
		tipo_noticia.setModel(tipoNoticia);
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
	            	 String carpeta = "C:\\Users\\Andres\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
	            	 FileOutputStream fileOutputStream=new FileOutputStream(carpeta+"\\"+media.getName());
	                 dir="Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"+media.getName();
	            	 fileOutputStream.write(media.getByteData());
		             if(tipo_noticia.getSelectedItem()==null|| titulo.getText().isEmpty()){
		         			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         		}else{
		         			Date fecha = new Date(System.currentTimeMillis());
		         			n = new Noticia(ndao.TotalRegistros().toString(),"Activo","00001",fecha,tipo_noticia1.get(tipo_noticia.getSelectedIndex()).getCodigo(),titulo.getText(),contenido.getText(),dir);
		         			ndao.agregarNoticia(n);
		         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         			limpiarCampos();
		         			cargarTabla();
		         		};
		         			 
		         		fileOutputStream.close();
		         		
	            	
	            	
	             }catch (Exception ex) {
	                 Logger.getLogger(ControladorNoticia.class.getName()).log(Level.SEVERE, null, ex);
	             }
	             
		
	             	

		}
		
	}
	
	public void limpiarCampos()
	{
		titulo.setText("");
	    contenido.setText("");
	    tipo_noticia.clearSelection();
		imagen.setContent((org.zkoss.image.Image) null);
	}
	
	
	@Listen("onClick = #cancelar")
public void cancelar(){
	    titulo.setText("");
		tipo_noticia.clearSelection();
		imagen.setContent((org.zkoss.image.Image) null);

	}
	@Listen("onNoticiaDelete = #listaNoticias")
	public void onIncidenciaDelete$incidencias(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 n = (Noticia)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	ndao.modificarStatus(n.getCodigo());
	            	//pdao.modificarStatus(p.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta Promoción?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	

	}

