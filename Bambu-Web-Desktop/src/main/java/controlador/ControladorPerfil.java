package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Ciudad;
import modelo.Cliente;
import modelo.Maestrico;
import modelo.PerfilUsuario;
import modelo.Usuario;
import modeloDAO.CiudadDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.MaestricoDAO;
import modeloDAO.PerfilUsuarioDAO;
import modeloDAO.RolDAO;
import modeloDAO.UsuarioDAO;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ControladorPerfil extends SelectorComposer<Component> {

	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox correo;
	@Wire
	private Textbox telefono;
	@Wire
	private Textbox usuario_texto;
	@Wire
	private Textbox rol;
	@Wire
	private Textbox contrasenna;
	@Wire
	private Textbox contrasenna_nueva;
	@Wire
	private Textbox confirmar_contrasenna;
	@Wire
	Combobox estado_civil;
	@Wire
	private Combobox sexo;
	@Wire
	private Combobox ocupacion;
	@Wire
	private Textbox direccion;
	@Wire
	private Combobox ciudad;
	@Wire
	private Textbox referencia;
	@Wire
	private Textbox empresa;
	@Wire
	private Label labelOcupacion;
	@Wire
	private Label labelCiudad;
	@Wire
	private Label labelEmpresa;

	//falta fecha de nacimiento
	
	Session miSession = Sessions.getCurrent();

	Usuario usuario = new Usuario();
	Cliente cliente = new Cliente();
	PerfilUsuario pf ;
	ClienteDAO clienteDao = new ClienteDAO();
	RolDAO rolDao = new RolDAO();
	UsuarioDAO usuarioDao = new UsuarioDAO();
	CiudadDAO ciuDao= new CiudadDAO();
	MaestricoDAO dao = new MaestricoDAO();
	PerfilUsuarioDAO pfDao = new PerfilUsuarioDAO(); 
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ListModelList<Maestrico> ocupacionCliente;
	ListModelList<Maestrico> ocupacionModel;
	ListModelList<Ciudad> ciudadModel;
	List<Maestrico> ocupaciones = clienteDao.ocupacion();
	List<Ciudad> cuidades = ciuDao.ciudades();
	//int posicionOcupacion = 0;
	//List<Maestrico> ocupacion1 = dao.listarMaestrico("tb_ocupacion");
	
	
	
//	public void cargarComboOcupacion(){
//		List<Maestrico> descripcion = clienteDao.ocupacion();
//		
//		String codigoOcup = cliente.getCodigo_ocupacion();
//		
//		ocupacionModel = new ListModelList<Maestrico>(ocupaciones);
//		
//		ocupacion.setModel(ocupacionModel);
//		
//		for (int i = 0; i < descripcion.size(); i++) {
//			if (descripcion.get(i).getCodigo().equals(codigoOcup)) {
//				posicionOcupacion = i;
//			}
//		}
//		ocupacion.setSelectedIndex(posicionOcupacion);
//	}
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/miPerfil.html");
	}
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		cargarOcupacion();
		cargarCiudad();
		//Messagebox.show(String.valueOf(ocupacion.getItemCount()));
		//ocupacion.setSelectedIndex(posicion);
	}

	@Listen("onCreate = #perfil")
	public void onCreate$perfil() {
		
		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
		if(usuario.getRol().equalsIgnoreCase("00001")){
			cliente = clienteDao.BuscarClienteXCorreo(usuario.getUsuario());
			nombre.setText(cliente.getNombre());
			apellido.setText(cliente.getApellido());
			cedula.setText(cliente.getCedula());
			cedula.setDisabled(true);
			correo.setText(cliente.getCorreo());
			correo.setDisabled(true);
			telefono.setText(cliente.getTelefeno());
			direccion.setText(cliente.getDireccion());
			referencia.setText(clienteDao.buscarReferenciaString(cliente
					.getCodigo_referecnia()));
			referencia.setDisabled(true);
			empresa.setText(clienteDao.buscarEmpresaString(cliente
					.getCodigo_acuerdo()));
			empresa.setDisabled(true);
			ciudad.setDisabled(true);
			sexo.setDisabled(true);

			sexo.setSelectedIndex(obtenerSexoCliente(cliente.getSexo()));
//			estado_civil.setSelectedIndex(obtenerEstadoCivilCliente(cliente
//					.getEsta_civil()));
			
			String codigo = cliente.getCodigo_ocupacion();
			
			
			int posicion = 0;
			
			for (int i = 0; i < ocupaciones.size(); i++) {
				if (ocupaciones.get(i).getCodigo().equals(codigo)) {
					posicion = i;
				}
			}
			ocupacion.setSelectedIndex(posicion);
			String codigoCiudad = cliente.getCuidad();
			int posicionCiudad = 0;
			
			for (int i = 0; i < cuidades.size(); i++) {
				if (cuidades.get(i).getCodigo().equals(codigoCiudad)) {
					posicionCiudad = i;
				}
			}
			ciudad.setSelectedIndex(posicionCiudad);
			
			String estadoCivilCliente = cliente.getEsta_civil();
			int estadoC = 0;
			if (estadoCivilCliente.equalsIgnoreCase("Soltero")){
				estadoC = 0;
			}
			else{
				if(estadoCivilCliente.equalsIgnoreCase("Casado")){
					estadoC = 1;
				}
				else{
					if(estadoCivilCliente.equalsIgnoreCase("Divorsiado")){
						estadoC = 2;
					}
					else{
						if(estadoCivilCliente.equalsIgnoreCase("Viudo")){
							estadoC = 3;
						}
					}
				}
			}
			estado_civil.setSelectedIndex(estadoC);
		}
		else{
			
			pf= pfDao.buscarPerfilUsuario(usuario.getUsuario());
			nombre.setText(pf.getNombre());
			apellido.setText(pf.getApellido());
			cedula.setText(pf.getCedula());
			cedula.setDisabled(true);
			correo.setText(pf.getCorreo());
			correo.setDisabled(true);
			telefono.setText(pf.getTelefono());
			direccion.setText(pf.getDireccion());
			referencia.setVisible(true);
			referencia.setText("Spinetti Laser");
			sexo.setDisabled(true);

			sexo.setSelectedIndex(obtenerSexoCliente(pf.getSexo()));
//			estado_civil.setSelectedIndex(obtenerEstadoCivilCliente(cliente
//					.getEsta_civil()));
			
			//String codigo = pf.getCodigo_ocupacion();
			
			ocupacion.setVisible(false);
			labelOcupacion.setVisible(false);
			labelCiudad.setVisible(false);
			labelEmpresa.setVisible(false);
			empresa.setVisible(false);
			
//			int posicion = 0;
//			
//			for (int i = 0; i < ocupaciones.size(); i++) {
//				if (ocupaciones.get(i).getCodigo().equals(codigo)) {
//					posicion = i;
//				}
//			}
//			ocupacion.setSelectedIndex(posicion);
			ciudad.setVisible(false);
//			String codigoCiudad = cliente.getCuidad();
//			int posicionCiudad = 0;
//			
//			for (int i = 0; i < cuidades.size(); i++) {
//				if (cuidades.get(i).getCodigo().equals(codigoCiudad)) {
//					posicionCiudad = i;
//				}
//			}
//			ciudad.setSelectedIndex(posicionCiudad);
			
			String estadoCivilCliente = pf.getEstado_civil();
			int estadoC = 0;
			if (estadoCivilCliente.equalsIgnoreCase("Soltero")){
				estadoC = 0;
			}
			else{
				if(estadoCivilCliente.equalsIgnoreCase("Casado")){
					estadoC = 1;
				}
				else{
					if(estadoCivilCliente.equalsIgnoreCase("Divorsiado")){
						estadoC = 2;
					}
					else{
						if(estadoCivilCliente.equalsIgnoreCase("Viudo")){
							estadoC = 3;
						}
					}
				}
			}
			estado_civil.setSelectedIndex(estadoC);
		}
		
	}
	
	

	public void cargarOcupacion(){
		
			
			ocupacionModel = new ListModelList<Maestrico>(ocupaciones);
			
			ocupacion.setModel(ocupacionModel);
			
			
			
		}
