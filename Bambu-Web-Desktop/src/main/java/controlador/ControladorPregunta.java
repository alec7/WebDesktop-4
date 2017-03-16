package controlador;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import modelo.Maestrico;
import modelo.Preferencia;
import modelo.Pregunta;
import modelo.Servicio;
import modeloDAO.MaestricoDAO;
import modeloDAO.PreferenciaDAO;
import modeloDAO.PreguntaDAO;
import modeloDAO.ServicioDAO;

import org.zkoss.util.media.Media;
import org.zkoss.zk.ui.Component;
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

public class ControladorPregunta extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Listbox tipo_pregunta;
	@Wire
	private Listbox listaPregunta;
	@Wire
	private Textbox descripcion;
	@Wire
	private Listbox listaTodasRespuestas;
	@Wire
	private Listbox listaRespuestasSeleccionadas;
	

	Pregunta pregunta = new Pregunta();
	PreguntaDAO preguntaDao = new PreguntaDAO();
	MaestricoDAO dao = new MaestricoDAO();
	ListModelList<Maestrico> tipoPregunta;
	List<Pregunta> listPregunta = new ArrayList<Pregunta>();
	ListModelList<Pregunta> preguntaListModel;
	//ARREGLO QUE TIENE TODOS LAS RESPUESTAS DE LA BD
	List<Maestrico> todasRespuestas = dao.listarMaestrico("tb_respuesta");
	//ARREGLO QUE TIENE TODOS LOS SERVICIOS QUE SELECCIONA EL USUARIO (ARREGLO LADO DERECHO)
	List<Maestrico> RespuestasSeleccionadas = new ArrayList<Maestrico>();
	//ARREGLO MODELO PARA CARGAR LA VISTA
	ListModelList<Maestrico> respuestaSeleccionadoModel;
	//ARREGLO MODELO DE TODOS LOS SERVICIOS DE LA BD
	ListModelList<Maestrico> todosRespuestasModel;
	

	List<Maestrico> tipo_pregunta1 = dao.listarMaestrico("tb_tipo_pregunta");
	
	@Listen("onCreate = #pregunta")
	public void cargarOrg(CreateEvent event){
		
    		
    		cargarComboBox();
	    }
	
	@Listen("onCreate = #listaPregunta")
	public void preferencia(CreateEvent event)
    {
		
		this.cargarTabla();
		cargarTodasRespuestas();
    }
	
	public void cargarTabla(){
		
		List<Pregunta> preguntas = preguntaDao.listaPregunta();
		listaPregunta.setModel(new ListModelList<Pregunta>(preguntas));
	
	
	}
	
	public void cargarComboBox(){
	
		
		/*for(int i=0;i<tipo_noticia.getItemCount();i++){
			if(tipo_noticia.getItemAtIndex(i).getLabel().equalsIgnoreCase(tipoNot)){
				 tipo_noticia.setSelectedIndex(i);
				 
			}
		}*/
		tipo_pregunta.setModel(new ListModelList<Maestrico>(tipo_pregunta1));
		}
	
	
	@Listen("onClick = #agregar")
	public void doAgregar(){
		if(listaTodasRespuestas.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe serleccionar un servicio", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			Maestrico r = null;
			//SACA EL SERVICIO QUE ESTA SELECCIONADO Y LO PASA AL LADO DERECHO
			r = listaTodasRespuestas.getSelectedItem().getValue();
			//LO AGREGA A LA LISTA DE LOS SELECCIONADOS
			RespuestasSeleccionadas.add(r);
			//ACTUALIZA LA TABLA DE LA VISTA
			respuestaSeleccionadoModel = new ListModelList<Maestrico>(RespuestasSeleccionadas);
			listaRespuestasSeleccionadas.setModel(respuestaSeleccionadoModel);
			//LO REMUEVE DE LA LISTA DE LADO IZQUIERDO
			todasRespuestas.remove(listaTodasRespuestas.getSelectedIndex());
			//ACTUALIZA LA TABLA DE LA VISTA
			listaTodasRespuestas.setModel(new ListModelList<Maestrico>(todasRespuestas));
		}
	}
	
	@Listen("onClick = #quitar")
	public void doQuitarMartes()
	{
		
		if(listaRespuestasSeleccionadas.getSelectedIndex() == -1)
		{
			Messagebox.show("Debe seleccionar un servicio ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			Maestrico r = null;
			r=listaRespuestasSeleccionadas.getSelectedItem().getValue();
			todasRespuestas.add(r);
			
			respuestaSeleccionadoModel = new ListModelList<Maestrico>(todasRespuestas);
			listaTodasRespuestas.setModel(respuestaSeleccionadoModel);
			RespuestasSeleccionadas.remove(listaRespuestasSeleccionadas.getSelectedIndex());
			listaRespuestasSeleccionadas.setModel(new ListModelList<Maestrico>(RespuestasSeleccionadas));
		}
	}
	//METODO QUE CARGA TODOS LOS SERVICIOS EN LA LISTA IZQUIERA
	public void cargarTodasRespuestas(){
		todasRespuestas = dao.listarMaestrico("tb_respuesta");
		respuestaSeleccionadoModel = new ListModelList<Maestrico>(todasRespuestas);
		listaTodasRespuestas.setModel(respuestaSeleccionadoModel);
	}
	
	@Listen("onCreate = #tipo_preferencia")
	public void tipoOrg(CreateEvent event)
    {	
		List<Maestrico> descripcion = preguntaDao.tipo_pregunta();
		
		tipoPregunta = new ListModelList<Maestrico>(descripcion);
		
		tipo_pregunta.setModel(tipoPregunta);
		cargarComboBox();
    }
	
	@Listen("onClick = #guardar")
	public void guargar(){
			            	 
	            	 if(tipo_pregunta.getSelectedItem()==null|| descripcion.getText().equals("")){
		         			Messagebox.show("Debe llenar Todos los campos", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         		}else{
		         			pregunta = new Pregunta(preguntaDao.TotalRegistros().toString(),descripcion.getText(),"Activo",tipo_pregunta1.get(tipo_pregunta.getSelectedIndex()).getCodigo());
		         			preguntaDao.agregarPregunta(pregunta);
		         			for(int i=0;i<RespuestasSeleccionadas.size();i++){
								String codigoDetalle = preguntaDao.TotalRegistrosFormulario();
								preguntaDao.agregarFormulario(RespuestasSeleccionadas.get(i).getCodigo(),pregunta.getCodigo(),"Activo",codigoDetalle);
							}
		         			Messagebox.show("Datos Guardados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		         			limpiarCampos();
		         			cargarTabla();
		     	     		}	
	     	}
	
	@Listen("onPreguntaDelete = #listaPregunta")
	public void onPreferenciaDelete$listaPreferencia(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 pregunta = (Pregunta)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	preguntaDao.modificarStatus(pregunta.getCodigo());
	            	//cargarTabla();
	            	cargarTabla();
	            						   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar esta pregunta?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	    
	  
	public void limpiarCampos()
	{
		
	    descripcion.setText("");
	    tipo_pregunta.clearSelection();
		
	}
	
	
	@Listen("onClick = #cancelar")
public void cancelar(){
	   descripcion.setText("");
		tipo_pregunta.clearSelection();

	}
	


}