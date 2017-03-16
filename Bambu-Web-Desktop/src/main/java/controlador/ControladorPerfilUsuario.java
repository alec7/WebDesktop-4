package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.ForwardEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Button;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Listitem;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Textbox;
import org.zkoss.zul.Messagebox.ClickEvent;

import modelo.Maestrico;
import modelo.PerfilUsuario;
import modelo.Usuario;
import modeloDAO.EmailHTMLDao;
import modeloDAO.EsteticistaDAO;
import modeloDAO.PerfilUsuarioDAO;
import modeloDAO.RolDAO;
import modeloDAO.UsuarioDAO;

public class ControladorPerfilUsuario extends SelectorComposer<Component>{
	
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Textbox direccion;
	@Wire
	private Textbox telefono;
	@Wire
	private Textbox correo;
	@Wire
	private Listbox sexo;
	@Wire
	private Listbox estado_civil;
	@Wire
	private Listbox rol;
	@Wire
	private Listbox usuarios;
	
	PerfilUsuarioDAO perfilDao = new PerfilUsuarioDAO();
	UsuarioDAO udao = new UsuarioDAO();
	RolDAO roldao = new RolDAO();
	EsteticistaDAO estdao = new EsteticistaDAO();
	ListModelList<Usuario> usuarioListModel;
	ListModelList<Maestrico> descripcionRoles;
	List<Usuario> listUsuario = new ArrayList<Usuario>();
	List<Usuario> listUsuarioTabla = new ArrayList<Usuario>();
	Usuario u;
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);

		//this.cargarTabala();
		//Messagebox.show(String.valueOf(ocupacion.getItemCount()));
		//ocupacion.setSelectedIndex(posicion);
	}
	
	@Listen("onCreate = #usuarios")
	public void usuarios(CreateEvent event)
    {
		this.cargarTabala();
    }
	
	@Listen("onCreate = #rol")
	public void roles(CreateEvent event)
    {	
		List<Maestrico> descripcion = roldao.descripciones();
		descripcionRoles = new ListModelList<Maestrico>(descripcion);
		rol.setModel(descripcionRoles);
    }
//	
	@Listen("onClick = #guardar")
	public void guargar(){
		String ced = cedula.getText();
		String emaile = correo.getText();
		String nom = nombre.getText();
		String ape = apellido.getText();
		//metodo para enviar correo una vez se guardan los datos del usuario
		EmailHTMLDao emailHtml = new EmailHTMLDao();
		emailHtml.enviarEmail("richard0227@gmail.com", "Registro de Usuario Spinetti Laser",  emaile, ced, nom, ape);
		PerfilUsuario pf = new PerfilUsuario(cedula.getText(), nombre.getText(), apellido.getText(), direccion.getText(), correo.getText(), sexo.getSelectedItem().getLabel(), estado_civil.getSelectedItem().getLabel(), telefono.getText(), "00001","Activo");
		String codigo = roldao.buscarRol(rol.getSelectedItem().getLabel());
		u = new Usuario(correo.getText(), cedula.getText(),codigo,"Activo");
		udao.registrarUsuario(u);
		if(rol.getSelectedItem().getLabel().equalsIgnoreCase("Esteticista")){
			//AGREGAR MENSAJE VALIDAR USUARIO YA REGISTRADO
			estdao.agregarPerfilEsteticista(pf);
			usuarioListModel.add(u);
			this.cancelar();
			usuarioListModel.add(u);
			usuarioListModel.addToSelection(u);
			
		}
		else{
			//AGREGAR MENSAJE VALIDAR USUARIO YA REGISTRADO
			perfilDao.agregarPerfilUsuario(pf);
			this.cancelar();
		}
		 Messagebox.show("Usuario Registrado Exitosamente", "Información", Messagebox.OK, Messagebox.INFORMATION);
		this.cargarTabala();

	}
	
	@Listen("onClick = #cancelar")
	public void cancelar(){
		cedula.setText("");
		nombre.setText("");
		apellido.setText("");
		direccion.setText("");
		telefono.setText("");
		correo.setText("");
		sexo.clearSelection();
		estado_civil.clearSelection();
		rol.clearSelection();
	}
	
	@Listen("onUsuarioDelete = #usuarios")
	public void onUsuarioDelete$usuarios(ForwardEvent evt){
		Button btn = (Button)evt.getOrigin().getTarget();
		Listitem litem = (Listitem)btn.getParent().getParent();
		 u = (Usuario)litem.getValue();
		EventListener<ClickEvent> clickListener = new EventListener<Messagebox.ClickEvent>() {
	        public void onEvent(ClickEvent event) throws Exception {
	            if(Messagebox.Button.YES.equals(event.getButton())) {
	            	udao.modificarStatus(u.getUsuario());
	            	cargarTabala();
					   
	            }
	        }
	    };
	    Messagebox.show("¿Seguro de eliminar este Usuario?", "Mensaje de confirmación", new Messagebox.Button[]{
	            Messagebox.Button.YES, Messagebox.Button.NO },Messagebox.QUESTION,clickListener);
	}
	
	
	public void nombreRoles(){
		 listUsuario = udao.listarUsuario();
		String codigoRol = roldao.buscarRol("Cliente");
		for(int i=0;i<listUsuario.size();i++){
			if(!listUsuario.get(i).getRol().equalsIgnoreCase(codigoRol)){
				listUsuarioTabla.add(listUsuario.get(i));
			}
		}
		for(int i=0;i<listUsuarioTabla.size();i++){
			String descripcion;
			descripcion= roldao.buscarDescpRol(listUsuarioTabla.get(i).getRol());
			listUsuarioTabla.get(i).setRol(descripcion);
			
		}
		
		
		
	}
	
	public void cargarTabala(){
		nombreRoles();
		usuarioListModel = new ListModelList<Usuario>(listUsuarioTabla);
		usuarios.setModel(usuarioListModel);
	}

}