//	@Listen("onCreate = #preferenciaCliente")
//	public void onCreate$preferenciaCliente() {
//
//		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
//		cliente = clienteDao.BuscarClienteXCorreo(usuario.getUsuario());
//		nombre.setText(cliente.getNombre());
//		apellido.setText(cliente.getApellido());
////		ocupacion.setSelectedIndex(obtenerOcupacionCliente(cliente.getCodigo_ocupacion()));
//		
//	}

	public int obtenerSexoCliente(String sexoCliente) {
		
		int numero =0;
			if (sexoCliente.equalsIgnoreCase("Femenino")){
				numero = 0;
			}
			else{
				numero =1;
			}
				
		return numero;
		
	}

//	public int obtenerEstadoCivilCliente(String estadoCivilCliente) {
//		int estadoC = 0;
//			if (estadoCivilCliente.equalsIgnoreCase("Soltero")){
//				estadoC = 0;
//			}
//			else{
//				if(estadoCivilCliente.equalsIgnoreCase("Casado")){
//					estadoC = 1;
//				}
//				else{
//					if(estadoCivilCliente.equalsIgnoreCase("Divorsiado")){
//						estadoC = 2;
//					}
//					else{
//						if(estadoCivilCliente.equalsIgnoreCase("Viudo")){
//							estadoC = 3;
//						}
//					}
//				}
//			}
//				
//		return estadoC;
//	}

	public void cargarCiudad(){
		

		ciudadModel = new ListModelList<Ciudad>(cuidades);
		
		ciudad.setModel(ciudadModel);
		
	}
	@Listen("onCreate = #tabUsuario")
