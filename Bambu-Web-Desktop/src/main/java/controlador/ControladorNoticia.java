package controlador;

import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Formatter;
import java.util.GregorianCalendar;
import java.util.List;

import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Textarea;
import org.zkoss.zk.ui.Component;
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
import modelo.Usuario;
import modeloDAO.NoticiaDAO;

public class ControladorNoticia extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox codigo;
	@Wire
	private Textbox titulo;
	@Wire
	private Listbox tipo_noticia;
	@Wire
	private Listbox listaObjtivos;
	@Wire
	private Window registrarDatos;
	@Wire
	private Image image2;
	@Wire
	private Image imgCerrarImg2;
	@Wire
	private Button  uploadBtnImg2;
	
	NoticiaDAO ndao = new NoticiaDAO();
	Noticia n;
	ListModelList<Maestrico> tipoNoticia;
	String tipoNot = "";
	
	
	@Listen("onCreate = #registrarDatos")
	public void cargarOrg(CreateEvent event){
		
    		//cargarTablaObjetivos();
    		cargarComboBox();
		
		
    }
	
	public void cargarComboBox(){
	
		
	
		
		for(int i=0;i<tipo_noticia.getItemCount();i++){
			if(tipo_noticia.getItemAtIndex(i).getLabel().equalsIgnoreCase(tipoNot)){
				tipo_noticia.setSelectedIndex(i);
				
			}
		}
	}
	
	
	@Listen("onCreate = #tipo_noticia")
	public void tipoOrg(CreateEvent event)
    {	
		List<Maestrico> descripcion = ndao.tipo_noticia();
		
		tipoNoticia = new ListModelList<Maestrico>(descripcion);
		
		tipo_noticia.setModel(tipoNoticia);
		cargarComboBox();
    }
	
	
	
	
	
	
	/*@Listen("onCreate = #listaObjtivos")
	public void cargarObjetivos(CreateEvent event)
    {	
		
		cargarTablaObjetivos();
		
    }*/
	
	
	
	/*@Listen("onClick = #agregarObj")
	public void agregarObj(){
		String codigo = objdao.TotalRegistros();
		
		listaObjetivos.add(new Objetivo(codigo, objetivos.getText(),tipo_objetivo.getSelectedItem().getLabel() , "Activo"));
		cargarTablaObjetivos();
		
	}
	*/

	
	@Listen("onClick = #guardar")
	public void guargar(){
		if(tipo_noticia.getSelectedItem()==null|| titulo.getText().isEmpty()){
			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}else{
			Date fecha = new Date(System.currentTimeMillis());
			n = new Noticia(ndao.TotalRegistros().toString(),titulo.getText(),"Activo",uploadBtnImg2.getImage(),"codigo_sistema",tipo_noticia.getSelectedItem().toString(),fecha);
			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
			//cancelar();
			

		}
		
	}
	@Listen("onClick = #cancelar")
public void cancelar(){
	    titulo.setText("");
		tipo_noticia.clearSelection();
//		direccion.setText("");
//		estado.clearSelection();
//		ciudad.clearSelection();
//		telefono.setText("");
//		correo.setText("");
//		mision.setValue("");
//		vision.setValue("");
//		objetivos.setValue("");
//		red_social.setText("");
//		tipo_objetivo.clearSelection();
//		tipo_noticia.clearSelection();
//		tipo_red_social.clearSelection();
	}
	
	/*@Listen("onUpload = #uploadBtnImg2")
	public void imagen(UploadEvent e) throws Exception
	{
	 media2 = e.getMedia();
	     
        //media.getByteData()
        //media.getStreamData()
        //media.getReaderData()
        if(media2 instanceof org.zkoss.image.Image) {
        	image2.setVisible(true);
        	image2.setWidth("100px");
        	image2.setHeight("100px");
        	image2.setContent((org.zkoss.image.Image) media2);
        	imgCerrarImg2.setVisible(true);
        	
        	
        }
        uploadBtnImg2.setVisible(false);
	}*/
	/*
	@Listen("onClick = #imgCerrarImg2")
	public void cerrarImagen()
	{
		
	            image2.setVisible(false);
	            
	        
	        uploadBtnImg2.setVisible(true);
	        imgCerrarImg2.setVisible(false);
	}
	
	@Listen("onObjetivoDelete = #listaObjtivos")
	public void onObjetivoDelete$listaOjtivos(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 obj = (Objetivo)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	objdao.modificarStatus(obj.getCodigo());
	            	cargarTablaObjetivos();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta red social?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
	/*@Listen("onRedesDelete = #listaRedes")
	public void onRedesDelete$listaRedes(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 rs = (RedSocial)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	reddao.modificarStatus(rs.getCodigo());
	            	cargarTablaRedesSociales();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta red social?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}*/
/*	public void cargarTablaObjetivos(){
		//listaObjetivos = objdao.listaObjetivo();
		listObjt = new ListModelList<Objetivo>(listaObjetivos);
		listaObjtivos.setModel(listObjt);
	}
	
	/*public void cargarTablaRedesSociales(){
		//listaRedSocial = reddao.listaRedesSociales();
		listRed = new ListModelList<RedSocial>(listaRedSocial);
		listaRedes.setModel(listRed);
	}*/

}
