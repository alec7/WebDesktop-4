package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

import modelo.Cita;
import modelo.Cliente;
import modelo.DetalleSolicitud;
import modelo.Maestrico;
import modelo.Servicio;
import modelo.Solicitud;
import modelo.Usuario;
import modeloDAO.CitaDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.ServicioDAO;
import modeloDAO.SolicitudDAO;

public class ControladorConfirmarCita extends SelectorComposer<Component>{

	/**
	 * 
	 */
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Listbox servicios;
	
	ClienteDAO clienteDao = new ClienteDAO();
	SolicitudDAO solicitudDao = new SolicitudDAO();
	CitaDAO citaDao = new CitaDAO();
	Cliente cliente = new Cliente();
	Usuario usuario = new Usuario();
	DetalleSolicitud ds;
	Servicio servicio = new Servicio();
	List<Solicitud> solicitudCliente = new ArrayList<Solicitud>();
	List<Cita> citaCliente = new ArrayList<Cita>();
	ListModelList<Cita> citaModel;
	List<DetalleSolicitud> detalleSolicitudCliente = new ArrayList<DetalleSolicitud>();
	Session miSession = Sessions.getCurrent();
	Session miSession2 = Sessions.getCurrent();
	
	private static final long serialVersionUID = 1L;

	@Override
	public void doAfterCompose(Component comp) throws Exception {
		super.doAfterCompose(comp);
		
	
	}

	@Listen("onCreate = #confirmacion")
	public void onCreate$confirmacion()  
	{
//		if(miSession2.getAttribute("rol").toString().equals("00004")){
		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
		String correo = miSession.getAttribute("correo").toString();
		cliente = clienteDao.obtenerCliente(correo);
		cedula.setText(cliente.getCedula());
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		cedula.setDisabled(true);
		nombre.setDisabled(true);
		apellido.setDisabled(true);
		
		cargartabla();
		System.out.println(citaCliente.size());
		for(int i=0;i<citaCliente.size();i++){
			System.out.println(citaCliente.get(i).getFecha());
			System.out.println(citaCliente.get(i).getCodigo_bloque());
		}
//		}
//		else
//		{
//			cedula.setDisabled(false);
//			nombre.setDisabled(false);
//			apellido.setDisabled(false);
//		}
		
	}
//	@Listen("onClick = #BuscarCliente")
//	public void onClick$BuscarCliente()
//	{
//		
//		cliente = clienteDao.BuscarClienteXCedula(cedula.getText());
//		if(cliente!=null)
//		{
//		nombre.setText(cliente.getNombre());
//		apellido.setText(cliente.getApellido());
//		}
//		else{
//			Messagebox.show("Cliente no registrado", "Información", Messagebox.OK, Messagebox.INFORMATION);
//		}
//		
//		
//	}
	
	public void buscarServicios(){
		solicitudCliente = solicitudDao.listaSolicitudPorCliente(cedula.getText());
		for(int i=0;i<solicitudCliente.size();i++){
			detalleSolicitudCliente.add(solicitudDao.detalleServiciosPorCliente(solicitudCliente.get(i).getCodigo()));
		}
		for(int i=0;i<detalleSolicitudCliente.size();i++){
			citaCliente.add(citaDao.citaPorCliente(detalleSolicitudCliente.get(i).getCodigo()));

		}
		
	}
	public void cargartabla(){
		buscarServicios();
		citaModel = new ListModelList<Cita>(citaCliente);
		
		servicios.setModel(citaModel);
	}

}
