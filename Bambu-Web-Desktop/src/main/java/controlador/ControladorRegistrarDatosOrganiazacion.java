package controlador;

import java.io.FileOutputStream;
import java.util.Formatter;
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
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modelo.Objetivo;
import modelo.Organizacion;
import modelo.RedSocial;
import modelo.Usuario;
import modeloDAO.ObjetivoDAO;
import modeloDAO.OrganizacionDAO;
import modeloDAO.RedSocialDAO;

public class ControladorRegistrarDatosOrganiazacion extends SelectorComposer<Component> {
	
	private static final long serialVersionUID = 1L;
	@Wire
	private Textbox rif;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox direccion;
	@Wire
	private Combobox estado;
	@Wire
	private Combobox ciudad;
	@Wire
	private Textbox telefono;
	@Wire
	private Textbox correo;
	@Wire
	private Textbox mision;
	@Wire
	private Textbox vision;
	@Wire
	private Textbox objetivos;
	@Wire
	private Combobox tipo_objetivo;
	@Wire
	private Combobox tipo_organizacion;
	@Wire
	private Combobox tipo_red_social;
	@Wire
	private Listbox listaObjtivos;
	@Wire
	private Listbox listaRedes;
	@Wire
	private Textbox red_social;
	@Wire
	private Window registrarDatos;
	@Wire
	private Image imagen;
	
	@Wire
	private Media media;
	
	@Wire
	private Button uploadImagen;
	
	OrganizacionDAO odao = new OrganizacionDAO();
	RedSocialDAO reddao = new RedSocialDAO();
	ObjetivoDAO objdao = new ObjetivoDAO();
	Organizacion o = odao.buscarRegistro();;
	Objetivo obj;
	RedSocial rs;
	ListModelList<Objetivo> listObjt;
	ListModelList<RedSocial> listRed;
	ListModelList<Maestrico> tipoOrganizacion;
	ListModelList<Maestrico> tipoObjetivo;
	ListModelList<Maestrico> tipoRed;
	List<Objetivo> listaObjetivos = objdao.listaObjetivo();
	List<RedSocial> listaRedSocial = reddao.listaRedesSociales();
	String tipoOrg = "";
	List<Maestrico> tipo_orga = odao.tipo_organizacion();
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception{
		super.doAfterCompose(comp);
		tipoOrg();

	}
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/regOrganizacionDatosBasicos.html");
	}
	@Listen("onClick = #ayuda1")
	public void ayuda1(){
		Executions.sendRedirect("vista/ayudas/regOrganizacionFilosofia.html");
	}
	@Listen("onClick = #ayuda2")
	public void ayuda2(){
		Executions.sendRedirect("vista/ayudas/regOrganizacionRedesSociales.html");
	}
	@Listen("onCreate = #registrarDatos")
	public void cargarOrg(CreateEvent event){
		if(odao.buscarRegistro()!=null){
    		
    		rif.setText(o.getRif());
    		nombre.setText(o.getNombre());
    		direccion.setText(o.getDireccion());
    		telefono.setText(o.getTelefono());
    		correo.setText(o.getCorreo());
    		mision.setText(o.getMision());
    		vision.setText(o.getVision());
    		tipoOrg = odao.buscarTipoOrganizacionString(o.getTipo_organizacion());
    		rif.setDisabled(true);  
    		ciudad.setDisabled(true);
    		estado.setDisabled(true);
    		cargarTablaObjetivos();
    		cargarComboBox();
    		int posicion = 0;
    		
    		for (int i = 0; i < tipoOrganizacion.size(); i++) {
    			if (tipoOrganizacion.get(i).getCodigo().equals(o.getTipo_organizacion())) {
    				posicion = i;
    			}
    		}
    		tipo_organizacion.setSelectedIndex(posicion);
		}

		
    }
	
	public void cargarComboBox(){
		for(int i=0;i<estado.getItemCount();i++){
			if(estado.getItemAtIndex(i).getLabel().equalsIgnoreCase("Lara")){
				estado.setSelectedIndex(i);
			}
		}
		
		for(int i=0;i<ciudad.getItemCount();i++){
			if(ciudad.getItemAtIndex(i).getLabel().equalsIgnoreCase("Barquisimeto")){
				ciudad.setSelectedIndex(i);
			}
		}
		
	}
	
	
	public void tipoOrg()
    {	
		
		
		tipoOrganizacion = new ListModelList<Maestrico>(tipo_orga);
		
		tipo_organizacion.setModel(tipoOrganizacion);
		
		//cargarComboBox();
    }
