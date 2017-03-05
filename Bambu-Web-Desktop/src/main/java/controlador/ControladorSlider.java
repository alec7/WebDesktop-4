package controlador;

import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.StringBufferInputStream;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.imageio.ImageIO;

import org.apache.commons.codec.binary.Base64;
import org.postgresql.core.Utils;
import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.Sessions;
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
import org.zkoss.zul.Messagebox.ClickEvent;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Maestrico;
import modelo.Slider;
import modeloDAO.SliderDAO;
import sun.misc.BASE64Encoder;
import sun.misc.BASE64Decoder;
import org.apache.commons.codec.binary.Base64;




public class ControladorSlider extends  SelectorComposer<Component>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox titulo;
	
	@Wire
	private Textbox subtitulo;
	
	@Wire
	private Textbox contenido;
	
	@Wire
	Component selectedSliderBlock;
	
	@Wire
	private Image imagen;
	
	@Wire
	private Media media;
	
	@Wire
	private Listbox listaSlider;
	@Wire
	private Button registrar;

	
    
    
	SliderDAO dao = new SliderDAO();
	Slider slider = new Slider();
	
	@Listen("onCreate = #listaSlider")
	public void acuerdo(CreateEvent event)
    {
		Messagebox.show("getContextPath(): " + Sessions.getCurrent().getWebApp().getNativeContext());
		this.cargarTabla();
    }
	
	public void cargarTabla(){
//		
		List<Slider> imagenesSlider = dao.obtenerImagenSlider();
		listaSlider.setModel(new ListModelList<Slider>(imagenesSlider));
//	
	}
  
	
	
	
	
	
	
	@SuppressWarnings("restriction")
	public static String encodeToString(BufferedImage image, String type) {
        String imageString = null;
        ByteArrayOutputStream bos = new ByteArrayOutputStream();

        try {
            ImageIO.write(image, type, bos);
            byte[] imageBytes = bos.toByteArray();

            BASE64Encoder encoder = new BASE64Encoder();
            imageString = encoder.encode(imageBytes);

            bos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return imageString;
    }
	
	@Listen("onUpload = #uploadImagen")
	public void onUpload$uploadImagen(UploadEvent e)
	{
		media = e.getMedia();
	    imagen.setContent((org.zkoss.image.Image) media);
	
	}
	
	
	
	public static byte [] ImageToByte(File file) throws FileNotFoundException{
        FileInputStream fis = new FileInputStream(file);
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        byte[] buf = new byte[1024];
        try {
            for (int readNum; (readNum = fis.read(buf)) != -1;) {
                bos.write(buf, 0, readNum);      
                System.out.println("read " + readNum + " bytes,");
            }
        } catch (IOException ex) {
        }
        byte[] bytes = bos.toByteArray();
     
     return bytes; 
    }
	
	@Listen("onClick = #registrar")
	public void onClick$registrar() throws IOException 
	{
		String dir="";
		 if(media instanceof org.zkoss.image.Image) {
	             try {
	                
	            	 String carpeta = "C:\\Users\\Andres\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
	            	 FileOutputStream fileOutputStream=new FileOutputStream(carpeta+"\\"+media.getName());
	                 dir="Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"+media.getName();
	            	 fileOutputStream.write(media.getByteData());
	            	 
	            	 String codigo = dao.TotalRegistros();
	            	 Messagebox.show(codigo);
	            	 
	            	 slider = new Slider(codigo,titulo.getText(),contenido.getText(),"Activo", subtitulo.getText(),dir);
	                 dao.agregarImagen(slider);
	                
	                 fileOutputStream.close();
	                 titulo.setText("");
	                 subtitulo.setText("");
	                 contenido.setText("");
	                 imagen.setSrc("");
	                 
	               
	                 
	                  
	    } catch (Exception ex) {
	                 Logger.getLogger(ControladorSlider.class.getName()).log(Level.SEVERE, null, ex);
	             }
	             
	             
		
		// slider = new Slider("0001",titulo.getText(),"asa","Activo", subtitulo.getText(),encodedBytes);
         //dao.agregarImagen(slider);
	         }
		 }
	
	@Listen("onSliderDelete = #slider")
	public void onAcuerdoDelete$acuerdos(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		slider = (Slider)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	dao.eliminarSlider(slider.getCodigo());
	            	 cargarTabla();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta imagen?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
}
	
	
	

	
	
	

