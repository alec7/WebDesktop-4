package controlador;

import java.util.ArrayList;
import java.util.List;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Combobox;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Textbox;

import modelo.Ciudad;
import modelo.Cliente;
import modelo.Maestrico;
import modelo.Organizacion;
import modeloDAO.CiudadDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.MaestricoDAO;
import modeloDAO.OrganizacionDAO;
import modeloDAO.RespuestaFormularioDAO;
import modeloDAO.UsuarioDAO;

public class ControladorClientePotencial extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;

	@Wire
	private Textbox cedula;
	@Wire
	private Textbox correo;
	@Wire
	private Textbox nombre;
	@Wire
	private Textbox apellido;
	@Wire
	private Textbox telefono;
	@Wire
	private Combobox sexo;
	@Wire
	private Combobox estado;
	@Wire
	private Combobox ciudad;
	@Wire
	private Combobox referido;
	@Wire
	private Label pregunta1;
	@Wire
	private Label pregunta2;
	@Wire
	private Label pregunta3;
	@Wire
	private Label pregunta4;
	@Wire
	private Combobox respuesta1;
	@Wire
	private Combobox respuesta2;
	@Wire
	private Combobox respuesta3;
	@Wire
	private Combobox respuesta4;
	
	static CiudadDAO ciudadDao = new CiudadDAO();
	static MaestricoDAO mdao = new MaestricoDAO();
	OrganizacionDAO oDao = new OrganizacionDAO();
	ClienteDAO cDao = new ClienteDAO();
	RespuestaFormularioDAO resforDao= new RespuestaFormularioDAO();
	ListModelList<Ciudad> ciudadModel;
	ListModelList<Maestrico> estadoModel;
	ListModelList<Maestrico> referenciaModel;
	//List<Ciudad> ciudades = ciudadDao.ciudades();
	static List<Maestrico> estados = mdao.listarMaestrico("tb_estado");
	List<Maestrico> referencias = mdao.listarMaestrico("tb_referencia");
	
	
	
	@Override
	public void doAfterCompose(Component comp) throws Exception {
		// TODO Auto-generated method stub
		super.doAfterCompose(comp);
		cargarEstados();
		
		//Messagebox.show(String.valueOf(ocupacion.getItemCount()));
		//ocupacion.setSelectedIndex(posicion);
	}
	
	public void cargarEstados(){
		
		estadoModel = new ListModelList<Maestrico>(estados);
			
		estado.setModel(estadoModel);
		
	}
public void cargarReferencias(){
		
	referenciaModel = new ListModelList<Maestrico>(referencias);
			
	referido.setModel(referenciaModel);
		
	}
	@Listen("onCreate = #referido")
	public void referencias(){
		cargarReferencias();
	}
//	@Listen("onCreate = #ciudad")
//	public void ciudades(){
//		cargarCiudad();
//	}
//	@Listen("onCreate = #estado")
//	public void estados(){
//		cargarEstados();
//	}
	@Listen("onSelect= #estado")
	public void cargarCiudades(){
		List<Ciudad> ciudadesPorEstado = new ArrayList<Ciudad>();
		List<Ciudad> ciudades = ciudadDao.ciudades();
		if(ciudadesPorEstado.size()>0){
			for(int i=0; i<ciudadesPorEstado.size();i++){
				ciudadesPorEstado.remove(i);
			}
		}
//		System.out.println(ciudadesPorEstado.size());
		int posicionEstado = estado.getSelectedIndex();
		String codigoEstado = estados.get(posicionEstado).getCodigo();
		for(int i=0;i<ciudades.size();i++){
			if(ciudades.get(i).getEstado().equals(codigoEstado)){
				ciudadesPorEstado.add(ciudades.get(i));
			}
		}
		
		ciudadModel = new ListModelList<Ciudad>(ciudadesPorEstado);
		
		ciudad.setModel(ciudadModel);
		
	}
	
	
	@Listen("onClick = #guardar")
	public void guargar(){
		if(!cedula.getText().equals("") && !nombre.getText().equals("")&& !apellido.getText().equals("")&& !telefono.equals("")&& !correo.equals("") && sexo.getSelectedIndex()!=-1&&estado.getSelectedIndex()!=-1 && ciudad.getSelectedIndex()!=1&&referido.getSelectedIndex()!=1){
			if(!respuesta1.getSelectedItem().getLabel().equals("")&&!respuesta2.getSelectedItem().getLabel().equals("")&&!respuesta3.getSelectedItem().getLabel().equals("")&&!respuesta4.getSelectedItem().getLabel().equals("")){
				String codigoCiudad = ciudadDao.codigoCiudad(ciudad.getSelectedItem().getLabel());
				String codigo_referencia= mdao.buscarCodigoReferencia(referido.getSelectedItem().getLabel());
				Organizacion o = oDao.buscarRegistro();
				Cliente c = new Cliente(cedula.getText(), nombre.getText(), apellido.getText(), sexo.getSelectedItem().getLabel(), correo.getText(), codigoCiudad,"00001", codigo_referencia, o.getRif(), "Activo",telefono.getText()); 
				cDao.agregarClientePotencial(c);
				String codigo = resforDao.TotalRegistros();
				resforDao.registraRespuestaFormulario(codigo,respuesta1.getText() ,respuesta1.getSelectedItem().getLabel() , cedula.getText());
				String codigo1 = resforDao.TotalRegistros();
				
				resforDao.registraRespuestaFormulario(codigo1,respuesta2.getText() ,respuesta2.getSelectedItem().getLabel() , cedula.getText());
				String codigo2 = resforDao.TotalRegistros();
				
				resforDao.registraRespuestaFormulario(codigo2,respuesta3.getText() ,respuesta3.getSelectedItem().getLabel() , cedula.getText());
				String codigo3 = resforDao.TotalRegistros();
				
				resforDao.registraRespuestaFormulario(codigo3,respuesta4.getText() ,respuesta4.getSelectedItem().getLabel() , cedula.getText());
				Messagebox.show("Datos Guardados Satisfactoriamente ", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
			else{
				Messagebox.show("Debe Llenar Todos los Campos ", "Información", Messagebox.OK, Messagebox.INFORMATION);
			}
			
		}else{
			Messagebox.show("Debe Llenar Todos los Campos ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
}