//	@Listen("onSelect = combobox#tipo_organizacion")
//	public void opcionSeleccionada(){
//		List<Maestrico> descripcion = odao.tipo_organizacion();
//		int index = tipo_organizacion.getSelectedIndex();
//		tipoOrg = descripcion.get(index).getDescripcion();
//	}
	
	
	
	
	
	@Listen("onCreate = #listaObjtivos")
	public void cargarObjetivos(CreateEvent event)
    {	
		
		cargarTablaObjetivos();
		
    }
	@Listen("onCreate = #listaRedes")
	public void cargarRedes(CreateEvent event)
    {	
		
		cargarTablaRedesSociales();
    }
	
	@Listen("onCreate = #tipo_red_social")
	public void tipoRed(CreateEvent event)
    {	
		List<Maestrico> descripcion = reddao.tipoRedeSociales();
		tipoRed = new ListModelList<Maestrico>(descripcion);
		tipo_red_social.setModel(tipoRed);
		
    }
	@Listen("onClick = #agregarObj")
	public void agregarObj(){
		String codigo = objdao.TotalRegistros();
		
		listaObjetivos.add(new Objetivo(codigo, objetivos.getText(),tipo_objetivo.getSelectedItem().getLabel() , rif.getText(), "Activo"));
		cargarTablaObjetivos();
		
	}
	
	@Listen("onClick = #agregarRed")
	public void agregarRed(){
		String codigo = reddao.TotalRegistros();
		String codigoTipoRedSocial = reddao.buscarTipoRedSocial(tipo_red_social.getSelectedItem().getLabel());
		//rs = new RedSocial(reddao.TotalRegistros(), red_social.getText(), codigoTipoRedSocial , o.getRif(), "Activo");
		listaRedSocial.add(new RedSocial(codigo,red_social.getText(), codigoTipoRedSocial, rif.getText(), "Activo"));
				//Objetivo(String.valueOf(fmt), objetivos.getText(),tipo_objetivo.getSelectedItem().getLabel() , rif.getText(), "Activo"));
		cargarTablaRedesSociales();
		
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
            	 if(tipo_organizacion.getSelectedItem()==null|| estado.getSelectedItem()==null|| ciudad.getSelectedItem()==null){
         			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
         		}
         		else{
         			String codigoTipoOrg = odao.buscarTipoOrganizacion(tipo_organizacion.getSelectedItem().getLabel());
         			o = new Organizacion(rif.getText(), nombre.getText(),codigoTipoOrg , correo.getText(), direccion.getText(), telefono.getText(), mision.getValue(), vision.getValue(), "Activo",dir);
         			if(odao.buscarRegistro()!=null){
         				odao.actualizarOrganizacion(o);
         			}
         			else{
         				odao.agregarOganizacion(o);
         			}
         			
         			for(int i=0;i<listaObjetivos.size();i++){
         				obj = listaObjetivos.get(i);
         				objdao.agregarObjetivo(obj);
         			}		
         			for(int i=0;i<listaRedSocial.size();i++){
         				rs = listaRedSocial.get(i);
         				String codigo= reddao.buscarTipoRedSocial(rs.getTipo_red_social());
         				rs.setTipo_red_social(codigo);
         				reddao.agregarRedSocial(rs);
         			}
         			//obj = new Objetivo(objdao.TotalRegistros(), objetivos.getText(),tipo_objetivo.getSelectedItem().getLabel() , o.getRif(), "Activo");
         			
         			//String codigoTipoRedSocial = reddao.buscarTipoRedSocial(tipo_red_social.getSelectedItem().getLabel());
         			//rs = new RedSocial(reddao.TotalRegistros(), red_social.getText(), codigoTipoRedSocial , o.getRif(), "Activo");
         			//reddao.agregarRedSocial(rs); 
         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
         			//cancelar();
         		}
            	 
            	 fileOutputStream.close();
			 }
			 
			 catch (Exception ex) {
                 Logger.getLogger(ControladorNoticia.class.getName()).log(Level.SEVERE, null, ex);
             }
		 }
		
		
	}
//	@Listen("onClick = #cancelar")
//	public void cancelar(){
//		rif.setText("");
//		nombre.setText("");
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
//		tipo_organizacion.clearSelection();
//		tipo_red_social.clearSelection();
//	}
	@Listen("onUpload = #uploadImagen")
	public void onUpload$uploadImagen(UploadEvent e)
	{
		media = e.getMedia();
	    imagen.setContent((org.zkoss.image.Image) media);
	    uploadImagen.setVisible(false);
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
	
	@Listen("onRedesDelete = #listaRedes")
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
	}
	public void cargarTablaObjetivos(){
		//listaObjetivos = objdao.listaObjetivo();
		listObjt = new ListModelList<Objetivo>(listaObjetivos);
		listaObjtivos.setModel(listObjt);
	}
	
	public void cargarTablaRedesSociales(){
		//listaRedSocial = reddao.listaRedesSociales();
		for(int i=0; i<listaRedSocial.size();i++){
			String descripion = reddao.buscarTipoRedSocialString(listaRedSocial.get(i).getTipo_red_social());
			listaRedSocial.get(i).setTipo_red_social(descripion);
		}
		listRed = new ListModelList<RedSocial>(listaRedSocial);
		listaRedes.setModel(listRed);
	}

}
