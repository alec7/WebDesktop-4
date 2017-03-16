package controlador;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.event.ChangeEvent;

import modelo.Bloque;
import modelo.Cod_Sesiones;
import modelo.Incidencia;
import modelo.Maestrico;
import modelo.Objetivo;
import modelo.Paquete;
import modelo.Servicio;
import modeloDAO.MaestricoDAO;
import modeloDAO.PaqueteDAO;
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
import org.zkoss.zul.Doublebox;
import org.zkoss.zul.Image;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

public class ControladorPaquete extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox tipo_paquete;
	@Wire
	private Listbox listaTodosServicios;
	@Wire
	private Listbox listaServiciosSeleccionados;
	@Wire 
	private Listbox listaPaquete;
	@Wire
	private Textbox descripcion;
	@Wire
	private Textbox precio;
	@Wire
	private Image imagen;
	@Wire
	private Media media;
	@Wire
	private Button uploadImagen;

	PaqueteDAO paqueteDao = new PaqueteDAO();
	ServicioDAO servicioDao = new ServicioDAO();
	//ARREGLO QUE TIENE TODOS LOS SERVICIOS DE LA BD
	List<Servicio> todosServicios =new ArrayList<Servicio>();
	//ARREGLO QUE TIENE TODOS LOS SERVICIOS QUE SELECCIONA EL USUARIO (ARREGLO LADO DERECHO)
	List<Servicio> ServiciosSeleccionados = new ArrayList<Servicio>();
	//ARREGLO MODELO PARA CARGAR LA VISTA
	ListModelList<Servicio> servicioSeleccionadoModel;
	//ARREGLO MODELO DE TODOS LOS SERVICIOS DE LA BD
	ListModelList<Servicio> todosServiciosModel;
	MaestricoDAO dao = new MaestricoDAO();
	Paquete paquete = new Paquete();
	List<Paquete> listPaquete = new ArrayList<Paquete>();
	ListModelList<Paquete> paqueteListModel;
	List<Cod_Sesiones> ServiciosPaquetes = new ArrayList<Cod_Sesiones>();

	@Listen("onCreate = #listaPaquete")
	public void paquete(CreateEvent event) {

		this.cargarTabla();
		cargarTodosServicios();
	}
	
	public void cargarTabla() {
		List<Paquete> paquetes = paqueteDao.listarPaquetes();
		listaPaquete.setModel(new ListModelList<Paquete>(paquetes));

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
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/regPaquetes.html");
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
	@Listen("onUpload = #uploadImagen")
	public void onUpload$uploadImagen(UploadEvent e)
	{
		media = e.getMedia();
	    imagen.setContent((org.zkoss.image.Image) media);
	    uploadImagen.setVisible(false);
	}

	@Listen("onClick = #guardar")
	public void guargar() {
		String dir = "";
		if (media instanceof org.zkoss.image.Image) {
			try {
				String carpeta = "C:\\Users\\Andres\\Documents\\GitHub\\WebDesktop\\Bambu-Web-Desktop\\src\\main\\webapp\\WebContent\\assets\\imagenesSlider";
				FileOutputStream fileOutputStream = new FileOutputStream(
						carpeta + "\\" + media.getName());
				dir = "Bambu-Web-Desktop/WebContent/assets/imagenesSlider/"
						+ media.getName();
				fileOutputStream.write(media.getByteData());

				if (tipo_paquete.getSelectedItem() == null
						|| descripcion.getText().isEmpty()) {
					Messagebox.show("Debe llenar Todos los campos",
							"Información", Messagebox.OK,
							Messagebox.INFORMATION);
				} else {
					paquete = new Paquete(paqueteDao.TotalRegistros()
							.toString(), descripcion.getText(), tipo_paquete.getSelectedItem().getLabel(),
							"Activo", dir, Double.parseDouble(precio.getText()));
					paqueteDao.agregarPaquete(paquete);		
					for(int i=0;i<ServiciosSeleccionados.size();i++){
						String codigoDetalle = paqueteDao.TotalRegistrosDetallePaquete();
						paqueteDao.agregaralleDetPaquete(ServiciosPaquetes.get(i).getCodigoServicio(),paquete.getCodigo(),"Activo",codigoDetalle,ServiciosPaquetes.get(i).getSesiones());
					}
					Messagebox.show("Datos Guardados Exitosamente",
							"Información", Messagebox.OK,
							Messagebox.INFORMATION);
					limpiarCampos();
					cargarTabla();
					
					
				}
				;

				fileOutputStream.close();

			} catch (Exception ex) {
				Logger.getLogger(ControladorPaquete.class.getName()).log(
						Level.SEVERE, null, ex);
			}

		}

	}

	public void limpiarCampos() {

		descripcion.setText("");
		tipo_paquete.clearSelection();
		imagen.setContent((org.zkoss.image.Image) null);
	}

	@Listen("onClick = #cancelar")
	public void cancelar() {
		descripcion.setText("");
		tipo_paquete.clearSelection();

	}
	
	@Listen("onPaqueteDelete = #listaPaquete")
	public void onPaqueteDelete$listaPaquete(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 paquete = (Paquete)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	paqueteDao.modificarStatus(paquete.getCodigo());
	            	//cargarTabla();
	            	cargarTabla2();
	            						   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar este paquete?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}

	public void cargarTabla2(){
//		String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//		Messagebox.show(codigoIncidencia);
		listPaquete = paqueteDao.listarPaquetes();
		paqueteListModel = new ListModelList<Paquete>(listPaquete);
		listaPaquete.setModel(paqueteListModel);
		
	}
	@Listen("onSesiones = #listaServiciosSeleccionados")
	public void onIncidenciaDelete$incidencias(ForwardEvent evt){
		Doublebox db= (Doublebox)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)db.getParent().getParent();
			Double d = new Double(db.getValue());
			int sesiones = d.intValue();
			Cod_Sesiones dp = new Cod_Sesiones(db.getId(),sesiones);
			ServiciosPaquetes.add(dp);
	}
}
