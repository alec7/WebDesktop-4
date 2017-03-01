package controlador;

import java.security.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.zkoss.bind.annotation.Command;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.event.CreateEvent;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Listen;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.util.Clients;
import org.zkoss.zkmax.zul.Chosenbox;
import org.zkoss.zul.Button;
import org.zkoss.zul.Div;
import org.zkoss.zul.Label;
import org.zkoss.zul.ListModel;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Messagebox;
import org.zkoss.zul.Tab;
import org.zkoss.zul.Tabpanel;
import org.zkoss.zul.Tabpanels;
import org.zkoss.zul.Tabs;
import org.zkoss.zul.Textbox;

import modelo.Bloque;
import modelo.Esteticista;
import modelo.Maestrico;
import modelo.Usuario;
import modeloDAO.BloqueDAO;
import modeloDAO.EsteticistaDAO;
import modeloDAO.MaestricoDAO;



public class ControladorHorarioEsteticista extends SelectorComposer<Component>{
	private static final long serialVersionUID = 1L;
	
	@Wire
	private Textbox cedula;
	@Wire
	private Textbox nombre;
	@Wire
	private Button buscar;
	@Wire
	private Div dias_laborables;
	@Wire
	private Tabs tabs;
	@Wire
	private Tabpanels tabpanels;
	@Wire
	private Chosenbox chosen;
	
	
	BloqueDAO bloqueDao = new BloqueDAO(); 
	EsteticistaDAO estDao = new EsteticistaDAO();
	List<Bloque> descripcion = bloqueDao.listarBloques();
	List<Maestrico> diasLab = new ArrayList<Maestrico>();
	ListModelList<Bloque> bloques;
	Esteticista est;
	MaestricoDAO mdao = new MaestricoDAO();
	List l = new ArrayList();
	String[] array = new String[]{};

	String horaInicio = new String();
	String horaFin = new String();
	

	
	private ListModelList _model;
	private Set _selectedObjects;
	public ListModel getModel () {
		if (_model == null) {		
			for(int i=0; i<descripcion.size();i++){
				horaInicio = new SimpleDateFormat("HH:mm a").format(descripcion.get(i).getHora_inicio());
				horaFin = new SimpleDateFormat("HH:mm a").format(descripcion.get(i).getHora_fin());
				l.add(horaInicio+"-"+horaFin);
				//l.add(descripcion.get(i).getDescripcion());
			}
			_model = new ListModelList(l);
		}
		return _model;
	}
	public void setSelectedObjects (Set selectedObjects) {
		_selectedObjects = selectedObjects;
	}
	public Set getSelectedObjects () {
		if (_selectedObjects == null) {
			_selectedObjects = new HashSet();
		}
		return _selectedObjects;
	}
	@Command
	public void showSelection () {
		Clients.alert(getSelectedObjects().toString());
		 Set<String> set = new HashSet<String>();
		 set= _selectedObjects;
		 array = set.toArray(new String[0]);
		System.out.println(set);
		String cadena = "12|juan|13|jose|"; 

		String[] separada = cadena.split("|"); 
		for(int i=0; i<separada.length;i++){
			Messagebox.show(separada[i]);
			
		}
		
		}
	
	@Listen("onClick = #buscar")
	public void buscarEsteticista(){
		est= estDao.buscarEsteticista(cedula.getValue());
		if(est==null){
			Messagebox.show("Esteticista no registrado", "Información", Messagebox.OK, Messagebox.INFORMATION);
		}
		else{
			nombre.setText(est.getNombre()+" "+est.getApellido());
		}
		
	}
	
	@Listen("onCreate = #dias_laborables")
	public void usuarios(CreateEvent event)
    {
		diasLab = mdao.listarMaestrico("tb_dia_laborable");
		for(int i=0; i<diasLab.size();i++){
			Tab tab = new Tab();
			tab.setId(diasLab.get(i).getCodigo());
			tab.setLabel(diasLab.get(i).getDescripcion());
			tab.setParent(tabs);
			//tabpanel.set
			
//			Label label = new Label();
//			label.setValue(diasLab.get(i).getDescripcion());
//			label.setParent(dias_laborables);
		}
		
    }
	
}
