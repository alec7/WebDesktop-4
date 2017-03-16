package controlador;


import modelo.Acuerdo;
import modelo.Cliente;
import modelo.Maestrico;
import modelo.Organizacion;
import modelo.Usuario;
import modeloDAO.AcuerdoDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.EmailHTMLDao;
import modeloDAO.OrganizacionDAO;
import modeloDAO.RolDAO;
import modeloDAO.UsuarioDAO;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Datebox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

public class ControladorFichaCliente extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	Textbox cedula;
	@Wire
	Textbox correo;
	@Wire
	Textbox apellido;
	@Wire
	Textbox nombre;
	@Wire
	Textbox fechaNac;
	@Wire
	Textbox referencia;
	@Wire
	Textbox direccion;
	@Wire
	Combobox estado_civil;
	@Wire
	private Combobox ocupacion;
	@Wire
	Combobox listAcuerdo;
	@Wire
	Label label_empresa;
	@Wire
	Datebox fecha;
	
	
	
	ClienteDAO cdao = new ClienteDAO();
	AcuerdoDAO adao = new AcuerdoDAO();
	UsuarioDAO udao = new UsuarioDAO();
	RolDAO rdao = new RolDAO();
	Cliente c;
	String acuerdo;
	String ocup;
	String estado_ci;
	OrganizacionDAO odao = new OrganizacionDAO();
	ListModelList<Maestrico> ocupacionModel;
	//ListModelList<Maestrico> ocupaciones ;
	ListModelList<Acuerdo> acuerdoModel;
	ListModelList<Acuerdo> acuerdos;
	List<Maestrico> ocupaciones = cdao.ocupacion();
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		cargarOcupacion();
		fechaNac.setVisible(false);
		
		//Messagebox.show(String.valueOf(ocupacion.getItemCount()));
		//ocupacion.setSelectedIndex(posicion);
	}
	@Listen("onClick = #ayuda")
	public void ayuda(){
		Executions.sendRedirect("vista/ayudas/fichaCliente.html");
	}
	
		@Listen("onCreate = #listAcuerdo")
	public void ficha()
	{
		listAcuerdo.setVisible(false);
		label_empresa.setVisible(false);
	}
	@Listen("onClick = #buscarCliente")
	public void buscar()
	{
		
		c = cdao.buscarCliente(cedula.getText());
		if(c!=null){
			nombre.setText(c.getNombre());
			apellido.setText(c.getApellido());
			correo.setText(c.getCorreo());
			//cargarOcupacion();
			referencia.setText(cdao.buscarReferenciaString(c.getCodigo_referecnia()));
			cargarEmpresa();
			validarCliente();
		}
		else{
			Messagebox.show("Cliente no Registrado ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	
	public void validarCliente(){
		if(c.getCodigo_ocupacion()!=null){
			String codigo = c.getCodigo_ocupacion();
			
			
			int posicion = 0;
			for (int i = 0; i < ocupaciones.size(); i++) {
				if (ocupaciones.get(i).getCodigo().equals(codigo)) {
					posicion = i;
				}
			}
			
			ocupacion.setSelectedIndex(posicion);
		}
		if(c.getEsta_civil()!=null){
			String estadoCivilCliente = c.getEsta_civil();
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
		if(c.getDireccion()!=null){
			direccion.setText(c.getDireccion());
		}
		if(c.getFecha_nacimiento()!=null){
			fechaNac.setDisabled(false);
			fecha.setVisible(false);
			fechaNac.setVisible(true);
			 
			fechaNac.setText(c.getFecha_nacimiento());
		}
	}
	
	@Listen("onSelect = combobox#ocupacion")
	public void opcionSeleccionada(){
		int index = ocupacion.getSelectedIndex();
		 ocup = ocupaciones.get(index).getDescripcion();
	}
	@Listen("onSelect = combobox#estado_civil")
	public void opcionEstadoCivilSeleccionada(){
		estado_ci = estado_civil.getSelectedItem().getLabel();
	}
	
	@Listen("onClick = #guardar")
	public void guargar(){
		String ced = cedula.getText();
		String emaile = correo.getText();
		String nom = nombre.getText();
		String ape = apellido.getText();
		//metodo para enviar correo una vez se guardan los datos del usuario
		EmailHTMLDao emailHtml = new EmailHTMLDao();
		emailHtml.enviarEmail("richard0227@gmail.com", "Registro de Usuario Spinetti Laser",  emaile, ced, nom, ape);
	
		//Messagebox.show(String.valueOf(estado_civil.getSelectedIndex()));
		if(estado_civil.getSelectedItem().toString()!=null & ocup!=null & !direccion.getText().equals("")){
			Date fecha1 = fecha.getValue();
			SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
			String fechaNac = sdf.format(fecha1);
			acuerdo="";
			Organizacion o = odao.buscarRegistro();
			if(referencia.getText().equalsIgnoreCase("por alianza") || referencia.getText().equalsIgnoreCase("por convenio")){
				acuerdo = cdao.buscarCodigoEmpresa(referencia.getText());
			}
			//Messagebox.show(cdao.buscarCodigoEmpresa(listAcuerdo.getSelectedItem().getLabel()));
			System.out.println(cedula.getText());
			System.out.println(estado_ci);
			System.out.println( direccion.getText());
			System.out.println(cdao.buscarCodigoOcupacion(ocup));
			System.out.println(fechaNac);
			System.out.println( o.getRif());
			//System.out.println(cdao.buscarCodigoEmpresa(listAcuerdo.getSelectedItem().getLabel()));
			if(listAcuerdo.isVisible()){
				cdao.modificarCliente(cedula.getText(), estado_ci, direccion.getText(), cdao.buscarCodigoOcupacion(ocup), fechaNac, o.getRif(),cdao.buscarCodigoEmpresa(listAcuerdo.getSelectedItem().getLabel()));
				String codigoRol = rdao.buscarRol("Cliente");
				Usuario usuario = new Usuario(correo.getText(), cedula.getText(), codigoRol, "Activo");
				udao.registrarUsuario(usuario);
				//cdao.modificarCliente(cedula.getText(), estado_ci, direccion.getText(), cdao.buscarCodigoOcupacion(ocup), fechaNac, o.getRif(),cdao.buscarCodigoEmpresa(referencia.getText()));
				//cdao.modificarCliente(cedula.getText(), estado_ci,direccion.getText(),cdao.buscarCodigoOcupacion(ocup),fechaNac,o.getRif(),cdao.buscarCodigoEmpresa(referencia.getText()));
			}
			else{
				cdao.modificarCliente(cedula.getText(), estado_ci, direccion.getText(), cdao.buscarCodigoOcupacion(ocup), fechaNac, o.getRif(),"00003");
				String codigoRol = rdao.buscarRol("Cliente");
				Usuario usuario = new Usuario(correo.getText(), cedula.getText(), codigoRol, "Activo");
				udao.registrarUsuario(usuario);
			}
			
			Messagebox.show("Cliente Registrado Satisfactoriamente ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			Messagebox.show("Debe Llenar Todos los Campos ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	
	public void cargarOcupacion(){
		
		ocupacionModel = new ListModelList<Maestrico>(ocupaciones);
		
		ocupacion.setModel(ocupacionModel);
		
	}
	
	public void cargarEmpresa(){
		if(referencia.getText().equalsIgnoreCase("por alianza") || referencia.getText().equalsIgnoreCase("por convenio")){
			listAcuerdo.setVisible(true);
			label_empresa.setVisible(true);
			List<Acuerdo> descripcion = adao.listarAcuerdo();
			acuerdoModel = new ListModelList<Acuerdo>(descripcion);
			listAcuerdo.setModel(acuerdoModel);
		}
		
	}
	
}