public void onCreate$tabUsuario() {
		
		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
		usuario_texto.setText(usuario.getUsuario());
		rol.setText(rolDao.buscarDescpRol(usuario.getRol()));
		usuario_texto.setDisabled(true);
		rol.setDisabled(true);
		
		}
	
	@Listen("onClick = #guardar")
public void onClick$guardar(){
		
		Boolean contra = contrasenna.getText().equals(usuarioDao.buscarContrasenna(usuario_texto.getText()));
		if(contra== false)
		{
			Messagebox.show("Verifique la contraseña actual ", "Información", Messagebox.OK, Messagebox.INFORMATION);
				
		}
		else{
		if(contra && contrasenna_nueva.getText().equals(confirmar_contrasenna.getText())){
			
		usuarioDao.cambiarContrasenna(usuario.getUsuario(), contrasenna_nueva.getText());
		Messagebox.show("Contraseña Modificada ", "Información", Messagebox.OK, Messagebox.INFORMATION);
			
			limpiar();}
		else
			{
			Messagebox.show("No coincide", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
		
		}
	}
	@Listen("onClick = #editarDatos")
	public void onClick$guardarDatos(){
		if(direccion.getText()!="" && telefono.getText()!=""){
			String codigoOcupacion = clienteDao.buscarCodigoOcupacion(ocupacion.getSelectedItem().getLabel());  
			clienteDao.modificarClientePerfil(estado_civil.getSelectedItem().getLabel(), direccion.getText(), codigoOcupacion, cedula.getText(),telefono.getText());
			Messagebox.show("Datos Modificados Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
	}
	@Listen("onClick = #cancelar")	
public void onClick$cancelar(){
	limpiar();
}

public void limpiar(){
	contrasenna.setText("");
	contrasenna_nueva.setText("");
	confirmar_contrasenna.setText("");
}
}
