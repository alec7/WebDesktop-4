package controlador;


import java.io.FileOutputStream;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.zkoss.util.media.Media;
import org.zkoss.zhtml.Messagebox;
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
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.impl.MeshElement;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Noticia;
import modelo.Paquete;
import modelo.Promocion;
import modeloDAO.PaqueteDAO;
import modeloDAO.PromocionDAO;

public class ControladorPromocion extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox nombre;
	@Wire
	private Datebox fechaInicio;
	@Wire
	private Datebox fechaFin;
	@Wire
	private Listbox paquete;
	@Wire
	private Textbox eslogan;
	@Wire
	private Listbox listaPromociones;
	@Wire
	private Media media;
	@Wire
	private Image imagen;
	@Wire
	private Button uploadImagen;
	
	
	PromocionDAO pdao = new PromocionDAO();
	PaqueteDAO paqdao = new PaqueteDAO();
	Promocion p;
	ListModelList<Promocion> promocionListModel;
	ListModelList<Paquete> paqueteModel;
	List<Promocion> listPromocion= new ArrayList<Promocion>();
	
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/registrarPromocion.html");
	}
	
	@Listen("onCreate = #listaPromociones")
	public void notifi(CreateEvent event)
    {
		this.cargarTabla();
    }
	
	public void cargarTabla(){
//		String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//		Messagebox.show(codigoIncidencia);
		listPromocion = pdao.listarPromocion();
		//Messagebox.show(String.valueOf(listNotificacion.size()));
		formatoPromociones();
		DateFormat inputFormat = new SimpleDateFormat("yyyy-MM-dd");
		DateFormat outputFormat = new SimpleDateFormat("dd/MM/yyyy");
		for(int i=0; i<listPromocion.size();i++){
			String inputDateStr=listPromocion.get(i).getFecha_inicio();
			String inputDateStr1=listPromocion.get(i).getFecha_fin();
			Date date=null;
			Date date1=null;
			try {
				date = inputFormat.parse(inputDateStr);
				date1 = inputFormat.parse(inputDateStr1);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			String outputDateStr = outputFormat.format(date);
			String outputDateStr1 = outputFormat.format(date1);
			listPromocion.get(i).setFecha_inicio(outputDateStr);
			listPromocion.get(i).setFecha_fin(outputDateStr1);
		}
		
		promocionListModel = new ListModelList<Promocion>(listPromocion);
		listaPromociones.setModel(promocionListModel);
		
	}
	
	
	public void formatoPromociones(){
		
		for(int i=0; i<listPromocion.size();i++){
			String descp =pdao.buscarDescpPaquete(listPromocion.get(i).getCodigo_paquete());
			listPromocion.get(i).setCodigo_paquete(descp);
		}
		
	}
	
	@Listen("onCreate = #paquete")
	public void roles(CreateEvent event)
    {	
		List<Paquete> descripcion = paqdao.listarPaquetesDifusion();
		
		paqueteModel = new ListModelList<Paquete>(descripcion);
		paquete.setModel(paqueteModel);
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
	            	 if(paquete.getSelectedItem()!=null){
	         			Date fecha1 = fechaInicio.getValue();
	         			Date fecha2 =  fechaFin.getValue();
	         			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
	         			String fechaInicio = sdf.format(fecha1);
	         			String fechaFin = sdf.format(fecha2);
	         			Promocion prom = new Promocion(pdao.TotalRegistros(), nombre.getText(), paqdao.buscarCodigoPaquete(paquete.getSelectedItem().getLabel()), "Activo", fechaInicio, fechaFin,eslogan.getText(),dir);
	         			pdao.registrarPromocion(prom);
	         			//ACOMODAR QUE GUARDE LA IMAGEN EN EL SLIDER
	         			pdao.registrarPromocionSlider(prom);
	         			Messagebox.show("Promoción Registrada Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
	         			cargarTabla();
	         			cancelar();
	         		}
	         		else{
	         			Messagebox.show("Debe Seleccionar un Paquete", "Información", Messagebox.OK, Messagebox.INFORMATION);
	         		}
		         			 
		         		fileOutputStream.close();
		         		
	            	
	            	
	             }catch (Exception ex) {
	                 Logger.getLogger(ControladorNoticia.class.getName()).log(Level.SEVERE, null, ex);
	             }
		}
		
	}
	
	@Listen("onClick = #cancelar")
	public void cancelar(){
		nombre.setText("");
		fechaInicio.setText("");
		fechaFin.setText("");
		paquete.clearSelection();
		eslogan.setText("");
		imagen.setContent((org.zkoss.image.Image) null);
	}
	
	@Listen("onPromocionDelete = #listaPromociones")
	public void onIncidenciaDelete$incidencias(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 p = (Promocion)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	pdao.modificarStatus(p.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta Promoción?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	

}
