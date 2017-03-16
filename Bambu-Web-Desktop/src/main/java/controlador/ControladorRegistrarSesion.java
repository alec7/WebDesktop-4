package controlador;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;
import modelo.Cliente;
import modelo.DetalleAvance;
import modelo.IndicadorServicio;
import modelo.Sesion;
import modeloDAO.ClienteDAO;
import modeloDAO.SesionDAO;

public class ControladorRegistrarSesion extends  GenericForwardComposer<Window>{

	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Listbox listaSesiones;
	@Wire
	private Div indicadores;
	
	private String codigoServicio;
	
	boolean seleccionServicio = false;
	

	ClienteDAO clienteDao = new ClienteDAO();
	Cliente cliente = new Cliente();
	
	SesionDAO sesionDao = new SesionDAO();
	Sesion sesion = new Sesion();
	
	IndicadorServicio indicador = new IndicadorServicio();
	DetalleAvance detalle = new DetalleAvance();
	ArrayList<IndicadorServicio> indicadoresTratamientos = new ArrayList<IndicadorServicio>();
	final ArrayList<DetalleAvance> detallesAvances = new ArrayList<DetalleAvance>();
	ArrayList<Sesion> indicadorServ ;
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	
	
	
	public void onClick$BuscarCliente()
	{
		
		cliente = clienteDao.BuscarClienteXCedula(cedula.getText());
		if(cliente!=null)
		{
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		}
		else{
			Messagebox.show("cliente no encontrado");
		}
		String cedulaCliente = cedula.getText();
		
		List<Sesion> sesiones = sesionDao.ObtenerSesion(cedulaCliente);
		listaSesiones.setModel(new ListModelList<Sesion>(sesiones));
		
	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public void onSelect$listaSesiones()
	{
		final Sesion  selected  = listaSesiones.getSelectedItem().getValue();
		codigoServicio = selected.getCodigoServicio();
		sesionDao.NumeroSesion(cedula.getText(), codigoServicio);
		final int numeroSesion = sesionDao.NumeroSesion(cedula.getText(), codigoServicio);
		
		    indicadoresTratamientos = sesionDao.ObtenerIndicadores(codigoServicio);
			 for (int i=0; i<indicadoresTratamientos.size(); i++)
			    {
			    	indicador = indicadoresTratamientos.get(i);
			    	final String codigoIndicador = indicador.getCodigo();
			    	indicadores.setClass("row");
			    	Div div = new Div();
			    	div.setClass("form-group");
			    	div.setParent(indicadores);
			    	Label label = new Label();
			    	label.setClass("col-lg-3 control-label");
			    	label.setValue(indicador.getDescripcion());
			    	label.setParent(div);
			    	Div div1 = new Div();
			    	div1.setClass("col-lg-3");
			        final Textbox textbox = new Textbox();
			        textbox.setClass("form-control required");
			    	textbox.setParent(div1);
			    	 
			    	div1.setParent(div);
			    	textbox.addEventListener(Events.ON_CHANGE, new EventListener(){
			    		
			        	@Override
						public void onEvent(Event event) throws Exception {
			        		
			        		Date today = Calendar.getInstance().getTime();
			        		final String valor = textbox.getText();
			        		detalle = new DetalleAvance(null, selected.getCodigo(),codigoIndicador, numeroSesion,valor, today);
			        		detallesAvances.add(detalle);
			        	}
			        	
					 });
			    }
			 indicadoresTratamientos.clear();
			
	}
	
	
	
	

	
	
	public void onClick$guardar()
	{
		Messagebox.show(codigoServicio);
		sesionDao.actualizarSesion(codigoServicio);
		
		for (int i=0; i<detallesAvances.size(); i++)
		{
			detallesAvances.get(i).setCodigo(sesionDao.TotalRegistros());
			detalle = detallesAvances.get(i);
			sesionDao.agregarDetalleAvance(detalle);
		}
		
		
	}
	
	
	
	
	
	
	
}
