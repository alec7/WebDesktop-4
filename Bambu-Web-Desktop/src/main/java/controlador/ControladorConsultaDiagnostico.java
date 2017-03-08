package controlador;

import java.util.ArrayList;
import java.util.List;

import modelo.Cliente;
import modelo.Cod_Des;
import modelo.HabitoCliente;
import modelo.Incidencia;
import modelo.Maestrico;
import modeloDAO.AntecedenteDAO;
import modeloDAO.ClienteDAO;
import modeloDAO.HabitoDAO;
import modeloDAO.IndicadorDAO;
import modeloDAO.NecesidadDAO;

import org.zkoss.zhtml.Messagebox;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.Event;
import org.zkoss.zk.ui.event.EventListener;
import org.zkoss.zk.ui.event.Events;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.Checkbox;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Textbox;

public class ControladorConsultaDiagnostico extends SelectorComposer<Component> {
	private static final long serialVersionUID = 1L;
	
	@Wire
	Tabpanel tabHabitos;
	@Wire
	Tabpanel tabAntecedentes;
	@Wire
	Div divIndicadores;
	@Wire
	Textbox cedula;
	@Wire
	Textbox apellido;
	@Wire
	Textbox nombre;
	@Wire
	Listbox necesidades;
	
	String nombreCliente;
	
	Maestrico maestrico = new Maestrico();
	ClienteDAO cdao = new ClienteDAO();
	NecesidadDAO nDao = new NecesidadDAO();
	HabitoCliente habCliente = new HabitoCliente();
	HabitoDAO habDao = new HabitoDAO();
	
	Cliente c;
	Cod_Des cd;
	ListModelList<Cod_Des> necesidadListModel;
	List<Cod_Des> listNecesidad = new ArrayList<Cod_Des>();
	ListModelList<Maestrico> descripcionNecesidad;
	
	
	
	@Listen("onClick = #buscar")
	public void buscar()
	{
		
		c = cdao.buscarCliente(cedula.getText());
		if(c!=null){
			nombre.setText(c.getNombre());
			apellido.setText(c.getApellido());
			
		
//				String codigoIncidencia = inDao.obtenerCodigo(tipoIncidencia.getSelectedItem().getLabel());
//				Messagebox.show(codigoIncidencia);
				listNecesidad = nDao.listarNecesidad(c.getCedula());
				nombreNecesidad();
				necesidadListModel = new ListModelList<Cod_Des>(listNecesidad);
				
				//necesidades.setModel(necesidadListModel);
				
			
		}
		else{
			Messagebox.show("Cliente no Registrado ", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		
	}
	
	
	
	
public void nombreNecesidad(){
		
		for(int i=0;i<listNecesidad.size();i++){
			String descripcion;
			descripcion= nDao.buscarDescpNecesidad(listNecesidad.get(i).getCodigo());
			listNecesidad.get(i).setCodigo(descripcion);
		}
		
	}
	
	
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@Listen("onCreate = #ficha")
	public void onCreate$ficha()
	{
		
		
		
		AntecedenteDAO aDao = new AntecedenteDAO();
		ArrayList<Maestrico> habitos = new ArrayList<Maestrico>();
	    ArrayList<Maestrico> antecedentes = new ArrayList<Maestrico>();
	    habitos = habDao.listaHabito();
	    antecedentes = aDao.listaAntecedente();
	    
	    
	   // Messagebox.show(String.valueOf(habitos.size()));
	    //Cargar Habitos
	    for(int i=0; i<habitos.size(); i++)
	    {
	    	maestrico = habitos.get(i);
	        Div div = new Div();
	        div.setClass("form-group");
	        div.setParent(tabHabitos);
	        Label label = new Label();
	        label.setClass("col-lg-2 control-label");
	        label.setValue(maestrico.getDescripcion());
	        
	        label.setParent(div);
	        
	        Checkbox check = new Checkbox();
	        check.setParent(div);
	        check.setId(maestrico.getCodigo());
	        check.addEventListener(Events.ON_CHECK, new EventListener() {

				@Override
				public void onEvent(Event event) throws Exception {
					
					habCliente = new HabitoCliente(maestrico.getCodigo(),cedula.getText(),habDao.TotalRegistros(),true);
					habDao.agregarHabitoACliente(habCliente);
				}						 
			 });
	        
	        
	        
	        
	    }
		
	    // Cargar Antecedentes
	    for(int j=0; j<antecedentes.size(); j++)
	    {
	    	maestrico = antecedentes.get(j);
	        Div div = new Div();
	        div.setClass("form-group");
	        div.setParent(tabAntecedentes);
	        Label label = new Label();
	        label.setClass("col-lg-2 control-label");
	        label.setValue(maestrico.getDescripcion());
	        label.setParent(div);
	        Checkbox check = new Checkbox();
	        check.setParent(div);
	        
	        
	    }
	    
	 	}
	
	@SuppressWarnings({ "unchecked", "rawtypes" })
	@Listen("onCreate = #diagnostico")
	public void onCreate$diagnostico(){
		
		IndicadorDAO iDao = new IndicadorDAO();
		ArrayList<Maestrico> indicadores = new ArrayList<Maestrico>();
		indicadores = iDao.listaIndicador();
		
		// Cargar Indicadores de Diagnostico
	    for(int k=0; k<indicadores.size(); k++)
	    {
	    	maestrico = indicadores.get(k);
	        Div div = new Div();
	        div.setClass("form-group");
	        div.setParent(divIndicadores);
	        Label label = new Label();
	        label.setClass("col-lg-2 control-label");
	        label.setValue(maestrico.getDescripcion());
	        label.setParent(div);
	        Checkbox check = new Checkbox();
	        check.setParent(div);
	        
	        check.addEventListener(Events.ON_CLICK, new EventListener() {

				@Override
				public void onEvent(Event event) throws Exception {
					
			    	
				}						 
			 });
	        
	    }
	}
	
	
	

}
