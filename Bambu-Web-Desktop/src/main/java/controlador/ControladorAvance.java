package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Avance;
import modelo.Cliente;
import modelo.DetalleAvance;
import modelo.Noticia;
import modelo.Servicio;
import modelo.Sesion;
import modeloDAO.ClienteDAO;
import modeloDAO.ServicioDAO;
import modeloDAO.SesionDAO;

public class ControladorAvance extends  GenericForwardComposer<Window> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
    @Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Listbox listaAvances;
	@Wire
	private Combobox avances1;
	@Wire
	private Combobox servicios;
	
	private String cedulaCliente;
	
	private String codigoServicio;
	
	ServicioDAO dao = new ServicioDAO();
	SesionDAO sesionDao = new SesionDAO();
	Servicio servicio = new Servicio();
	Avance avance = new Avance();
	
	ClienteDAO clienteDao = new ClienteDAO();
	Cliente cliente = new Cliente();
	
	DetalleAvance detalle = new DetalleAvance();
	
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
	     cedulaCliente = cedula.getText();
		
		//List<Sesion> sesiones = sesionDao.ObtenerSesion(cedulaCliente);
		//listaSesiones.setModel(new ListModelList<Sesion>(sesiones));
		
	}
	public void onClick$ayuda(){
		Executions.sendRedirect("vista/ayudas/regAvance.html");
	}

	
	
	
	public void onCreate$servicios(){
		
		List<Servicio> listaServicios = dao.listaServicios();
		servicios.setModel(new ListModelList<Servicio>(listaServicios));
		
	
	}
	
	
	public void onSelect$servicios()
	{
		Servicio  selected = servicios.getSelectedItem().getValue();
		codigoServicio = selected.getCodigo();
		//Messagebox.show(selected.getCodigo());
		List<Avance> listaAvance = sesionDao.filtrarIndicadoresXservicio(selected.getCodigo());
		avances1.setModel(new ListModelList<Avance>(listaAvance));
		
	  
		
		
	}
	
	
	public void onSelect$avances1()
	{
		
		
		Avance select = avances1.getSelectedItem().getValue();
		//Messagebox.show(cedulaCliente);
		//Messagebox.show(select.getCodigo());
		//Messagebox.show(codigoServicio);
		List<DetalleAvance> detalles = sesionDao.filtrarAvancesXCliente(cedulaCliente,select.getCodigo(),codigoServicio );
		listaAvances.setModel(new ListModelList<DetalleAvance>(detalles));
	}
	

	
	
	
	
	
	
	
	
	
public void onCreate$Indicadores(){
		
		//List<Avance> listaIndicadores = sesionDao.filtrarIndicadoresXservicio(codigoServicio);
		//indicadores.setModel(new ListModelList<Servicio>(listaServicios));
	    //servicios.getSelectedItem().getc
	
	}
	
}
