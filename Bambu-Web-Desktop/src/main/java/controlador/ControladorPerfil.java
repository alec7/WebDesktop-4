package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Maestrico;
import modelo.Usuario;
import modeloDAO.ClienteDAO;
import modeloDAO.MaestricoDAO;

import org.zkoss.zk.ui.Session;
import org.zkoss.zk.ui.Sessions;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.GenericForwardComposer;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Window;

public class ControladorPerfil extends GenericForwardComposer<Window> {

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
	private Listbox estado_civil;
	@Wire
	private Listbox sexo;
	@Wire
	private Listbox ocupacion;
	@Wire
	private Textbox direccion;
	@Wire
	private Listbox ciudad;
	@Wire
	private Textbox referencia;
	@Wire
	private Textbox empresa;

	//falta fecha de nacimiento
	
	Session miSession = Sessions.getCurrent();

	Usuario usuario = new Usuario();
	Cliente cliente = new Cliente();
	ClienteDAO clienteDao = new ClienteDAO();
	MaestricoDAO dao = new MaestricoDAO();
	ArrayList<Cliente> clientes = new ArrayList<Cliente>();
	ListModelList<Maestrico> ocupacionCliente;
	List<Maestrico> ocupacion1 = dao.listarMaestrico("tb_ocupacion");

	public void onCreate$perfil() {

		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
		cliente = clienteDao.BuscarClienteXCorreo(usuario.getUsuario());
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		cedula.setText(cliente.getCedula());
		correo.setText(cliente.getCorreo());
		telefono.setText(cliente.getTelefeno());
		direccion.setText(cliente.getDireccion());
		referencia.setText(clienteDao.buscarReferenciaString(cliente
				.getCodigo_referecnia()));
		empresa.setText(clienteDao.buscarEmpresaString(cliente
				.getCodigo_acuerdo()));

		sexo.setSelectedIndex(obtenerSexoCliente(cliente.getSexo()));
		estado_civil.setSelectedIndex(obtenerEstadoCivilCliente(cliente
				.getEsta_civil()));
		ocupacion.setSelectedIndex(obtenerOcupacionCliente(cliente.getCodigo_ocupacion()));
	}

	public void onCreate$preferenciaCliente() {

		usuario = (Usuario) miSession.getAttribute("UsuarioCesion");
		cliente = clienteDao.BuscarClienteXCorreo(usuario.getUsuario());
		nombre.setText(cliente.getNombre());
		apellido.setText(cliente.getApellido());
		
	}

	public int obtenerSexoCliente(String sexoCliente) {
		int sexoC = 0;
		for (int i = 0; i < clientes.size(); i++) {
			if (sexoCliente.equals(cliente.getSexo()))
				sexoC = i;
		}

		return sexoC;
	}

	public int obtenerEstadoCivilCliente(String estadoCivilCliente) {
		int estadoC = 0;
		for (int i = 0; i < clientes.size(); i++) {
			if (estadoCivilCliente.equals(cliente.getEsta_civil()))
				estadoC = i;
		}

		return estadoC;
	}

	public int obtenerOcupacionCliente(String ocupacionCliente) {
		int ocupacionC = 0;
		for (int i = 0; i < ocupacion1.size(); i++) {
			if (ocupacionCliente.equals(ocupacion1.get(i).getCodigo()))
				ocupacionC = i;
		}
		Messagebox.show(String.valueOf(ocupacionC));
		return ocupacionC;
		
	}

	public void onCreate$ocupacion(CreateEvent event) {
		List<Maestrico> descripcion = clienteDao.ocupacion();
		ocupacionCliente = new ListModelList<Maestrico>(descripcion);
		ocupacion.setModel(ocupacionCliente);
		

	}

}
